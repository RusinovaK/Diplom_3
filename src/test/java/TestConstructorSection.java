import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.HomePage;

public class TestConstructorSection {

    private WebDriver driver;

    @Before
    public void setUp() {

        driver = WebDriverFactory.createWebDriver();
    }

    @Test
    @DisplayName("Check if click on buns button than scrolling to buns section")
    public void checkIfClickOnBunsButtonThanScrollingToBunsSection(){
        HomePage objHomePage = new HomePage(driver);

        //проверка перехода к разделу «Булки» в разделе "Конструктор" на главной странице
        objHomePage.openMainPage();
        objHomePage.clickOnFillingsButton();
        objHomePage.clickOnBunsButton();
        objHomePage.waitForBunsButtonToHaveClass();
        objHomePage.checkThatSelectedBunsButtonHasCurrentClass();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
