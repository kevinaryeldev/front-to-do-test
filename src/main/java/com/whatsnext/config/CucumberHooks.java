package com.whatsnext.config;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class CucumberHooks {

    @Before(order = 0)
    public void setup(Scenario scenario) {
        System.out.println("Iniciando: " + scenario.getName());
        DriverManager.initDriver();
    }

    @After(order = 1)
    public void attachScreenshot(Scenario scenario) {
            if (scenario.isFailed()) {
                try {
                    if (DriverManager.getDriver() instanceof TakesScreenshot ts) {
                        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                        scenario.attach(screenshot, "image/png", "Falha - " + scenario.getName());
                    }
                } catch (Exception e) {
                    System.err.println("Falha ao capturar screenshot: " + e.getMessage());
                }
            }
    }

    @After(order = 0)
    public void teardown() {
        DriverManager.quitDriver();
    }
}