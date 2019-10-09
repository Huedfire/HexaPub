package com.hexaware.apiscripts.login;

import com.hexaware.frameworks.api.ApiFramework;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;


public class Login {
    String filepath ;
    String jsonpath ;
    Properties prop=new Properties();
    InputStream input ;
    String uri;
    ApiFramework r = new ApiFramework();
    String Username;
    String Password;
    String Action;
    String ExpectedCode;
    String request;
    String parameter;
    Response response = null;
    ArrayList<String> dataList;
    ArrayList<Object> list = new ArrayList<Object>();
    String[] parts;

    @BeforeMethod(groups = {"logintest"})
    public void getDataListName() throws IOException {
        input = new FileInputStream("C:\\Users\\Training\\HexaboardAutomationTest\\hexaboards\\conf.txt");
        prop.load(input);
        filepath = prop.getProperty("DataFile");
        jsonpath = prop.getProperty("JsonLog");
        uri = prop.getProperty("URI");
        RestAssured.baseURI = uri;
        dataList = r.readExcel(filepath, 0);
    }

    //scenario1 description: The request is sent with the correct user data
    @Test(groups = {"logintest"})
    public void scenario1(){
        parts = r.turnArray(dataList,1);
        Username = parts[0];
        //System.out.println(Username);
        Password = parts[1];
        Action = parts[2];
        parameter = parts[parts.length-2];
        ExpectedCode = parts[parts.length - 1];
        //Username   Password   Action Parameters Expected Code
        request = "{\"username\":\"" + Username + "\",\"password\":\""+Password+"\"}";
        //Username   Password   Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).post(parameter);
            list.add(r.getDataReport(request,response.getBody().asString(),response.getStatusCode(),parts,parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //scenario2 description: The request is sent with a correct username and wrong password
    @Test(groups = {"logintest"})
    public void scenario2(){
        parts = r.turnArray(dataList,2);
        Username = parts[0];
        Password = parts[1];
        Action = parts[2];
        parameter = parts[parts.length-2];
        ExpectedCode = parts[parts.length - 1];
        //Username   Password   Action Parameters Expected Code
        request = "{\"username\":\"" + Username + "\",\"password\":\""+Password+"\"}";
        //Username   Password   Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).post(parameter);
            list.add(r.getDataReport(request,response.getBody().asString(),response.getStatusCode(),parts,parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //scenario3 description: The request is sent with a wrong username and correct password
    @Test(groups = {"logintest"})
    public void scenario3(){
        parts = r.turnArray(dataList,3);
        Username = parts[0];
        Password = parts[1];
        Action = parts[2];
        parameter = parts[parts.length-2];
        ExpectedCode = parts[parts.length - 1];
        //Username   Password   Action Parameters Expected Code
        request = "{\"username\":\"" + Username + "\",\"password\":\""+Password+"\"}";
        //Username   Password   Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).post(parameter);
            list.add(r.getDataReport(request,response.getBody().asString(),response.getStatusCode(),parts,parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //scenario4 description: The request is sent with a wrong username and wrong password
    @Test(groups = {"logintest"})
    public void scenario4(){
        parts = r.turnArray(dataList,4);
        Username = parts[0];
        Password = parts[1];
        Action = parts[2];
        parameter = parts[parts.length-2];
        ExpectedCode = parts[parts.length - 1];
        //Username   Password   Action Parameters Expected Code
        request = "{\"username\":\"" + Username + "\",\"password\":\""+Password+"\"}";
        //Username   Password   Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).post(parameter);
            list.add(r.getDataReport(request,response.getBody().asString(),response.getStatusCode(),parts,parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //scenario5 description: The request is sent with a empty username and empty password
    @Test(groups = {"logintest"})
    public void scenario5(){
        parts = r.turnArray(dataList,5);
        Username = parts[0];
        Password = parts[1];
        Action = parts[2];
        parameter = parts[parts.length-2];
        ExpectedCode = parts[parts.length - 1];
        //Username   Password   Action Parameters Expected Code
        request = "{\"username\":\"" + Username + "\",\"password\":\""+Password+"\"}";
        //Username   Password   Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).post(parameter);
            list.add(r.getDataReport(request,response.getBody().asString(),response.getStatusCode(),parts,parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //scenario6 description: The request is sent only with a username
    @Test(groups = {"logintest"})
    public void scenario6(){
        parts = r.turnArray(dataList,6);
        Username = parts[0];
        Password = parts[1];
        Action = parts[2];
        parameter = parts[parts.length-2];
        ExpectedCode = parts[parts.length - 1];
        //Username   Password   Action Parameters Expected Code
        request = "{\"username\":\"" + Username + "\",\"password\":\""+Password+"\"}";
        //Username   Password   Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).post(parameter);
            list.add(r.getDataReport(request,response.getBody().asString(),response.getStatusCode(),parts,parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //scenario7 description: The request is sent with only with a password
    @Test(groups = {"logintest"})
    public void scenario7(){
        parts = r.turnArray(dataList,7);
        Username = parts[0];
        Password = parts[1];
        Action = parts[2];
        parameter = parts[parts.length-2];
        ExpectedCode = parts[parts.length - 1];
        //Username   Password   Action Parameters Expected Code
        request = "{\"username\":\"" + Username + "\",\"password\":\""+Password+"\"}";
        //Username   Password   Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).post(parameter);
            list.add(r.getDataReport(request,response.getBody().asString(),response.getStatusCode(),parts,parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //scenario8 description: The request is sent with a wrong username and correct password
    @Test(groups = {"logintest"})
    public void scenario8(){
        parts = r.turnArray(dataList,8);
        Username = parts[0];
        Password = parts[1];
        Action = parts[2];
        parameter = parts[parts.length-2];
        ExpectedCode = parts[parts.length - 1];
        //Username   Password   Action Parameters Expected Code
        request = "{\"username\":\"" + Username + "\",\"password\":\""+Password+"\"}";
        //Username   Password   Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).post(parameter);
            list.add(r.getDataReport(request,response.getBody().asString(),response.getStatusCode(),parts,parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @AfterMethod(groups = {"logintest"})
    public void finish(){
        r.convertToJSON(list,jsonpath);
    }

}