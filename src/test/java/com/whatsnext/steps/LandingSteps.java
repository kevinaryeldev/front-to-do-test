package com.whatsnext.steps;

import com.whatsnext.steps.context.ScenarioContext;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import com.whatsnext.config.DriverManager;
import com.whatsnext.pages.LandingPage;
import org.openqa.selenium.JavascriptExecutor;

import static org.assertj.core.api.Assertions.assertThat;

public class LandingSteps {

    private final LandingPage landingPage;

    public LandingSteps(ScenarioContext ctx) {
        this.landingPage = ctx.landingPage;
    }
    @Dado("que estou na landing page sem autenticação")
    public void landingPageNoAuth() {
        DriverManager.navigateTo("/");
    }

    @Dado("que estou na landing page autenticado")
    public void landingPageWithAuth() {
        DriverManager.navigateTo("/");
        ((JavascriptExecutor) DriverManager.getDriver())
                .executeScript("localStorage.setItem('whatsnext_token', 'fake-token');");
        DriverManager.getDriver().navigate().refresh();
    }

    @Entao("devo ver o botão {string} na navbar")
    public void validateNavbarButton(String label) {
        if ("Entrar".equals(label)) {
            assertThat(landingPage.isNavLoginVisible()).isTrue();
        } else if ("Ir para o Dashboard".equals(label)) {
            assertThat(landingPage.isNavDashboardVisible()).isTrue();
        }
    }

    @Quando("clico no botão {string} do hero")
    public void clickHeroButton(String label) {
        landingPage.clickHeroLogin();
    }

    @Quando("clico no botão {string} do CTA")
    public void clickCtaButton(String label) {
        landingPage.clickCtaLogin();
    }
}
