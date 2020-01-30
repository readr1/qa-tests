package stepDefs;


import io.cucumber.java.en.*;
import net.thucydides.core.annotations.Steps;
import steps.ApiSteps;

import java.util.logging.Logger;

public class ApiStepDefs {


    private static final Logger LOGGER = Logger.getLogger(ApiStepDefs.class.getName());

    @Steps
    ApiSteps apiSteps = new ApiSteps();

    @Given("^I have access to the Sensyne test API")
    public void ICanAccessTheApi() {
        //If the service had a health check endpoint I'd check it here, as it doesn't I'm leaving this method
        //empty for now.
    }

    //Requests
    @When("I request a list of all products and get a successful response")
    public void iRequestAListOfAllProducts() {
        apiSteps.getAllProducts();
    }

    //Assertions
    @Then("^I receive a list of all products containing an name, product code and price")
    public void iReceiveAListOfAllProductsContainingAnNameProductCodeAndPrice() {
        apiSteps.compareResponseToJson("defaultProducts");
    }


    @And("I add a new product with a {string} and {string}")
    public void iAddANewProductWithTheFollowingIdNameAndPrice(String name, String price) {
        apiSteps.addProduct(name, Double.parseDouble(price));
    }

    @And("I see the product is visible when I view all products")
    public void iSeeTheProductIsVisibleWhenIViewAllProducts() {
    }

    @When("I request the products details using its product {string}")
    public void iRequestTheProductsDetailsUsingItsProductCode(String id) {
    }

    @Then("I receive the details of the product")
    public void iReceiveTheDetailsOfTheProduct() {
    }

    @Then("I receive the details of the product and can see the {string} is now {string}")
    public void iReceiveTheDetailsOfTheProductAndCanSeeTheTypeIsNowValue(String type, String value) {
    }

    @When("I delete the products using its product {string}")
    public void iDeleteTheProductsUsingItsProductCode(String code) {
    }

    @Then("I see the product is not visible when I view all products")
    public void iSeeTheProductIsNotVisibleWhenIViewAllProducts() {
    }

    @When("I delete the product {string}")
    public void iDeleteTheProduct(String name) {
        apiSteps.deleteProduct(
                apiSteps.getProductId(name)
        );
    }
}

