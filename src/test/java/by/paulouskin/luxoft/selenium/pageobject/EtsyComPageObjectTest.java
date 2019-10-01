package by.paulouskin.luxoft.selenium.pageobject;

import by.paulouskin.luxoft.selenium.drivermanager.DriverManager;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EtsyComPageObjectTest {

    private WebDriver webDriver;

    @BeforeMethod
    public void setUp() {
        webDriver = DriverManager.getChromeDriver(Boolean.valueOf(System.getProperty("isHeadless")));
    }

    @Test
    public void mainPageLoadTest() {
       webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
       webDriver.get("http://www.etsy.com");
       BaseLuxoftPageObject bp = new BaseLuxoftPageObject(webDriver, By.cssSelector(".etsy-icon"));
       new PolicyModalWindow(webDriver).acceptPolicy();
        List<WebElement> popularItems = webDriver.findElements(By.cssSelector("li.v2-listing-car"));
       assertThat(webDriver.getTitle().contains("Etsy"), equalTo(true));
    }

    @Test
    public void listOfPopularItemsTest() {
       Function<WebElement, String> ratingMapper = e -> e.findElement(By.cssSelector("input[name='rating']")).getAttribute("value");
       webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
       webDriver.get("http://www.etsy.com");
       new PolicyModalWindow(webDriver).acceptPolicy();
       List<WebElement> popularItems = webDriver.findElements(By.cssSelector("li.v2-listing-car"));
       //assertThat(popularItems.size(), equalTo(6));
       double averageRating = popularItems.stream()
               .map(ratingMapper)
               .mapToDouble(s -> Double.valueOf(s))
               .average().orElseThrow(() -> new IllegalArgumentException("Error"));
       assertThat(averageRating, greaterThan(4.0));
    }

    @Test
    public void localizationSettingsTest() {
        webDriver.get("http://www.etsy.com");
        BaseLuxoftPageObject bp = new BaseLuxoftPageObject(webDriver, By.cssSelector(".etsy-icon"));
        new PolicyModalWindow(webDriver).acceptPolicy();
        WebElement localeSettingsButton = bp.findElementWithWait(By.cssSelector("button[aria-controls='wt-locale-picker-overlay']"));
        localeSettingsButton.click();
        assertThat(webDriver.findElement(By.id("wt-locale-picker-overlay")).isDisplayed(), equalTo(true));
    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }

}
