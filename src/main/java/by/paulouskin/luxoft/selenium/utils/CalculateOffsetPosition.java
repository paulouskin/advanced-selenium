package by.paulouskin.luxoft.selenium.utils;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;

public class CalculateOffsetPosition {

    public enum CursorPosition {
        TOP_LEFT,
        CENTER
    }

    final WebElement parentElement;
    final WebElement childElement;
    final CursorPosition cursorPosition;
    private int xOffset = 0;
    private int yOffset = 0;

    public CalculateOffsetPosition(WebElement parentElement, WebElement childElement, CursorPosition cursorPosition) {
        this.parentElement = parentElement;
        this.childElement = childElement;
        this.cursorPosition = cursorPosition;
        calculateOffset();
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }

    private void calculateOffset() throws ElementNotVisibleException {
        int parentHeight = parentElement.getSize().getHeight();
        int parentWidth = parentElement.getSize().getWidth();
        int childHeight = childElement.getSize().getHeight();
        int childWidth = childElement.getSize().getWidth();

        if (childHeight >= parentHeight &&
                childWidth >= parentWidth) {
            throw new ElementNotVisibleException("The child element is totally covering parent one.");
        }

        if (cursorPosition.equals(CursorPosition.TOP_LEFT)) {
            xOffset = 1;
            yOffset = 1;
        }

        if (cursorPosition.equals(CursorPosition.CENTER)) {
            if (childHeight < parentHeight) {
                xOffset = (childHeight / 2) + 1;
            }
            if (childWidth < parentWidth) {
                yOffset = (childWidth / 2) + 1;
            }
        }

    }


}
