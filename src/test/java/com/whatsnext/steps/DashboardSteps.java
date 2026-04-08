package com.whatsnext.steps;


import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import com.whatsnext.pages.DashboardPage;
import com.whatsnext.config.DriverManager;
import org.openqa.selenium.JavascriptExecutor;

import static org.assertj.core.api.Assertions.assertThat;

public class DashboardSteps {

    private final DashboardPage dashboardPage = new DashboardPage();

    @Dado("que estou logado no dashboard")
    public void goToDashboardPageLogged() {
        DriverManager.navigateTo("/");
        ((JavascriptExecutor) DriverManager.getDriver())
                .executeScript("localStorage.setItem('whatsnext_token','fake-token'); localStorage.setItem('whatsnext_user','admin');");
        DriverManager.navigateTo("/dashboard");
    }

    @Quando("crio uma tarefa com título {string} nota {string} e urgência {string}")
    public void createTask(String titulo, String nota, String urgencia) {
        dashboardPage.createTask(titulo, nota.isEmpty() ? null : nota, urgencia);
    }

    @Quando("abro o drawer de nova tarefa")
    public void openDrawer() {
        dashboardPage.openNewTaskDrawer();
    }

    @E("submeto o formulário sem preencher o título")
    public void submitTask() {
        dashboardPage.submitTask();
    }

    @E("fecho o drawer")
    public void closeDrawer() {
        dashboardPage.closeDrawer();
    }

    @E("marco a primeira tarefa como concluída")
    public void toggleFirstTaskDoneCheckbox() {
        dashboardPage.toggleFirstCard();
    }

    @E("deleto a primeira tarefa")
    public void deleteFirstTask() {
        dashboardPage.deleteFirstCard();
    }

    @Quando("faço logout")
    public void clickLogout() {
        dashboardPage.logout();
    }

    @Quando("limpo o localStorage e acesso o dashboard")
    public void cleanLocalStorageAngReload() {
        DriverManager.clearLocalStorage();
        DriverManager.reload();
    }

    @E("filtro por {string}")
    public void filterBy(String filtro) {
        dashboardPage.filterBy(filtro);
    }

    @Entao("a primeira tarefa deve estar concluída")
    public void validateFirstTaskDone() {
        assertThat(dashboardPage.isFirstCardDone()).isTrue();
    }

    @Entao("a primeira tarefa não deve estar concluída")
    public void validateFirstTaskNotDone() {
        assertThat(dashboardPage.isFirstCardDone()).isFalse();
    }

    @Entao("devo ver o estado vazio")
    public void validateEmptyState() {
        assertThat(dashboardPage.isEmptyStateVisible()).isTrue();
    }

    @Entao("^devo ver (\\d+) tarefas? na lista$")
    public void validateTotalTasksOnView(int expectedTaskQuantity) {
        assertThat(dashboardPage.getCardCount()).isEqualTo(expectedTaskQuantity);
    }

    @E("a primeira tarefa deve ter o título {string}")
    public void validateFirstTaskTitle(String expectedTitle) {
        assertThat(dashboardPage.getFirstCardTitle()).isEqualTo(expectedTitle);
    }

    @E("a primeira tarefa deve ter urgência {string}")
    public void validateFirstTaskUrgency(String urgency) {
        assertThat(dashboardPage.getFirstCardUrgency()).isEqualTo(urgency);
    }

    @E("o total de tarefas deve ser {int}")
    public void validateTotalTasksNumber(int n) {
        assertThat(dashboardPage.getStatTotal()).isEqualTo(n);
    }

    @E("as tarefas pendentes devem ser {int}")
    public void validatePendingTasksNumber(int n) {
        assertThat(dashboardPage.getStatPending()).isEqualTo(n);
    }

    @E("as tarefas concluídas devem ser {int}")
    public void validateDoneTasksNumber(int n) {
        assertThat(dashboardPage.getStatDone()).isEqualTo(n);
    }

    @E("as tarefas urgentes devem ser {int}")
    public void validateUrgentTasksNumber(int n) {
        assertThat(dashboardPage.getStatUrgent()).isEqualTo(n);
    }

}
