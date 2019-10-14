package by.paulouskin.luxoft.selenium.pageobject;

import by.paulouskin.luxoft.selenium.config.DriverBase;
import by.paulouskin.luxoft.selenium.pageobjects.GoogleIndexPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class BasicIT extends DriverBase {



    private void googleExampleThatSearchesFor(final String searchString) {
        GoogleIndexPage googlePage = new GoogleIndexPage();
        googlePage.goTo();
        googlePage.searchFor(searchString);
        googlePage.waitUntilPageTitleContainsSearchQuery(searchString);
        assertThat(googlePage.getPageTitle(), containsString(searchString));
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
