package selenium.onet.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.common.pages.BasePage;

public class LoginFormPage extends BasePage<LoginFormPage> {

    @FindBy(id = "f_login")
    private WebElement addressEmailInput;

    @FindBy(id = "f_password")
    private WebElement passwordInput;

    @FindBy(xpath = "//*[@id='loginForm']//input[2]")
    private WebElement loginButton;

    @FindBy(className = "messageContent")
    private WebElement errorMsg;

    @FindBy(xpath = "//*[@id='top']/div[3]/div[1]/div[2]//span")
    private WebElement rodoMsg;

    public LoginFormPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginFormPage enterAddressEmail(String emailAddress) {
        custom.clearAndSendKey(addressEmailInput, emailAddress);
        return this;
    }

    public LoginFormPage enterPassword(String password) {
        custom.clearAndSendKey(passwordInput, password);
        return this;
    }

    public LoginFormPage clickLoginButton() {
        custom.isElementVisible(loginButton);
        custom.clickElement(loginButton, 5);
        return this;
    }

    public LoginFormPage closeRodoMsgIfIsVisible(){
        try{
            custom.clickElement(rodoMsg, 5);
        } catch (Exception e){
            //no-op
        }
        return this;
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
