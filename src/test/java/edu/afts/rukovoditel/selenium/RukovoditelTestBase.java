package edu.afts.rukovoditel.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class RukovoditelTestBase {

    protected ChromeDriver driver;
    protected WebDriverWait wait;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 7);
    }

    @AfterEach
    public void cleanup() {
        driver.close();
        driver.quit();
    }
}
