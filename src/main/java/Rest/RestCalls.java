package Rest;

import static io.restassured.RestAssured.*;

public class RestCalls {

    private static final String baseUrl = "http://localhost:5000/v1/";

    public String getProducts() {
        return when().
                get(baseUrl.concat("products"))
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .response()
                .getBody().asString();
    }
}
