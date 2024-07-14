package pages;


import service.Endpoints;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static java.time.Duration.ofSeconds;
import static org.junit.Assert.assertTrue;

@Data
@AllArgsConstructor
public class MainPage {
    private final WebDriver webDriver;
    private final By buttonEnterToAccount = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By headerCreateBurger = By.xpath(".//h1[text()='Соберите бургер']");
    private final By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']/parent::a");
    private final By logo = By.xpath(".//div[contains(@class, 'AppHeader_header__logo')]");
    private final By constructor = By.xpath(".//p[contains(@class, 'AppHeader_header__linkText')]");
    private final By sauce = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By filling = By.xpath(".//span[text()='Начинки']/parent::div");
    private final By bunButtonLocator = By.xpath(".//span[text()='Булки']/parent::div");
    @Step("Открытие браузера")
    public MainPage open() {
        webDriver.get(Endpoints.BASE_URI);
        return this;
    }
    @Step("Клик на кнопку войти в учетную запись")
    public void clickOnButtonEnterToAccount() {
        webDriver.findElement(buttonEnterToAccount).click();
    }
    @Step("Получить токен")
    public String getAccessTokenFromLocalStorage() {
        new WebDriverWait(webDriver, ofSeconds(Endpoints.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(headerCreateBurger));
        LocalStorage localStorage = ((WebStorage) webDriver).getLocalStorage();
        return localStorage.getItem("accessToken");
    }
    @Step("Проверяем успешный вход в систему")
    public MainPage checkLoginSuccess() {
        new WebDriverWait(webDriver, ofSeconds(Endpoints.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
        assertTrue(webDriver.findElement(createOrderButton).isDisplayed());
        return this;
    }
    @Step("Клик на личный кабинет")
    public void clickOnPersonalAccount() {
        webDriver.findElement(personalAccountButton).click();
    }

    @Step("Клик на логотип")
    public MainPage clickOnLogo() {
        webDriver.findElement(logo).click();
        return this;
    }
    @Step("Переключиться на главную страницу ")
    public MainPage checkSwitchToMainPage() {
        new WebDriverWait(webDriver, ofSeconds(Endpoints.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(headerCreateBurger));
        assertTrue(webDriver.findElement(headerCreateBurger).isDisplayed());
        return this;
    }
    @Step("Клик на кнопку конструктор")
    public MainPage clickOnConstructor() {
        webDriver.findElement(constructor).click();
        return this;
    }
    @Step("Клик  на кнопку соус")
    public MainPage clickOnSauce() {
        webDriver.findElement(sauce).click();
        return this;
    }
    @Step("Выбрана кнопка Соус")
    public MainPage checkSauceIsSelected() {
        new WebDriverWait(webDriver, ofSeconds(Endpoints.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(sauce));
        assertTrue(new WebDriverWait(webDriver, ofSeconds(Endpoints.TIMEOUT))
                .until(ExpectedConditions.attributeContains(sauce, "class", "current")));
        return this;
    }
    @Step("Клик на кнопку Начинки")
    public MainPage clickOnFilling() {
        webDriver.findElement(filling).click();
        return this;
    }
    @Step("Выбрана кнопка Начинки")
    public MainPage checkFillingIsSelected() {
        new WebDriverWait(webDriver, ofSeconds(Endpoints.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(filling));
        assertTrue(new WebDriverWait(webDriver, ofSeconds(Endpoints.TIMEOUT))
                .until(ExpectedConditions.attributeContains(filling, "class", "current")));
        return this;
    }
    @Step("Клик на кнопку Булки")
    public MainPage clickOnBreads() {
        webDriver.findElement(bunButtonLocator);
        return this;
    }
    @Step("Выбрано кнопка Булки")
    public MainPage checkIsBreads() {
        new WebDriverWait(webDriver, ofSeconds(Endpoints.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(bunButtonLocator));
        assertTrue(new WebDriverWait(webDriver, ofSeconds(Endpoints.TIMEOUT))
                .until(ExpectedConditions.attributeContains(bunButtonLocator, "class", "current")));
        return this;
    }

}
