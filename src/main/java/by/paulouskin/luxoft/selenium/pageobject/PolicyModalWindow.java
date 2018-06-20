package by.paulouskin.luxoft.selenium.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PolicyModalWindow extends BaseLuxoftPageObject {

    public PolicyModalWindow(WebDriver webDriver) {
        super(webDriver);
    }

    public void acceptPolicy() {
        findElementWithWait(By.cssSelector("div.alert"))
                .findElement(By.xpath("//button[text()='Accept']")).click();
    }
}
