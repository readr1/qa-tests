package rest;

import com.google.gson.JsonObject;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

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

    public String addProduct(String name, double price) {
        JsonObject newProduct = new JsonObject();
        newProduct.addProperty("name", name);
        newProduct.addProperty("price", price);

        return given()
                .contentType("application/json")
                .accept("application/json")
                .body(newProduct)
                .when()
                .post(baseUrl.concat("product"))
                .then()
                .statusCode(204)
                .assertThat()
                .extract()
                .response()
                .getBody().asString();
    }

    public JsonObject getProductDetails(String name) {
            Response response = when()
                .get(baseUrl.concat("products"))
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .response();
            List<JsonObject> listOfProducts = response
                    .getBody()
                    .jsonPath()
                    .getList("");
            return listOfProducts.stream().filter(p -> p.get("name").getAsString().equals(name))
                    .findFirst().get();
    }
}
