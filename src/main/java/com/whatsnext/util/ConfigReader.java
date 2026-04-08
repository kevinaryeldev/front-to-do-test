package com.whatsnext.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ConfigReader {

    private static ConfigReader instance;
    private final Properties props = new Properties();
    private final String loadedFile;
    private static final Pattern ENV_PATTERN = Pattern.compile("\\$\\{([^}]+)}");

    private ConfigReader() {
        String env = System.getenv("ENVIRONMENT");
        boolean isCI = "ci".equalsIgnoreCase(env) || "true".equalsIgnoreCase(System.getenv("CI"));
        this.loadedFile = isCI ? "config.properties" : "config-dev.properties";
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(loadedFile)) {
            if (is == null) {
                throw new RuntimeException("Arquivo não encontrado no classpath: " + loadedFile);
            }
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar " + loadedFile, e);
        }
        resolveAllPlaceholders();
        System.out.println("[ConfigReader] Ambiente: " + (loadedFile.contains("dev") ? "DEV" : "CI"));
    }

    public static ConfigReader getInstance() {
        if (instance == null) {
            synchronized (ConfigReader.class) {
                if (instance == null) {
                    instance = new ConfigReader();
                }
            }
        }
        return instance;
    }

    public String get(String key) {
        String value = props.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Chave não encontrada no " + loadedFile + ": " + key);
        }
        return value;
    }

    /**
     * Retorna o valor da chave ou o default informado.
     */
    public String get(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    // Percorre as propriedades e resolve os ${VAR}
    private void resolveAllPlaceholders() {
        for (String key : props.stringPropertyNames()) {
            String raw = props.getProperty(key);
            Matcher matcher = ENV_PATTERN.matcher(raw);
            if (!matcher.find()) continue;
            StringBuilder resolved = new StringBuilder();
            int lastEnd = 0;
            matcher.reset();
            while (matcher.find()) {
                resolved.append(raw, lastEnd, matcher.start());
                String envKey = matcher.group(1);
                String envValue = System.getenv(envKey);
                if (envValue == null) {
                    throw new RuntimeException(
                            "Variável de ambiente não definida: " + envKey
                                    + " (referenciada em " + loadedFile + ", chave: " + key + ")"
                    );
                }
                resolved.append(envValue);
                lastEnd = matcher.end();
            }
            resolved.append(raw, lastEnd, raw.length());
            props.setProperty(key, resolved.toString());
        }
    }
}