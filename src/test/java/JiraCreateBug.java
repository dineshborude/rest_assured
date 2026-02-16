import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class JiraCreateBug {

    @Test
    public void createBug() {

        RestAssured.baseURI = "https://dannys-workspace.atlassian.net";

        String auth = "Basic ZGluZXNoYm9ydWRlMUBnbWFpbC5jb206QVRBVFQzeEZmR0YwWDJadWZsZWNRd2Niekswbk96T2lPZTlWOTIwdm5IV2NCRUpsYklBOG1ld0N2NUt5T0RxT01aNTJvYUhoanN6bms5LVNzNjVGRHZZZWRBQXlFOVk0aHU1bHBjOHdoVGFtUUl0a2gzXzFXcm1mbmtlOU9UZVllSHJaRXhSbDJiZjhic2U1OTNzUlZPQ0E3MlQyM1dLSk4wZHJDRkthWkU0Y1JYYVdmcjlqS0FjPUY2N0M5MDU1";

        // Step 1: Create the Bug
        String responseBody =
                given()
                        .contentType("application/json")
                        .header("Authorization", auth)
                        .body(Payload.bugBody())
                        .when()
                        .post("/rest/api/3/issue")
                        .then()
                        .log().all()
                        .assertThat().statusCode(201)
                        .extract().response().asString();

        // Step 2: Extract Issue ID
        JsonPath json = new JsonPath(responseBody);
        String id = json.getString("id");
        System.out.println("Created Issue ID: " + id);

        // Step 3: Attach File
        given()
                .header("Authorization", auth)
                .header("X-Atlassian-Token", "no-check")
                .pathParam("issueId", id)
                .multiPart("file", new File("C:\\Users\\dines\\Downloads\\Generated Image September 30, 2025 - 1_04AM.png"))
                .when()
                .post("/rest/api/3/issue/{issueId}/attachments")
                .then()
                .log().all()
                .assertThat().statusCode(200);
    }
}