package selenium.interia.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.interia.pages.LoginFormPage;

public class PageObjectGoogleFlightsManager {

    private final WebDriver webDriver;
    private LoginFormPage loginFormPage;

    public PageObjectGoogleFlightsManager(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginFormPage formPage() {
        if (loginFormPage == null) {
            loginFormPage = PageFactory.initElements(webDriver, LoginFormPage.class);
        }
        return loginFormPage;
    }

}
