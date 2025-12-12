package factory;

import exceptions.BrowserNotSupportedException;
import factory.settings.ChromeSettings;
import factory.settings.EdgeSettings;
import factory.settings.FirefoxSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Locale;

public class WebDriverFactory {

    protected String browser = System.getProperty("browser.type")
            .toLowerCase(Locale.ROOT);

    public WebDriver create() {

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions = new ChromeSettings().settings(chromeOptions);
                return new ChromeDriver(chromeOptions);

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions = new FirefoxSettings().settings(firefoxOptions);
                return new FirefoxDriver(firefoxOptions);

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions = new EdgeSettings().settings(edgeOptions);
                return new EdgeDriver(edgeOptions);

            default:
                throw new BrowserNotSupportedException(browser);
        }
    }



    public void init() {

        switch (browser) {

            case "chrome" -> WebDriverManager.chromedriver().setup();
            case "firefox" -> WebDriverManager.firefoxdriver().setup();
            case "edge" -> WebDriverManager.edgedriver().setup();

            default -> throw new
                    BrowserNotSupportedException(browser);
        }
    }
}