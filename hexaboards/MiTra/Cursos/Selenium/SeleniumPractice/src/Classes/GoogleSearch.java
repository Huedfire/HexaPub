package Classes;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Training
 */
public class GoogleSearch {

    public static void main(String[] args) {

        WebDriver driver;
        WebElement element;

        System.setProperty("webdriver.chrome.driver", "c://selenium//chromedriver.exe");

        driver = new ChromeDriver();

        //Acciones de Selenium
        //get
        // driver.get("http://www.google.com");
        //navigate
        driver.navigate().to("http://www.google.com");

        element = driver.findElement(By.name("q"));
        element.sendKeys("Hexaware");
        element.sendKeys(Keys.ENTER);

        element = driver.findElement(By.partialLinkText("Hexaware - IT, BPO, Consulting and Next-Generation Services"));
        element.click();

    

    }
}
