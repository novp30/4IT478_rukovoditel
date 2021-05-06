package edu.afts.rukovoditel.testframework.service;

import edu.afts.rukovoditel.testframework.constants.TaskPriority;
import edu.afts.rukovoditel.testframework.constants.TaskStatus;
import edu.afts.rukovoditel.testframework.constants.TaskType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static edu.afts.rukovoditel.testframework.constants.Selectors.*;
import static edu.afts.rukovoditel.testframework.constants.Selectors.DASH_RESET_FILTER;

public class TasksPage extends Page {

    private static final String BASE_TASKS_URI = BASE_PATH + "/index.php?module=items/items&path=21-3180/22";

    private List<String> statuses = new ArrayList<>();

    Actions actions = new Actions(driver);

    public TasksPage(ChromeDriver driver, WebDriverWait wait) {
        super(driver, wait);
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
        driver.findElement(DESCRIPTION_EDITOR_SELECTOR).sendKeys(taskDescription);
    }

    public void saveTask() {
        driver.findElement(SAVE_BUTTON_SELECTOR).click();
    }

    public List<WebElement> getTaskTableRows() {
        return driver.findElements(TASK_TABLE_ROWS_SELECTOR);
    }

    public List<String> getTaskStatusesFromTable() {
        for (WebElement row : getTaskTableRows()) {
            statuses.add(
                row.findElement(By.cssSelector(".field-169-td.fieldtype_dropdown div")).getText());
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
            WebElement selectSearchInput = driver.findElementByCssSelector(".chosen-choices > li > input");
            selectSearchInput.clear();
            selectSearchInput.sendKeys(filter);
            super.waitForElement(By.cssSelector("li.active-result"));
            selectSearchInput.sendKeys(Keys.ENTER);
            // new Select(select)
            //         .selectByVisibleText(filtersToAdd.get(i));
        }
        driver.findElement(FILTER_EDIT_SAVE_BUTTON_SELECTOR).click();
    }

    // ## CLEANUP METHODS ##
    public void removeAddedTasks() {
        driver.findElement(BULK_SELECT_ALL_CHECKBOX_SELECTOR).click();
        WebElement target = driver.findElement(BULK_ACTIONS_OPTIONS_DROPDOWN_SELECTOR);
        actions.moveToElement(target).perform();
        super.waitForElement(BULK_ACTIONS_OPTIONS_DROPDOWN_SELECTOR_EXPANDED);
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

}

import edu.afts.rukovoditel.testframework.constants.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static edu.afts.rukovoditel.testframework.constants.Selectors.*;

public class TasksPage extends Page {

//    private static final String BASE_DASHBOARD_URI = BASE_PATH + ;

    private List<String> addedTasks = new ArrayList<>();

    public TasksPage(ChromeDriver driver, WebDriverWait wait) {
        super(driver, wait);
//        super.getPage(BASE_DASHBOARD_URI);
    }

    public void showTaskPageForProject() {
        waitForElement(PROJECT_PAGE_GRID_SELECTOR);
        driver.findElement(PROJECT_YOURNAME_LINK_SELECTOR).click();
        waitForElement(TASK_PAGE_GRID_SELECTOR);
    }

    public void showAddTaskForm() {
        waitForElement(TASK_PAGE_GRID_SELECTOR);
        driver.findElement(TASK_PAGE_ADD_TASK_BUTTON_SELECTOR).click();
        waitForElement(TASK_FORM_TITLE_SELECTOR);
    }

    public void setName(String taskName) {
        driver.findElement(TASK_NAME_INPUT_SELECTOR)
                .sendKeys(taskName);
    }
    public void setType(TaskType type) {
       new Select(driver.findElement(TASK_TYPE_SELECT_SELECTOR))
                .selectByVisibleText(type.value());
   }
    public void setPriority(TaskPriority priority) {
        new Select(driver.findElement(TASK_PRIORITY_SELECT_SELECTOR))
                .selectByVisibleText(priority.value());
    }

    public void setStatus(TaskStatus status) {
        new Select(driver.findElement(TASK_STATUS_SELECT_SELECTOR))
                .selectByVisibleText(status.value());
    }

    public void setDescription(String taskDescription) {
        var iframe = driver.switchTo().frame(0);
        iframe.findElement(By.xpath("//body[@contenteditable='true']"))
                .sendKeys(taskDescription);
        driver.switchTo().defaultContent();
    }

    public void saveTask(String taskName) {
        // save task
        driver.findElement(TASK_SUBMIT_BUTTON_SELECTOR)
                .click();

        waitForElement(TASK_PAGE_GRID_SELECTOR);
        addedTasks.add(taskName);
    }

    // ## CLEANUP METHODS ##
    public void removeTask() {
        driver.findElementByXPath("//button[contains(text(), 'More Actions')]")
                .click();
        driver.findElementByXPath("//a[contains(text(), 'Delete')]")
                .click();
        waitForElement(TASK_DELETE_SUBMIT_BUTTON_SELECTOR);
        driver.findElement(TASK_DELETE_SUBMIT_BUTTON_SELECTOR)
                .click();
    }

    public void showTaskInfoPage(String taskName) {
        driver.findElement(getTaskGridLinkSelectorByName(taskName))
                .click();
        waitForElement(TASK_INFO_SELECTOR);
    }

    public String getTaskNameString() {
        return driver.findElementByXPath("//div[contains(@class, 'portlet-title')]/div[contains(@class, 'caption')]").getText();
    }

    public String getTaskTypeString() {
        return driver.findElementByXPath("//tr[contains(@class, 'form-group-167')]/td/div").getText();
    }

    public String getTaskStatusString() {
        return driver.findElementByXPath("//tr[contains(@class, 'form-group-169')]/td/div").getText();
    }

    public String getTaskPriorityString() {
        return driver.findElementByXPath("//tr[contains(@class, 'form-group-170')]/td/div").getText();
    }

    public String getTaskDescriptionString() {
        return driver.findElementByXPath("//div[contains(@class, 'content_box_content fieldtype_textarea_wysiwyg')]").getText();
    }

    public void cleanup() {
//        super.getPage(BASE_DASHBOARD_URI);
        showTaskPageForProject();

        for (var taskName : addedTasks) {
            if (isElementShown(getTaskGridLinkSelectorByName(taskName)))
            {
                showTaskInfoPage(taskName);
                removeTask();
            }
        }
    }

    public By getTaskGridLinkSelectorByName(String taskName) {
        return By.xpath("//a[contains(text(), '" + taskName + "')]");
    }
}
