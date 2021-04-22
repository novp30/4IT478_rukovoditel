package edu.afts.rukovoditel.testframework.service;

import edu.afts.rukovoditel.testframework.constants.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static edu.afts.rukovoditel.testframework.constants.Selectors.*;

public class TasksPage extends Page {

    private static final String BASE_DASHBOARD_URI = BASE_PATH + "/index.php?module=reports/prepare_add_item&reports_id=70";

    private List<String> addedTasks = new ArrayList<>();

    public TasksPage(ChromeDriver driver, WebDriverWait wait) {
        super(driver, wait);
        super.getPage(BASE_DASHBOARD_URI);
    }

    public void showAddTaskForm() {
//        driver.findElement(DASH_ADD_PROJECT_BUTTON_SELECTOR) TODO
//                .click();
        waitForElement(PROJECT_MODAL_SELECTOR);
        //I need to add additional step here to add the task (Add task again in pop-up window)
    }

    public void setName(String projectName) {
        driver.findElement(PROJECT_NAME_INPUT_SELECTOR)
                .sendKeys(projectName);
    }
    public void setType(TaskType type) {
        new Select(driver.findElement(TASK_TYPE_SELECT_SELECTOR))
                .selectByVisibleText(type.value());
    }
    public void setPriority(TaskPriority priority) {
        new Select(driver.findElement(PROJECT_PRIORITY_SELECT_SELECTOR))
                .selectByVisibleText(priority.value());
    }

    public void setStatus(TaskStatus status) {
        new Select(driver.findElement(PROJECT_STATUS_SELECT_SELECTOR))
                .selectByVisibleText(status.value());
    }

    public void setDescription(String taskDescription) {
        driver.findElement(TASK_DESCRIPTION_INPUT_SELECTOR)
                .sendKeys(taskDescription);
    }

    public void saveTask() {
        String taskName = driver.findElement(PROJECT_NAME_INPUT_SELECTOR)
                .getText();

        // save task
        driver.findElement(PROJECT_SUBMIT_BUTTON_SELECTOR)
                .click();

        // check if task was added
        if (!isElementShown(PROJECT_MODAL_SELECTOR)) {
            // project was added, save project name for after test cleanup
            addedTasks.add(taskName);
        }
    }

    public void filterTasksTable(String taskName) {
        waitForElement(PROJECT_SEARCH_INPUT_SELECTOR);
        driver.findElement(PROJECT_SEARCH_INPUT_SELECTOR)
                .sendKeys(taskName);
        driver.findElement(DASH_PROJECT_SEARCH_SUBMIT_BUTTON_SELECTOR)
                .click();
    }

    public List<WebElement> getTaskTableRows() {
        return driver.findElements(DASH_PROJECT_TABLE_SELECTOR);
    }

    public String getTaskNameFromRow(WebElement webElement) {
        return webElement.findElement(DASH_PROJECT_TABLE_NAME_SELECTOR)
                .getText();
    }

    public boolean isTaskFilterActive() {
        return isElementShown(DASH_RESET_FILTER);
    }

    // ## CLEANUP METHODS ##
    public void removeAddedTasks() {
        if (isTaskFilterActive()) {
            resetFilters();
        }

        for (String addedProject : addedTasks) {
            filterTasksTable(addedProject);
            // TODO
//            removeTasksFromTable();
        }
    }

    /**
     * Removes all rows from tasks table if any search filter is active.
     */
    private void removeTasksFromTable() {
        waitForElement(DASH_RESET_FILTER);
        List<WebElement> taskTableRows = getTaskTableRows();
        for (WebElement element : taskTableRows) {
            element.findElement(DASH_PROJECT_REMOVE_BUTTON_SELECTOR)
                    .click();

            waitForElement(DELETE_MODAL_SELECTOR);
            element.findElement(DELETE_CONFIRM_CHECKBOX_SELECTOR)
                    .click();
            element.findElement(DELETE_SUBMIT_BUTTON_SELECTOR)
                    .click();
        }
        resetFilters();
    }

    private void resetFilters() {
        waitForElement(DASH_RESET_FILTER);
        driver.findElement(DASH_RESET_FILTER)
                .click();
    }
}
