package ProjectCreation;

import Framework.ExcelReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class TC_PC_02 {

    String filepath = "C:\\Users\\Training\\IdeaProjects\\Hexaboard\\DataFile.xlsx";

    ExcelReader r = new ExcelReader();

    @Test
    public void TestScenariosAll() throws IOException {

        String request;
        String projectid;
        String users;
        Object role;
        String ExpectedCode;

        ArrayList<String> user = r.readRows(filepath, 7);

        RestAssured.baseURI = "http://192.168.0.108:3000";
        Response response = null;

        for (int i = 1; i < user.size(); i++) {
            int j = 1;
            String[] parts = user.get(i).split(",");
            projectid = parts[0];
            users = parts[1].substring(1);
            role = parts[2].substring(1);
            ExpectedCode = parts[parts.length - 1].substring(1);

            request = "{\"projid\": \""+ projectid +"\",\"user\": \"" + users + "\",\"role\":\"" + role + "\"}";
            System.out.println(request);
            try {

                response = RestAssured.given().contentType(ContentType.JSON).body(request).post("/api/postmember");
                System.out.print("Scenario: " + i + "\n");
                response.body().prettyPrint();

                System.out.println("Status code : " + response.getStatusCode());
                System.out.println("Expected code : " + ExpectedCode);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }


        }


    }
}
