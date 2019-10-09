package EditProfile;

import Framework.ExcelReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;

//Requests with name and username

public class TC_EP_01 {
    String filepath = "C:\\Users\\Training\\IdeaProjects\\Hexaboard\\DataFile.xlsx";
    ExcelReader r = new ExcelReader();

    @Test
    public void NameTestScenariosAll() throws IOException {

        String Name;
        String Username;
        String ExpectedCode;
        String request;
        System.out.println(r.readExcel(filepath));
        ArrayList<String> user = r.readRows(filepath,4);

        RestAssured.baseURI = "http://192.168.0.108:3000";
        Response response = null;

        for (int i = 1; i < user.size(); i++) {
            int j = 1;
            String[] parts = user.get(i).split(",");
            Name = parts[0];
            Username = parts[1].substring(1);
            ExpectedCode = parts[parts.length - 1].substring(1);

            request = "{\"name\":\""+Name+"\" ,\"username\":\"" + Username + "\"}";

            try {

                response = RestAssured.given().contentType(ContentType.JSON).body(request).put("/api/nameupd");
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
