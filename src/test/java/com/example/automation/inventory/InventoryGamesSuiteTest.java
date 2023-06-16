package com.example.automation.inventory;

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

public class InventoryGamesSuiteTest {
//ValidateGameSuiteTest
    WebDriver driver;
    WebDriverWait wait;

    String url = "https://www.slot.com/es";

    String usuario = "test0023";
    String password = "test0023_qa";

    String expectedUrl = "https://www.slot.com/es/games";

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
        //Step1
        driver.get(url);
        driver.findElement(By.xpath("//*[@id=\"termsfeed-com---nb\"]/div/div[2]/button[1]")).click();
        }

        @Test
        public void validateNumberCardGames(){
            driver.findElement(By.xpath("//button[@id='letsplay-button']")).click();

            driver.findElement(By.xpath("//button[@id='mail-button']")).click();

            driver.findElement(By.xpath("//*[@id=\"alreadyHaveAccount\"]")).click();

            //Step 2
            driver.findElement(By.xpath("//input[@id='userNameLogin']")).sendKeys(usuario);

            //Step 3
            driver.findElement(By.xpath("//div[@clas='partial-modal-login-form']//input[@id='input-pwd']")).sendKeys(password);

            driver.findElement(By.xpath("//input[@id='modalLoginSubmitButton']")).click();

            //Step 5
            int numProduct = driver.findElements(By.xpath("//slotcom-lobby-game-page[1]//slotcom-lobby-game")).size();
            System.out.println("The number of products displayed is: " + numProduct);
            if (numProduct == 6) {
                System.out.println("6 products are displayed.");
            } else {
                System.out.println("ERROR: 6 products have not been displayed..");
            }

        }

        @Test
        public void validateGameInInventory(){
            driver.findElement(By.xpath("//button[@id='letsplay-button']")).click();

            driver.findElement(By.xpath("//button[@id='mail-button']")).click();

            driver.findElement(By.xpath("//*[@id=\"alreadyHaveAccount\"]")).click();

            //Step 2
            driver.findElement(By.xpath("//input[@id='userNameLogin']")).sendKeys(usuario);

            //Step 3
            driver.findElement(By.xpath("//div[@clas='partial-modal-login-form']//input[@id='input-pwd']")).sendKeys(password);

            driver.findElement(By.xpath("//input[@id='modalLoginSubmitButton']")).click();

            try{
                WebElement inventoryProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//slotcom-lobby-game-page[1]//slotcom-lobby-game[1]")));
                // Validar que el producto está visible en el inventario
                Assert.assertTrue(inventoryProduct.isDisplayed());
                System.out.println("The product " + inventoryProduct + " has been found in inventory.");
            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail("Error: product has not been validated in inventory");
            }

        }

    @After
    public void TearDown(){
        //step closure
        driver.quit();

    }
    }
