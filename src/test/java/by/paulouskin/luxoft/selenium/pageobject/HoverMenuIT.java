package by.paulouskin.luxoft.selenium.pageobject;

import by.paulouskin.luxoft.selenium.config.DriverBase;
import by.paulouskin.luxoft.selenium.pageobjects.HoverMenuPage;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HoverMenuIT extends DriverBase {

    @Test
    public void testHoverMenuOption() {
        HoverMenuPage hPage = new HoverMenuPage();
        hPage.goTo();
        hPage.hoverOverMenu();
    }
}
