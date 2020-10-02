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

public class Custom {

    private static final int DEFAULT_TIMEOUT_IN_SEC = 10;
    private static final Logger logger = LoggerFactory.getLogger(Custom.class);
    private final WebDriver webDriver;

    public Custom(WebDriver driver) {
        this.webDriver = driver;
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
        element.clear();
        element.sendKeys(text);
    }

    public void clickElement(final WebElement element, int defaultTimeoutInSec) {
        try {
            element.wait(defaultTimeoutInSec);
        } catch (Exception ex) {
            //no-op
        }
        element.click();
    }

    public boolean isElementPresent(By locator) {
        FluentWait<WebDriver> wait = createWait();
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException ex) {
            logger.error("Element not found: ", ex);
            return false;
        }
    }

    private FluentWait<WebDriver> createWait() {
        return new WebDriverWait(webDriver, DEFAULT_TIMEOUT_IN_SEC);
    }
}
