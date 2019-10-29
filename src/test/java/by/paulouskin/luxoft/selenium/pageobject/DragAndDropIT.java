package by.paulouskin.luxoft.selenium.pageobject;

import by.paulouskin.luxoft.selenium.config.DriverBase;
import by.paulouskin.luxoft.selenium.pageobjects.DragAndDropPage;
import by.paulouskin.luxoft.selenium.pageobjects.DragAndDropWithOffsetPage;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DragAndDropIT extends DriverBase {

    @Test
    public void itemRemovedAfterDragAndDrop() {
        DragAndDropPage page = new DragAndDropPage();
        page.goTo();
        int initialBoxCount = page.getRemovableBoxesCount();
        page.destroyBoxWithIndex(1);
        assertThat(page.getRemovableBoxesCount(), is(equalTo(initialBoxCount - 1)));
    }

    @Test
    public void itemRemovedAfterDragAndDropWithOffset() {
        DragAndDropPage page = new DragAndDropWithOffsetPage();
        page.goTo();
        int initialBoxCount = page.getRemovableBoxesCount();
        page.destroyBoxWithIndex(1);
        assertThat(page.getRemovableBoxesCount(), is(equalTo(initialBoxCount - 1)));
    }
}
