package edu.afts.rukovoditel.selenium;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import edu.afts.rukovoditel.testframework.constants.TaskPriority;
import edu.afts.rukovoditel.testframework.constants.TaskStatus;
import edu.afts.rukovoditel.testframework.constants.TaskType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import edu.afts.rukovoditel.testframework.service.LoginPage;
import edu.afts.rukovoditel.testframework.service.TasksPage;
import edu.afts.rukovoditel.testframework.service.ProjectsPage;

import static edu.afts.rukovoditel.testframework.constants.Selectors.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TasksTest extends RukovoditelTestBase {

    private TasksPage fixture;

    @BeforeEach
    public void setup() {
        super.setup();
        fixture = new TasksPage(driver, wait);
        new LoginPage(driver, wait)
            .loginUser();
    }

    //@AfterEach
    public void cleanup() {
        super.cleanup();
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
