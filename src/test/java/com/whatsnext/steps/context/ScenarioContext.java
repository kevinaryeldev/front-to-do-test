package com.whatsnext.steps.context;

import com.whatsnext.pages.DashboardPage;
import com.whatsnext.pages.LandingPage;
import com.whatsnext.pages.LoginPage;

public class ScenarioContext {
    public final LandingPage landingPage       = new LandingPage();
    public final LoginPage loginPage           = new LoginPage();
    public final DashboardPage dashboardPage   = new DashboardPage();
}