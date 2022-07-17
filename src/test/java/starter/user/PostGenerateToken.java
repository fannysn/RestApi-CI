package starter.user;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.Matchers.equalTo;

public class PostGenerateToken {

    private String username;
    private static String base_url = "https://demoqa.com/Account/v1/";

    @Step("I set an endpoint for POST generate token")
    public String setEndpointForGenerate(){
        return base_url + "GenerateToken";
    }

    @Step("I request POST generate token")
    public void requestPostGenerateToken() throws Exception{
        username = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//username.json"), StandardCharsets.UTF_8);
        JSONObject requestData = new JSONObject();
        requestData.put("userName", username);
        requestData.put("password", "Password1234!");

        given().header("Content-Type", "application/json")
                .body(requestData.toJSONString())
                .when().post(setEndpointForGenerate());
    }

    @Step("validate the data detail after generate token")
    public void validateDataDetailGenerateToken(){
        then().body("result", equalTo("User authorized successfully."));
    }

    @Step("get token for other request")
    public String getToken(){
        Response response = SerenityRest.lastResponse();
        String token = response.body().path("token");
        System.out.println(token);
        try (FileWriter file = new FileWriter("src//test//resources//filejson//token.json")) {
            file.write(token);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }
}
