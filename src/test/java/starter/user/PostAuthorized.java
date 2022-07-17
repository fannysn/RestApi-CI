package starter.user;

import net.thucydides.core.annotations.Step;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.nio.charset.StandardCharsets;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.Matchers.equalTo;

public class PostAuthorized {

    private String username, token;
    private static String base_url = "https://demoqa.com/Account/v1/";

    @Step("I set an endpoint for POST authorized")
    public String setEndpoint(){
        return base_url + "Authorized";
    }

    @Step("I request POST Authorized")
    public void requestPostAuthorized() throws Exception{
        username = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//username.json"), StandardCharsets.UTF_8);
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//token.json"), StandardCharsets.UTF_8);
        JSONObject requestData = new JSONObject();
        requestData.put("userName", username);
        requestData.put("password", "Password1234!");

        given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestData.toJSONString())
                .when().post(setEndpoint());
    }

    @Step("validate the data detail after generate token")
    public void validateDataDetailAuthorized(){
        then().body(equalTo("true"));
    }


}
