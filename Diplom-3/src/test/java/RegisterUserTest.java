import driver.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import users.User;
import users.UserChecks;
import users.UserProperties;



public class RegisterUserTest {
    String accessToken;
    User user;
    private final UserProperties userProperties = new UserProperties();
    private final UserChecks userChecks = new UserChecks();

   WebDriver webDriver = WebDriverFactory.getWebDriver();

    @Before
    @DisplayName("Создать нового пользователя")
    public void createUser() {
        user = User.getRandomUser();
    }
    @Test
    @DisplayName("Регистрация пользователя")
    public void registerUser() {
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        RegisterPage registerPage = new RegisterPage(webDriver);
        mainPage.open()
                .clickOnButtonEnterToAccount();

        loginPage.clickOnLinkToAuth();

        registerPage.enterName(user)
                .enterEmail(user)
                .enterPassword(user)
                .clickOnButtonToRegister();
        loginPage.checkRegisterSuccess();
        loginPage.enterEmail(user)
                .enterPassword(user)
                .clickOnButtonEnter();

        accessToken = mainPage.getAccessTokenFromLocalStorage();
    }
    @Test
    @DisplayName("Регистрация пользователя с коротким паролем")
    public void registerUserWithShortPassword() {
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        RegisterPage registerPage = new RegisterPage(webDriver);
        String newPassword = user.getPassword().substring(0, 5);
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
    @DisplayName("Закрытие браузера и удаление пользователя")
    public void tearDown() {
    webDriver.quit();
    if (accessToken != null) {
        ValidatableResponse response = userProperties.deleteExistingUser(accessToken);
        userChecks.deleteSuccess(response);
      }
   }
}
