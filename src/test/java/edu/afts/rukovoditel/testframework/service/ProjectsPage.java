package edu.afts.rukovoditel.testframework.service;

import edu.afts.rukovoditel.testframework.constants.ProjectPriority;
import edu.afts.rukovoditel.testframework.constants.ProjectStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static edu.afts.rukovoditel.testframework.constants.Selectors.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectsPage extends Page {

    public static final String BASE_PROJECTS_URI = BASE_PATH + "/index.php?module=items/items&path=21";
    public static final String PROJECT_PAGE_TITLE = "Rukovoditel | Projects";

    public ProjectsPage(ChromeDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open() {
        super.getPage(BASE_PROJECTS_URI);
        assertEquals(PROJECT_PAGE_TITLE, driver.getTitle());
    }

    public void showAddProjectForm() {
        driver.findElement(DASH_ADD_PROJECT_BUTTON_SELECTOR)
                .click();
        waitForElement(PROJECT_MODAL_SELECTOR);
    }

    public void setName(String projectName) {
        driver.findElement(PROJECT_NAME_INPUT_SELECTOR)
                .sendKeys(projectName);
    }

    public void setPriority(ProjectPriority priority) {
        new Select(driver.findElement(PROJECT_PRIORITY_SELECT_SELECTOR))
                .selectByVisibleText(priority.value());
    }

    public void setStatus(ProjectStatus status) {
        new Select(driver.findElement(PROJECT_STATUS_SELECT_SELECTOR))
                .selectByVisibleText(status.value());
    }

    public void setTodayStartDate() {
        driver.findElement(PROJECT_DATE_SET_SELECTOR)
                .click();

        driver.findElement(PROJECT_DATE_ACTIVE_DAY_SELECTOR)
                .click();
    }

    public void saveProject() {
        // save project
        driver.findElement(PROJECT_SUBMIT_BUTTON_SELECTOR)
                .click();
    }

    public void filterProjectsTable(String projectName) {
        waitForElement(PROJECT_SEARCH_INPUT_SELECTOR);
        driver.findElement(PROJECT_SEARCH_INPUT_SELECTOR).clear();
        driver.findElement(PROJECT_SEARCH_INPUT_SELECTOR)
                .sendKeys(projectName);
        driver.findElement(DASH_PROJECT_SEARCH_SUBMIT_BUTTON_SELECTOR)
                .click();
        waitForElement(DASH_RESET_FILTER);
    }

    public List<WebElement> getProjectTableRows() {
        waitForElement(DASH_RESET_FILTER);
        return driver.findElements(DASH_PROJECT_TABLE_SELECTOR);
    }

    public String getProjectNameFromRow(WebElement webElement) {
        waitForElement(DASH_PROJECT_TABLE_NAME_SELECTOR);
        return webElement.findElement(DASH_PROJECT_TABLE_NAME_SELECTOR)
                .getText();
    }

    /**
     * CLEANUP
     *
     * Removes all rows from projects table if any search filter is active.
     */
    public void removeProjectsFromTable() throws InterruptedException {
        waitForElement(DASH_RESET_FILTER);
        waitForElement(DASH_DELETE_ALL_SELECTED);
        driver.findElement(DASH_DELETE_ALL_SELECTED).click();
        driver.findElement(DASH_DROPDOWN_SELECTED).click();
        waitForElement(DASH_DELETE_SELECTED);
        driver.findElement(DASH_DELETE_SELECTED).click();
        waitForElement(DASH_DELETE_SELECTED_BUTTON);
        driver.findElement(By.cssSelector(".single-checkbox label")).click();
        driver.findElement(DASH_DELETE_SELECTED_BUTTON).click();
        resetFilters();
    }

    private void resetFilters() {
        waitForElement(DASH_RESET_FILTER);
        driver.findElement(DASH_RESET_FILTER)
                .click();
    }
}
