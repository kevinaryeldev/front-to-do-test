package com.whatsnext.pages;

import org.openqa.selenium.WebElement;

public class DashboardPage extends BasePage {

    private static final String BTN_NEW_TASK     = "btn-new-task";
    private static final String MENU_USER        = "menu-user";
    private static final String BTN_LOGOUT       = "btn-logout";

    private static final String INPUT_TITLE      = "input-task-title";
    private static final String INPUT_NOTE       = "input-task-note";
    private static final String SELECT_URGENCY   = "select-task-urgency";
    private static final String BTN_SUBMIT       = "btn-submit-task";
    private static final String BTN_CLOSE_DRAWER = "btn-close-drawer";

    private static final String TODO_CARD        = "todo-card";
    private static final String EMPTY_STATE      = "empty-state";
    private static final String CARD_TITLE       = "todo-card-title";
    private static final String CARD_TOGGLE      = "todo-card-toggle";
    private static final String CARD_DELETE      = "todo-card-delete";

    private static final String STAT_TOTAL       = "stat-total";
    private static final String STAT_PENDING     = "stat-pending";
    private static final String STAT_DONE        = "stat-done";
    private static final String STAT_URGENT      = "stat-urgent";

    public DashboardPage openNewTaskDrawer() {
        click(BTN_NEW_TASK);
        find(INPUT_TITLE);
        return this;
    }

    public DashboardPage fillTitle(String title) {
        type(INPUT_TITLE, title);
        return this;
    }

    public DashboardPage fillNote(String note) {
        type(INPUT_NOTE, note);
        return this;
    }

    public DashboardPage selectUrgency(String value) {
        selectByValue(SELECT_URGENCY, value);
        return this;
    }

    public DashboardPage submitTask() {
        click(BTN_SUBMIT);
        return this;
    }

    public DashboardPage closeDrawer() {
        click(BTN_CLOSE_DRAWER);
        waitUntilGone(INPUT_TITLE);
        return this;
    }

    public DashboardPage createTask(String title, String note, String urgency, int expectedTaskCount) {
        openNewTaskDrawer().fillTitle(title);
        if (note != null && !note.isBlank()) {
            fillNote(note);
        }
        selectUrgency(urgency).
                submitTask().
                waitForCardCount(expectedTaskCount).
                waitUntilGone(INPUT_TITLE);
        return this;
    }

    public int getCardCount() {
        if (isPresent(TODO_CARD)) {
            return countElements(TODO_CARD);
        }
        return 0;
    }

    public WebElement getFirstCard() {
        return find(TODO_CARD);
    }

    public String getFirstCardTitle() {
        return findChild(getFirstCard(), CARD_TITLE).getText();
    }

    public String getFirstCardUrgency() {
        return getFirstCard().getDomAttribute("data-urgency");
    }

    public boolean isFirstCardDone() {
        return "true".equals(getFirstCard().getDomAttribute("data-done"));
    }

    public DashboardPage toggleFirstCard() {
        findChild(getFirstCard(), CARD_TOGGLE).click();
        return this;
    }

    public DashboardPage deleteFirstCard() {
        findChild(getFirstCard(), CARD_DELETE).click();
        return this;
    }

    public boolean isEmptyStateVisible() {
        return isVisible(EMPTY_STATE);
    }


    public DashboardPage filterBy(String filterValue) {
        click("filter-" + filterValue);
        return this;
    }

    public DashboardPage waitForCardCount(int expected) {
        getWait().until(driver ->
                countElements(TODO_CARD) == expected
        );
        return this;
    }

    private int getStatValue(String statTestId) {
        return Integer.parseInt(getText(statTestId + "-value"));
    }

    public int getStatTotal() {
        return getStatValue(STAT_TOTAL);
    }

    public int getStatPending() {
        return getStatValue(STAT_PENDING);
    }

    public int getStatDone() {
        return getStatValue(STAT_DONE);
    }

    public int getStatUrgent() {
        return getStatValue(STAT_URGENT);
    }

    public void logout() {
        click(MENU_USER);
        click(BTN_LOGOUT);
    }
}