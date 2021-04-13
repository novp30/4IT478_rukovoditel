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
//    public static final By DASH_ADD_PROJECT_BUTTON_SELECTOR = By.cssSelector(""); TODO
    public static final By PROJECT_SEARCH_INPUT_SELECTOR = By.id("entity_items_listing69_21_search_keywords");

    // DELETE MODAL
    public static final By DELETE_MODAL_SELECTOR = By.xpath("//h4[contains(text(), 'Delete?')]");
    public static final By DELETE_CONFIRM_CHECKBOX_SELECTOR = By.cssSelector("input[@id='delete_confirm']");
    public static final By DELETE_SUBMIT_BUTTON_SELECTOR = By.xpath("//button[contains(text(), 'Delete')]");


}
