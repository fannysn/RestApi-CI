package starter.books;

import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;

import java.io.FileWriter;
import java.io.IOException;

import static net.serenitybdd.rest.RestRequests.given;
import static net.serenitybdd.rest.SerenityRest.then;

import static org.hamcrest.Matchers.equalTo;
public class GetListBook {

    String base_url = "https://demoqa.com/BookStore/v1/Books";

    public String getEndpoint(){
        return base_url;
    }

    public void requestGetListBook(){
        given().when().get(getEndpoint());
    }

    public void verifyStatusCode(int code){
        then().statusCode(equalTo(code));
    }

    public void getIsbn(){
        Response response = SerenityRest.lastResponse();
        String isbn = response.body().path("books[0].isbn");
        try (FileWriter file = new FileWriter("src//test//resources//filejson//isbn.json")) {
            file.write(isbn);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
