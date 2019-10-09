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

public class EditAll {
    Properties prop = new Properties();
    InputStream input;
    String uri, name, username, email, ExpectedCode, request, parameter, filepath, jsonpath;
    ApiFramework r = new ApiFramework();
    Response response = null;
    ArrayList<String> dataList;
    ArrayList<Object> list = new ArrayList<Object>();
    String[] profile;

    @BeforeTest(groups = {"editall"})
    public void getSetupEmail() throws IOException {
        input = new FileInputStream("C:\\Users\\Training\\HexaboardAutomationTest\\hexaboards\\conf.txt");
        prop.load(input);
        filepath = prop.getProperty("DataFile");
        jsonpath = prop.getProperty("JsonEPAll");
        uri = prop.getProperty("URI");
        RestAssured.baseURI = uri;
        dataList = r.readExcel(filepath, 7);
    }

    @Test(groups = {"editall"}, priority = 1)
    public void scenario17() {
        profile = r.turnArray(dataList, 1);
        name = profile[0];
        email = profile[1];
        username = profile[2];
        parameter = profile[profile.length-2];
        ExpectedCode = profile[profile.length - 1];
        //Name Username    Action Parameters Expected Code
        request = "{\"name\":\"" + name + "\" ,\"email\":\"" + email + "\" ,\"username\":\"" + username + "\"}";
        //Name Username    Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).put(parameter);

            list.add(r.getDataReport(request, response.getBody().asString(), response.getStatusCode(), profile, parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test(groups = {"editall"}, priority = 2)
    public void scenario18() {
        profile = r.turnArray(dataList, 2);
        name = profile[0];
        email = profile[1];
        username = profile[2];
        parameter = profile[profile.length-2];
        ExpectedCode = profile[profile.length - 1];
        //Name Username    Action Parameters Expected Code
        request = "{\"name\":\"" + name + "\" ,\"email\":\"" + email + "\" ,\"username\":\"" + username + "\"}";
        //Name Username    Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).put(parameter);

            list.add(r.getDataReport(request, response.getBody().asString(), response.getStatusCode(), profile, parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test(groups = {"editall"}, priority = 3)
    public void scenario19() {
        profile = r.turnArray(dataList, 3);
        name = profile[0];
        email = profile[1];
        username = profile[2];
        parameter = profile[profile.length-2];
        ExpectedCode = profile[profile.length - 1];
        //Name Username    Action Parameters Expected Code
        request = "{\"name\":\"" + name + "\" ,\"email\":\"" + email + "\" ,\"username\":\"" + username + "\"}";
        //Name Username    Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).put(parameter);

            list.add(r.getDataReport(request, response.getBody().asString(), response.getStatusCode(), profile, parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Test(groups = {"editall"}, priority = 4)
    public void scenario20() {
        profile = r.turnArray(dataList, 4);
        name = profile[0];
        email = profile[1];
        username = profile[2];
        parameter = profile[profile.length-2];ExpectedCode = profile[profile.length - 1];
        //Name Username    Action Parameters Expected Code
        request = "{\"name\":\"" + name + "\" ,\"email\":\"" + email + "\" ,\"username\":\"" + username + "\"}";
        //Name Username    Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).put(parameter);

            list.add(r.getDataReport(request, response.getBody().asString(), response.getStatusCode(), profile, parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Test(groups = {"editall"}, priority = 5)
    public void scenario21() {
        profile = r.turnArray(dataList, 5);
        name = profile[0];
        email = profile[1];
        username = profile[2];
        parameter = profile[profile.length-2];
        ExpectedCode = profile[profile.length - 1];
        //Name Username    Action Parameters Expected Code
        request = "{\"name\":\"" + name + "\" ,\"email\":\"" + email + "\" ,\"username\":\"" + username + "\"}";
        //Name Username    Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).put(parameter);

            list.add(r.getDataReport(request, response.getBody().asString(), response.getStatusCode(), profile, parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Test(groups = {"editall"}, priority = 6)
    public void scenario22() {
        profile = r.turnArray(dataList, 6);
        name = profile[0];
        email = profile[1];
        username = profile[2];
        parameter = profile[profile.length-2];ExpectedCode = profile[profile.length - 1];
        //Name Username    Action Parameters Expected Code
        request = "{\"name\":\"" + name + "\" ,\"email\":\"" + email + "\" ,\"username\":\"" + username + "\"}";
        //Name Username    Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).put(parameter);

            list.add(r.getDataReport(request, response.getBody().asString(), response.getStatusCode(), profile, parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }



    @Test(groups = {"editall"}, priority = 7)
    public void scenario23() {
        profile = r.turnArray(dataList, 7 );
        name = profile[0];
        email = profile[1];
        username = profile[2];
        parameter = profile[profile.length-2];
        ExpectedCode = profile[profile.length - 1];
        //Name Username    Action Parameters Expected Code
        request = "{\"name\":\"" + name + "\" ,\"email\":\"" + email + "\" ,\"username\":\"" + username + "\"}";
        //Name Username    Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).put(parameter);

            list.add(r.getDataReport(request, response.getBody().asString(), response.getStatusCode(), profile, parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }



    @AfterTest(groups = {"editall"})
    public void finishAll() {
        r.convertToJSON(list, jsonpath);
    }

}
