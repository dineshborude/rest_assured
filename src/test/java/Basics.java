import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class Basics {

    public static void main(String[] args) {

        // validate if add place API is working as expected

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        given()
                .queryParam("key","qaclick123")
                .header("Content-Type", "application-json")
                .body("");



    }
}
