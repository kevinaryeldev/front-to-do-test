package com.whatsnext.pages;

public class LandingPage extends BasePage {

    private static final String BTN_NAV_LOGIN     = "btn-nav-login";
    private static final String BTN_NAV_DASHBOARD = "btn-nav-dashboard";
    private static final String BTN_HERO_LOGIN    = "btn-hero-login";
    private static final String BTN_CTA_LOGIN     = "btn-cta-login";

    public LandingPage clickHeroLogin() {
        click(BTN_HERO_LOGIN);
        return this;
    }
    public LandingPage clickCtaLogin() {
        scrollTo(BTN_CTA_LOGIN);
        click(BTN_CTA_LOGIN);
        return this;
    }
    public boolean isNavLoginVisible() {
        return isVisible(BTN_NAV_LOGIN);
    }
    public boolean isNavDashboardVisible() {
        return isVisible(BTN_NAV_DASHBOARD);
    }
}