package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class RegisterPage {

    private WebDriver driver;

    //локатор поля "Имя"
    private By nameField = By.xpath(".//div/label[text()='Имя']/following::input[1]");

    //локатор поля "Email"
    private By emailField = By.xpath(".//div/label[text()='Email']/following::input[1]");

    //локатор поля "Пароль"
    private By passwordField = By.xpath(".//div/input[@name='Пароль']");

    //локатор текста "Некорректный пароль" под полем "Пароль"
    private By errorTextOfPasswordField = By.xpath(".//div/p[text()='Некорректный пароль']");

    //локатор кнопки "Зарегистироваться"
    private By registerButton = By.xpath(".//form/button[text()='Зарегистрироваться']");

    //локатор кнопки "Войти"
    private By loginButton = By.xpath(".//p/a[text()='Войти']");

    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Set name field")
    public void setNameField() {
        driver.findElement(nameField).sendKeys("rusinova60");
    }

    @Step("Set email field")
    public void setEmailField() {
        driver.findElement(emailField).sendKeys("rusinova60@gmail.com");
    }

    @Step("Set password field valid value")
    public void setPasswordFieldValidValue() {
        driver.findElement(passwordField).sendKeys("07865ghyt67");
    }

    @Step("Set password field invalid value")
    public void setPasswordFieldInvalidValue(String value){
        driver.findElement(passwordField).sendKeys(value);
    }

    @Step("Click on register button")
    public void clickOnRegisterButton(){
        driver.findElement(registerButton).click();
    }

    @Step("Click on login button")
    public void clickOnLoginButton(){
        driver.findElement(loginButton).click();
    }

    @Step("Fill out register form")
    public void fillOutRegisterForm(){
        setNameField();
        setEmailField();
        setPasswordFieldValidValue();
        clickOnRegisterButton();
    }

    @Step("Get error text and check")
    public void getErrorTextAndCheck(){
        String expected = "Некорректный пароль";
        String actual = driver.findElement(errorTextOfPasswordField).getText();
        assertEquals(actual, expected);
    }
}
