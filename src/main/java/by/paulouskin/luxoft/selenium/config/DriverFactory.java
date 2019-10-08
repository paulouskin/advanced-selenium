package by.paulouskin.luxoft.selenium.config;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

    private RemoteWebDriver webDriver;
    private DriverType selectedDriverType;
    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch");

    public DriverFactory() {
        DriverType driverType = DriverType.FIREFOX;
        String browser = System.getProperty("browser", driverType.toString().toUpperCase());
        try {
            driverType = DriverType.valueOf(browser.toUpperCase());
        } catch (IllegalArgumentException ignored) {
            System.err.println("Unknown driver specified, defaulting to '"+ driverType + "'...");
        } catch (NullPointerException ignored) {
            System.err.println("No driver specified, defaulting to '"+ driverType + "'...");
        }
        selectedDriverType = driverType;
    }

    RemoteWebDriver getDriver() {
        if (null == webDriver) {
            instantiateWebDriver(selectedDriverType);
        }
        return webDriver;
    }

    private void instantiateWebDriver(DriverType driverType) {
        System.out.println(" ");
        System.out.println("Current Operating System: " +
                operatingSystem);
        System.out.println("Current Architecture: " +
                systemArchitecture);
        System.out.println("Current Browser Selection: " + selectedDriverType);
        System.out.println(" ");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        webDriver = driverType.getWebDriverObject(desiredCapabilities);
    }

    void quitDriver() {
        if (null != webDriver) {
            webDriver.quit();
            webDriver = null;
        }
    }
}
