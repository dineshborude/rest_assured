import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJSON {

    @Test
    public void addBook() {

        RestAssured.baseURI = "http://216.10.245.166";

        String name = "Author_" + java.util.UUID.randomUUID().toString().substring(0,6);
        String isbn = "isbn_"+java.util.UUID.randomUUID().toString().substring(0,4);
        String aisle = java.util.UUID.randomUUID().toString().substring(2,6);

        String response =

        given().contentType("application/json")
                .body(Payload.addBook(name,isbn,aisle)).
        when().
                post("/Library/Addbook.php").
        then().
                log().all().assertThat().statusCode(200).
                extract().response().asString();

        JsonPath json = new JsonPath(response);
        String ID = json.getString("ID");
        System.out.println("ID OF ABOVE ADDED BOOK : " +ID);

        String getBookJSON =

        given().contentType("application/json").
                queryParam("ID",ID).
        when().
                get("/Library/GetBook.php").

        then().log().all().assertThat().statusCode(200).
                extract().response().asString();

        JsonPath gotThisBookID = new JsonPath(getBookJSON);
        Assert.assertEquals(ID,gotThisBookID.getString("ID"));






    }



}
