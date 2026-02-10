public class Payload {

    public static String addPlace() {
        return """
        {
          "location": {
            "lat": -38.383494,
            "lng": 33.427362
          },
          "accuracy": 50,
          "name": "Rahul Shetty Academy",
          "phone_number": "(+91) 983 893 3937",
          "address": "29, side layout, cohen 09",
          "types": ["shoe park", "shop"],
          "website": "http://rahulshettyacademy.com",
          "language": "French-IN"
        }
        """;
    }

    public static String updatePlace(String placeID, String newAddress) {
        return """
        {
          "place_id": "%s",
          "address": "%s",
          "key": "qaclick123"
        }
        """.formatted(placeID, newAddress);
    }

    public static String jsonValidation() {
        return """
                                
               {
                                
                "dashboard": {
                                
                "purchaseAmount": 910,
                                
                "website": "rahulshettyacademy.com"
                                
               },
                                
                "courses": [
                                
                {
                                
                "title": "Selenium Python",
                                
                "price": 50,
                                
                "copies": 6
                                
                },
                                
                {
                                
                "title": "Cypress",
                                
                "price": 40,
                                
                "copies": 4
                                
                },
                                
                {
                                
                "title": "RPA",
                                
                "price": 45,
                                
                "copies": 10
                                
                }
                                
                ]
                                
               }
                """;
    }

}
