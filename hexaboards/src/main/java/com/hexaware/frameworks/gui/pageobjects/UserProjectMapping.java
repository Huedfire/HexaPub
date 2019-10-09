package com.hexaware.frameworks.gui.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserProjectMapping {

    WebDriver driver;

    public UserProjectMapping(WebDriver driver) {
        this.driver = driver;
    }


    By myProjectsButton = By.xpath("//span[@mattooltip=\"Projects you're part of.\"]");

    //aqui se cambia el nombre test_project por el nombre del proyecto que vas a utilizar o abrir
    public WebElement getMyProjectsButton() {
        return driver.findElement(myProjectsButton);
    }

    By clickOnProject = By.xpath("//*[contains(text(),\"Test_Project\")]");

    //aqui se cambia el numero depende del boton que vaya a agarrar (empieza en 0, que es el primer proyecto visible)
    public WebElement getClickOnProject() {
        return driver.findElement(clickOnProject);
    }

    By openButton = By.xpath("//div[@id=\"cdk-accordion-child-0\"]/div/div/button/span");

    //Burger button
    public WebElement getBurgerButton() {
        return driver.findElement(openButton);
    }

    //panel1
    public WebElement getPanel1(String panel) {
        return driver.findElement(By.xpath("//div[@class=\"projpanel\"]//mat-panel-title[contains(text(),\"" + panel + "\")]"));
    }
    //panel2
    public WebElement getPanel2(String panel) {
        return driver.findElement(By.xpath("//div[@class=\"projpanel\"]//mat-panel-title[contains(text(),\"" + panel + "\")]"));
    }


}
