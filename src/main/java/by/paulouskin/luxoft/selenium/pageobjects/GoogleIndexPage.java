package by.paulouskin.luxoft.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleIndexPage {

    public static By searchFieldLocator = By.name("q");



    public static void goTo(WebDriver driver) {
        driver.get("http://www.google.com");
    }

    public static void searchFor(String searchString, WebDriver driver) {
        WebElement searchField = driver.findElement(searchFieldLocator);
        searchField.clear();
        searchField.sendKeys(searchString);
        searchField.submit();
    }

    private static ExpectedCondition<Boolean> pageTitleStartsWith(final String searchString) {
        return driver -> driver.getTitle().toLowerCase()
                .startsWith(searchString.toLowerCase());
    }

    public static void waitUntilPageTitleContainsSearchQuery(String searchString, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10, 100);
        wait.until(pageTitleStartsWith(searchString));
    }
}
