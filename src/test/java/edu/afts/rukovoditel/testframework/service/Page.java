package edu.afts.rukovoditel.testframework.service;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {

    protected ChromeDriver driver;
    protected WebDriverWait wait;

    public Page(ChromeDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void getPage(String url) {
        driver.get(url);
    }

    public boolean isElementShown(By selector) {
        try {
            return driver.findElement(selector).isDisplayed();
        } catch (NoSuchElementException e) {
            // element does not exist so it cannot be displayed
            return false;
        }
    }

    public void waitForElement(By selector) {
        wait.until(ExpectedConditions.presenceOfElementLocated(selector));
    }
}
