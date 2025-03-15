import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TestApiAuthorization {

    @Step("Send POST request to /api/auth/login with valid log pass")
    public Response sendPostRequestWithValidLogPass(){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body("{\"email\":\"rusinova60@gmail.com\",\"password\":\"07865ghyt67\"}")
                        .when()
                        .post("/api/auth/login")
                        .then()
                        .extract().response();
        return response;
    }
}
