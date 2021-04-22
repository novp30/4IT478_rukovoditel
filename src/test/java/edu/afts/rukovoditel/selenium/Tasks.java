package edu.afts.rukovoditel.selenium;

import java.util.List;
import java.util.UUID;

import edu.afts.rukovoditel.testframework.constants.*;
import edu.afts.rukovoditel.testframework.service.TasksPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import edu.afts.rukovoditel.testframework.service.LoginPage;
import edu.afts.rukovoditel.testframework.service.ProjectsPage;

import static edu.afts.rukovoditel.testframework.constants.Selectors.PROJECT_MODAL_SELECTOR;
import static edu.afts.rukovoditel.testframework.constants.Selectors.DASH_SEARCH_NOTE_SELECTOR;
import static edu.afts.rukovoditel.testframework.constants.Selectors.PROJECT_NAME_ERROR_CONTAINER_SELECTOR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Tasks extends RukovoditelTestBase {

    private TasksPage fixture;

    @BeforeEach
    public void setup() {
        super.setup();
        fixture = new TasksPage(driver, wait);
        new LoginPage(driver, wait).loginUser();
        //creating new project "your_name"? (preconditions)
    }

    @AfterEach
    public void cleanup() {
        fixture.removeAddedTasks();
        super.cleanup();
    }

    @Test
    void newTask() {
// GIVEN
        String expectedUuid = UUID.randomUUID().toString();
        TaskType expectedType = TaskType.TASK;
        TaskStatus expectedStatus = TaskStatus.NEW;
        TaskPriority expectedPriority = TaskPriority.MEDIUM;
        String expectedDescription = "It is a new task";

        // WHEN
        // show form
        fixture.showAddTaskForm();
        assertTrue(fixture.isElementShown(PROJECT_MODAL_SELECTOR));

        // fill form
        fixture.setType(expectedType);
        fixture.setName(expectedUuid);
        fixture.setStatus(expectedStatus);
        fixture.setPriority(expectedPriority);
        fixture.setDescription(expectedDescription);

        // save project
        fixture.saveTask();
        assertFalse(fixture.isElementShown(PROJECT_MODAL_SELECTOR));

        // filter table
        fixture.filterTasksTable(expectedUuid);
        fixture.waitForElement(DASH_SEARCH_NOTE_SELECTOR);

        // THEN
        List<WebElement> taskTableRows = fixture.getTaskTableRows();
        assertEquals(1, taskTableRows.size());
        assertEquals(expectedUuid, fixture.getTaskNameFromRow(taskTableRows.get(0)));
    }

    @Test
    void newSevenTasks() {

    }

}
