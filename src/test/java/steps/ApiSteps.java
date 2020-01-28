package steps;

import Rest.RestCalls;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class ApiSteps {

    RestCalls restCall = new RestCalls();

    String response;

    public void getAllProducts(){
        response = restCall.getProducts();
    }

    public void compareResponseToJson(String json) {
        String expected = "";
        try {
            expected = new String(Files.readAllBytes(Paths.get(
                    String.format("src/test/resources/json/%s.json", json))));
        } catch(IOException e ) {
            e.getLocalizedMessage();
        }
        assertEquals("Response did not match expected", expected , response);
    }

}
