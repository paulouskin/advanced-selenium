package by.paulouskin.luxoft.selenium.sampletests;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.containsInAnyOrder;

public class SampleSeleniumTest {

    private WebDriver webDriver;

    @BeforeTest
    public void setUp() {
        webDriver = new ChromeDriver();
        //webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void FirstSampleSeleniumTest() throws InterruptedException {
        webDriver.get("http://www.etsy.com");
        webDriver.findElement(By.cssSelector("div.alert")).findElement(By.xpath("//button[text()='Accept']")).click();
        hoverAndClick(webDriver, "Home & Living","Pet Supplies");
        Thread.sleep(2000);
        WebElement search_field = webDriver.findElement(By.cssSelector("input#search-query"));
        search_field.clear();
        search_field.sendKeys("leather bag");
        webDriver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();
        new WebDriverWait(webDriver,60)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class*='search-listings-group']")));
        //Apply handmade, on sale, ships to russia
        WebElement search_filter_form = webDriver.findElement(By.cssSelector("form#search-filter-reset-form"));
        WebElement free_shipping = search_filter_form.
                findElement(By.linkText("Free shipping"));
        free_shipping.click();
        webDriver.findElement(By.linkText("On sale")).click();
        new Select(webDriver.findElement(By.cssSelector("select[name='ship_to']"))).selectByVisibleText("Russia");
        //Check if tags for applied filters are visible in search result
        List<String> filter_tags = webDriver.findElements(By.cssSelector(".tag")).stream()
                .map(webElement -> webElement.getText()).collect(Collectors.toList());
        List<String> filter_tags2 = new ArrayList<>();
        List<WebElement> tags_elements = webDriver.findElements(By.cssSelector(".tag"));
        for (WebElement tag_el : tags_elements) {
            filter_tags2.add(tag_el.getText());
        }
        MatcherAssert.assertThat(filter_tags2, containsInAnyOrder("Free shipping","On sale","Ships to Russia"));
    }

    @AfterTest
    public void tearDown() {
        webDriver.quit();
    }

    private void hoverAndClick(WebDriver webDriver, String category, String subCategory) {
        Actions builder = new Actions(webDriver);
        WebElement cat_el = webDriver.findElement(
                By.xpath(String.format("//*[@role='menuitem' and contains(.,'%s')]",category)));
        Action moveToCat = builder.moveToElement(cat_el).build();
        moveToCat.perform();
        WebElement subCat_el = new WebDriverWait(webDriver,5).
                until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(String.format("//*[@role='menuitem' and contains(.,'%s')]",subCategory))));
        Action moveToSubCat = builder.moveToElement(subCat_el).click().build();
        moveToSubCat.perform();
    }

}
