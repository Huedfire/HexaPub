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

public class EditPassword {
    Properties prop = new Properties();
    InputStream input;
    String uri, username, password, ExpectedCode, request, parameter, filepath, jsonpath;
    ApiFramework r = new ApiFramework();
    Response response = null;
    ArrayList<String> dataList;
    ArrayList<Object> list = new ArrayList<Object>();
    String[] profile;

    @BeforeTest(groups = {"newpass"})
    public void getSetupPass() throws IOException {
        input = new FileInputStream("C:\\Users\\Training\\HexaboardAutomationTest\\hexaboards\\conf.txt");
        prop.load(input);
        filepath = prop.getProperty("DataFile");
        jsonpath = prop.getProperty("JsonEPPassword");
        uri = prop.getProperty("URI");
        RestAssured.baseURI = uri;
        dataList = r.readExcel(filepath, 6);
    }

    @Test(groups = {"newpass"}, priority = 1)
    public void scenario17() {
        profile = r.turnArray(dataList, 1);
        password = profile[0];
        username = profile[1];
        parameter = profile[profile.length-2];
        ExpectedCode = profile[profile.length - 1];
        //Name Username    Action Parameters Expected Code
        request = "{\"newpass\":\"" + password + "\" ,\"username\":\"" + username + "\"}";
        //Name Username    Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).put(parameter);

            list.add(r.getDataReport(request, response.getBody().asString(), response.getStatusCode(), profile, parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test(groups = {"newpass"}, priority = 2)
    public void scenario18() {
        profile = r.turnArray(dataList, 2);
        password = profile[0];
        username = profile[1];
        parameter = profile[profile.length-2];
        ExpectedCode = profile[profile.length - 1];
        //Name Username    Action Parameters Expected Code
        request = "{\"newpass\":\"" + password + "\" ,\"username\":\"" + username + "\"}";
        //Name Username    Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).put(parameter);

            list.add(r.getDataReport(request, response.getBody().asString(), response.getStatusCode(), profile, parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test(groups = {"newpass"}, priority = 3)
    public void scenario19() {
        profile = r.turnArray(dataList, 3);
        password = profile[0];
        username = profile[1];
        parameter = profile[profile.length-2];
        ExpectedCode = profile[profile.length - 1];
        //Name Username    Action Parameters Expected Code
        request = "{\"newpass\":\"" + password + "\" ,\"username\":\"" + username + "\"}";
        //Name Username    Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).put(parameter);

            list.add(r.getDataReport(request, response.getBody().asString(), response.getStatusCode(), profile, parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test(groups = {"newpass"}, priority = 4)
    public void scenario20() {
        profile = r.turnArray(dataList, 4);
        password = profile[0];
        username = profile[1];
        parameter = profile[profile.length-2];
        ExpectedCode = profile[profile.length - 1];
        //Name Username    Action Parameters Expected Code
        request = "{\"newpass\":\"" + password + "\" ,\"username\":\"" + username + "\"}";
        //Name Username    Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).put(parameter);

            list.add(r.getDataReport(request, response.getBody().asString(), response.getStatusCode(), profile, parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test(groups = {"newpass"}, priority = 5)
    public void scenario21() {
        profile = r.turnArray(dataList, 5);
        password = profile[0];
        username = profile[1];
        parameter = profile[profile.length-2];
        ExpectedCode = profile[profile.length - 1];
        //Name Username    Action Parameters Expected Code
        request = "{\"newpass\":\"" + password + "\" ,\"username\":\"" + username + "\"}";
        //Name Username    Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).put(parameter);

            list.add(r.getDataReport(request, response.getBody().asString(), response.getStatusCode(), profile, parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test(groups = {"newpass"}, priority = 6)
    public void scenario22() {
        profile = r.turnArray(dataList, 6);
        password = profile[0];
        username = profile[1];
        parameter = profile[profile.length-2];
        ExpectedCode = profile[profile.length - 1];
        //Name Username    Action Parameters Expected Code
        request = "{\"newpass\":\"" + password + "\" ,\"username\":\"" + username + "\"}";
        //Name Username    Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).put(parameter);

            list.add(r.getDataReport(request, response.getBody().asString(), response.getStatusCode(), profile, parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test(groups = {"newpass"}, priority = 7)
    public void scenario23() {
        profile = r.turnArray(dataList, 7);
        password = profile[0];
        username = profile[1];
        parameter = profile[profile.length-2];
        ExpectedCode = profile[profile.length - 1];
        //Name Username    Action Parameters Expected Code
        request = "{\"newpass\":\"" + password + "\" ,\"username\":\"" + username + "\"}";
        //Name Username    Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).put(parameter);

            list.add(r.getDataReport(request, response.getBody().asString(), response.getStatusCode(), profile, parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test(groups = {"newpass"}, priority = 8)
    public void scenario24() {
        profile = r.turnArray(dataList, 8);
        password = profile[0];
        username = profile[1];
        parameter = profile[profile.length-2];
        ExpectedCode = profile[profile.length - 1];
        //Name Username    Action Parameters Expected Code
        request = "{\"newpass\":\"" + password + "\" ,\"username\":\"" + username + "\"}";
        //Name Username    Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).put(parameter);

            list.add(r.getDataReport(request, response.getBody().asString(), response.getStatusCode(), profile, parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @AfterTest(groups = {"newpass"})
    public void finishAll() {
        r.convertToJSON(list, jsonpath);
    }

}
