/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import static junit.framework.Assert.assertEquals;

/**
 *
 * @author Training
 */
public class TestAPI {

    public void getUsers() {
        try {
            RestAssured.baseURI = "https://reqres.in";
            RestAssured.port = 443;
            RestAssured.basePath = "/api";

            Response resp = given()
                    .header("content-type", "application/json")
                    .when().param("page", "1")
                    .param("per_page", "6")
                    .get("/users");

            assertEquals(resp.getBody().jsonPath()
                    .get("total_pages")
                    .toString(), "2");

            resp.body().prettyPrint();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String createCharacter(String name, String realName, String location, boolean alive, String id) {
        try {
            RestAssured.baseURI = "https://restool-sample-app.herokuapp.com";
            RestAssured.basePath = "/api/character";

            Map<String, Object> json = new HashMap<>();
            json.put("name", name);
            json.put("realName", realName);
            json.put("location", location);
            json.put("isAlive", alive);

            Response resp = given().header("content-type", "application/json").body(json).when().post();

            String respName = resp.getBody().jsonPath().get("name").toString();
            id = resp.getBody().jsonPath().getString("id").toString();
            int status = resp.getStatusCode();

            assertEquals(respName, name);
            assertEquals(status, 200);

            resp.getBody().prettyPrint();
            return id;

        } catch (Exception ex) {
            throw ex;
        }
    }

    public void updCharacter(String name, String id) {
        try {
            RestAssured.baseURI = "https://restool-sample-app.herokuapp.com";
            RestAssured.basePath = "/api/character" + id;

            Map<String, Object> json = new HashMap<>();
            json.put("name", name);
            Response resp = given().header("content-type", "application/json").body(json).when().post();
            String respName = resp.getBody().jsonPath().get("name").toString();
            assertEquals(resp.getBody().asString(), "ok");
            assertEquals(resp.getStatusCode(), 200);
           resp.getBody().prettyPrint();

        } catch (Exception ex) {
            throw ex;
        }
    }

    public void deleteCharacter(String name, String id) {
        try {
            RestAssured.baseURI = "https://restool-sample-app.herokuapp.com";
            RestAssured.basePath = "/api/character" + id;

            Map<String, Object> json = new HashMap<>();
            json.put("name", name);
            Response resp = given().header("content-type", "application/json").body(json).when().post();
            String respName = resp.getBody().jsonPath().get("name").toString();
            assertEquals(resp.getBody().asString(), "ok");
            assertEquals(resp.getStatusCode(), 200);

        } catch (Exception ex) {
            throw ex;
        }
    }

}
