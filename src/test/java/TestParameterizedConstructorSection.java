import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageobject.HomePage;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class TestParameterizedConstructorSection {

    private WebDriver driver;

    private String buttonName;
    private String fElementName;
    private String sElementName;

    public TestParameterizedConstructorSection(String buttonName, String fElementName, String sElementName){
        this.buttonName = buttonName;
        this.fElementName = fElementName;
        this.sElementName = sElementName;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"Соусы", "Соус Spicy-X", "Соус фирменный Space Sauce"},
                {"Начинки", "Мясо бессмертных моллюсков Protostomia", "Говяжий метеорит (отбивная)"}
        };
    }

    @Before
    public void setUp() {

        driver = WebDriverFactory.createWebDriver();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Check if click on button in constructor section than scrolling to certain section")
    public void checkIfClickOnButtonInConstructorSectionThanScrollingToCertainSection(){
        HomePage objHomePage = new HomePage(driver);

        //проверка перехода к разделу «Соусы» / «Начинки» в разделе "Конструктор" на главной странице
        objHomePage.openMainPage();
        objHomePage.clickOnButtonInConstructorSection(buttonName);
        objHomePage.getFirstElementTextAndCheck(fElementName);
        objHomePage.getSecondElementTextAndCheck(sElementName);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
