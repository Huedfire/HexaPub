package Login;

import Framework.ExcelReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class TC_L_01 {

    private String filepath = "C:\\Users\\Training\\IdeaProjects\\Hexaboard\\DataFile.xlsx";

    ExcelReader r = new ExcelReader();

    @Test
    public void TestScenariosAll() throws IOException {

        String Username;
        String Password;
        String ExpectedCode;
        String request;



        ArrayList<String> user = r.readRows(filepath, 0);

        RestAssured.baseURI = "http://192.168.0.108:3000";
        Response response = null;

        for (int i = 1; i < user.size(); i++) {

            String[] parts = user.get(i).split(",");
            Username = parts[0];
            Password = parts[1].substring(1);

            ExpectedCode = parts[parts.length - 1].substring(1);


            request = "{\"username\":\"" + Username + "\",\"password\":\"" + Password + "\"}";

            try {

                response = RestAssured.given().contentType(ContentType.JSON).body(request).post("/api/login");
                System.out.print("Scenario: " + i + "\n");
                response.body().prettyPrint();



                System.out.println("Status code expected : " + Integer.parseInt(ExpectedCode));
                System.out.println("Status code received : " + response.getStatusCode());



            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }


        }


    }
}




