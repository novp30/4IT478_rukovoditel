package edu.afts.rukovoditel.selenium;
import java.util.concurrent.TimeUnit;
import edu.afts.rukovoditel.testframework.constants.ProjectPriority;
import edu.afts.rukovoditel.testframework.constants.ProjectStatus;
import edu.afts.rukovoditel.testframework.service.LoginPage;
import edu.afts.rukovoditel.testframework.service.ProjectsPage;
import org.junit.*;
import static edu.afts.rukovoditel.testframework.constants.Selectors.DEFAULT_PROJECT_NAME;
import static edu.afts.rukovoditel.testframework.constants.Selectors.LOGIN_LOGIN_BUTTON_SELECTOR;
import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


// - TC: Project with status New, priority High and filled start date as today is created.
// Verify that there is new row in project table. Delete project after test.
//

public class ProjectTC {
    private ChromeDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private WebDriverWait wait = null;

    @BeforeEach
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
    @Test
    public void tc2() throws Exception {

        driver.get("http://digit107.wwwnlss4.a2hosted.com/rukovoditel/index.php?module=users/login");
        LoginPage page = new LoginPage(driver, wait);
        page.loginUser();
        assertEquals("Rukovoditel | Dashboard", driver.getTitle());
        int count = 0;
        //go to projects
        ProjectsPage projectsPage = new ProjectsPage(driver, wait);
        projectsPage.filterProjectsTable(DEFAULT_PROJECT_NAME);
        count = projectsPage.getProjectsCount();
        projectsPage.showAddProjectForm();
        projectsPage.setName(DEFAULT_PROJECT_NAME);
        projectsPage.setPriority(ProjectPriority.HIGH);
        projectsPage.setTodayStartDate();
        projectsPage.setStatus(ProjectStatus.NEW);
        projectsPage.saveProject();
        projectsPage.getPage(ProjectsPage.BASE_DASHBOARD_URI);
        assertEquals("Rukovoditel | Projects", driver.getTitle());
        projectsPage.filterProjectsTable(DEFAULT_PROJECT_NAME);
        int newCount = projectsPage.getProjectsCount();
        Thread.sleep(1000);
        assertEquals(count+1, newCount);
        projectsPage.removeProjectsFromTable();

    }


}