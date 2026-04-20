package com.whatsnext.steps;

import com.whatsnext.steps.context.ScenarioContext;
import com.whatsnext.util.ConfigReader;
import com.whatsnext.config.DriverManager;
import com.whatsnext.pages.LoginPage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {

    private final LoginPage loginPage;

    public LoginSteps(ScenarioContext context){
        this.loginPage = context.loginPage;
    }

    private final ConfigReader config = ConfigReader.getInstance();

    @Dado("que estou na página de login")
    public void navegarParaLogin() {
        DriverManager.navigateTo("/login");
    }

    @E("preencho o usuario {string}")
    public void fillUser(String user) {
        String input = "valido".equals(user)
                ? config.get("test.username")
                : user;
        loginPage.insertUsername(input);
    }

    @E("preencho a senha {string}")
    public void fillPassword(String password) {
        String input = "valida".equals(password)
                ? config.get("test.password")
                : password;
        loginPage.insertPassword(input);
    }

    @Quando("clico no botão de login")
    public void clickLoginButton() {
        loginPage.clickLogin();
    }

    @Quando("clico no botão de alternar visibilidade")
    public void clickPasswordVisibility() {
        loginPage.togglePasswordVisibility();
    }

    @Entao("devo ser redirecionado para o dashboard")
    public void validateDashboardRedirect() {
        loginPage.waitRedirect();
        assertThat(DriverManager.getDriver().getCurrentUrl()).contains("/dashboard");
    }

    @Entao("devo ver a mensagem de erro {string}")
    public void validateErrorMessage(String expectedMessage) {
        assertThat(loginPage.isErrorVisible()).isTrue();
        assertThat(loginPage.getErrorMessage()).contains(expectedMessage);
    }

    @Entao("o campo de senha deve estar com tipo {string}")
    public void validatePasswordFieldType(String expectedType) {
        assertThat(loginPage.getPasswordInputType()).isEqualTo(expectedType);
    }

    @E("devo permanecer na pagina de login")
    public void validateKeepOnLoginPage() {
        assertThat(DriverManager.getDriver().getCurrentUrl()).contains("/login");
    }
}