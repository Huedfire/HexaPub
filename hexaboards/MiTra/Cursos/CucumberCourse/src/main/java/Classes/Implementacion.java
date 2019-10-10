package Classes;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Implementacion {
    WebDriver driver;
    WebElement element;
    JavascriptExecutor js;


    @Given("^El usuario se encuentra en la pagina https://www\\.ultimateqa\\.com/filling-out-forms/$")
    public void el_usuario_se_encuentra_en_la_pagina_https_www_ultimateqa_com_filling_out_forms() {

        // Write code here that turns the phrase above into concrete actions


        System.setProperty("webdriver.chrome.driver", "c://selenium//chromedriver.exe");
        ChromeOptions option = new ChromeOptions();

        driver = new ChromeDriver();

        driver.navigate().to("https://www.ultimateqa.com/filling-out-forms/");


    }


    @When("^El usuario presiona el boton submit sin llenar ningun otro campo$")
    public void el_usuario_presiona_el_boton_submit_sin_llenar_ningun_otro_campo() {

        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.xpath("//div[@id='et_pb_contact_form_1']/div[2]/form/div/button")).click();

    }

    @Then("^La aplicacion muestra un mensaje donde se menciona que debe de llenar los campos$")
    public void la_aplicacion_muestra_un_mensaje_donde_se_menciona_que_debe_de_llenar_los_campos() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
       String text = driver.findElement(By.xpath("//*[@id=\"et_pb_contact_form_1\"]/div[1]/p")).getText();
       System.out.println(text);

       switch(text){
           case "Please, fill in the following fields:":
               System.out.println("Exito");
               break;

           default: System.out.println("Error");
                   break;

       }

    }

    @When("^El usuario coloca su nombre en el campo nombre$")
    public void el_usuario_coloca_su_nombre_en_el_campo_nombre() {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.id("et_pb_contact_name_1")).sendKeys("Hugo Jacobo");
    }

    @When("^El usuario coloca una descripcion$")
    public void el_usuario_coloca_una_descripcion() {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.id("et_pb_contact_message_1")).sendKeys("Mensaje de prueba");
    }

    @When("^El usuario coloca el resultado$")
    public void el_usuario_coloca_el_resultado() {
        // Write code here that turns the phrase above into concrete actions
       String text = driver.findElement(By.xpath("//*[@id=\"et_pb_contact_form_1\"]/div[2]/form/div/div/p/span")).getText();
        System.out.print(text);


        for(int i=0; i<text.length();i++){

        }



    }

    @Then("^La aplicacion muestra un mensaje de registro exitoso$")
    public void la_aplicacion_muestra_un_mensaje_de_registro_exitoso() {
        // Write code here that turns the phrase above into concrete actions

    }




}
