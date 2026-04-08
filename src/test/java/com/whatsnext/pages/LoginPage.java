package com.whatsnext.pages;

public class LoginPage extends BasePage {

    private static final String INPUT_USERNAME       = "input-username";
    private static final String INPUT_PASSWORD       = "input-password";
    private static final String BTN_LOGIN            = "btn-login-submit";
    private static final String BTN_TOGGLE_PASSWORD  = "btn-toggle-password";
    private static final String ALERT_ERROR          = "alert-login-error";
    private static final String CREDENTIALS_HINT     = "credentials-hint";

    public LoginPage insertUsername(String username) {
        type(INPUT_USERNAME, username);
        return this;
    }

    public LoginPage insertPassword(String password) {
        type(INPUT_PASSWORD, password);
        return this;
    }

    public LoginPage clickLogin() {
        click(BTN_LOGIN);
        return this;
    }

    public LoginPage togglePasswordVisibility() {
        click(BTN_TOGGLE_PASSWORD);
        return this;
    }

    public DashboardPage loginAs(String username, String password) {
        insertUsername(username);
        insertPassword(password);
        clickLogin();
        waitForUrlContains("/dashboard");
        return new DashboardPage();
    }

    public DashboardPage waitRedirect(){
        waitForUrlContains("/dashboard");
        return new DashboardPage();
    }

    public String getErrorMessage() {
        return getText(ALERT_ERROR);
    }

    public boolean isErrorVisible() {
        return isVisible(ALERT_ERROR);
    }

    public String getPasswordInputType() {
        return getAttribute(INPUT_PASSWORD, "type");
    }
}