package by.paulouskin.luxoft.selenium.pageobject;

import by.paulouskin.luxoft.selenium.drivermanager.DriverManager;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;

public class EtsyComPageObjectTest {

    private WebDriver webDriver;

    @BeforeClass
    public void setUp() {
        boolean isHeadless = Boolean.valueOf(System.getProperty("isHeadless"));
        webDriver = DriverManager.getChromeDriver(isHeadless);
    }

    @Test
    public void pickFirstSearchSuggestionAndApplyResultFilters() {

    }

    @AfterClass
    public void tearDown() {
        webDriver.quit();
    }

}
