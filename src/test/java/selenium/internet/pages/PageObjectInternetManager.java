package selenium.internet.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObjectInternetManager {

    private final WebDriver webDriver;
    private MainPage mainPage;
    private FileUploadPage fileUploadPage;

    public PageObjectInternetManager(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public MainPage getMainPage() {
        if (mainPage == null) {
            mainPage = PageFactory.initElements(webDriver, MainPage.class);
        }
        return mainPage;
    }

    public FileUploadPage getFileUploadPage() {
        if (fileUploadPage == null) {
            fileUploadPage = PageFactory.initElements(webDriver, FileUploadPage.class);
        }
        return fileUploadPage;
    }
}
