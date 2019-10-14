package by.paulouskin.luxoft.selenium.pageobject;

import by.paulouskin.luxoft.selenium.config.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.Assertion;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class BasicIT extends DriverBase {

    private ExpectedCondition<Boolean> pageTitleStartsWith(final String searchString) {
        return driver -> driver.getTitle().toLowerCase()
                .startsWith(searchString.toLowerCase());
    }

    private void googleExampleThatSearchesFor(final String searchString) {
        WebDriver driver = DriverBase.getDriver();
        driver.get("http://www.google.com");
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.clear();
        searchField.sendKeys(searchString);
        searchField.submit();
        WebDriverWait wait = new WebDriverWait(driver, 10, 100);
        wait.until(pageTitleStartsWith(searchString));
        assertThat(driver.getTitle(), containsString(searchString));
    }

    @Test
    public void googleCheeseExample() {
        googleExampleThatSearchesFor("Cheese!");
    }

    @Test
    public void googleMilkExample() {
        googleExampleThatSearchesFor("Milk!");
    }

}
