package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.user.Get;
import starter.user.Post;
import starter.user.PostAuthorized;
import starter.user.PostGenerateToken;

public class UserStep {

    public String userId, token, username;

    @Steps
    Get get;

    @Steps
    Post post;

    @Steps
    PostAuthorized postAuthorized;

    @Steps
    PostGenerateToken postGenerateToken;

    @Given("I set an endpoint for GET detail user")
    public void iSetAnEndpointForGETDetailUser() {
        get.setAnEndpointForGet(this.userId);
    }

    @When("I request GET detail user")
    public void iRequestGETDetailUser() throws Exception{
        get.requestGetDetailUser();
    }

    @And("validate the data detail")
    public void validateTheDataDetail() {
        get.validateDataDetail(this.userId);
    }

    @Given("I set an endpoint for POST new user")
    public void iSetAnEndpointForPOSTNewUser() {
        post.setPostEndpoint();
    }

    @When("I request POST detail user")
    public void iRequestPOSTDetailUser() {
//        post.requestPostDetailUser();
        this.username = post.getUsername();
    }

    @And("validate the data detail after create user")
    public void validateTheDataDetailAfterCreateUser(String message) {
        post.validateDataDetail(message);
    }

    @And("get userId for other request")
    public void getUserIdForOtherRequest() {
        this.userId = post.getUserId();
    }

    @Given("I set an endpoint for POST generate token")
    public void iSetAnEndpointForPOSTGenerateToken() {
        postGenerateToken.setEndpointForGenerate();
    }

    @When("I request POST generate token")
    public void iRequestPOSTGenerateToken() throws Exception{
        postGenerateToken.requestPostGenerateToken();
    }

    @And("validate the data detail after generate token")
    public void validateTheDataDetailAfterGenerateToken() {
        postGenerateToken.validateDataDetailGenerateToken();
    }

    @And("get token for other request")
    public void getTokenForOtherRequest() {
        this.token = postGenerateToken.getToken();
    }

    @Then("I validate the status code is {int}")
    public void iValidateTheStatusCodeIs(int arg0) {
        get.validateStatusCode(arg0);
    }

    @Given("I set an endpoint for POST new {string} with {string}")
    public void iSetAnEndpointForPOSTNewWith(String username, String password) {
        post.requestPostDetailUser(username, password);
    }

    @And("validate the {string} after create user")
    public void validateTheAfterCreateUser(String message) {
        post.validateDataDetail(message);
    }

    @And("get userId if {string} for other request")
    public void getUserIdIfForOtherRequest(String message) {
        if (message.equals("success")){
            post.getUserId();
        }
    }

    @Given("I set an endpoint for authorized myself")
    public void iSetAnEndpointForAuthorizedMyself() {
        postAuthorized.setEndpoint();
    }

    @When("I request POST authorized myself")
    public void iRequestPOSTAuthorizedMyself() throws Exception{
        postAuthorized.requestPostAuthorized();
    }

    @And("validate the data detail for authorized")
    public void validateTheDataDetailForAuthorized() {
        postAuthorized.validateDataDetailAuthorized();
    }
}
