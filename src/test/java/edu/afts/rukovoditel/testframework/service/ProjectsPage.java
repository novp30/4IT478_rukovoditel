package edu.afts.rukovoditel.testframework.service;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import edu.afts.rukovoditel.testframework.constants.ProjectPriority;
import edu.afts.rukovoditel.testframework.constants.ProjectStatus;

import static edu.afts.rukovoditel.testframework.constants.Selectors.*;

public class ProjectsPage extends Page {

    public static final String BASE_DASHBOARD_URI = BASE_PATH + "/index.php?module=items/items&path=21";

    private List<String> addedProjects = new ArrayList<>();

    public ProjectsPage(ChromeDriver driver, WebDriverWait wait) {
        super(driver, wait);
        super.getPage(BASE_DASHBOARD_URI);
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
        String projectName = driver.findElement(PROJECT_NAME_INPUT_SELECTOR)
                .getText();

        // save project
        driver.findElement(PROJECT_SUBMIT_BUTTON_SELECTOR)
                .click();

        // check if project was added
        if (!isElementShown(PROJECT_MODAL_SELECTOR)) {
            // project was added, save project name for after test cleanup
            addedProjects.add(projectName);
        }
    }

    public void filterProjectsTable(String projectName) {
        waitForElement(PROJECT_SEARCH_INPUT_SELECTOR);
        driver.findElement(PROJECT_SEARCH_INPUT_SELECTOR)
                .sendKeys(projectName);
        driver.findElement(DASH_PROJECT_SEARCH_SUBMIT_BUTTON_SELECTOR)
                .click();
    }

    public List<WebElement> getProjectTableRows() {
        return driver.findElements(DASH_PROJECT_TABLE_SELECTOR);
    }

    public String getProjectNameFromRow(WebElement webElement) {
        return webElement.findElement(DASH_PROJECT_TABLE_NAME_SELECTOR)
                .getText();
    }

    public boolean isProjectFilterActive() {
        return isElementShown(DASH_RESET_FILTER);
    }

    // ## CLEANUP METHODS ##
    public void removeAddedProjects() {
        if (isProjectFilterActive()) {
            resetFilters();
        }

        for (String addedProject : addedProjects) {
            filterProjectsTable(addedProject);
            // TODO
//            removeProjectsFromTable();
        }
    }

    /**
     * Removes all rows from projects table if any search filter is active.
     */
    private void removeProjectsFromTable() {
        waitForElement(DASH_RESET_FILTER);
        List<WebElement> projectTableRows = getProjectTableRows();
        for (WebElement element : projectTableRows) {
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
