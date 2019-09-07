package selenium.interia.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.common.pages.BasePage;

public class LoginFormPage extends BasePage<LoginFormPage> {

    @FindBy(id = "formEmail")
    private WebElement addressEmailInput;

    @FindBy(id = "formPassword")
    private WebElement passwordInput;

    @FindBy(id = "formSubmit")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id='pocztaLoginForm']/div[@class='errorMsg']")
    private WebElement errorMsg;

    public LoginFormPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void enterAddressEmail(String departureCity) {
        custom.clearAndSendKey(addressEmailInput, departureCity);
    }

    public void enterPassword(String arrivalCity) {
        custom.clearAndSendKey(passwordInput, arrivalCity);
    }

    public void clickLoginButton() {
        custom.isElementVisible(loginButton);
        custom.clickElement(loginButton, 5);
    }

    public boolean errorMsgIsVisible() {
        return custom.isElementVisible(errorMsg);
    }

    @Override
    protected LoginFormPage getThis() {
        return this;
    }

    @Override
    public boolean isLoaded() {
        return custom.isElementVisible(addressEmailInput) && custom.isElementVisible(passwordInput);
    }
}
