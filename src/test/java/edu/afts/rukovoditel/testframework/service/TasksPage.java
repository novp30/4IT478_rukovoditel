package edu.afts.rukovoditel.testframework.service;

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

    private static final String BASE_DASHBOARD_URI = ProjectsPage.BASE_DASHBOARD_URI;

    private List<String> addedTasks = new ArrayList<>();

    public TasksPage(ChromeDriver driver, WebDriverWait wait) {
        super(driver, wait);
        super.getPage(BASE_DASHBOARD_URI);
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
        super.getPage(BASE_DASHBOARD_URI);
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
