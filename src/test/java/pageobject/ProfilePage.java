package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class ProfilePage {

    private WebDriver driver;

    //локатор кнопки "Профиль"
    private By profileButton = By.xpath(".//li/a[text()='Профиль']");

    //локатор кнопки "Конструктор"
    private By constructorButton = By.xpath(".//a/p[text()='Конструктор']");

    //локатор логотипа Stellar Burgers
    private By stellarBurgersLogo = By.xpath(".//div/a[@href='/']");

    //локатор кнопки "Выход"
    private By exitButton = By.xpath(".//li/button[text()='Выход']");

    public ProfilePage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Click on constructor button")
    public void clickOnConstructorButton(){
        driver.findElement(constructorButton).click();
    }

    @Step("Click on Stellar Burgers logo")
    public void clickOnStellarBurgersLogo(){
        driver.findElement(stellarBurgersLogo).click();
    }

    @Step("Click on exit button")
    public void clickOnExitButton(){
        driver.findElement(exitButton).click();
    }

    @Step("Get profile button text and check")
    public void getProfileButtonTextAndCheck(){
        String expected = "Профиль";
        String actual = driver.findElement(profileButton).getText();
        assertEquals(actual, expected);
    }
}
