/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Training
 */
public class DemoPage {

    public static void main(String[] args) {

        WebDriver driver;
        WebElement element;

        System.setProperty("webdriver.chrome.driver", "c://selenium//chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {

            driver.navigate().to("https://bladimiro-hexaware.github.io/DemoPage/actions.html");
            //Enviar y validar que las contrase;as coincidan
            element = driver.findElement(By.name("password"));
            element.sendKeys("Hexaware");
            element = driver.findElement(By.name("verifyPass"));
            element.sendKeys("Hexaware");

            element = driver.findElement(By.id("submit"));
            element.click();

            element = driver.findElement(By.id("valid"));

            if (element.getText().equals("Password Matched")) {
                System.out.println("Es correcta");
            } else {
                System.out.println("Es incorrecta");
            }

            //Text modal
            element = driver.findElement(By.name("textArea"));
            element.sendKeys("Hexaware321");

            element = driver.findElement(By.xpath("/html/body/div[3]/div/div/button[2]"));
            element.click();

            //.btns button:last-child  con cssSelector
            // element = driver.findElement(By.xpath("//*[@type=\"button\" and text()=\"Close\"]"));
            //  element.click();
            //alertas
            driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_alert2");

            //switch to frame
            driver.switchTo().frame("iframeResult");

            element = driver.findElement(By.xpath("//button[text()=\"Try it\"]"));
            element.click();

            //imprimir y aceptar la alerta
            String alertText = driver.switchTo().alert().getText();
            System.out.println(alertText);

            driver.switchTo().alert().accept();

            //switch from windows
            driver.get("https://www.w3schools.com/jsref/met_win_alert.asp");
            element = driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/a"));
            element.click();

            //cambiar de pesta;as
            String currentWindow = driver.getWindowHandle();
            Set<String> allWindows = driver.getWindowHandles();

            allWindows.remove(currentWindow);

            for (String window : allWindows) {

                // driver.switchTo().window(window);
                if (!currentWindow.equals(window)) {
                    driver.switchTo().window(window);
                }
            }

            driver.switchTo().frame("iframeResult");

            element = driver.findElement(By.xpath("//button[text()=\"Try it\"]"));
            element.click();

            //imprimir y aceptar la alerta
            alertText = driver.switchTo().alert().getText();
            System.out.println(alertText);

            driver.switchTo().alert().accept();
            
            
            driver.quit();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
