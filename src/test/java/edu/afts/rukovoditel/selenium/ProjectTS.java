package edu.afts.rukovoditel.selenium;

import edu.afts.rukovoditel.testframework.constants.ProjectPriority;
import edu.afts.rukovoditel.testframework.constants.ProjectStatus;
import edu.afts.rukovoditel.testframework.service.LoginPage;
import edu.afts.rukovoditel.testframework.service.ProjectsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import static edu.afts.rukovoditel.testframework.constants.Selectors.DEFAULT_PROJECT_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// - TC: Project with status New, priority High and filled start date as today is created.
// Verify that there is new row in project table. Delete project after test.
//

class ProjectTS extends RukovoditelTestBase {

    private ProjectsPage projectsPage;

    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeEach
    public void setUp() {
        super.setup();
        LoginPage loginPage = new LoginPage(driver, wait); // TODO add open()
        loginPage.loginUser();

        projectsPage = new ProjectsPage(driver, wait);
        projectsPage.open();
    }

    @Test
    void projectTC() throws Exception { // TODO rename
        // GIVEN
        projectsPage.filterProjectsTable(DEFAULT_PROJECT_NAME);
        int expectedProjectCount = projectsPage.getProjectsCount() + 1;

        // WHEN
        projectsPage.showAddProjectForm();
        projectsPage.setName(DEFAULT_PROJECT_NAME);
        projectsPage.setPriority(ProjectPriority.HIGH);
        projectsPage.setTodayStartDate();
        projectsPage.setStatus(ProjectStatus.NEW);
        projectsPage.saveProject();
        projectsPage.getPage(ProjectsPage.BASE_PROJECTS_URI);

        // THEN
        assertEquals("Rukovoditel | Projects", driver.getTitle());
        projectsPage.filterProjectsTable(DEFAULT_PROJECT_NAME);
        int actualProjectCount = projectsPage.getProjectsCount();
        assertEquals(expectedProjectCount, actualProjectCount);

        // CLEAN AFTER
        projectsPage.removeProjectsFromTable();
    }

    @AfterEach
    public void tearDown() {
        super.setup();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}
