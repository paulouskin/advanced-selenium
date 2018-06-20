package by.paulouskin.luxoft.selenium.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class EtsyComPageObject extends BaseLuxoftPageObject {

    //Selectors
    private final String SEARCH_FIELD = "#search-query";
    private final String SEARCH_BUTTON = "//button[contains(text(),'Search')]";
    private final String SEARCH_FIRST_SUGGESTION = "#search-suggestions ul li.as-first";
    private final String SEARCH_SUGGESTION_LIST = "#search-suggestions ul li";
    private  String FILTER_FOR_CATEGORY_LINK = "//h5[text()='%s']/..//a[contains(.,'%s')]";
    private final String SHIPPING_COUNTRY_SELECTBOX = "#ship_to_select";
    private final String FILTER_TAGS = ".tag";

    private PolicyModalWindow policyModalWindow;
    public EtsyComPageObject(WebDriver webDriver) {
        super(webDriver);
        policyModalWindow = new PolicyModalWindow(webDriver);
    }

    public void get() {
        webDriver.get("http://www.etsy.com");
        policyModalWindow.acceptPolicy();
    }

    //Page Interaction
    public EtsyComPageObject searchForItem(String item) {
        WebElement searchField = getSearchField();
        searchField.clear();
        searchField.sendKeys(item);
        waitForSuggestionDropDown();
        //getSearchButton().click();
        return this;
    }


    public EtsyComPageObject pickFirstSuggestion() {
        WebElement firstSuggestion = getFirstSuggestionElement();
        firstSuggestion.click();
        return this;
    }

    public EtsyComPageObject applyFilterFromCategory(String filter, String category) {
        WebElement filter_link = checkFilterForCategorySection(filter,category);
        filter_link.click();
        return this;
    }

    private WebElement checkFilterForCategorySection(String filter, String category) {
        String xpath = String.format(FILTER_FOR_CATEGORY_LINK, category, filter);
        return new WebDriverWait(webDriver,10).until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))
        );
    }

    public EtsyComPageObject selectShippingCountry(String shippingCountry) {
        Select shippingCountryDropbox = new Select(getShippingCountryElement());
        shippingCountryDropbox.selectByVisibleText(shippingCountry);
        return this;
    }
    //Wait
    private void waitForSuggestionDropDown() {
        new WebDriverWait(webDriver,30).until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(SEARCH_SUGGESTION_LIST)));
    }
    //Getters

    private WebElement getSearchField() {
        return findElementWithWait(By.cssSelector(SEARCH_FIELD));
    }


    private WebElement getFirstSuggestionElement() {
        return findElementWithWait(By.cssSelector(SEARCH_FIRST_SUGGESTION));
    }

    private WebElement getSearchButton() {
        return findElementWithWait(By.xpath(SEARCH_BUTTON));
    }

    private WebElement getShippingCountryElement() {
        return findElementWithWait(By.cssSelector(SHIPPING_COUNTRY_SELECTBOX));
    }

    public List<String> getAppliedFilters() {
        return findElementsWithWait(By.cssSelector(FILTER_TAGS)).stream()
                .map(webElement -> webElement.getText())
                .collect(Collectors.toList());
    }
}
