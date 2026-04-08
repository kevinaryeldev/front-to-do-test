package com.whatsnext.config;

import com.whatsnext.util.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public final class DriverFactory {

    private DriverFactory() {}

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase().trim();
        boolean headless = Boolean.parseBoolean(
                ConfigReader.getInstance().get("headless", "false")
        );
        return switch (browser) {
            case "firefox" -> createFirefox(headless);
            case "edge"    -> createEdge(headless);
            default        -> createChrome(headless);
        };
    }

    private static WebDriver createChrome(boolean headless) {
        ChromeOptions options = new ChromeOptions();

        if (headless) {
            options.addArguments("--headless=new");
        }
        options.addArguments(
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-gpu",
                "--window-size=1920,1080",
                "--disable-extensions",
                "--remote-allow-origins=*"
        );

        return new ChromeDriver(options);
    }

    private static WebDriver createFirefox(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();

        if (headless) {
            options.addArguments("-headless");
        }

        options.addArguments("--width=1920", "--height=1080");

        return new FirefoxDriver(options);
    }

    private static WebDriver createEdge(boolean headless) {
        EdgeOptions options = new EdgeOptions();

        if (headless) {
            options.addArguments("--headless=new");
        }

        options.addArguments(
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-gpu",
                "--window-size=1920,1080",
                "--disable-extensions",
                "--remote-allow-origins=*"
        );
        return new EdgeDriver(options);
    }
}
