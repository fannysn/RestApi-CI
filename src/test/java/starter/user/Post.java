package starter.user;

import Utils.General;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;

public class Post {

    General general = new General();
    String username;

    private static String base_url = "https://demoqa.com/Account/v1/";

    @Step("I set an endpoint for POST new user")
    public String setPostEndpoint(){
        return base_url + "User";
    }

    @Step("I set an endpoint for POST new user")
    public String getUsername(){
        return this.username;
    }

    @Step("I request POST detail user")
    public void requestPostDetailUser(String username, String password){
        JSONObject requestData = new JSONObject();
        if (username.equals("new")){
            this.username = general.randomUsername();
            try (FileWriter file = new FileWriter("src//test//resources//filejson//username.json")) {
                file.write(this.username);
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (username.equals("same")){
            this.username = "aisyahns22";
        }

        requestData.put("userName", this.username);
        requestData.put("password", password);

        SerenityRest.given().header("Content-Type", "application/json").body(requestData.toJSONString());
        SerenityRest.when().post(setPostEndpoint());
    }

    @Step("validate the data detail after create user")
    public void validateDataDetail(String message){
        if (message.equals("success")){
            SerenityRest.then().body("username", equalTo(this.username));
        } else {
            SerenityRest.then().body("username", equalTo(null));
        }
    }

    @Step("Get userId from the response")
    public String getUserId(){
        Response response = SerenityRest.lastResponse();
        String userId = response.body().path("userID");
        try (FileWriter file = new FileWriter("src//test//resources//filejson//userId.json")) {
            file.write(userId);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(userId);
        return userId;
    }
}
