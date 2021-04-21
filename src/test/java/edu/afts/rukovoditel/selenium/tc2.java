package edu.afts.rukovoditel.selenium;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class tc2 {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void tc2() throws Exception {
        driver.get("http://digit107.wwwnlss4.a2hosted.com/rukovoditel/index.php?module=users/login");
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("rukovoditel");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("vse456ru");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.xpath("//li[4]/a/span")).click();
        driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
        new Select(driver.findElement(By.id("fields_156"))).selectByVisibleText("High");
        driver.findElement(By.id("fields_158")).click();
        driver.findElement(By.id("fields_158")).clear();
        driver.findElement(By.id("fields_158")).sendKeys("Testik");
        driver.findElement(By.xpath("//div[@id='fields_159_rendered_value']/div/span/button/i")).click();
        driver.findElement(By.xpath("//tr[4]/td[4]")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.linkText("Testik")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Projects')]")).click();
        driver.findElement(By.xpath("//div[@id='slimScroll']/table/tbody/tr[3]/td[2]/a/i")).click();
        driver.findElement(By.id("delete_confirm")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.close();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}