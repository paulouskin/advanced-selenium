package by.paulouskin.luxoft.selenium.pageobjects;

import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class HoverMenuPage extends BasePage{

    By menuLocator = By.id("services");
    By servicesSubMenuLocator = By.cssSelector("#services ul li:nth-child(2)");

    public void goTo() {
        driver.get("http://web.masteringselenium.com/cssMenu.html");
    }

    private void waitForSubmenu() {
         new WebDriverWait(driver, 2, 100)
                .until(webDriver -> webDriver.findElement(servicesSubMenuLocator).isDisplayed());
    }


    public void hoverOverMenu(){
        WebElement menu = driver.findElement(menuLocator);
        WebElement subMenu = driver.findElement(servicesSubMenuLocator);
        Actions builder = new Actions(driver);
        builder.moveToElement(menu).perform();
        waitForSubmenu();
        builder.moveToElement(subMenu).click().perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
