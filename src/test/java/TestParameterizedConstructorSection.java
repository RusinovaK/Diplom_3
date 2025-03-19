import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageobject.HomePage;

@RunWith(Parameterized.class)
public class TestParameterizedConstructorSection {

    private WebDriver driver;

    private String buttonName;

    public TestParameterizedConstructorSection(String buttonName){
        this.buttonName = buttonName;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"Соусы"},
                {"Начинки"}
        };
    }

    @Before
    public void setUp() {

        driver = WebDriverFactory.createWebDriver();
    }

    @Test
    @DisplayName("Check if click on button in constructor section than scrolling to certain section")
    public void checkIfClickOnButtonInConstructorSectionThanScrollingToCertainSection(){
        HomePage objHomePage = new HomePage(driver);

        //проверка перехода к разделу «Соусы» / «Начинки» в разделе "Конструктор" на главной странице
        objHomePage.openMainPage();
        objHomePage.clickOnButtonInConstructorSection(buttonName);
        objHomePage.waitForButtonInConstructorSectionToHaveClass(buttonName);
        objHomePage.checkThatSelectedButtonInConstructorSectionHasCurrentClass(buttonName);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
