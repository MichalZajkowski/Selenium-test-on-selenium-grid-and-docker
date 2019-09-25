package selenium.onet.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObjectOnetManager {

    private final WebDriver webDriver;
    private LoginFormPage loginFormPage;

    public PageObjectOnetManager(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginFormPage formPage() {
        if (loginFormPage == null) {
            loginFormPage = PageFactory.initElements(webDriver, LoginFormPage.class);
        }
        return loginFormPage;
    }

}
