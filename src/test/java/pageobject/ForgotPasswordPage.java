package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

    private WebDriver driver;

    //локатор кнопки "Войти"
    private By loginButton = By.xpath(".//p/a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Click on login button")
    public void clickOnLoginButton(){
        driver.findElement(loginButton).click();
    }

}
