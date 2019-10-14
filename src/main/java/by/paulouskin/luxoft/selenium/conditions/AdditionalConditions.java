package by.paulouskin.luxoft.selenium.conditions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.Map;
import java.util.function.Function;

public class AdditionalConditions {

    public static ExpectedCondition<Boolean> jQueryAJAXCallsHaveCompleted() {
        final ExpectedCondition<Boolean> booleanExpectedCondition = driver -> (Boolean) ((JavascriptExecutor) driver)
                .executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
        return booleanExpectedCondition;
    }

    public static ExpectedCondition<Boolean> angularHasFinishedProcessing() {
        return webDriver -> Boolean.valueOf(
                ((JavascriptExecutor)webDriver)
                .executeScript("" +
                        "return (window.angular !== undefined) " +
                        "&& (angular.element(document).injector() !== undefined )" +
                        "&& (angular.element(document).injector().get('$http').pendingRequests.length === 0)")
                .toString());
    }

    public static ExpectedCondition<Boolean> listenerIsRegisteredOnElement(final String listenerType,
                                                                           final WebElement element) {
        return webDriver -> {
            Map<String, Object> registeredListeners = (Map<String, Object>)
                    ((JavascriptExecutor)webDriver).executeScript("" +
                            "return (window.jQuery != null) " +
                            "&& (jQuery._data(jQuery(arguments[0]).get(0)), 'events')", element);
            for (Map.Entry<String, Object> listener : registeredListeners.entrySet()){
                if (listener.getKey().equals(listenerType)) {
                    return true;
                }
            }
            return false;
        };
    }

    public static ExpectedCondition<Boolean> elementHasStoppedMoving(final WebElement element) {
        return webDriver -> {
            Point initialLocation = ((Locatable)element).getCoordinates()
                    .inViewPort();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {

            }
            Point finalLocation = ((Locatable)element).getCoordinates()
                    .inViewPort();
            return initialLocation.equals(finalLocation);
        };
    }
}
