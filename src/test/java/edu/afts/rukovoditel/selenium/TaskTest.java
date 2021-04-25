package edu.afts.rukovoditel.selenium;

import java.util.Date;
import java.util.UUID;

import edu.afts.rukovoditel.testframework.constants.*;
import edu.afts.rukovoditel.testframework.service.TasksPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.afts.rukovoditel.testframework.service.LoginPage;

import static edu.afts.rukovoditel.testframework.constants.Selectors.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskTest extends RukovoditelTestBase {

    private TasksPage fixture;

    @BeforeEach
    public void setup() {
        super.setup();
        fixture = new TasksPage(driver, wait);
        new LoginPage(driver, wait).loginUser();
    }

    @AfterEach
    public void cleanup() {
        fixture.cleanup();
        super.cleanup();
    }

    @Test
    void newTask() {

        // GIVEN
        String expectedName = "Name_" + UUID.randomUUID().toString() + "_" + java.time.LocalTime.now().toString();
        TaskType expectedType = TaskType.TASK;
        TaskStatus expectedStatus = TaskStatus.NEW;
        TaskPriority expectedPriority = TaskPriority.MEDIUM;
        String expectedDescription = "Description_" + UUID.randomUUID().toString();

        // show form
        fixture.showTaskPageForProject();
        assertTrue(fixture.isElementShown(TASK_PAGE_GRID_SELECTOR));
        fixture.showAddTaskForm();
        assertTrue(fixture.isElementShown(TASK_FORM_TITLE_SELECTOR));

        // fill form
        fixture.setType(expectedType);
        fixture.setName(expectedName);
        fixture.setStatus(expectedStatus);
        fixture.setPriority(expectedPriority);
        fixture.setDescription(expectedDescription);

        // save task
        fixture.saveTask(expectedName);
        assertFalse(fixture.isElementShown(TASK_FORM_TITLE_SELECTOR));
        assertTrue(fixture.isElementShown(TASK_PAGE_GRID_SELECTOR));
        fixture.showTaskInfoPage(expectedName);

        assertEquals(expectedName, fixture.getTaskNameString());
        assertEquals(expectedType.value(), fixture.getTaskTypeString());
        assertEquals(expectedPriority.value(), fixture.getTaskPriorityString());
        assertEquals(expectedStatus.value(), fixture.getTaskStatusString());
        assertEquals(expectedDescription, fixture.getTaskDescriptionString());

        fixture.removeTask();
        assertFalse(fixture.isElementShown(fixture.getTaskGridLinkSelectorByName(expectedName)));
    }

    @Test
    void newSevenTasks() {

    }

}
