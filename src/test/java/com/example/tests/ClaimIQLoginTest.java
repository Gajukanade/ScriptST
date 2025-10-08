package com.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import java.time.Duration;

public class ClaimIQLoginTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String URL = "https://claimiq-sbx.vigorus.ai/login";

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // For CI runner use headless:
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void openLoginPage() {
        driver.get(URL);

        // wait for a form or input to appear
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']")),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form")),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input"))
        ));

        // basic sanity: ensure the page contains 'login' or form element
        String title = driver.getTitle();
        System.out.println("Page title: " + title);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
