package steps;

import rest.RestCalls;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class ApiSteps {

    RestCalls restCall = new RestCalls();

    private static final Logger LOGGER = Logger.getLogger(ApiSteps.class.getName());

    String response;
    String activeProductName = "";
    String activeProductId = "";

    public void getAllProducts(){
        response = restCall.getProducts();
    }

    public void compareResponseToJson(String json) {
        String expected = "";
        try {
            System.out.println(System.getProperty("user.dir"));
            expected = new String(Files.readAllBytes(Paths.get(
                    String.format("src/test/resources/json/%s.json", json))));
        } catch(IOException e ) {
            e.getLocalizedMessage();
        }
        assertEquals("Response did not match expected", expected , response);
    }

    public void addProduct(String name, double price) {
        activeProductName = name.concat(String.valueOf(Instant.now().toEpochMilli()));
        LOGGER.info(String.format("Product renamed to %s", activeProductName));
        response = restCall.addProduct(activeProductName, price);
    }

    public String getProductId() {
        activeProductId = restCall.getProductDetails(activeProductName);
        return activeProductId;
    }

    public String getProductId(String name) {
        activeProductId = restCall.getProductDetails(name);
        return activeProductId;
    }

    public void deleteProduct(String productId) {
        restCall.deleteProduct(productId);
    }

    public void checkForProduct() {
        assertTrue(restCall.isProductPresentWithName(activeProductName));
    }

    public void checkForProduct(String name) {
        assertTrue(restCall.isProductPresentWithName(name));
    }

    public void checkForProductIsMissing() {
        assertFalse(restCall.isProductPresentWithPrice(activeProductName));
    }


    public void updateProduct(String productId, String name, double price) {
        restCall.updateProduct(productId, name, price);
    }
}
