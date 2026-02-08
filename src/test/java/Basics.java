import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Basics {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        Response response =
        given()
                .queryParam("key", "qaclick123")
                .contentType("application/json")
                .body(Payload.addPlace())
                .when()
                .post("/maps/api/place/add/json")
                .then()
                .statusCode(200)
                .body("status", equalTo("OK"))
                .body("scope", equalTo("APP"))
                .body("place_id", notNullValue())
                .header("server", "Apache/2.4.52 (Ubuntu)")
                .extract().response();

        String responseString = response.asString();
        System.out.println(responseString);

        JsonPath jsResponse = new JsonPath(responseString);

        String placeID = jsResponse.getString("place_id");

        // Update place now

        given().queryParam("key","qaclick123")
                .contentType("application/json")
                .body();

    }
}
