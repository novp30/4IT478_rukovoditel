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
    public static final By DASH_PROJECT_SEARCH_SUBMIT_BUTTON_SELECTOR =
            By.cssSelector(".listing-search-form button[title='Search']");
    public static final By DASH_PROJECT_TABLE_SELECTOR = By.cssSelector("#entity_items_listing66_21 tbody tr");
    public static final By DASH_SEARCH_NOTE_SELECTOR = By.cssSelector(".search-notes");

    // project info page
    public static final By PROJECT_INFO_MORE_ACTIONS_DROPDOWN = By.cssSelector("div.prolet-body-actions > ul > li:nth-child(2) > div.btn-group");
    public static final By PROJECT_INFO_DROPDOWN_DELETE_BUTTON = By.cssSelector("div.prolet-body-actions > ul > li:nth-child(2) > div.btn-group > ul > li:nth-of-type(2) > a");
    public static final By PROJECT_INFO_DELETE_CONFIRM_CHECKBOX = By.cssSelector("#delete_confirm");
    public static final By PROJECT_INFO_DELETE_FINAL_BUTTON = By.cssSelector(".modal-footer .btn-primary-modal-action");

    // TASKS
    public static final By TASK_FORM_TITLE_SELECTOR = By.xpath("//h4[contains(text(), 'Task Info')]");
    public static final By TASK_PAGE_GRID_SELECTOR = By.xpath("//div[contains(text(), 'Action')]");
    public static final By TASK_PAGE_ADD_TASK_BUTTON_SELECTOR = By.xpath("//button[contains(text(), 'Add Task')]");
    public static final By TASK_INFO_SELECTOR = By.xpath("//h4[contains(text(), 'Info')]");
    public static final By TASK_DELETE_SUBMIT_BUTTON_SELECTOR = By.xpath("//button[contains(text(), 'Delete')]");
    public static final By DASH_ADD_PROJECT_BUTTON_SELECTOR = By.cssSelector(".entitly-listing-buttons-left > button");
    public static final By PROJECT_SEARCH_INPUT_SELECTOR = By.id("entity_items_listing66_21_search_keywords");
    public static final By DASH_DELETE_ALL_SELECTED = By.id("select_all_items");
    public static final By DASH_DROPDOWN_SELECTED =  By.xpath("(.//*[@class=\"entitly-listing-buttons-left\"]//button)[2]");
    public static final By DASH_DELETE_SELECTED = By.cssSelector(":nth-child(2) > .link-to-modalbox");
    public static final By DASH_DELETE_SELECTED_BUTTON = By.cssSelector("div.modal-footer > .btn-primary-modal-action");
    public static final By ADD_TASK_BUTTON_SELECTOR = By.cssSelector(".btn.btn-primary");
    public static final By TYPE_SELECT_SELECTOR = By.cssSelector("[class='form-group form-group-167'] [class='col-md-9'] div [data-placeholder='Select some options']");
    public static final By NAME_INPUT_SELECTOR = By.cssSelector("input[id='fields_168']");
    public static final By STATUS_INPUT_SELECTOR = By.cssSelector("[class='form-group form-group-169'] [class='col-md-9'] div [data-placeholder='Select some options']");
    public static final By PRIORITY_SELECT_SELECTOR = By.cssSelector("[class='form-group form-group-170'] [class='col-md-9'] div [data-placeholder='Select some options']");
    public static final By SAVE_BUTTON_SELECTOR = By.cssSelector(".btn.btn-primary.btn-primary-modal-action");
    public static final By REMOVE_ALL_FILTERS_BUTTON_SELECTOR = By.cssSelector("a[title='Remove All Filters']");
    public static final By EDIT_DEFAULT_FILTERS_SELECTOR = By.cssSelector(".filters-preview-box-heading");
    public static final By FILTER_SECTION_DROPDOWN_SELECTOR = By.cssSelector(".btn.btn-users-filters.dropdown-toggle > .fa.fa-angle-down");
    public static final By ADD_DEFAULT_FILTERS_BUTTON = By.cssSelector("div.btn-group.open ul li a:not([class*='link-to-modalbox'])");
    public static final By DELETE_FILTER_OPTIONS = By.cssSelector("li > .search-choice-close");
    public static final By FILTER_STATUS_SELECT_SELECTOR = By.cssSelector("select[id='values']");
    public static final By FILTER_EDIT_SAVE_BUTTON_SELECTOR = By.cssSelector(".btn.btn-primary.btn-primary-modal-action");
    public static final By BULK_SELECT_ALL_CHECKBOX_SELECTOR = By.cssSelector("input#select_all_items");
    public static final By BULK_ACTIONS_OPTIONS_DROPDOWN_SELECTOR = By.cssSelector(".btn-group > .btn.btn-default.dropdown-toggle");
    public static final By BULK_ACTIONS_DELETE_OPTION_SELECTOR = By.cssSelector("li:nth-of-type(2) > .link-to-modalbox");
    public static final By BULK_ACTIONS_DELETE_OPTION_CONFIRM_SELECTOR = By.cssSelector(".btn.btn-primary.btn-primary-modal-action");
    public static final By TASK_TABLE_ROWS_SELECTOR = By.cssSelector("tbody > tr");

}
