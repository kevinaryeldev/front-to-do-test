package com.whatsnext.config;

import com.whatsnext.util.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class DriverManager {

    private static WebDriver driver;
    private static WebDriverWait wait;

    private DriverManager() {}

    public static void initDriver() {
        if (driver != null) {
            quitDriver();
        }
        ConfigReader config = ConfigReader.getInstance();
        int timeout = Integer.parseInt(config.get("timeout.seconds", "10"));
        driver = DriverFactory.createDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeout));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(timeout));
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver não inicializado."
            );
        }
        return driver;
    }


    public static WebDriverWait getWait() {
        if (wait == null) {
            throw new IllegalStateException(
                    "WebDriverWait não inicializado."
            );
        }
        return wait;
    }
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait = null;
        }
    }

    public static void navigateTo(String path) {
        String baseUrl = ConfigReader.getInstance().get("base.url");
        getDriver().get(baseUrl + path);
    }

    public static void reload(){
        getDriver().navigate().refresh();
    }


    public static void clearLocalStorage() {
        ((JavascriptExecutor) getDriver())
                .executeScript("window.localStorage.clear();");
    }
}