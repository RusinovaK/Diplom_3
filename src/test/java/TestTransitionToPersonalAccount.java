import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.HomePage;
import pageobject.LoginPage;
import pageobject.ProfilePage;

import java.util.concurrent.TimeUnit;

public class TestTransitionToPersonalAccount {

    private WebDriver driver;

    private String authToken;

    @Before
    public void setUp() {

        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";

        driver = WebDriverFactory.createWebDriver();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Check transition to personal account with auth")
    public void checkTransitionToPersonalAccountWithAuth(){
        HomePage objHomePage = new HomePage(driver);
        LoginPage objLoginPage = new LoginPage(driver);
        ProfilePage objProfilePage = new ProfilePage(driver);
        TestApiCreateUser testCreateUser = new TestApiCreateUser();

        //создание пользователя с помощью API
        Response response = testCreateUser.sendPostRequestWithUniqueData();
        authToken = response.jsonPath().getString("accessToken");

        //проверка перехода в личный кабинет с авторизацией
        objHomePage.openMainPage();
        objHomePage.clickOnLoginToAccountButton();

        objLoginPage.fillOutAuthorizationForm();

        objHomePage.clickOnPersonalAccountButton();

        objProfilePage.getProfileButtonTextAndCheck();
    }

    @After
    public void tearDown() {
        //удаление пользователя с помощью API
        TestApiDeleteUser testDeleteUser = new TestApiDeleteUser();
        testDeleteUser.sendDeleteRequestWithValidToken(authToken);

        driver.quit();
    }
}
