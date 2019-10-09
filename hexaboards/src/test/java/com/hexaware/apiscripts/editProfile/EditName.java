package com.hexaware.apiscripts.editProfile;

import com.hexaware.frameworks.api.ApiFramework;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class EditName {
    String filepath,jsonpath,uri,username,name,ExpectedCode,request,parameter;
    Properties prop = new Properties();
    InputStream input;
    ApiFramework r = new ApiFramework();
    Response response = null;
    ArrayList<String> dataList;
    ArrayList<Object> list = new ArrayList<Object>();
    String[] profile;

    @BeforeTest(groups = {"editname"})
    public void getSetupName() throws IOException {
        input = new FileInputStream("C:\\Users\\Training\\HexaboardAutomationTest\\hexaboards\\conf.txt");
        prop.load(input);
        filepath = prop.getProperty("DataFile");
        jsonpath = prop.getProperty("JsonEPName");
        uri = prop.getProperty("URI");
        RestAssured.baseURI = uri;
        dataList = r.readExcel(filepath, 4);
    }

    //scenario1 description: The request is sent with the correct user data
    @Test(groups = {"editname"}, priority = 1)
    public void scenario1() {
        profile = r.turnArray(dataList, 1);
        name = profile[0];
        username = profile[1];
        parameter = profile[profile.length-2];

        ExpectedCode = profile[profile.length - 1];
        //Name Username    Action Parameters Expected Code
        request = "{\"name\":\"" + name + "\" ,\"username\":\"" + username + "\"}";
        //Name Username    Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).put(parameter);

            list.add(r.getDataReport(request, response.getBody().asString(), response.getStatusCode(), profile, parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //scenario1 description: The request is sent with the correct user data
    @Test(groups = {"editname"},priority = 2)
    public void scenario2() {
        profile = r.turnArray(dataList, 2);
        name = profile[0];
        username = profile[1];
        parameter = profile[profile.length-2];

        ExpectedCode = profile[profile.length - 1];
        //Name Username    Action Parameters Expected Code
        request = "{\"name\":\"" + name + "\" ,\"username\":\"" + username + "\"}";
        //Name Username    Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).put(parameter);

            list.add(r.getDataReport(request, response.getBody().asString(), response.getStatusCode(), profile, parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //scenario1 description: The request is sent with the correct user data
    @Test(groups = {"editname"},priority = 3)
    public void scenario3() {
        profile = r.turnArray(dataList, 3);
        name = profile[0];
        username = profile[1];
        parameter = profile[profile.length-2];

        ExpectedCode = profile[profile.length - 1];
        //Name Username    Action Parameters Expected Code
        request = "{\"name\":\"" + name + "\" ,\"username\":\"" + username + "\"}";
        //Name Username    Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).put(parameter);

            list.add(r.getDataReport(request, response.getBody().asString(), response.getStatusCode(), profile, parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //scenario1 description: The request is sent with the correct user data
    @Test(groups = {"editname"},priority = 4)
    public void scenario4() {
        profile = r.turnArray(dataList, 4);
        name = profile[0];
        username = profile[1];
        parameter = profile[profile.length-2];

        ExpectedCode = profile[profile.length - 1];
        //Name Username    Action Parameters Expected Code
        request = "{\"name\":\"" + name + "\" ,\"username\":\"" + username + "\"}";
        //Name Username    Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).put(parameter);

            list.add(r.getDataReport(request, response.getBody().asString(), response.getStatusCode(), profile, parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test(groups = {"editname"},priority = 5)
    public void scenario5() {
        profile = r.turnArray(dataList, 5);
        name = profile[0];
        username = profile[1];
        parameter = profile[profile.length-2];

        ExpectedCode = profile[profile.length - 1];
        //Name Username    Action Parameters Expected Code
        request = "{\"name\":\"" + name + "\" ,\"username\":\"" + username + "\"}";
        //Name Username    Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).put(parameter);

            list.add(r.getDataReport(request, response.getBody().asString(), response.getStatusCode(), profile, parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test(groups = {"editname"},priority = 6)
    public void scenario6() {
        profile = r.turnArray(dataList, 6);
        name = profile[0];
        username = profile[1];
        parameter = profile[profile.length-2];

        ExpectedCode = profile[profile.length - 1];
        //Name Username    Action Parameters Expected Code
        request = "{\"name\":\"" + name + "\" ,\"username\":\"" + username + "\"}";
        //Name Username    Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).put(parameter);

            list.add(r.getDataReport(request, response.getBody().asString(), response.getStatusCode(), profile, parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    @AfterTest(groups = {"editname"})
    public void finishName() {
        r.convertToJSON(list, jsonpath);
    }



}




