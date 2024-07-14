import driver.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.PersonalAccount;
import users.User;
import users.UserChecks;
import users.UserProperties;



public class NavigationTest {
    String accessToken;
    User user;
    private final UserProperties userProperties = new UserProperties();
    private final UserChecks userChecks = new UserChecks();

   WebDriver webDriver = WebDriverFactory.getWebDriver();

    @Before
    @DisplayName("Создать нового пользователя")
    public void createUser() {
        user = User.getRandomUser();
        ValidatableResponse response = userProperties.createNewUser(user);
        accessToken = User.getToken(response);

    }

    @Test
    @DisplayName("Перейти в личный кабинет")
    public void goToPersonalAccount() {
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        PersonalAccount personalAccount = new PersonalAccount(webDriver);

        mainPage.open()
                .clickOnButtonEnterToAccount();

        loginPage.enterEmail(user)
                .enterPassword(user)
                .clickOnButtonEnter();
        mainPage.checkLoginSuccess()
                .clickOnPersonalAccount();

        personalAccount.checkSwitchToAccount();
    }

    @Test
    @DisplayName("Перейдите на главную страницу, нажав на логотип")
    public void mainPageByClickLogo() {
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        PersonalAccount personalAccount = new PersonalAccount(webDriver);
        mainPage.open()
                .clickOnButtonEnterToAccount();

        loginPage.enterEmail(user)
                .enterPassword(user)
                .clickOnButtonEnter();
        mainPage.checkLoginSuccess()
                .clickOnPersonalAccount();

        personalAccount.checkSwitchToAccount();
        mainPage.clickOnLogo().
                checkSwitchToMainPage();
    }

    @Test
    @DisplayName("Перейдите на главную страницу, нажав на конструктор")
    public void mainPageByClickConstructor() {
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        PersonalAccount personalAccount = new PersonalAccount(webDriver);
        mainPage.open()
                .clickOnButtonEnterToAccount();

        loginPage.enterEmail(user)
                .enterPassword(user)
                .clickOnButtonEnter();
        mainPage.checkLoginSuccess()
                .clickOnPersonalAccount();

        personalAccount.checkSwitchToAccount();
        mainPage.clickOnConstructor()
                .checkSwitchToMainPage();
    }

    @Test
    @DisplayName("Выход из учетной записи ")
    public void exitFromAccount() {
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        PersonalAccount personalAccount = new PersonalAccount(webDriver);
        mainPage.open()
                .clickOnButtonEnterToAccount();

        loginPage.enterEmail(user)
                .enterPassword(user)
                .clickOnButtonEnter();
        mainPage.checkLoginSuccess()
                .clickOnPersonalAccount();

        personalAccount.checkSwitchToAccount()
                .exitFromAccount();
        loginPage.checkLogoutSuccess();
    }

@Test
@DisplayName("Переключение на секцию Соусы ")
public void sauceSection() {
    MainPage mainPage = new MainPage(webDriver);
    mainPage.open()
            .clickOnSauce()
            .checkSauceIsSelected();

}
    @Test
    @DisplayName("Переключение на секцию Начинки")
    public void fillingSection() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.open()
                .clickOnFilling()
                .checkFillingIsSelected();

    }
    @Test
    @DisplayName("Переключение на секцию Булки ")
    public void breadsSection() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.open()
                .clickOnBreads()
                .checkIsBreads();
    }


    @After
    @DisplayName("Закрытие браузера.Удалить пользователя, проверка удаление пользователя ")
    public void tearDown() {
    webDriver.quit();
    if (accessToken != null) {
        ValidatableResponse response = userProperties.deleteExistingUser(accessToken);
        userChecks.deleteSuccess(response);
    }
  }
}
