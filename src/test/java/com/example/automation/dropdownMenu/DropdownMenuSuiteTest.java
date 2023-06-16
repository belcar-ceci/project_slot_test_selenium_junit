package com.example.automation.dropdownMenu;

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

import java.util.concurrent.TimeUnit;

public class DropdownMenuSuiteTest {
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
        //Step1
        driver.get(url);
        driver.findElement(By.xpath("//*[@id=\"termsfeed-com---nb\"]/div/div[2]/button[1]")).click();
    }

    @Test
    public void dropdownElementMenu(){
        driver.findElement(By.xpath("//button[@id='letsplay-button']")).click();

        driver.findElement(By.xpath("//button[@id='mail-button']")).click();

        driver.findElement(By.xpath("//*[@id=\"alreadyHaveAccount\"]")).click();

        driver.findElement(By.xpath("//input[@id='userNameLogin']")).sendKeys(usuario);

        driver.findElement(By.xpath("//div[@clas='partial-modal-login-form']//input[@id='input-pwd']")).sendKeys(password);

        driver.findElement(By.xpath("//input[@id='modalLoginSubmitButton']")).click();

        // Hacer clic en el elemento con XPath "//span[@class='c-buy-button__text']" para abrir el modal
        WebElement menuButton = driver.findElement(By.xpath("//div[@class='side-menu__icon']"));
        menuButton.click();

        WebElement sideNavIsShow = driver.findElement(By.xpath("//mat-list[@class='mat-list mat-list-base']"));
        sideNavIsShow.click();

        // Encontrar el elemento de "Términos y Condiciones" en el menú desplegable y mostrar el texto
        WebElement termsLink = driver.findElement(By.xpath("//p[@id='Términos y Condiciones']"));
        String termsText = termsLink.getText();
        System.out.println("Is show text : " + termsText);

    }

    @Test
    public void clickProfileUser(){

        driver.findElement(By.xpath("//button[@id='letsplay-button']")).click();

        driver.findElement(By.xpath("//button[@id='mail-button']")).click();

        driver.findElement(By.xpath("//*[@id=\"alreadyHaveAccount\"]")).click();

        driver.findElement(By.xpath("//input[@id='userNameLogin']")).sendKeys(usuario);

        driver.findElement(By.xpath("//div[@clas='partial-modal-login-form']//input[@id='input-pwd']")).sendKeys(password);

        driver.findElement(By.xpath("//input[@id='modalLoginSubmitButton']")).click();

        WebElement menuButton = driver.findElement(By.xpath("//div[@class='side-menu__icon']"));
        menuButton.click();


        WebElement clickOptionProfile = driver.findElement(By.xpath("//mat-list-item[@class='mat-list-item mat-focus-indicator mat-badge mat-badge-overlap mat-badge-above mat-badge-after mat-badge-medium mat-badge-hidden']//div[@class='mat-list-item-content']"));
        clickOptionProfile.click();
    }

    @After
    public void TearDown(){
        //step closure
        driver.quit();

    }
}
