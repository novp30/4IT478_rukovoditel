package edu.afts.rukovoditel.selenium;

import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class RukovoditelTestBase {

    @Value("${chrome.path:}")
    private String chromePath;

    protected ChromeDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setup() {
        prepareChromeDriver();
        wait = new WebDriverWait(driver, 1);
    }

    @AfterEach
    protected void cleanup() {
        driver.quit();
    }

    private void prepareChromeDriver() {
        setChromeDriverProperty();
        ChromeOptions chromeOptions = new ChromeOptions();
        if (!chromePath.isEmpty()) {
            chromeOptions.setBinary(chromePath);
        }
        driver = new ChromeDriver(chromeOptions);
    }

    private void setChromeDriverProperty() {
        if (SystemUtils.IS_OS_LINUX)
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/unix/chromedriver");

        if (SystemUtils.IS_OS_WINDOWS)
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/win/chromedriver.exe");

        if (SystemUtils.IS_OS_MAC)
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/mac/chromedriver");
    }

}
