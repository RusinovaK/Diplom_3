import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageobject.HomePage;
import pageobject.LoginPage;
import pageobject.RegisterPage;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class TestParameterizedRegister {

    private WebDriver driver;
    private String value;

    public TestParameterizedRegister(String value){
        this.value = value;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"54321"},
                {"531"}
        };
    }

    @Before
    public void setUp() {

        driver = WebDriverFactory.createWebDriver();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Check if enter incorrect password error appears")
    public void checkIfEnterIncorrectPasswordErrorAppears(){
        HomePage objHomePage = new HomePage(driver);
        LoginPage objLoginPage = new LoginPage(driver);
        RegisterPage objRegisterPage = new RegisterPage(driver);

        objHomePage.openMainPage();
        objHomePage.clickOnLoginToAccountButton();

        objLoginPage.clickOnRegisterLink();

        //проверка что при вводе менее 6 символов в поле "Пароль" отображается ошибка "Некорректный пароль"
        objRegisterPage.setPasswordFieldInvalidValue(value);
        objRegisterPage.clickOnRegisterButton();
        objRegisterPage.getErrorTextAndCheck();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
