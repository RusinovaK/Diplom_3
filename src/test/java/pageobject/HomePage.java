package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class HomePage {
    private WebDriver driver;

    //url главной страницы Stellar Burgers
    private final String URL_MAIN_PAGE = "https://stellarburgers.nomoreparties.site/";

    //локатор кнопки "Войти в аккаунт"
    private By loginToAccountButton = By.xpath(".//div/button[text()='Войти в аккаунт']");

    //локатор кнопки "Личный кабинет"
    private By personalAccountButton = By.xpath(".//nav/a[@class='AppHeader_header__link__3D_hX']");

    //локатор кнопки "Оформить заказ"
    private By placeAnOrderButton = By.xpath(".//div/button[text()='Оформить заказ']");

    //локатор кнопки "Соусы" / "Начинки" в разделе "Конструктор"
    private String buttonInConstructorSection = ".//div/span[text()='%s']";

    //локатор названия первого элемента в разделе "Соусы" / "Начинки"
    private String fElementLocator = ".//ul/a/p[text()='%s']";

    //локатор названия второго элемента в разделе "Соусы"/ "Начинки"
    private String sElementLocator = ".//ul/a/p[text()='%s']";

    //локатор кнопки "Булки" в разделе "Конструктор"
    private By bunsButton = By.xpath(".//div/span[text()='Булки']");

    //локатор кнопки "Начинки" в разделе "Конструктор"
    private By fillingsButton = By.xpath(".//div/span[text()='Начинки']");

    //локатор названия элемента "Флюоресцентная булка R2-D3" в разделе "Булки"
    private By fluorescentBun = By.xpath(".//ul/a/p[text()='Флюоресцентная булка R2-D3']");

    //локатор названия элемента "Краторная булка N-200i" в разделе "Булки"
    private By kratornayaBun = By.xpath(".//ul/a/p[text()='Краторная булка N-200i']");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Open main page")
    public void openMainPage(){
        driver.get(URL_MAIN_PAGE);
    }

    @Step("Click on login to account button")
    public void clickOnLoginToAccountButton(){
        driver.findElement(loginToAccountButton).click();
    }

    @Step("Click on personal account button")
    public void clickOnPersonalAccountButton(){
        driver.findElement(personalAccountButton).click();
    }

    @Step("Click on button in constructor section")
    public void clickOnButtonInConstructorSection(String buttonName){
        driver.findElement(By.xpath(String.format(buttonInConstructorSection, buttonName))).click();
    }

    @Step("Click on Buns button")
    public void clickOnBunsButton(){
        driver.findElement(bunsButton).click();
    }

    @Step("Click on Fillings button")
    public void clickOnFillingsButton(){
        driver.findElement(fillingsButton).click();
    }

    @Step("Get place an order button text and check")
    public void getPlaceAnOrderButtonTextAndCheck(){
        String expected = "Оформить заказ";
        String actual = driver.findElement(placeAnOrderButton).getText();
        assertEquals(actual, expected);
    }

    @Step("Get first element text and check")
    public void getFirstElementTextAndCheck(String fElementName){
        String actual = driver.findElement(By.xpath(String.format(fElementLocator, fElementName))).getText();
        assertEquals(actual, fElementName);
    }

    @Step("Get second element text and check")
    public void getSecondElementTextAndCheck(String sElementName){
        String actual = driver.findElement(By.xpath(String.format(sElementLocator, sElementName))).getText();
        assertEquals(actual, sElementName);
    }

    @Step("Get Fluorescent bun text and check")
    public void getFluorescentBunTextAndCheck(){
        String expected = "Флюоресцентная булка R2-D3";
        String actual = driver.findElement(fluorescentBun).getText();
        assertEquals(actual, expected);
    }

    @Step("Get Kratornaya bun text and check")
    public void getKratornayaBunTextAndCheck(){
        String expected = "Краторная булка N-200i";
        String actual = driver.findElement(kratornayaBun).getText();
        assertEquals(actual, expected);
    }
}
