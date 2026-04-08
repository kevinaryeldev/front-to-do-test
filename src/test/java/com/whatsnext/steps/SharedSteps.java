package com.whatsnext.steps;

import com.whatsnext.config.DriverManager;
import io.cucumber.java.pt.Entao;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class SharedSteps {

    @Entao("devo ser redirecionado para a página de login")
    public void checkLoginRedirect() {
        DriverManager.getWait().until(ExpectedConditions.urlContains("/login"));
        assertThat(DriverManager.getDriver().getCurrentUrl()).contains("/login");
    }
}
