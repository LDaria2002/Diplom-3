import driver.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.RecoveryPasswordPage;
import pages.RegisterPage;
import users.User;
import users.UserChecks;
import users.UserProperties;



public class AuthUserTest {

    String  accessToken;
    User user;
    private final UserProperties userProperties = new UserProperties();
    private final UserChecks userChecks = new UserChecks();

    WebDriver webDriver = WebDriverFactory.getWebDriver();

    @Before
    @DisplayName("Создаю нового пользователя")
    public void createUserTest() {
        user = User.getRandomUser();
        ValidatableResponse response = userProperties.createNewUser(user);
        accessToken = User.getToken(response);
    }

    @Test
    @DisplayName("Авторизация пользователя с помощью кнопки на главной странице")
    public void authByButtonOnManePage() {
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        mainPage.open()
                .clickOnButtonEnterToAccount();

        loginPage.enterEmail(user)
                .enterPassword(user)
                .clickOnButtonEnter();
        mainPage.checkLoginSuccess();
    }

    @Test
    @DisplayName("Авторизация пользователя из личного кабинета")
    public void authFromPersonalAccount() {
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        mainPage.open()
                .clickOnPersonalAccount();

        loginPage.enterEmail(user)
                .enterPassword(user)
                .clickOnButtonEnter();
        mainPage.checkLoginSuccess();
    }

    @Test
    @DisplayName("Авторизация пользователя на странице регистрации")
    public void authFromRegister() {
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        RegisterPage registerPage = new RegisterPage(webDriver);
        mainPage.open()
                .clickOnButtonEnterToAccount();
        loginPage.clickOnLinkToAuth();

        registerPage.clickOnLinkToEnterAccount();

        loginPage.enterEmail(user)
                .enterPassword(user)
                .clickOnButtonEnter();

        mainPage.checkLoginSuccess();
    }

    @Test
    @DisplayName("Авторизация пользователя на странице восстановления пароля")
    public void authFromPasswordRecovery() {
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        RecoveryPasswordPage recoveryPasswordPage = new RecoveryPasswordPage(webDriver);
        mainPage.open()
                .clickOnButtonEnterToAccount();

        loginPage.clickOnLinkToRecoverPassword();

        recoveryPasswordPage.clickOnLinkToEnterAccount();

        loginPage.enterEmail(user)
                .enterPassword(user)
                .clickOnButtonEnter();

        mainPage.checkLoginSuccess();
    }

    @After
      @DisplayName("Закрытие браузера.Удалить пользователя")
      public void tearDown() {
      webDriver.quit();
      if (accessToken != null) {
        ValidatableResponse response = userProperties.deleteExistingUser(accessToken);
        userChecks.deleteSuccess(response);
    }
  }
}