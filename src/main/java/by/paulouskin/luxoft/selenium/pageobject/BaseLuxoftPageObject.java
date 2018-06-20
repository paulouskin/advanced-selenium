package by.paulouskin.luxoft.selenium.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseLuxoftPageObject {
    WebDriver webDriver;
    private final int MAX_WAIT_TIME = 60;
    public BaseLuxoftPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public WebElement findElementWithWait(By locator) {
        return new WebDriverWait(webDriver, MAX_WAIT_TIME).until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }
    public List<WebElement> findElementsWithWait(By locator) {
        return new WebDriverWait(webDriver, MAX_WAIT_TIME).until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)
        );
    }
}
