package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.books.DeleteIsbn;

public class DeleteIsbnStep {

    @Steps
    DeleteIsbn deleteIsbn;

    @Given("I set endpoint for delete isbn")
    public void iSetEndpointForDeleteIsbn() {
        deleteIsbn.getEndpoint();
    }

    @When("I request delete isbn to user")
    public void iRequestDeleteIsbnToUser() throws Exception{
        deleteIsbn.requestDelete();
    }

    @And("I validate the data detail for delete")
    public void iValidateTheDataDetailForDelete() {
        deleteIsbn.validateDataDetail();
    }
}
