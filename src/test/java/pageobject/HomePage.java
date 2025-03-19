package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    private String buttonInConstructorSection = ".//div/span[text()='%s']/parent::div";

    //локатор кнопки "Булки" в разделе "Конструктор"
    private By bunsButton = By.xpath(".//div/span[text()='Булки']/parent::div");

    //локатор кнопки "Начинки" в разделе "Конструктор"
    private By fillingsButton = By.xpath(".//div/span[text()='Начинки']");

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

    @Step("Сheck that selected Buns button has current class")
    public void checkThatSelectedBunsButtonHasCurrentClass(){
        String classNameToCheck = "tab_tab_type_current__2BEPc";
        String classAttribute = driver.findElement(bunsButton).getAttribute("class");
        assertTrue(classAttribute.contains(classNameToCheck));
    }

    @Step("Сheck that selected button in constructor section has current class")
    public void checkThatSelectedButtonInConstructorSectionHasCurrentClass(String buttonName){
        String classNameToCheck = "tab_tab_type_current__2BEPc";
        String classAttribute = driver.findElement(By.xpath(String.format(buttonInConstructorSection, buttonName))).getAttribute("class");
        assertTrue(classAttribute.contains(classNameToCheck));
    }

    @Step("Wait for buns button to have class")
    public void waitForBunsButtonToHaveClass(){
        new WebDriverWait(driver, 10)
                .until(driver -> driver.findElement(bunsButton).getAttribute("class").contains("tab_tab_type_current__2BEPc"));
    }

    @Step("Wait for button in constructor section to have class")
    public void waitForButtonInConstructorSectionToHaveClass(String buttonName){
        new WebDriverWait(driver, 10)
                .until(driver -> driver.findElement(By.xpath(String.format(buttonInConstructorSection, buttonName))).getAttribute("class").contains("tab_tab_type_current__2BEPc"));
    }

}
