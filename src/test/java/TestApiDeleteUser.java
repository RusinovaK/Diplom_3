import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class TestApiDeleteUser {

    @Step("Send DELETE request to /api/auth/user with valid token")
    public void sendDeleteRequestWithValidToken(String authToken){
        given()
                .header("Content-type", "application/json")
                .header("Authorization", authToken)
                .when()
                .delete("/api/auth/user");
    }
}
