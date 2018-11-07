package by.paulouskin.luxoft.selenium.drivermanager;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    public static ChromeDriver getChromeDriver(boolean isHeadless) {
        ChromeOptions opt = new ChromeOptions();
        opt.setHeadless(isHeadless);
        return new ChromeDriver(opt);
    }

    public static FirefoxDriver getFirefoxDriver() {
        return new FirefoxDriver();
    }

}
