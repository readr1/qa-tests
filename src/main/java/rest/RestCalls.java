package rest;

import com.google.gson.JsonObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class RestCalls {

    private static final String baseUrl = "http://localhost:5000/v1/";
    private static final Logger LOGGER = Logger.getLogger(RestCalls.class.getName());


    public String getProducts() {
        return when()
                .get(baseUrl.concat("products"))
                .then()
                .statusCode(200)
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
                .extract()
                .response()
                .getBody().asString();
    }

    public String getProductDetails(String name) {
        int i = 0;
        List<LinkedHashMap> response = when()
                .get(baseUrl.concat("products"))
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .response()
                .body()
                .jsonPath()
                .getList("");
        for (i = 0; i < response.size(); i++) {
            if (response.get(i).containsValue(name)) {
                return response.get(i).get("id").toString();
            }
        }
        LOGGER.info(String.format("Error - Product %s not found", name));
        return null;
    }

    public void deleteProduct(String productId) {
        given()
                .accept("application/json")
                .when()
                .delete(baseUrl.concat("product/" +productId))
                .then()
                .statusCode(204);
    }

    public boolean isProductPresentWithName(String name) {
        int i = 0;
        List<LinkedHashMap> response = when()
                .get(baseUrl.concat("products"))
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .response()
                .body()
                .jsonPath()
                .getList("");
        for (i = 0; i < response.size(); i++) {
            if (response.get(i).containsValue(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean isProductPresentWithPrice(String price) {
        int i = 0;
        List<LinkedHashMap> response = when()
                .get(baseUrl.concat("products"))
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .response()
                .body()
                .jsonPath()
                .getList("");
        for (i = 0; i < response.size(); i++) {
            if (response.get(i).containsValue(price)) {
                return true;
            }
        }
        return false;    }

    public void updateProduct(String productId, String name, double price) {
        JsonObject body = new JsonObject();
        body.addProperty("name", name);
        body.addProperty("price", price);
        given()
                .body(body)
                .contentType("application/json")
                .accept("application/json")
                .when()
                .put(baseUrl.concat("product/" + productId))
                .then()
                .statusCode(204);
    }
}
