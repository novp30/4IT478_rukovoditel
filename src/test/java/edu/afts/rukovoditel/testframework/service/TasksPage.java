package edu.afts.rukovoditel.testframework.service;

import edu.afts.rukovoditel.testframework.constants.ProjectPriority;
import edu.afts.rukovoditel.testframework.constants.ProjectStatus;
import edu.afts.rukovoditel.testframework.constants.TaskPriority;
import edu.afts.rukovoditel.testframework.constants.TaskStatus;
import edu.afts.rukovoditel.testframework.constants.TaskType;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static edu.afts.rukovoditel.testframework.constants.Selectors.Project.*;
import static edu.afts.rukovoditel.testframework.constants.Selectors.Tasks.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TasksPage extends Page {

    private static final String BASE_TASKS_URI = BASE_PATH + "/index.php?module=items/items&path=21-3180/22";
    private final ProjectsPage projectsPage;

    private List<String> statuses = new ArrayList<>();

    Actions actions = new Actions(driver);

    public TasksPage(ChromeDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.projectsPage = new ProjectsPage(driver, wait);
    }

    public void open() {
        super.getPage(BASE_TASKS_URI);
    }

    public void addTask() {
        driver.findElement(ADD_TASK_BUTTON_SELECTOR).click();
    }

    public void setType(TaskType taskType) {
        new Select(driver.findElement(TYPE_SELECT_SELECTOR)).selectByVisibleText(taskType.value());
    }

    public void setName(String taskName) {
        driver.findElement(NAME_INPUT_SELECTOR).sendKeys(taskName);
    }

    public void setPriority(TaskPriority taskPriority) {
        new Select(driver.findElement(PRIORITY_SELECT_SELECTOR)).selectByVisibleText(taskPriority.value());
    }

    public void setStatus(TaskStatus taskStatus) {
        new Select(driver.findElement(STATUS_INPUT_SELECTOR)).selectByVisibleText(taskStatus.value());
    }

    public void setDescription(String taskDescription) {
        driver.switchTo().frame(driver.findElement(TASK_FORM_DESCRIPTION_IFRAME));
        WebElement element = driver.findElement(TASK_FORM_DESCRIPTION_IFRAME_BODY);
        element.sendKeys(taskDescription);
        driver.switchTo().defaultContent();
    }

    public void saveTask() {
        driver.findElement(SAVE_BUTTON_SELECTOR).click();
        waitForElement(TASK_PAGE_GRID_SELECTOR);
    }

    public List<WebElement> getTaskTableRows() {
        return driver.findElements(TASK_TABLE_ROWS_SELECTOR);
    }

    public List<String> getTaskStatusesFromTable() {
        for (WebElement row : getTaskTableRows()) {
            statuses.add(
                row.findElement(TASK_TABLE_STATUS_VALUE).getText());
        }
        return statuses;
    }

    public boolean areValidStatusesInTable(List<String> actualStatuses, List<String> validStatuses,
        int actualNumberOfTasks, int expectedNumberOfTasks) {
        return actualNumberOfTasks == expectedNumberOfTasks && validStatuses.equals(actualStatuses);
    }

    public void editFilterOptions(List<String> filtersToAdd) {
        driver.findElement(EDIT_DEFAULT_FILTERS_SELECTOR).click();
        super.waitForElement(DELETE_FILTER_OPTIONS);
        while (driver.findElements(DELETE_FILTER_OPTIONS).size() > 0) {
            driver.findElement(DELETE_FILTER_OPTIONS).click();
        }

        driver.findElement(FILTER_EDIT_SAVE_BUTTON_SELECTOR).click();
        driver.findElement(EDIT_DEFAULT_FILTERS_SELECTOR).click();
        super.waitForElement(FILTER_STATUS_SELECT_SELECTOR);

        for (String filter : filtersToAdd) {
            WebElement select = driver.findElement(FILTER_STATUS_SELECT_SELECTOR);
            WebElement selectSearchInput = driver.findElement(SELECT_SEARCH_INPUT);
            selectSearchInput.clear();
            selectSearchInput.sendKeys(filter);
            super.waitForElement(FILTERS_ACTIVE_RESULT);
            selectSearchInput.sendKeys(Keys.ENTER);
            // new Select(select)
            //         .selectByVisibleText(filtersToAdd.get(i));
        }
        driver.findElement(FILTER_EDIT_SAVE_BUTTON_SELECTOR).click();
    }

    // ## CLEANUP METHODS ##
    public void removeAddedTasks() {
        driver.findElement(BULK_SELECT_ALL_CHECKBOX_SELECTOR).click();
        actions.moveToElement(driver.findElement(BULK_ACTIONS_OPTIONS_DROPDOWN_SELECTOR))
            .perform();
        super.waitForElement(BULK_ACTIONS_DELETE_OPTION_SELECTOR);
        super.waitForElement(BULK_DELETE);
        driver.findElement(BULK_ACTIONS_DELETE_OPTION_SELECTOR).click();
        super.waitForElement(BULK_ACTIONS_DELETE_OPTION_CONFIRM_SELECTOR);
        driver.findElement(BULK_ACTIONS_DELETE_OPTION_CONFIRM_SELECTOR).click();
    }

    public void deleteAllFilters() {
        driver.findElement(REMOVE_ALL_FILTERS_BUTTON_SELECTOR).click();
    }

    public void addDefaultFilters() {
        WebElement target = driver.findElement(FILTER_SECTION_DROPDOWN_SELECTOR);
        actions.moveToElement(target).perform();
        super.waitForElement(ADD_DEFAULT_FILTERS_BUTTON);
        driver.findElement(ADD_DEFAULT_FILTERS_BUTTON).click();
    }

     // ############ PART 2
    public void showAddTaskForm() {
        waitForElement(TASK_PAGE_GRID_SELECTOR);
        driver.findElement(TASK_PAGE_ADD_TASK_BUTTON_SELECTOR).click();
        waitForElement(TASK_FORM_TITLE_SELECTOR);
    }

    // ## CLEANUP METHODS ##
    public void removeTask() {
        driver.findElement(TASK_MORE_ACTIONS).click();
        driver.findElement(TASK_MORE_ACTIONS_DELETE).click();
        waitForElement(TASK_DELETE_SUBMIT_BUTTON_SELECTOR);
        driver.findElement(TASK_DELETE_SUBMIT_BUTTON_SELECTOR).click();
    }

    public void showTaskInfoPage(String taskName) {
        driver.findElement(getTaskGridLinkSelectorByName(taskName)).click();
        waitForElement(TASK_INFO_SELECTOR);
    }

    public String getTaskNameString() {
        return driver.findElement(TASK_NAME_STRING).getText();
    }

    public String getTaskTypeString() {
        return driver.findElement(TASK_TYPE_STRING).getText();
    }

    public String getTaskStatusString() {
        return driver.findElement(TASK_STATUS_STRING).getText();
    }

    public String getTaskPriorityString() {
        return driver.findElement(TASK_PRIORITY_STRING).getText();
    }

    public String getTaskDescriptionString() {
        return driver.findElement(TASK_DESCRIPTION_STRING).getText();
    }

    public By getTaskGridLinkSelectorByName(String taskName) {
        return By.xpath("//a[contains(text(), '" + taskName + "')]");
    }

    public String createTestProject() {
        String projectName = UUID.randomUUID().toString();
        // WHEN
        // show form
        projectsPage.open();
        projectsPage.showAddProjectForm();
        assertTrue(projectsPage.isElementShown(PROJECT_MODAL_SELECTOR));

        // fill form
        projectsPage.setStatus(ProjectStatus.NEW);
        projectsPage.setPriority(ProjectPriority.HIGH);
        projectsPage.setTodayStartDate();
        projectsPage.setName(projectName);

        // save project
        projectsPage.saveProject();
        assertFalse(projectsPage.isElementShown(PROJECT_MODAL_SELECTOR));
        return projectName;
    }

    public void removeTestProject(String projectName) {
        waitForElement(By.partialLinkText(projectName));
        WebElement projectToDelete = driver.findElementByLinkText(projectName);
        projectToDelete.click();
        WebElement target = driver.findElement(PROJECT_INFO_MORE_ACTIONS_DROPDOWN);
        actions.moveToElement(target).perform();
        waitForElement(TASK_PROJECT_DELETE_SHOWN_SELECTOR);
        WebElement deleteButton = driver.findElement(PROJECT_INFO_DROPDOWN_DELETE_BUTTON);
        deleteButton.click();
        try {
            waitForElement(PROJECT_INFO_DELETE_CONFIRM_CHECKBOX);
            WebElement projectDeleteConfirm = driver.findElement(PROJECT_INFO_DELETE_CONFIRM_CHECKBOX);
            projectDeleteConfirm.click();
        } catch (TimeoutException ex1) {
            // delete checkbox was removed again
        }
        driver.findElement(PROJECT_INFO_DELETE_FINAL_BUTTON).click();
    }
}
