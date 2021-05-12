package edu.afts.rukovoditel.selenium;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import edu.afts.rukovoditel.testframework.constants.ProjectPriority;
import edu.afts.rukovoditel.testframework.constants.ProjectStatus;
import edu.afts.rukovoditel.testframework.service.LoginPage;
import edu.afts.rukovoditel.testframework.service.ProjectsPage;

import static edu.afts.rukovoditel.testframework.constants.Selectors.Dashboard.*;
import static edu.afts.rukovoditel.testframework.constants.Selectors.Project.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectTest extends RukovoditelTestBase {

    private ProjectsPage fixture;
    private boolean removeAddedData;

    @BeforeEach
    public void setup() {
        super.setup();
        new LoginPage(driver, wait).loginUser();
        fixture = new ProjectsPage(driver, wait);
        fixture.open();
        fixture.resetFilters();
        removeAddedData = true;
    }

    @AfterEach
    public void cleanup() {
        if (removeAddedData) {
            try {
                fixture.removeProjectsFromTable();
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        super.cleanup();
    }

    @Test
    void testProjectWithoutNameNotCreated() {
        // SETUP
        removeAddedData = false;
        // WHEN
        fixture.showAddProjectForm();
        fixture.setName("");
        fixture.saveProject();

        // THEN
        assertTrue(fixture.isElementShown(PROJECT_NAME_ERROR_CONTAINER_SELECTOR));
    }

    @Test
    void testCreateProjectFillRequired() {
        // GIVEN
        String expectedUuid = UUID.randomUUID().toString();
        ProjectStatus expectedStatus = ProjectStatus.NEW;
        ProjectPriority expectedPriority = ProjectPriority.HIGH;

        // WHEN
        // show form
        fixture.showAddProjectForm();
        assertTrue(fixture.isElementShown(PROJECT_MODAL_SELECTOR));

        // fill form
        fixture.setStatus(expectedStatus);
        fixture.setPriority(expectedPriority);
        fixture.setTodayStartDate();
        fixture.setName(expectedUuid);

        // save project
        fixture.saveProject();
        assertFalse(fixture.isElementShown(PROJECT_MODAL_SELECTOR));

        // filter table
        fixture.open();
        fixture.filterProjectsTable(expectedUuid);
        fixture.waitForElement(DASH_SEARCH_NOTE_SELECTOR);

        // THEN
        List<WebElement> projectTableRows = fixture.getProjectTableRows();
        assertEquals(1, projectTableRows.size());
        assertEquals(expectedUuid, fixture.getProjectNameFromRow(projectTableRows.get(0)));
    }
}
