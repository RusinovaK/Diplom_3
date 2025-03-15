import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.HomePage;

import java.util.concurrent.TimeUnit;

public class TestConstructorSection {

    private WebDriver driver;

    @Before
    public void setUp() {

        driver = WebDriverFactory.createWebDriver();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("check if click on buns button than scrolling to buns section")
    public void checkIfClickOnBunsButtonThanScrollingToBunsSection(){
        HomePage objHomePage = new HomePage(driver);

        //проверка перехода к разделу «Булки» в разделе "Конструктор" на главной странице
        objHomePage.openMainPage();
        objHomePage.clickOnFillingsButton();
        objHomePage.clickOnBunsButton();
        objHomePage.getFluorescentBunTextAndCheck();
        objHomePage.getKratornayaBunTextAndCheck();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
