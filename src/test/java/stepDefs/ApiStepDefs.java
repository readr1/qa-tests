package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.ApiSteps;

import java.io.IOException;
import java.util.logging.Logger;

public class ApiStepDefs {


    private static final Logger LOGGER = Logger.getLogger(ApiStepDefs.class.getName());

    @Steps
    ApiSteps apiSteps = new ApiSteps();

    @Given("^I have access to the Sensyne test API$")
    public void ICanAccessTheApi(){
        //If the service had a health check endpoint I'd check it here, as it doesn't I'm leaving this method
        //empty for now.
    }

    //Requests
    @When("I request a list of all products and get a successful response")
    public void iRequestAListOfAllProducts() {
        apiSteps.getAllProducts();
    }

    //Assertions
    @Then("I receive a list of all products containing an name, product code and price")
    public void iReceiveAListOfAllProductsContainingAnNameProductCodeAndPrice() {
        apiSteps.compareResponseToJson("defaultProducts");
    }


}
