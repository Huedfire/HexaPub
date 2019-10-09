package UserMappingProject;


import Framework.ExcelReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class TC_UPM_01 {

    String filepath = "C:\\Users\\Training\\IdeaProjects\\Hexaboard\\DataFile.xlsx";


    ExcelReader r = new ExcelReader();

    @Test
    public void TestScenariosAll() throws IOException {

        String Userid;
        String ExpectedCode;
        String request;


        ArrayList<String> user = r.readRows(filepath, 3);

        RestAssured.baseURI = "http://192.168.0.108:3000";
        Response response = null;

        for (int i = 1; i < user.size(); i++) {
            int j = 1;
            String[] parts = user.get(i).split(",");
            Userid = parts[0];
            ExpectedCode = parts[parts.length - 1].substring(1);

            request = "{\"userid\":\"" + Userid + "\"}";

            try {

                response = RestAssured.given().contentType(ContentType.JSON).body(request).post("/api/userprojs");
                System.out.print("Scenario: " + i + "\n");
                response.body().prettyPrint();

                System.out.println("Status code expected : " + Integer.parseInt(ExpectedCode));
                System.out.println("Status code received : " + response.getStatusCode() + "\n");

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }


        }


    }
}
