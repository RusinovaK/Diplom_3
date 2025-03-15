import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.HomePage;
import pageobject.LoginPage;
import pageobject.RegisterPage;

import java.util.concurrent.TimeUnit;

public class TestRegister {

    private WebDriver driver;

    @Before
    public void setUp() {

        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";

        driver = WebDriverFactory.createWebDriver();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Check if enter valid data and click on button user registered")
    public void checkIfEnterValidDataAndClickOnButtonUserRegistered(){
        HomePage objHomePage = new HomePage(driver);
        LoginPage objLoginPage = new LoginPage(driver);
        RegisterPage objRegisterPage = new RegisterPage(driver);
        TestApiAuthorization testAuthorization = new TestApiAuthorization();
        TestApiDeleteUser testDeleteUser = new TestApiDeleteUser();

        objHomePage.openMainPage();
        objHomePage.clickOnLoginToAccountButton();

        objLoginPage.clickOnRegisterLink();

        //регистрация с валидными данными
        objRegisterPage.fillOutRegisterForm();

        //проверка, что пользователю открылась страница авторизации
        objLoginPage.getLoginButtonTextAndCheck();

        //удаление пользователя с помощью API
        Response response = testAuthorization.sendPostRequestWithValidLogPass();
        String authToken = response.jsonPath().getString("accessToken");
        testDeleteUser.sendDeleteRequestWithValidToken(authToken);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
