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

    EtsyComPageObject(WebDriver webDriver) {
        super(webDriver, By.id("content"));
        PolicyModalWindow policyModalWindow = new PolicyModalWindow(webDriver);
        webDriver.get("http://www.etsy.com");
        policyModalWindow.acceptPolicy();
    }

    /*public void acceptPolicy() {
        policyModalWindow.acceptPolicy();
    }*/

    public EtsyComPageObject get() {
        try {
            isLoaded();
            return this;
        } catch (Error e) {
            load();
        }
        isLoaded();
        return this;
    }

    //Page Interaction
    public EtsyComPageObject searchForItem(String item) {
        WebElement searchField = getSearchField();
        searchField.clear();
        searchField.sendKeys(item);
        waitForSuggestionDropDown();
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
        String FILTER_FOR_CATEGORY_LINK = "//h3[text()='%s']/../..//a[contains(.,'%s')]";
        String xpath = String.format(FILTER_FOR_CATEGORY_LINK, category, filter);
        return new WebDriverWait(webDriver,10).until(
                webDriver1 -> webDriver1.findElement(By.xpath(xpath))
        );
    }

    public EtsyComPageObject selectShippingCountry(String shippingCountry) {
        Select shippingCountryDropbox = new Select(getShippingCountryElement());
        shippingCountryDropbox.selectByVisibleText(shippingCountry);
        return this;
    }
    //Wait
    private void waitForSuggestionDropDown() {
        String SEARCH_SUGGESTION_LIST = "#search-suggestions ul li";
        new WebDriverWait(webDriver,30).until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(SEARCH_SUGGESTION_LIST)));
    }
    //Getters

    private WebElement getSearchField() {
        //Selectors
        String SEARCH_FIELD = "#search-query";
        return findElementWithWait(By.cssSelector(SEARCH_FIELD));
    }


    private WebElement getFirstSuggestionElement() {
        String SEARCH_FIRST_SUGGESTION = "#search-suggestions ul li.as-first";
        return findElementWithWait(By.cssSelector(SEARCH_FIRST_SUGGESTION));
    }

    private WebElement getSearchButton() {
        String SEARCH_BUTTON = "//button[contains(text(),'Search')]";
        return findElementWithWait(By.xpath(SEARCH_BUTTON));
    }

    private WebElement getShippingCountryElement() {
        String SHIPPING_COUNTRY_SELECTBOX = "#ship_to_select";
        return findElementWithWait(By.cssSelector(SHIPPING_COUNTRY_SELECTBOX));
    }

    public List<String> getAppliedFilters() {
        String FILTER_TAGS = ".tag";
        return findElementsWithWait(By.cssSelector(FILTER_TAGS)).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
