package UserRegistration;
import Framework.ExcelReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class TC_UR_01 {
    String filepath = "C:\\Users\\Training\\IdeaProjects\\Hexaboard\\DataFile.xlsx";
    ExcelReader r = new ExcelReader();

    @Test
    public void TestScenariosAll() throws IOException {
        String name;
        String  e_mail;
        String Username;
        String password;

        String ExpectedCode;
        String request;
        ArrayList<String> user = r.readRows(filepath, 1);
        RestAssured.baseURI = "http://192.168.0.108:3000";
        Response response = null;

        for (int i = 1; i < user.size(); i++) {
            int j = 1;
            String[] parts = user.get(i).split(",");
            name = parts[0];
            e_mail = parts[1].substring(1);
            Username = parts[2].substring(1);
            password = parts[3].substring(1);
            ExpectedCode = parts[parts.length - 1].substring(1);
            request = "{\"name\":\""+name+"\",\"email\":\""+e_mail+"\",\"username\":\""+Username+"\",\"password\":\"" + password + "\"}";

            try {
                response = RestAssured.given().contentType(ContentType.JSON).body(request).post("/api/postuser");
                System.out.print("Scenario: " + i + "\n");
                response.body().prettyPrint();
                System.out.println("Status code: " + response.getStatusCode());
                System.out.println("Expected code: " + ExpectedCode);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
