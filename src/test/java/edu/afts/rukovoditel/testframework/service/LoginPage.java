package edu.afts.rukovoditel.testframework.service;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static edu.afts.rukovoditel.testframework.constants.Selectors.Dashboard.*;
import static edu.afts.rukovoditel.testframework.constants.Selectors.Login.*;

public class LoginPage extends Page {

    private static final String BASE_LOGIN_URI = BASE_PATH + "/index.php?module=users/login";
    private static final String DEFAULT_USERNAME = "rukovoditel";
    private static final String DEFAULT_PASSWORD = "vse456ru";

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
