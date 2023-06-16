package com.example.automation.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginSuiteTest {

    WebDriver driver;
    WebDriverWait wait;

    String url = "https://www.slot.com/es";

    String usuario = "test0023";
    String password = "test0023_qa";

    String expectedUrl = "https://www.slot.com/es/games";

    @Before
    public void SetUp(){
        WebDriverManager. chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);

        driver.navigate().to(url);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        wait = new WebDriverWait(driver,5);
        driver.get(url);
        driver.findElement(By.xpath("//*[@id=\"termsfeed-com---nb\"]/div/div[2]/button[1]")).click();

    }

    @Test
    public void validationLoginCorrect(){
        driver.findElement(By.xpath("//button[@id='letsplay-button']")).click();

        driver.findElement(By.xpath("//button[@id='mail-button']")).click();

        driver.findElement(By.xpath("//*[@id=\"alreadyHaveAccount\"]")).click();

        driver.findElement(By.xpath("//input[@id='userNameLogin']")).sendKeys(usuario);

        driver.findElement(By.xpath("//div[@clas='partial-modal-login-form']//input[@id='input-pwd']")).sendKeys(password);

        driver.findElement(By.xpath("//input[@id='modalLoginSubmitButton']")).click();

        try {
            wait.until(ExpectedConditions.urlToBe("https://www.slot.com/es/games"));
            String actualUrl = driver.getCurrentUrl();
            Assert.assertEquals("Error: The requested page is not displayed " + actualUrl, expectedUrl, actualUrl);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Error while waiting for URL: " + e.getMessage());
        }

    }

    @Test
    public void validationLoginError(){
        driver.findElement(By.xpath("//button[@id='letsplay-button']")).click();

        driver.findElement(By.xpath("//button[@id='mail-button']")).click();

        driver.findElement(By.xpath("//*[@id=\"alreadyHaveAccount\"]")).click();

        driver.findElement(By.xpath("//input[@id='userNameLogin']")).sendKeys(usuario);

        WebElement pass = driver.findElement(By.xpath("//div[@clas='partial-modal-login-form']//input[@id='input-pwd']"));
        pass.sendKeys("test22_qa");

        driver.findElement(By.xpath("//input[@id='modalLoginSubmitButton']")).click();

        WebElement errorLogin = driver.findElement(By.xpath("//span[@id='login-username-error']"));
        String errorLoginText = errorLogin.getText();
        System.out.println("Is show text: " + errorLoginText);

    }

    @After
    public void TearDown(){
        driver.quit();
    }
}



