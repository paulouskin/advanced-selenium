package by.paulouskin.luxoft.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class DragAndDropPage extends BasePage {

    protected static final By CONSUMER_RECTANGLE = By.id("obliterate");
    protected static final By REMOVABLE_BOX = By.cssSelector("ul > li > div");

    public void goTo() {
        driver.get("http://web.masteringselenium.com/jsDragAndDrop.html");
    }

    protected List<WebElement> getBoxes() {
        return driver.findElements(REMOVABLE_BOX);
    }

    public int getRemovableBoxesCount() {
        return getBoxes().size();
    }

    public void destroyBoxWithIndex(int index) {
        WebElement consumer = driver.findElement(CONSUMER_RECTANGLE);
        WebElement targetBox = getBoxes().get(index - 1);
        Actions actionBuilder = new Actions(driver);
        actionBuilder.clickAndHold(targetBox)
                .pause(3000)
                .moveToElement(consumer)
                .release()
                .perform();
    }

}
