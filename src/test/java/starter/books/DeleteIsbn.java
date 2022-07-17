package starter.books;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.nio.charset.StandardCharsets;

import static net.serenitybdd.rest.RestRequests.*;

public class DeleteIsbn {

    String token, isbn, userId;
    String base_url = "https://demoqa.com/BookStore/v1/Book";

    public String getEndpoint(){
        return base_url;
    }

    public JSONObject bodyDelete() throws Exception{
        this.isbn = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//isbn.json"), StandardCharsets.UTF_8);
        this.userId = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//userId.json"), StandardCharsets.UTF_8);

        JSONObject data = new JSONObject();
        data.put("isbn", isbn);
        data.put("userId", userId);

        return data;
    }

    public void requestDelete() throws Exception {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//token.json"), StandardCharsets.UTF_8);

        given().header("Authorization", "Bearer " + this.token)
                .header("Content-Type", "application/json")
                        .body(bodyDelete().toJSONString());
        when().delete(getEndpoint());
    }

    public void validateDataDetail(){
        Response response = SerenityRest.lastResponse();
        System.out.println(response);
    }
}
