package by.paulouskin.luxoft.selenium.pageobjects;

import by.paulouskin.luxoft.selenium.utils.CalculateOffsetPosition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DragAndDropWithOffsetPage extends DragAndDropPage {


    private static final By BOX_TEXT = By.cssSelector("span");

    @Override
    public void goTo() {
        driver.get("http://web.masteringselenium.com/jsDragAndDropWithHandle.html");
    }

    @Override
    public void destroyBoxWithIndex(int index) {
        WebElement consumer = driver.findElement(CONSUMER_RECTANGLE);
        WebElement targetBox = getBoxes().get(index - 1);
        WebElement targetBoxText = targetBox.findElement(BOX_TEXT);
        CalculateOffsetPosition op = new CalculateOffsetPosition(
                targetBox, targetBoxText, CalculateOffsetPosition.CursorPosition.CENTER
        );
        Actions builder = new Actions(driver);
        builder.moveToElement(targetBox)
                .moveByOffset(op.getXOffset(), op.getYOffset())
                .clickAndHold()
                .moveToElement(consumer)
                .pause(2000)
                .release()
                .perform();
    }
}
