package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class LoginPage {

    private WebDriver driver;

    //локатор гиперссылки "Зарегистрироваться"
    private By registerLink = By.xpath(".//div/p/a[text()='Зарегистрироваться']");

    //локатор поля "Email"
    private By emailField = By.xpath(".//div/input[@name='name']");

    //локатор поля "Пароль"
    private By passwordField = By.xpath(".//div/input[@name='Пароль']");

    //локатор кнопки "Войти"
    private By loginButton = By.xpath(".//form/button[text()='Войти']");

    //локатор кнопки "Восстановить пароль"
    private By recoverPasswordButton = By.xpath(".//p/a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Click on register link")
    public void clickOnRegisterLink(){
        driver.findElement(registerLink).click();
    }

    @Step("Click on recover password button")
    public void clickOnRecoverPasswordButton(){
        driver.findElement(recoverPasswordButton).click();
    }

    @Step("Get login button text and check")
    public void getLoginButtonTextAndCheck(){
        String expected = "Войти";
        String actual = driver.findElement(loginButton).getText();
        assertEquals(actual, expected);
    }

    @Step("Set email field")
    public void setEmailField() {
        driver.findElement(emailField).sendKeys("rusinova60@gmail.com");
    }

    @Step("Set password field")
    public void setPasswordField() {
        driver.findElement(passwordField).sendKeys("07865ghyt67");
    }

    @Step("Click on login button")
    public void clickOnLoginButton(){
        driver.findElement(loginButton).click();
    }

    @Step("Fill out authorization form")
    public void fillOutAuthorizationForm(){
        setEmailField();
        setPasswordField();
        clickOnLoginButton();
    }
}
