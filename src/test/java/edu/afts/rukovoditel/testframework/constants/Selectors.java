package edu.afts.rukovoditel.testframework.constants;

import org.openqa.selenium.By;

public class Selectors {

    // LOGIN PAGE
    public static final By LOGIN_USERNAME_INPUT_SELECTOR = By.cssSelector("#login_form input[name='username']");
    public static final By LOGIN_PASSWORD_INPUT_SELECTOR = By.cssSelector("#login_form input[name='password']");
    public static final By LOGIN_LOGIN_BUTTON_SELECTOR = By.cssSelector("#login_form button[type='submit']");

    // PROJECT MODAL
    public static final By PROJECT_MODAL_SELECTOR = By.xpath("//h4[contains(text(), 'Project Info')]");
    public static final By PROJECT_NAME_INPUT_SELECTOR = By.id("fields_158");
    public static final By PROJECT_PRIORITY_SELECT_SELECTOR = By.id("fields_156");
    public static final By PROJECT_STATUS_SELECT_SELECTOR = By.id("fields_157");
    public static final By PROJECT_NAME_ERROR_CONTAINER_SELECTOR = By.id("fields_158-error");
    public static final By PROJECT_DATE_SET_SELECTOR = By.cssSelector(".date-set");
    public static final By PROJECT_DATE_ACTIVE_DAY_SELECTOR = By.xpath("//td[@class='active day']");
    public static final By PROJECT_SUBMIT_BUTTON_SELECTOR = By.cssSelector("button[type='submit']");

    // DASHBOARD PAGE
    public static final By DASH_RESET_FILTER = By.cssSelector(".reset_search");
    // user menu
    public static final By DASH_USER_DROPDOWN_SELECTOR = By.cssSelector("li[class='dropdown user']");
    public static final By DASH_LOGOFF_LINK_SELECTOR = By.xpath("//a[contains(text(), ' Logoff')]");
    // project
    public static final By DASH_PROJECT_TABLE_NAME_SELECTOR = By.cssSelector(".item_heading_link");
    public static final By DASH_PROJECT_REMOVE_BUTTON_SELECTOR = By.cssSelector("a[title='Delete']");
    public static final By DASH_PROJECT_SEARCH_SUBMIT_BUTTON_SELECTOR =
            By.cssSelector(".listing-search-form button[title='Search']");
    public static final By DASH_PROJECT_TABLE_SELECTOR = By.cssSelector("#entity_items_listing69_21 tbody tr");
    public static final By DASH_SEARCH_NOTE_SELECTOR = By.cssSelector(".search-notes");
//    public static final By DASH_ADD_PROJECT_BUTTON_SELECTOR = By.cssSelector(".entitly-listing-buttons-left .btn-primary");
//    public static final By PROJECT_SEARCH_INPUT_SELECTOR = By.id("entity_items_listing69_21_search_keywords");

    // TASKS

    public static final By PROJECT_PAGE_GRID_SELECTOR = By.xpath("//div[contains(text(), 'Action')]");
    public static final By PROJECT_YOURNAME_LINK_SELECTOR = By.xpath("//a[contains(text(), 'yourname')]");
    public static final By TASK_FORM_TITLE_SELECTOR = By.xpath("//h4[contains(text(), 'Task Info')]");
    ////
    public static final By TASK_PAGE_GRID_SELECTOR = By.xpath("//div[contains(text(), 'Action')]");
    public static final By TASK_PAGE_ADD_TASK_BUTTON_SELECTOR = By.xpath("//button[contains(text(), 'Add Task')]");
    public static final By TASK_PAGE_ADD_TASK_BUTTON_SELECTOR_ALTERNATIVE =
            By.cssSelector(".entitly-listing-buttons-left button[title='Add Task']");
    public static final By TASK_NAME_INPUT_SELECTOR = By.id("fields_168");
    public static final By TASK_PRIORITY_SELECT_SELECTOR = By.id("fields_170");
    public static final By TASK_STATUS_SELECT_SELECTOR = By.id("fields_169");
    public static final By TASK_TYPE_SELECT_SELECTOR = By.id("fields_167");
    public static final By TASK_SUBMIT_BUTTON_SELECTOR = By.cssSelector("button[type='submit']");
    public static final By TASK_INFO_SELECTOR = By.xpath("//h4[contains(text(), 'Info')]");
    public static final By TASK_DELETE_SUBMIT_BUTTON_SELECTOR = By.xpath("//button[contains(text(), 'Delete')]");

    // DELETE MODAL
    public static final By DELETE_MODAL_SELECTOR = By.xpath("//h4[contains(text(), 'Delete?')]");
    public static final By DELETE_CONFIRM_CHECKBOX_SELECTOR = By.cssSelector("input[@id='delete_confirm']");
    public static final By DELETE_SUBMIT_BUTTON_SELECTOR = By.xpath("//button[contains(text(), 'Delete')]");


}
