import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidationTest {

    @Test
    public void sumOfCoursesPriceCheck() {

        JsonPath json = new JsonPath(Payload.jsonValidation());

        int sum = 0;
        for (int i = 0; i < json.getInt("courses.size()"); i++) {

            sum += json.getInt("courses[" +i+"].price") * json.getInt("courses[" +i+"].copies") ;


        }

        Assert.assertEquals(sum,json.getInt("dashboard.purchaseAmount"));

    }

}
