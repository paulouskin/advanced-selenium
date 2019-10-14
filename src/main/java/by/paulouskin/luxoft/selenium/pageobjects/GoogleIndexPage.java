package by.paulouskin.luxoft.selenium.pageobjects;

import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleIndexPage extends BasePage{

    private Query searchField = new Query(By.name("q"), driver);

    public void goTo() {
        driver.get("http://www.google.com");
    }

    public void searchFor(String searchString) {
        waitForSearchFieldVisibility();
        WebElement searchFieldElement = searchField.findWebElement();
        searchFieldElement.clear();
        searchFieldElement.sendKeys(searchString);
        searchFieldElement.submit();
    }

    private ExpectedCondition<Boolean> pageTitleStartsWith(final String searchString) {
        return driver -> driver.getTitle().toLowerCase()
                .startsWith(searchString.toLowerCase());
    }

    private void waitForSearchFieldVisibility() {
        new WebDriverWait(driver, 10, 100)
                .until(ExpectedConditions.visibilityOfElementLocated(searchField.locator()));
    }

    public void waitUntilPageTitleContainsSearchQuery(String searchString) {
        WebDriverWait wait = new WebDriverWait(driver, 10, 100);
        wait.until(pageTitleStartsWith(searchString));
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
