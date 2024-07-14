import driver.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import users.User;




@RunWith(Parameterized.class)
public class RegisterUserShortPasswordTest {
    User user;
    private final int passLength;

   WebDriver webDriver = WebDriverFactory.getWebDriver();

    public RegisterUserShortPasswordTest(int passLength) {
        this.passLength = passLength;
    }



    @Before
    @DisplayName("Создать нового пользователя")
    public void createUser() {
        user = User.getRandomUser();

    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {4},
                {5}
        };
    }
    @Test
    @DisplayName("Регистрация пользователя с коротким паролем")
    public void registerWithShortPassword() {
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        RegisterPage registerPage = new RegisterPage(webDriver);
        String newPassword = user.getPassword().substring(0, passLength);
        user.setPassword(newPassword);

        mainPage.open()
                .clickOnButtonEnterToAccount();

        loginPage.clickOnLinkToAuth();

        registerPage.enterName(user)
                .enterEmail(user)
                .enterPassword(user)
                .clickOnButtonToRegister()
                .checkInvalidPassword();
    }
    @After
    public void tearDown() {
        webDriver.quit();
    }
}