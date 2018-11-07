package by.paulouskin.luxoft.selenium.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BaseLuxoftPageObject extends LoadableComponent<BaseLuxoftPageObject> {

    WebDriver webDriver;
    By pageWrapper;
    private final int MAX_WAIT_TIME = 60;
    private final int LOAD_WAIT_TIME = 5;

    public BaseLuxoftPageObject(WebDriver webDriver, By pageWrapper) {
        this.webDriver = webDriver;
        this.pageWrapper = pageWrapper;
    }

    public WebElement findElementWithWait(By locator) {
        return new WebDriverWait(webDriver, MAX_WAIT_TIME).until(
                we -> we.findElement(locator)
        );
    }

    public List<WebElement> findElementsWithWait(By locator) {
        return new WebDriverWait(webDriver, MAX_WAIT_TIME).until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)
        );
    }


    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        if(!isPageWrapperVisible()) {
            webDriver.quit();
            throw new Error("Page didn't load properly.");
        }
    }

    private boolean isPageWrapperVisible() {
        try {
            new WebDriverWait(webDriver, LOAD_WAIT_TIME).until(
                    ExpectedConditions.visibilityOfElementLocated(pageWrapper)
            );
        } catch (WebDriverException ex) {
            captureScreenShot();
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private void captureScreenShot() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        String time = LocalDateTime.now().format(dtf);
        File screenshot1 = new File("target/screenshot"+time+".png");
        File outFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(outFile.toPath(), screenshot1.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void captureElementScreenshot(WebElement webElement) throws IOException {
        File screenshot = ((TakesScreenshot)webDriver)
                .getScreenshotAs(OutputType.FILE);
        BufferedImage fullImg = ImageIO.read(screenshot);
        Point elementLocation = webElement.getLocation();
        int webElHeight = webElement.getSize().getHeight();
        int webElWidth = webElement.getSize().getWidth();
        BufferedImage elementScreenShotImage = fullImg.getSubimage(
                elementLocation.getX(), elementLocation.getY(),
                webElWidth, webElHeight
        );
        ImageIO.write(elementScreenShotImage, "png",screenshot);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        String time = LocalDateTime.now().format(dtf);
        File elementScreenshot = new File("target/we_screenshot"+
                time +".png");
        Files.copy(screenshot.toPath(), elementScreenshot.toPath());
    }
}
