import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TestApiCreateUser {

    @Step("Send POST request to /api/auth/register with unique data")
    public Response sendPostRequestWithUniqueData(){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body("{\"email\":\"rusinova60@gmail.com\",\"password\":\"07865ghyt67\",\"name\":\"rusinova60\"}")
                        .when()
                        .post("/api/auth/register")
                        .then()
                        .extract().response();
        return response;
    }
}
