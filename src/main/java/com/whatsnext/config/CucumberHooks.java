package com.whatsnext.config;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

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
                        String base64Screenshot = ts.getScreenshotAs(OutputType.BASE64);
                        ExtentCucumberAdapter.getCurrentStep()
                                .fail("Screenshot da falha",
                                        MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
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