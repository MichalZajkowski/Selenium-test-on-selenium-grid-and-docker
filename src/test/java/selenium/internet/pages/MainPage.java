package selenium.internet.pages;

import framework.tools.utils.Custom;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.common.pages.BasePage;

public class MainPage extends BasePage<MainPage> {

    private Custom custom;

    @FindBy(xpath = "//h1[contains(text(), 'Welcome to the-internet')]")
    private WebElement theInternetHeader;

    @FindBy(xpath = "//a[contains(text(), 'File Upload')]")
    private WebElement fileUploadLink;

    @FindBy(xpath = "//a[contains(text(), 'Drag and Drop')]")
    private WebElement dragAndDropLink;

    public MainPage(WebDriver webDriver) {
        super(webDriver);
        init();
    }

    @Override
    protected MainPage getThis() {
        return this;
    }

    @Override
    public boolean isLoaded() {
        return custom.isElementVisible(theInternetHeader);
    }

    public void clickUploadLink() {
        fileUploadLink.click();
        getThis();
    }

    public MainPage clickDragAndDropLink() {
        dragAndDropLink.click();
        return getThis();
    }

    private void init(){
        custom = new Custom(getWebDriver());
    }
}
