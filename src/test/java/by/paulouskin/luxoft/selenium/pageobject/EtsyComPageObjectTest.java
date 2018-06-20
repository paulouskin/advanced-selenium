package by.paulouskin.luxoft.selenium.pageobject;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;

public class EtsyComPageObjectTest {

    private WebDriver webDriver;
    private EtsyComPageObject etsyCom;

    @BeforeTest
    public void setUp() {
        webDriver = new ChromeDriver();
        etsyCom = new EtsyComPageObject(webDriver);
        etsyCom.get();
    }

    @Test
    public void pickFirstSearchSuggestionAndApplyResultFilters() {
        etsyCom.searchForItem("leather bag")
                .pickFirstSuggestion()
                .applyFilterFromCategory("Free shipping","Shipping")
                .applyFilterFromCategory("On sale", "Special offers")
                .selectShippingCountry("Russia");
        List<String> filter_tag = etsyCom.getAppliedFilters();
        MatcherAssert.assertThat(filter_tag, containsInAnyOrder("Free shipping", "On sale", "Ships to Russia"));
    }




    @AfterTest
    public void tearDown() {
        webDriver.quit();
    }

}
