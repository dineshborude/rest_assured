import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Basics {

    @Test
    public void addUpdateAndVerifyPlace() {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        // ADD PLACE
        String addResponse =
                given()
                        .queryParam("key", "qaclick123")
                        .contentType("application/json")
                        .body(Payload.addPlace())
                        .when()
                        .post("/maps/api/place/add/json")
                        .then()
                        .statusCode(200)
                        .extract().asString();

        JsonPath addJs = new JsonPath(addResponse);
        String placeID = addJs.getString("place_id");

        // UPDATE PLACE
        given()
                .queryParam("key","qaclick123")
                .contentType("application/json")
                .body(Payload.updatePlace(placeID, "Shahartakli, Shevgaon"))
                .when()
                .put("maps/api/place/update/json")
                .then()
                .statusCode(200)
                .body("msg", equalTo("Address successfully updated"));

        // GET & VERIFY
        String getResponse =
                given()
                        .queryParam("key","qaclick123")
                        .queryParam("place_id", placeID)
                        .when()
                        .get("maps/api/place/get/json")
                        .then()
                        .statusCode(200)
                        .extract().asString();

        JsonPath getJs = new JsonPath(getResponse);
        Assert.assertEquals(getJs.getString("address"), "Shahartakli, Shevgaon");
    }
}
