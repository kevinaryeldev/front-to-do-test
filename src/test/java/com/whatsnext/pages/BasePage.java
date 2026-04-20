package com.whatsnext.pages;

import org.openqa.selenium.By;
import com.whatsnext.util.ByTestId;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.whatsnext.config.DriverManager;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


public abstract class BasePage {
    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    protected WebDriverWait getWait() {
        return DriverManager.getWait();
    }

    protected By byTestId(String testId) {
        return ByTestId.of(testId);
    }

    protected WebElement find(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement find(String testId) {
        return find(byTestId(testId));
    }

    protected WebElement findChild(WebElement parent, By locator) {
        getWait().until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(parent, locator));
        return parent.findElement(locator);
    }

    protected WebElement findChild(WebElement parent, String testId) {
        return findChild(parent, byTestId(testId));
    }

    protected void click(By locator) {
        getWait().until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void click(String testId) {
        click(byTestId(testId));
    }

    protected void type(By locator, String text) {
        WebElement element = find(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void type(String testId, String text) {
        type(byTestId(testId), text);
    }
    protected void selectByValue(By locator, String value) {
        new Select(find(locator)).selectByValue(value);
    }

    protected void selectByValue(String testId, String value) {
        selectByValue(byTestId(testId), value);
    }

    protected String getText(By locator) {
        return find(locator).getText();
    }

    protected String getText(String testId) {
        return getText(byTestId(testId));
    }

    protected String getAttribute(By locator, String attribute) {
        return find(locator).getAttribute(attribute);
    }

    protected String getAttribute(String testId, String attribute) {
        return getAttribute(byTestId(testId), attribute);
    }

    protected boolean isVisible(By locator) {
        try {
            return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isVisible(String testId) {
        return isVisible(byTestId(testId));
    }

    protected boolean isPresent(By locator) {
        return !getDriver().findElements(locator).isEmpty();
    }

    protected boolean isPresent(String testId) {
        return isPresent(byTestId(testId));
    }

    protected int countElements(By locator) {
        return getDriver().findElements(locator).size();
    }

    protected int countElements(String testId) {
        return countElements(byTestId(testId));
    }

    protected void waitForUrlContains(String path) {
        getWait().until(ExpectedConditions.urlContains(path));
    }

    protected void scrollTo(By locator) {
        WebElement element = find(locator);
        ((JavascriptExecutor) getDriver())
                .executeScript("arguments[0].scrollIntoView({behavior:'smooth',block:'center'});", element);
    }

    protected void scrollTo(String testId) {
        scrollTo(byTestId(testId));
    }

    protected void waitUntilGone(String testId) {
        waitUntilGone(byTestId(testId));
    }

    protected void waitUntilGone(By locator) {
        getWait().until(ExpectedConditions.numberOfElementsToBe(locator,0));
    }
}