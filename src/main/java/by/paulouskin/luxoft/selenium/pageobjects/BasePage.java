package by.paulouskin.luxoft.selenium.pageobjects;

import by.paulouskin.luxoft.selenium.config.DriverBase;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;

public abstract class BasePage {

    protected RemoteWebDriver driver;

    public BasePage() {
        driver = DriverBase.getDriver();
    }
}
