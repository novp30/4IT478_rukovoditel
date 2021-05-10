package edu.afts.rukovoditel.selenium;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import edu.afts.rukovoditel.testframework.constants.*;
import edu.afts.rukovoditel.testframework.service.ProjectsPage;
import edu.afts.rukovoditel.testframework.service.TasksPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.afts.rukovoditel.testframework.service.LoginPage;
import org.openqa.selenium.By;

import static edu.afts.rukovoditel.testframework.constants.Selectors.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskTest extends RukovoditelTestBase {

    private TasksPage fixture;
    private String projectName;

    @BeforeEach
    public void setup() {
        super.setup();
        new LoginPage(driver, wait).loginUser();
        fixture = new TasksPage(driver, wait);
        projectName = fixture.createTestProject();
    }

    @AfterEach
    public void cleanup() {
        fixture.removeTestProject(projectName);
        super.cleanup();
    }

    @Test
    void newTask() {
        // GIVEN
        String expectedName = "Name_" + UUID.randomUUID() + "_" + java.time.LocalTime.now().toString();
        TaskType expectedType = TaskType.TASK;
        TaskStatus expectedStatus = TaskStatus.NEW;
        TaskPriority expectedPriority = TaskPriority.MEDIUM;
        String expectedDescription = "Description_" + UUID.randomUUID();

        // show form
        fixture.showAddTaskForm();
        assertTrue(fixture.isElementShown(TASK_FORM_TITLE_SELECTOR));

        // fill form
        fixture.setType(expectedType);
        fixture.setName(expectedName);
        fixture.setStatus(expectedStatus);
        fixture.setPriority(expectedPriority);
        fixture.setDescription(expectedDescription);

        // save task
        fixture.saveTask();
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
        //given
        List<String> names = Arrays.asList("1", "2", "3", "3", "5", "6", "7");
        List<String> descriptions = Arrays.asList("descr", "descr", "descr", "descr", "descr", "descr", "descr");
        List<String> validStatuses = Arrays.asList("New", "Open", "Waiting");
        List<String> allStatuses = Arrays.asList("New", "Open", "Waiting", "Closed", "Canceled", "Done", "Paid");
        List<String> filtersToAdd = Arrays.asList("New", "Waiting");
        int expectedNumberOfTasks = validStatuses.size();
        //when
        for (int i=0; i<names.size();i++) {
            TaskStatus status = TaskStatus.values()[i];
            TaskPriority priority = TaskPriority.values()[1];
            TaskType type = TaskType.values()[0];
            fixture.addTask();
            fixture.waitForElement(NAME_INPUT_SELECTOR);
            fixture.setName(names.get(i));
            fixture.setType(type);
            fixture.setStatus(status);
            fixture.setDescription(descriptions.get(i));
            fixture.setPriority(priority);
            fixture.saveTask();
            fixture.waitForElement(ADD_TASK_BUTTON_SELECTOR);
        }
        driver.navigate()
            .refresh();

        fixture.waitForElement(By.cssSelector("td.fieldtype_action"));
        List<String> actualStatuses = fixture.getTaskStatusesFromTable();
        int actualNumberOfTasks = fixture.getTaskTableRows().size();

        //then
        assertTrue(fixture.areValidStatusesInTable(actualStatuses, validStatuses, actualNumberOfTasks, expectedNumberOfTasks));

        //when

        fixture.editFilterOptions(filtersToAdd);
        //then
        actualStatuses.clear();
        fixture.waitForElement(By.cssSelector("td.fieldtype_action"));
        actualStatuses = fixture.getTaskStatusesFromTable();
        actualNumberOfTasks = fixture.getTaskTableRows().size();

        assertTrue(fixture.areValidStatusesInTable(actualStatuses, filtersToAdd, actualNumberOfTasks, filtersToAdd.size()));

        //when
        fixture.deleteAllFilters();
        actualStatuses.clear();
        fixture.waitForElement(By.cssSelector("td.fieldtype_action"));
        actualStatuses = fixture.getTaskStatusesFromTable();
        actualNumberOfTasks = fixture.getTaskTableRows().size();


        //then
        assertTrue(fixture.areValidStatusesInTable(actualStatuses, allStatuses, actualNumberOfTasks, allStatuses.size()));

        //cleanup
        fixture.removeAddedTasks();
        fixture.addDefaultFilters();
    }
}
