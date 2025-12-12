package extensions;

import com.google.inject.Guice;
import com.google.inject.Injector;
import factory.WebDriverFactory;
import modules.PagesModule;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class UiExtension implements BeforeEachCallback, BeforeAllCallback, AfterEachCallback, ParameterResolver {


    protected WebDriver driver;

    protected WebDriverFactory webDriverFactory = new WebDriverFactory();


    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        driver = webDriverFactory.create();
        Guice.createInjector(new PagesModule(driver))
                .injectMembers(context
                        .getTestInstance()
                        .orElseThrow(() -> new NoSuchElementException("Without WebDriverFactory")));

    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        webDriverFactory.init();

    }

    @Override
    public boolean supportsParameter
            (ParameterContext parameterContext, ExtensionContext extensionContext) {
        return true;
    }

    @Override
    public Object resolveParameter
            (ParameterContext parameterContext, ExtensionContext extensionContext) {
        Field field = Arrays.stream(parameterContext.getParameter().getType().getDeclaredFields())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Object not found"));

        Injector injector = Guice.createInjector(new PagesModule(driver));
        return injector.getInstance(parameterContext.getParameter().getType());
    }
}

