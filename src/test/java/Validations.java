import io.restassured.path.json.JsonPath;

public class Validations {

    public static void main(String[] args) {


        JsonPath json = new JsonPath(Payload.jsonValidation());
        System.out.println("Print No of courses returned by API : " +json.getInt("courses.size()"));
        System.out.println("Print Purchase Amount : " +json.getInt("dashboard.purchaseAmount"));
        System.out.println("Print Title of the first course : "+json.getString("courses.title[0]"));


        int sum = 0;
        for (int i = 0; i < json.getInt("courses.size()"); i++) {
            System.out.println(
                    json.getString("courses[" + i + "].title") + " : " +
                            json.getInt("courses[" + i + "].price")
            );
            sum += json.getInt("courses[" +i+"].price") * json.getInt("courses[" +i+"].copies") ;

            if(json.getString("courses[" + i + "].title").equalsIgnoreCase("RPA")){
                System.out.println("no of copies sold by RPA Course : " +json.getInt("courses[2].copies"));
            }
        }

        // Verify if Sum of all Course prices matches with Purchase Amount
        System.out.println("Sum of all Course prices matches with Purchase Amount : "+sum +","+json.getInt("dashboard.purchaseAmount"));

    }

}
