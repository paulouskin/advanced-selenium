package by.paulouskin.luxoft.selenium.config;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;

public enum DriverType implements DriverSetup {

    FIREFOX {
        @Override
        public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            FirefoxOptions options = new FirefoxOptions();
            options.merge(capabilities);
            return new FirefoxDriver(options);
        }
    },

    CHROME {
        @Override
        public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            HashMap<String, Object> chromePreferences = new HashMap<>();
            chromePreferences.put("profile.password_manager_enabled", false);
            ChromeOptions options = new ChromeOptions();
            options.merge(capabilities);
            options.addArguments("--no-default-browser-check");
            options.setExperimentalOption("prefs", chromePreferences);
            return new ChromeDriver(options);
        }
    }
}
