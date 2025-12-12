package factory.settings;

import org.openqa.selenium.edge.EdgeOptions;


public class EdgeSettings implements ISettings<EdgeOptions> {

    @Override
    public EdgeOptions settings(EdgeOptions edgeOptions) {

        edgeOptions.addArguments("--start-fullscreen");
        edgeOptions.addArguments("--no-sandbox");
        edgeOptions.addArguments("--disable-dev-shm-usage");

        return edgeOptions;
    }
}
