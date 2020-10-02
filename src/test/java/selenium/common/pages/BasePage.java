package selenium.common.pages;

import framework.tools.utils.Custom;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage<T> {

    private final WebDriver webDriver;
    private Custom custom;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        init();
    }

    protected abstract T getThis();

    protected abstract boolean isLoaded();

    public void makeElementScaled(WebElement uploadButton) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].style.transform='scale(1)';", uploadButton);
        getThis();
    }

    public void loadPage(String url) {
        webDriver.get(url);
    }

    public WebDriver getWebDriver() {
        return this.webDriver;
    }

    private void init() {
        custom = new Custom(webDriver);
    }

    public Custom getCustom() {
        return custom;
    }
}
