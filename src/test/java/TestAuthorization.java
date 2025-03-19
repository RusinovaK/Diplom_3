import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.ForgotPasswordPage;
import pageobject.HomePage;
import pageobject.LoginPage;
import pageobject.RegisterPage;

import java.util.concurrent.TimeUnit;

public class TestAuthorization {
    private WebDriver driver;
    private String authToken;

    @Before
    public void setUp() {

        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";

        driver = WebDriverFactory.createWebDriver();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Check authorization by login to account button on main page")
    public void checkAuthorizationByLoginToAccountButtonOnMainPage(){
        HomePage objHomePage = new HomePage(driver);
        LoginPage objLoginPage = new LoginPage(driver);
        TestApiCreateUser testCreateUser = new TestApiCreateUser();

        //создание пользователя с помощью API
        Response response = testCreateUser.sendPostRequestWithUniqueData();
        authToken = response.jsonPath().getString("accessToken");

        //проверка авторизации по кнопке "Войти в аккаунт" на главной странице
        objHomePage.openMainPage();
        objHomePage.clickOnLoginToAccountButton();

        objLoginPage.fillOutAuthorizationForm();

        objHomePage.getPlaceAnOrderButtonTextAndCheck();
    }

    @Test
    @DisplayName("Check authorization by personal account button")
    public void checkAuthorizationByPersonalAccountButton(){
        HomePage objHomePage = new HomePage(driver);
        LoginPage objLoginPage = new LoginPage(driver);
        TestApiCreateUser testCreateUser = new TestApiCreateUser();

        //создание пользователя с помощью API
        Response response = testCreateUser.sendPostRequestWithUniqueData();
        authToken = response.jsonPath().getString("accessToken");

        //проверка авторизации по кнопке "Личный кабинет"
        objHomePage.openMainPage();
        objHomePage.clickOnPersonalAccountButton();

        objLoginPage.fillOutAuthorizationForm();

        objHomePage.getPlaceAnOrderButtonTextAndCheck();
    }

    @Test
    @DisplayName("Check authorization by login button on register page")
    public void checkAuthorizationByLoginButtonOnRegisterPage(){
        HomePage objHomePage = new HomePage(driver);
        LoginPage objLoginPage = new LoginPage(driver);
        RegisterPage objRegisterPage = new RegisterPage(driver);
        TestApiCreateUser testCreateUser = new TestApiCreateUser();

        //создание пользователя с помощью API
        Response response = testCreateUser.sendPostRequestWithUniqueData();
        authToken = response.jsonPath().getString("accessToken");

        //проверка авторизации по кнопке "Войти" на странице регистрации
        objHomePage.openMainPage();
        objHomePage.clickOnLoginToAccountButton();

        objLoginPage.clickOnRegisterLink();

        objRegisterPage.clickOnLoginButton();

        objLoginPage.fillOutAuthorizationForm();

        objHomePage.getPlaceAnOrderButtonTextAndCheck();
    }

    @Test
    @DisplayName("Check authorization by login button on forgot password page")
    public void checkAuthorizationByLoginButtonOnForgotPasswordPage(){
        HomePage objHomePage = new HomePage(driver);
        LoginPage objLoginPage = new LoginPage(driver);
        ForgotPasswordPage objForgotPasswordPage = new ForgotPasswordPage(driver);
        TestApiCreateUser testCreateUser = new TestApiCreateUser();

        //создание пользователя с помощью API
        Response response = testCreateUser.sendPostRequestWithUniqueData();
        authToken = response.jsonPath().getString("accessToken");

        //проверка авторизации по кнопке "Войти" на странице восстановления пароля
        objHomePage.openMainPage();
        objHomePage.clickOnLoginToAccountButton();

        objLoginPage.clickOnRecoverPasswordButton();

        objForgotPasswordPage.clickOnLoginButton();

        objLoginPage.fillOutAuthorizationForm();

        objHomePage.getPlaceAnOrderButtonTextAndCheck();
    }

    @After
    public void tearDown() {
        //удаление пользователя с помощью API
        TestApiDeleteUser testDeleteUser = new TestApiDeleteUser();
        testDeleteUser.sendDeleteRequestWithValidToken(authToken);

        driver.quit();
    }
}
