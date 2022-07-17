package starter.user;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;

public class Get {

    private String token, userId;
    protected static String base_url = "https://demoqa.com/Account/v1/";

    @Step("I set an endpoint for GET detail user")
    public String setAnEndpointForGet(String userId){
        return base_url + "User/" + userId;
    }

    @Step("I request GET detail user")
    public void requestGetDetailUser() throws Exception{
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//token.json"), StandardCharsets.UTF_8);
        this.userId = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//userId.json"), StandardCharsets.UTF_8);
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(setAnEndpointForGet(userId));
    }

    @Step("I validate the status code is {int}")
    public void validateStatusCode(int statusCode){
        SerenityRest.then().statusCode(statusCode);
    }

    @Step("validate the data detail")
    public void validateDataDetail(String userId){
        SerenityRest.then().body("userId", equalTo(this.userId));
    }
}
