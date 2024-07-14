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
public class LoginPage {
    private final WebDriver webDriver;
    private final By fieldEmail = By.xpath(".//label[text()='Email']/parent::div/input");
    private final By fieldPassword = By.xpath(".//label[text()='Пароль']/parent::div/input");
    private final By linkToAuthorize = By.xpath(".//a[text()='Зарегистрироваться']");
    private final By buttonEnter = By.xpath(".//button[text()='Войти']");
    private final By linkToRecoveryPassword = By.xpath(".//a[text()='Восстановить пароль']");


    @Step("Клик на кногпку для авторизации")
    public void clickOnLinkToAuth() {
        new WebDriverWait(webDriver, ofSeconds(Endpoints.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(fieldEmail));
        webDriver.findElement(linkToAuthorize).click();
    }
    @Step("Проверка успешной регистрации")
    public void checkRegisterSuccess() {
        new WebDriverWait(webDriver, ofSeconds(Endpoints.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonEnter));
        assertTrue(webDriver.findElement(buttonEnter).isDisplayed());
    }
    @Step("Клик на кнопку enter")
    public void clickOnButtonEnter() {
        webDriver.findElement(buttonEnter).click();
    }
    @Step("Ввод пароля")
    public LoginPage enterPassword(User user) {
        webDriver.findElement(fieldPassword).sendKeys(user.getPassword());
        return this;
    }
    @Step("Ввод адреса электронной почты")
    public LoginPage enterEmail(User user) {
        webDriver.findElement(fieldEmail).sendKeys(user.getEmail());
        return this;
    }
    @Step("Клик на ссылку, чтобы восстановить пароль")
    public void clickOnLinkToRecoverPassword() {
        new WebDriverWait(webDriver, ofSeconds(Endpoints.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(linkToRecoveryPassword));
        webDriver.findElement(linkToRecoveryPassword).click();
    }
    @Step("Проверка успешного выхода из системы")
    public void checkLogoutSuccess() {
        new WebDriverWait(webDriver, ofSeconds(Endpoints.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonEnter));
        assertTrue(webDriver.findElement(buttonEnter).isDisplayed());
    }
}
