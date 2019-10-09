package com.hexaware.apiscripts.userDeletion;

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

public class UserDeletion {


    Properties prop=new Properties();
    InputStream input ;
    ApiFramework r = new ApiFramework();

    String  uri, filepath, jsonpath, username, userID, password, expectedCode, request, projectname , projID;

    String  parameterGetID = "/api/user";
    String parameterDelUser = "/api/deluser";
    String parameterGetProject = "/api/lastproject";
    String parameterAddMember = "/api/postmember";

    Response response = null;
    ArrayList<String> dataList;
    ArrayList<Object> list = new ArrayList<Object>();
    String[] parts;

    @BeforeTest(groups = {"functest"})
    public void getDataListName() throws IOException {
        input = new FileInputStream("C:\\Users\\Training\\HexaboardAutomationTest\\hexaboards\\conf.txt");
        prop.load(input);
        filepath = prop.getProperty("DataFile");
        jsonpath = prop.getProperty("JsonDel");
        uri = prop.getProperty("URI");
        RestAssured.baseURI = uri;
        dataList = r.readExcel(filepath, 10);
    }

    //scenario1 description: The request is sent with the correct user data
    @Test(groups = {"functest"})
    public void scenario1(){
        parts = r.turnArray(dataList,1);
        username = parts[0];
        password = parts[1];
        expectedCode = parts[parts.length - 1];
        //Name E-mail Username   Password   Action Parameters Expected Code
        request = "{\"username\":\"" + username + "\"}";

        //Name E-mail Username   Password   Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).post(parameterGetID);

            //Json y le quito {}
            userID = response.getBody().asString().substring(2,response.getBody().asString().length()-2);
            //Json separado por comas
            String[] array = userID.split(",");
            //json separado por : (osea los valores)
            String[] array2 = array[array.length-1].split(":");

            userID = array2[array2.length-1];


            request = "{\"userid\":\""+userID+"\" ,\"password\":\""+password+"\"}";

            response = RestAssured.given().contentType(ContentType.JSON).body(request).post(parameterDelUser);

          list.add(r.getDataReport(request,response.getBody().asString(),response.getStatusCode(),parts,parameterDelUser));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //scenario2 description: The request is sent with the correct user data (the user must be member of a project)
    @Test(groups = {"functest"})
    public void scenario2(){
        parts = r.turnArray(dataList,2);
        username = parts[0];
        password = parts[1];
        projectname = parts[2];
        expectedCode = parts[parts.length - 1];
        //Name E-mail Username   Password   Action Parameters Expected Code
        request = "{\"username\":\"" + username + "\"}";

        //Name E-mail Username   Password   Action Parameters Expected Code
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(request).post(parameterGetID);

            //Json y le quito {}
            userID = response.getBody().asString().substring(2,response.getBody().asString().length()-2);
            //Json separado por comas
            String[] array = userID.split(",");
            //json separado por : (osea los valores)
            String[] array2 = array[array.length-1].split(":");

            userID = array2[array2.length-1];

            request = "{\"name\":\""+ projectname +"\"}";

            response = RestAssured.given().contentType(ContentType.JSON).body(request).post(parameterGetProject);

            //Json y le quito {}
          projID = response.getBody().asString().substring(2,response.getBody().asString().length()-2);

           //Json separado por comas
            array = projID.split(",");
            //json separado por : (osea los valores)
            array2 = array[array.length-1].split(":");

            projID = array2[0];

            request = "{\"projid\":\""+projID+"\",\"user\":\""+userID+"\",\"role\":\"ScrumMaster\"}";
            response = RestAssured.given().contentType(ContentType.JSON).body(request).post(parameterAddMember);


            request = "{\"userid\":\""+userID+"\" ,\"password\":\""+password+"\"}";

            response = RestAssured.given().contentType(ContentType.JSON).body(request).post(parameterDelUser);

            list.add(r.getDataReport(request,response.getBody().asString(),response.getStatusCode(),parts,parameterDelUser));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    @AfterTest(groups = {"functest"})
    public void finish(){
        r.convertToJSON(list,jsonpath);
    }
}



