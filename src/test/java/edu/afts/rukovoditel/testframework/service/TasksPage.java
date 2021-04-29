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

    private static final String BASE_TASKS_URI =  BASE_PATH + "/index.php?module=items/items&path=21-2929/22";

    private List<String> statuses = new ArrayList<>();

    Actions actions = new Actions(driver);

    public TasksPage(ChromeDriver driver, WebDriverWait wait) {
        super(driver, wait);
        super.getPage(BASE_TASKS_URI);
    }

    public void addTask() {
        driver.findElement(ADD_TASK_BUTTON_SELECTOR)
                .click();
    }

    public void setType(TaskType taskType) {
        new Select(driver.findElement(TYPE_SELECT_SELECTOR))
                .selectByVisibleText(taskType.value());
    }

    public void setName(String taskName) {
        driver.findElement(NAME_INPUT_SELECTOR)
                .sendKeys(taskName);
    }

    public void setPriority(TaskPriority taskPriority) {
        new Select(driver.findElement(PRIORITY_SELECT_SELECTOR))
                .selectByVisibleText(taskPriority.value());
    }

    public void setStatus(TaskStatus taskStatus) {
        new Select(driver.findElement(STATUS_INPUT_SELECTOR))
                .selectByVisibleText(taskStatus.value());
    }

    public void setDescription(String taskDescription) {
        driver.findElement(DESCRIPTION_EDITOR_SELECTOR)
                .sendKeys(taskDescription);
    }

    public void saveTask() {
        driver.findElement(SAVE_BUTTON_SELECTOR)
                .click();
    }

    public List<WebElement> getTaskTableRows() {
        return driver.findElements(TASK_TABLE_ROWS_SELECTOR);
    }

    public List<String> getTaskStatusesFromTable() {
        for (WebElement row : getTaskTableRows()) {
            statuses.add(row.findElement(By.cssSelector(".field-169-td.fieldtype_dropdown div"))
                .getText());
        }
        return statuses;
    }

    public boolean areValidStatusesInTable(List<String> actualStatuses,
                                            List<String> validStatuses,
                                            int actualNumberOfTasks,
                                            int expectedNumberOfTasks) {
        return actualNumberOfTasks == expectedNumberOfTasks
            && validStatuses.equals(actualStatuses);
    }

    public void editFilterOptions(List<String> filtersToAdd){
        driver.findElement(EDIT_DEFAULT_FILTERS_SELECTOR)
                .click();
        super.waitForElement(DELETE_FILTER_OPTIONS);
        while(driver.findElements(DELETE_FILTER_OPTIONS).size() > 0){
            driver.findElement(DELETE_FILTER_OPTIONS)
                    .click();
        }

        driver.findElement(FILTER_EDIT_SAVE_BUTTON_SELECTOR)
                .click();
        driver.findElement(EDIT_DEFAULT_FILTERS_SELECTOR)
                .click();
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
        driver.findElement(FILTER_EDIT_SAVE_BUTTON_SELECTOR)
                .click();
    }

    // ## CLEANUP METHODS ##
    public void removeAddedTasks() {
        driver.findElement(BULK_SELECT_ALL_CHECKBOX_SELECTOR)
                .click();
        WebElement target = driver.findElement(BULK_ACTIONS_OPTIONS_DROPDOWN_SELECTOR);
        actions.moveToElement(target).perform();
        super.waitForElement(BULK_ACTIONS_OPTIONS_DROPDOWN_SELECTOR_EXPANDED);
        driver.findElement(BULK_ACTIONS_DELETE_OPTION_SELECTOR)
                .click();
        super.waitForElement(BULK_ACTIONS_DELETE_OPTION_CONFIRM_SELECTOR);
        driver.findElement(BULK_ACTIONS_DELETE_OPTION_CONFIRM_SELECTOR)
                .click();
    }

    public void deleteAllFilters() {
        driver.findElement(REMOVE_ALL_FILTERS_BUTTON_SELECTOR)
                .click();
    }

    public void addDefaultFilters() {
        WebElement target = driver.findElement(FILTER_SECTION_DROPDOWN_SELECTOR);
        actions.moveToElement(target).perform();
        super.waitForElement(ADD_DEFAULT_FILTERS_BUTTON);
        driver.findElement(ADD_DEFAULT_FILTERS_BUTTON)
            .click();
    }

}
