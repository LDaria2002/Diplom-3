package pages;

import service.Endpoints;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static java.time.Duration.ofSeconds;
import static org.junit.Assert.assertTrue;

@Data
@AllArgsConstructor
public class PersonalAccount {
    private final WebDriver webDriver;
    private final By exitButton = By.xpath(".//button[text()='Выход']");
    @Step
    public PersonalAccount checkSwitchToAccount() {
        new WebDriverWait(webDriver,ofSeconds(Endpoints.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(exitButton));
        assertTrue(webDriver.findElement(exitButton).isDisplayed());
        return this;
    }
    @Step("Выход из учетной записи")
    public void exitFromAccount() {
        new WebDriverWait(webDriver, ofSeconds(Endpoints.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(exitButton));
        webDriver.findElement(exitButton).click();
    }
}
