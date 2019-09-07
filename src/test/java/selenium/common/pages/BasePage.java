package selenium.common.pages;

import framework.tools.utils.Custom;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage<T extends BasePage<T>> {

    public WebDriver webDriver;
    protected Custom custom;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        custom = new Custom(webDriver);
    }

    protected abstract T getThis();

    public abstract boolean isLoaded();

    public T makeElementScaled(WebElement uploadButton) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].style.transform='scale(1)';", uploadButton);
        return getThis();
    }

    public BasePage loadPage(String url) {
        webDriver.get(url);
        return this;
    }
}
