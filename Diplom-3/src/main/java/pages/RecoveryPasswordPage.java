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

@Data
@AllArgsConstructor
public class RecoveryPasswordPage {
    private final WebDriver webDriver;
    private final By linkToEnterToAccount = By.xpath(".//a[text()='Войти']");

    @Step("Клик на ссылку для входа в личный Кабинет")
    public void clickOnLinkToEnterAccount() {
        new WebDriverWait(webDriver, ofSeconds(Endpoints.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(linkToEnterToAccount));
        webDriver.findElement(linkToEnterToAccount).click();
    }
}

