package com.whatsnext.util;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ByTestId extends By {

    private final String testId;
    private final By internal;

    private ByTestId(String testId) {
        this.testId = testId;
        this.internal = By.cssSelector("[data-test-id='" + testId + "']");
    }

    public static ByTestId of(String testId) {
        return new ByTestId(testId);
    }

    @Override
    public List<WebElement> findElements(SearchContext context) {
        return context.findElements(internal);
    }

    @Override
    public WebElement findElement(SearchContext context) {
        return context.findElement(internal);
    }

    @Override
    public String toString() {
        return "ByTestId: " + testId;
    }
}