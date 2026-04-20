package com.whatsnext.util;

import com.whatsnext.config.DriverManager;
import org.openqa.selenium.JavascriptExecutor;

public final class AuthHelper {

    private AuthHelper() {}

    public static void bypassLogin() {
        ((JavascriptExecutor) DriverManager.getDriver())
                .executeScript(
                        "localStorage.setItem('whatsnext_token','fake-token');" +
                                "localStorage.setItem('whatsnext_user','admin');"
                );
    }
}