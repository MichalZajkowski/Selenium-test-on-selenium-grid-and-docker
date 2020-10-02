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

    @Override
    protected LoginFormPage getThis() {
        return this;
    }

    @Override
    public boolean isLoaded() {
        return getCustom().isElementVisible(addressEmailInput) && getCustom().isElementVisible(passwordInput);
    }

    public LoginFormPage enterAddressEmail(String emailAddress) {
        getCustom().clearAndSendKey(addressEmailInput, emailAddress);
        return this;
    }

    public LoginFormPage enterPassword(String password) {
        getCustom().clearAndSendKey(passwordInput, password);
        return this;
    }

    public void clickLoginButton() {
        getCustom().isElementVisible(loginButton);
        getCustom().clickElement(loginButton, 5);
    }

    public LoginFormPage closeRodoMsgIfIsVisible(){
        try{
            getCustom().clickElement(rodoMsg, 5);
        } catch (Exception e){
            //no-op
        }
        return this;
    }

    public boolean errorMsgIsVisible() {
        return getCustom().isElementVisible(errorMsg);
    }
}
