package by.paulouskin.luxoft.selenium.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PolicyModalWindow {

    private final WebDriver webDriver;

    public PolicyModalWindow(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void acceptPolicy() {
        String MODAL_WRAPPER = "div.alert";
        String ACCEPT_BUTTON = "button.btn-outline-black";
        new WebDriverWait(webDriver,5).until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(MODAL_WRAPPER))
        ).findElement(By.cssSelector(ACCEPT_BUTTON)).click();
    }
}
