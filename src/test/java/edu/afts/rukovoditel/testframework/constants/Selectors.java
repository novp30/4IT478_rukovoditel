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

    //tasks
    public static final By ADD_TASK_BUTTON_SELECTOR = By.cssSelector(".btn.btn-primary");
    public static final By TYPE_SELECT_SELECTOR = By.cssSelector("[class='form-group form-group-167'] [class='col-md-9'] div [data-placeholder='Select some options']");
    public static final By NAME_INPUT_SELECTOR = By.cssSelector("input[id='fields_168']");
    public static final By STATUS_INPUT_SELECTOR = By.cssSelector("[class='form-group form-group-169'] [class='col-md-9'] div [data-placeholder='Select some options']");
    public static final By PRIORITY_SELECT_SELECTOR = By.cssSelector("[class='form-group form-group-170'] [class='col-md-9'] div [data-placeholder='Select some options']");
    public static final By DESCRIPTION_IFRAME_SELECTOR = By.xpath("#cke_1_contents > iframe");
    public static final By DESCRIPTION_EDITOR_SELECTOR = By.cssSelector("body");
    public static final By SAVE_BUTTON_SELECTOR = By.cssSelector(".btn.btn-primary.btn-primary-modal-action");
    public static final By INFO_BUTTON_SELECTOR = By.cssSelector("a[title='Info']");
    public static final By TASK_DETAIL_TYPE_SELECTOR = By.cssSelector(".form-group-167 > td > div");
    public static final By TASK_DETAIL_STATUS_SELECTOR = By.cssSelector(".form-group-169 > td > div");
    public static final By TASK_DETAIL_PRIORITY_SELECTOR = By.cssSelector(".form-group-170 > td > div");
    public static final By TASK_DETAIL_DESCRIPTION_SELECTOR = By.cssSelector(".content_box_content.fieldtype_textarea_wysiwyg");
    public static final By REMOVE_ALL_FILTERS_BUTTON_SELECTOR = By.cssSelector("a[title='Remove All Filters']");
    public static final By EDIT_DEFAULT_FILTERS_SELECTOR = By.cssSelector(".filters-preview-box-heading");
    public static final By FILTER_SECTION_DROPDOWN_SELECTOR = By.cssSelector(".btn.btn-users-filters.dropdown-toggle > .fa.fa-angle-down");
    public static final By ADD_DEFAULT_FILTERS_BUTTON = By.cssSelector("div.btn-group.open ul li a:not([class*='link-to-modalbox'])");
    public static final By DELETE_FILTER_OPTIONS = By.cssSelector("li > .search-choice-close");
    public static final By FILTER_STATUS_SELECT_SELECTOR = By.cssSelector("select[id='values']");
    public static final By FILTER_EDIT_SAVE_BUTTON_SELECTOR = By.cssSelector(".btn.btn-primary.btn-primary-modal-action");
    public static final By BULK_SELECT_ALL_CHECKBOX_SELECTOR = By.cssSelector("input#select_all_items");
    public static final By BULK_ACTIONS_OPTIONS_DROPDOWN_SELECTOR = By.cssSelector(".btn-group > .btn.btn-default.dropdown-toggle");
    public static final By BULK_ACTIONS_OPTIONS_DROPDOWN_SELECTOR_EXPANDED = By.cssSelector(".btn-group > .btn.btn-default.dropdown-toggle:not([aria-expanded='false'])");
    public static final By BULK_ACTIONS_DELETE_OPTION_SELECTOR = By.cssSelector("li:nth-of-type(2) > .link-to-modalbox");
    public static final By BULK_ACTIONS_DELETE_OPTION_CONFIRM_SELECTOR = By.cssSelector(".btn.btn-primary.btn-primary-modal-action");
    public static final By TASK_TABLE_ROWS_SELECTOR = By.cssSelector("tbody > tr");

}
