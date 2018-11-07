package by.paulouskin.luxoft.selenium.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PolicyModalWindow {

    private final String ACCEPT_BUTTON = "button.btn-outline-black";
    private final String MODAL_WRAPPER = "div.alert";

    private WebDriver webDriver;

    public PolicyModalWindow(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void acceptPolicy() {
        new WebDriverWait(webDriver,5).until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(MODAL_WRAPPER))
        ).findElement(By.cssSelector(ACCEPT_BUTTON)).click();
    }
}
