package pages;

import service.Endpoints;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import users.User;
import static java.time.Duration.ofSeconds;
import static org.junit.Assert.assertTrue;

@Data
@AllArgsConstructor
public class RegisterPage {
    private final WebDriver webDriver;
    private final By fieldPassword = By.xpath(".//label[text()='Пароль']/parent::div/input");
    private final By fieldEmail = By.xpath(".//label[text()='Email']/parent::div/input");
    private final By fieldName = By.xpath(".//label[text()='Имя']/parent::div/input");
    private final By buttonToRegister = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By errorMessageWrongPassword = By.xpath(".//p[text()='Некорректный пароль']");
    private final By redBorderFieldEmail = By.xpath(".//div[contains(@class, 'input_status_error')]");
    private final By linkToEnterToAccount = By.xpath(".//a[text()='Войти']");

    @Step("Введите password")
    public RegisterPage enterPassword(User user) {
        webDriver.findElement(fieldPassword).sendKeys(user.getPassword());
        return this;
    }

    @Step("Введите email")
    public RegisterPage enterEmail(User user) {
        webDriver.findElement(fieldEmail).sendKeys(user.getEmail());
        return this;
    }

    @Step("Введите имя")
    public RegisterPage enterName(User user) {
        new WebDriverWait(webDriver, ofSeconds(Endpoints.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(fieldName));
        webDriver.findElement(fieldName).sendKeys(user.getName());
        return this;
    }

    @Step("Клик на кнопку для зарегистрации")
    public RegisterPage clickOnButtonToRegister() {
        webDriver.findElement(buttonToRegister).click();
        return this;
    }
    @Step("Проверка ошибки при неверном пароле")
    public void checkInvalidPassword() {
        new WebDriverWait(webDriver, ofSeconds(Endpoints.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(errorMessageWrongPassword));
        assertTrue(webDriver.findElement(errorMessageWrongPassword).isDisplayed());
        assertTrue(webDriver.findElement(redBorderFieldEmail).isDisplayed());
    }

    @Step("Клик по линку аккаунта")
    public void clickOnLinkToEnterAccount() {
        new WebDriverWait(webDriver, ofSeconds(Endpoints.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(linkToEnterToAccount));
        webDriver.findElement(linkToEnterToAccount).click();
    }
}