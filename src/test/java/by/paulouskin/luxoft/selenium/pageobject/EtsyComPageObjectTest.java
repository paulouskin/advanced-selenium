package by.paulouskin.luxoft.selenium.pageobject;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;

public class EtsyComPageObjectTest {

    private WebDriver webDriver;
    private EtsyComPageObject etsyCom;

    @DataProvider
    public Object[][] testData() {
        return new Object[][] {
                new Object[] {"leather bag"}
                //new Object[] {"wedding gifts"},
                //new Object[] {"paper toys"}
        };
    }

    @BeforeClass
    public void setUp() {
        webDriver = new ChromeDriver();
        etsyCom = new EtsyComPageObject(webDriver).get();
    }

    @Test(dataProvider = "testData")
    public void pickFirstSearchSuggestionAndApplyResultFilters(String searchItem) {
        etsyCom
                .searchForItem(searchItem)
                .pickFirstSuggestion()
                .applyFilterFromCategory("Free shipping","Shipping")
                //.applyFilterFromCategory("On sale", "Special offers")
                .selectShippingCountry("Russia");
        List<String> filter_tag = etsyCom.getAppliedFilters();
        MatcherAssert.assertThat(filter_tag,
                containsInAnyOrder("Free shipping",
                        //"On sale",
                        "Ships to Russia"));
    }




    @AfterClass
    public void tearDown() {
        webDriver.quit();
    }

}
