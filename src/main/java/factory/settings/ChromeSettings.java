package factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeSettings implements ISettings<ChromeOptions> {

    @Override
    public ChromeOptions settings(ChromeOptions chromeOptions) {

        chromeOptions.addArguments("--start-fullscreen");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");

        return chromeOptions;
    }
}
