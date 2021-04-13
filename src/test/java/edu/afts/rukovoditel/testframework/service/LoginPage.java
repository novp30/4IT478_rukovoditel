package edu.afts.rukovoditel.testframework.service;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static edu.afts.rukovoditel.testframework.constants.Selectors.LOGIN_LOGIN_BUTTON_SELECTOR;
import static edu.afts.rukovoditel.testframework.constants.Selectors.DASH_LOGOFF_LINK_SELECTOR;
import static edu.afts.rukovoditel.testframework.constants.Selectors.LOGIN_PASSWORD_INPUT_SELECTOR;
import static edu.afts.rukovoditel.testframework.constants.Selectors.LOGIN_USERNAME_INPUT_SELECTOR;
import static edu.afts.rukovoditel.testframework.constants.Selectors.DASH_USER_DROPDOWN_SELECTOR;

public class LoginPage extends Page {

    private static final String BASE_LOGIN_URI = "https://demoen.rukovoditel.space/index.php?module=users/login";
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "admin";

    public LoginPage(ChromeDriver driver, WebDriverWait wait) {
        super(driver, wait);
        super.getPage(BASE_LOGIN_URI);
    }

    public void loginUser() {
        loginUser(DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    public void loginUser(String username, String password) {
        driver.findElement(LOGIN_USERNAME_INPUT_SELECTOR)
                .sendKeys(username);

        driver.findElement(LOGIN_PASSWORD_INPUT_SELECTOR)
                .sendKeys(password);

        driver.findElement(LOGIN_LOGIN_BUTTON_SELECTOR)
                .click();
    }

    public boolean isLoggedIn() {
        return !driver.getCurrentUrl().equals(BASE_LOGIN_URI);
    }

    public void logoutUser() {
        wait.until(ExpectedConditions.presenceOfElementLocated(DASH_USER_DROPDOWN_SELECTOR));

        Actions actionsBuilder = new Actions(driver);
        actionsBuilder.moveToElement(driver.findElement(DASH_USER_DROPDOWN_SELECTOR))
                .click()
                .moveToElement(driver.findElement(DASH_LOGOFF_LINK_SELECTOR))
                .click();

        actionsBuilder
                .build()
                .perform();
    }
}
