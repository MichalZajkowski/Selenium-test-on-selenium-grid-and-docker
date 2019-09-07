package framework.tools.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.midi.Track;
import java.time.Duration;

public class Custom {

    private static final int DEFAULT_TIMEOUT_IN_SEC = 10;
    private static Logger logger = LoggerFactory.getLogger(Custom.class);
    private final WebDriver webDriver;

    public Custom(WebDriver driver) {
        this.webDriver = driver;
    }

    private FluentWait<WebDriver> createFluentWait() {
        return new WebDriverWait(webDriver, DEFAULT_TIMEOUT_IN_SEC);
    }

    private FluentWait<WebDriver> createWait() {
        return createFluentWait().withTimeout(Duration.ofSeconds(Custom.DEFAULT_TIMEOUT_IN_SEC));
    }

    public boolean isElementVisible(final WebElement webElement) {
        FluentWait<WebDriver> wait = createWait();
        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (TimeoutException ex) {
            logger.error("Element not found: ", ex);
            return false;
        }
    }

    public void clearAndSendKey(final WebElement element, final String text) {
        try {
            element.clear();
            element.sendKeys(text);
        } catch (Throwable ex) {
            logger.error("Clear and send key to element error: ", ex);
        }
    }

    public void clickElement(final WebElement element, int defaultTimeoutInSec) {
        try {
            element.wait(defaultTimeoutInSec);
            element.click();
        } catch (Throwable ex) {
            logger.error("No such element found error: ", ex);
        }
    }

    public boolean isElementPresent(final By locator) {
        FluentWait<WebDriver> wait = createWait();
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException ex) {
            logger.error("Element not found: ", ex);
            return false;
        }
    }


}
