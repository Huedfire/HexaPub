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

public class EditEmail {
    Properties prop = new Properties();
    InputStream input;
    String uri,username,email,ExpectedCode,request,parameter,filepath,jsonpath;
    ApiFramework r = new ApiFramework();
    Response response = null;
    ArrayList<String> dataList;
    ArrayList<Object> list = new ArrayList<Object>();
    String[] profile;

    @BeforeTest(groups = {"editmail"})
    public void getSetupEmail() throws IOException {
        input = new FileInputStream("C:\\Users\\Training\\HexaboardAutomationTest\\hexaboards\\conf.txt");
        prop.load(input);
        filepath = prop.getProperty("DataFile");
        jsonpath = prop.getProperty("JsonEPEmail");
        uri = prop.getProperty("URI");
        RestAssured.baseURI = uri;
        dataList = r.readExcel(filepath, 5);
    }

    @Test(groups = {"editmail"}, priority = 1)
    public void scenario7() {
        profile = r.turnArray(dataList, 1);
        email = profile[0];
        username = profile[1];
        parameter = profile[profile.length-2];

        ExpectedCode = profile[profile.length - 1];
        //Name Username    Action Parameters Expected Code
        request = "{\"email\":\"" + email + "\" ,\"username\":\"" + username + "\"}";
        //Name Username    Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).put(parameter);

            list.add(r.getDataReport(request, response.getBody().asString(), response.getStatusCode(), profile, parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test(groups = {"editmail"}, priority = 2)
    public void scenario8() {
        profile = r.turnArray(dataList, 2);
        email = profile[0];
        username = profile[1];
        parameter = profile[profile.length-2];

        ExpectedCode = profile[profile.length - 1];
        //Name Username    Action Parameters Expected Code
        request = "{\"email\":\"" + email + "\" ,\"username\":\"" + username + "\"}";
        //Name Username    Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).put(parameter);

            list.add(r.getDataReport(request, response.getBody().asString(), response.getStatusCode(), profile, parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test(groups = {"editmail"}, priority = 3)
    public void scenario9() {
        profile = r.turnArray(dataList, 3);
        email = profile[0];
        username = profile[1];
        parameter = profile[profile.length-2];

        ExpectedCode = profile[profile.length - 1];
        //Name Username    Action Parameters Expected Code
        request = "{\"email\":\"" + email + "\" ,\"username\":\"" + username + "\"}";
        //Name Username    Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).put(parameter);

            list.add(r.getDataReport(request, response.getBody().asString(), response.getStatusCode(), profile, parameter));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    @AfterTest(groups = {"editmail"})
    public void finishEmail() {
        r.convertToJSON(list, jsonpath);
    }


}
