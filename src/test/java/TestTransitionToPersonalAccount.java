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
        TestApiDeleteUser testDeleteUser = new TestApiDeleteUser();

        //создание пользователя с помощью API
        Response response = testCreateUser.sendPostRequestWithUniqueData();
        String authToken = response.jsonPath().getString("accessToken");

        //проверка перехода в личный кабинет с авторизацией
        objHomePage.openMainPage();
        objHomePage.clickOnLoginToAccountButton();

        objLoginPage.fillOutAuthorizationForm();

        objHomePage.clickOnPersonalAccountButton();

        objProfilePage.getProfileButtonTextAndCheck();

        //удаление пользователя с помощью API
        testDeleteUser.sendDeleteRequestWithValidToken(authToken);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
