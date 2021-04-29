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

import static edu.afts.rukovoditel.testframework.constants.Selectors.PROJECT_MODAL_SELECTOR;
import static edu.afts.rukovoditel.testframework.constants.Selectors.DASH_SEARCH_NOTE_SELECTOR;
import static edu.afts.rukovoditel.testframework.constants.Selectors.PROJECT_NAME_ERROR_CONTAINER_SELECTOR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectTest extends RukovoditelTestBase {

    private ProjectsPage fixture;

    @BeforeEach
    public void setup() {
        super.setup();
        fixture = new ProjectsPage(driver, wait);
        new LoginPage(driver, wait).loginUser();
    }

    @AfterEach
    public void cleanup() {
        try {
            fixture.removeProjectsFromTable();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.cleanup();
    }

    @Test // TODO FIX THIS
    void testProjectWithoutNameNotCreated() {
        // WHEN
        fixture.showAddProjectForm();
        fixture.setName("");
        fixture.saveProject();

        // THEN
        assertTrue(fixture.isElementShown(PROJECT_NAME_ERROR_CONTAINER_SELECTOR));
        assertTrue(fixture.isElementShown(PROJECT_MODAL_SELECTOR));
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
        fixture.filterProjectsTable(expectedUuid);
        fixture.waitForElement(DASH_SEARCH_NOTE_SELECTOR);

        // THEN
        List<WebElement> projectTableRows = fixture.getProjectTableRows();
        assertEquals(1, projectTableRows.size());
        assertEquals(expectedUuid, fixture.getProjectNameFromRow(projectTableRows.get(0)));
    }

}
