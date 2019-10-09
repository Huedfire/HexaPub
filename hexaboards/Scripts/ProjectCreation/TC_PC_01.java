package ProjectCreation;

import Framework.ExcelReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class TC_PC_01 {

    ExcelReader r = new ExcelReader();
    String filepath = "C:\\Users\\Training\\IdeaProjects\\HexaBoard\\DataFile.xlsx";

    @Test
    public void TestScenariosAll() throws IOException {
        String request;
        String name;
        String description;
        Object start_date;
        Object end_date;
        String ExpectedCode;

        ArrayList<String> user = r.readRows(filepath, 8);

        RestAssured.baseURI = "http://192.168.0.108:3000";
        Response response = null;

        for (int i = 1; i < user.size(); i++) {
            int j = 1;
            String[] parts = user.get(i).split(",");
            name = parts[0];
            description = parts[1].substring(1);
            start_date = parts[2].substring(1);
            end_date = parts[3].substring(1);
            ExpectedCode = parts[parts.length - 1].substring(1);

            request = "{\"name\": \""+ name +"\",\"description\": \"" + description + "\",\"start_date\":\"" + start_date + "\",\"end_date\":\"" + end_date + "\", \"image\":null}";
            System.out.println(request);
            try {

                response = RestAssured.given().contentType(ContentType.JSON).body(request).post("/api/postproject");
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
