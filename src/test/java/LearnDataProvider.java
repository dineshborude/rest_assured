import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LearnDataProvider {

    @Test(dataProvider = "BookData")
    public void booksAPI(String name, String isbn, String aisle) {

        RestAssured.baseURI = "http://216.10.245.166";

        Response response =
        given().contentType("application/json")
                .body(Payload.addBook(name,isbn,aisle))
                .when()
                .post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response();

        JsonPath json = new JsonPath(response.asString());

    }

    @DataProvider(name = "BookData")
    public Object[][] getData()
    {

        return new Object[][]
                {
                    {"Name01","D&D", "Robin"},{"Name02","R&D","Danny"},{"Name03","Ronny Wilson", "Harrington"}
                };

    }

}
