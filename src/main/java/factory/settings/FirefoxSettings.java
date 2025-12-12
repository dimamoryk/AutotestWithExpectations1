package factory.settings;

import org.openqa.selenium.firefox.FirefoxOptions;


public class FirefoxSettings implements ISettings<FirefoxOptions> {

    @Override
    public FirefoxOptions settings(FirefoxOptions firefoxOptions) {

        firefoxOptions.addArguments("--start-fullscreen");
        firefoxOptions.addArguments("--no-sandbox");
        firefoxOptions.addArguments("--disable-dev-shm-usage");

        return firefoxOptions;
    }
}
