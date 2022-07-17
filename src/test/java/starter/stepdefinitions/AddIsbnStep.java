package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import starter.books.PostAddIsbn;

public class AddIsbnStep {

    @Steps
    PostAddIsbn postAddIsbn;

    @Given("I set endpoint for add isbn")
    public void iSetEndpointForAddIsbn() {
        postAddIsbn.getEndpoint();
    }

    @When("I request add isbn to user")
    public void iRequestAddIsbnToUser() throws Exception{
        postAddIsbn.requestAddIsbn();
    }

    @Then("I verify the status code is {int}")
    public void iVerifyTheStatusCodeIs(int code) {
        postAddIsbn.validateStatusCode(code);
    }

    @And("I validate the data detail")
    public void iValidateTheDataDetail() {
        postAddIsbn.validateDataDetail();
    }
}
