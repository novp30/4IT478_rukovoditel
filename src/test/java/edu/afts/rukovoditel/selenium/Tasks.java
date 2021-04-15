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

class Tasks extends RukovoditelTestBase {

    private ProjectsPage fixture;

    @BeforeEach
    public void setup() {
        super.setup();
        fixture = new ProjectsPage(driver, wait);
        new LoginPage(driver, wait).loginUser();
        //creating new project "yourname"? (preconditions)
    }

    @AfterEach
    public void cleanup() {
        fixture.removeAddedProjects();
        super.cleanup();
    }

    @Test
    void newTask() {

    }

    @Test
    void newSevenTasks() {

    }

}
