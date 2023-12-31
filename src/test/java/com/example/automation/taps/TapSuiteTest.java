package com.example.automation.taps;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TapSuiteTest {

    WebDriver driver;
    WebDriverWait wait;

    String url = "https://www.slot.com/es";

    String usuario = "test0023";
    String password = "test0023_qa";

    //String expectedUrl = "https://www.slot.com/es/games";

    @Before
    public void SetUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);

        driver.navigate().to(url);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 5);
        driver.get(url);
        driver.findElement(By.xpath("//*[@id=\"termsfeed-com---nb\"]/div/div[2]/button[1]")).click();
    }

    @Test
    public void validateTapsInHeader() {
        driver.findElement(By.xpath("//button[@id='letsplay-button']")).click();

        driver.findElement(By.xpath("//button[@id='mail-button']")).click();

        driver.findElement(By.xpath("//*[@id=\"alreadyHaveAccount\"]")).click();

        driver.findElement(By.xpath("//input[@id='userNameLogin']")).sendKeys(usuario);

        driver.findElement(By.xpath("//div[@clas='partial-modal-login-form']//input[@id='input-pwd']")).sendKeys(password);

        driver.findElement(By.xpath("//input[@id='modalLoginSubmitButton']")).click();

        WebElement headerElement = driver.findElement(By.className("c-slotcom-header"));

        List<WebElement> headerElements = headerElement.findElements(By.xpath(".//*"));

        Set<String> uniqueTexts = new HashSet<>();

        for (WebElement element : headerElements) {
            String text = element.getText().trim();
            if (!text.isEmpty()) {
                uniqueTexts.add(text);
            }
        }

        System.out.println("Unique header texts:");
        for (String text : uniqueTexts) {
            System.out.println(text);
        }

    }

    @Test
    public void validateBuyTap(){
        driver.findElement(By.xpath("//button[@id='letsplay-button']")).click();

        driver.findElement(By.xpath("//button[@id='mail-button']")).click();

        driver.findElement(By.xpath("//*[@id=\"alreadyHaveAccount\"]")).click();

        driver.findElement(By.xpath("//input[@id='userNameLogin']")).sendKeys(usuario);

        driver.findElement(By.xpath("//div[@clas='partial-modal-login-form']//input[@id='input-pwd']")).sendKeys(password);

        driver.findElement(By.xpath("//input[@id='modalLoginSubmitButton']")).click();

           WebElement buyButton = driver.findElement(By.xpath("//span[@class='c-buy-button__text']"));
           buyButton.click();

           WebElement backButton = driver.findElement(By.xpath("//div[@id='shop-back-button']"));
           backButton.click();

           System.out.println("It has been returned after clicking the 'Return' button");

    }



    @After
    public void TearDown(){
        driver.quit();

    }
}
