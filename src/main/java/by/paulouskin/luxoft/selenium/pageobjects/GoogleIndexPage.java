package by.paulouskin.luxoft.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleIndexPage {

    @FindBy(how = How.NAME, using = "q")
    private WebElement searchField;

    public GoogleIndexPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void goTo(WebDriver driver) {
        driver.get("http://www.google.com");
    }

    public void searchFor(String searchString, WebDriver driver) {
        searchField.clear();
        searchField.sendKeys(searchString);
        searchField.submit();
    }

    private ExpectedCondition<Boolean> pageTitleStartsWith(final String searchString) {
        return driver -> driver.getTitle().toLowerCase()
                .startsWith(searchString.toLowerCase());
    }

    public void waitUntilPageTitleContainsSearchQuery(String searchString, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10, 100);
        wait.until(pageTitleStartsWith(searchString));
    }
}
