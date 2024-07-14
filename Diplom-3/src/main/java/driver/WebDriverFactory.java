package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class WebDriverFactory {
    private static WebDriver webDriver;
    public static WebDriver getWebDriver() {
        if (webDriver == null) {
            String browser = System.getProperty("browser", "yandex");
            switch (browser) {
                case "chrome":
                    return WebDriverManager.chromedriver().create();
                case "firefox":
                    return WebDriverManager.firefoxdriver().create();
                case "yandex":
                    System.setProperty("src/test/resources/yandexdriver", "src/test/resources/yandexdriver");
                    return new ChromeDriver();
                default:
                    throw new RuntimeException("Unsupported browser: " + browser);
            }
        }
        return webDriver;
    }
}