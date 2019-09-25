package selenium.onet.tests;

import framework.tools.utils.dataprovider.LoginDataProvider;
import framework.tools.utils.dataprovider.UrlProvider;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import selenium.common.tests.BaseTest;
import selenium.onet.pages.PageObjectOnetManager;

public class ShouldNotLoginTest extends BaseTest {

    private static String url;
    private static String addressEmail;
    private static String password;
    private PageObjectOnetManager manager;

    public ShouldNotLoginTest() {
        manager = new PageObjectOnetManager(webDriver);
    }

    @BeforeClass
    public static void setUp() {
        url = UrlProvider.ONET.getUrl();
        addressEmail = LoginDataProvider.USER_LOGIN_WITH_WRONG_DATA.getUserEmailAddress();
        password = LoginDataProvider.USER_LOGIN_WITH_WRONG_DATA.getPassword();
    }

    @AfterClass
    public static void treadDown() {
        webDriver.quit();
        tearDownGridIfNeeded();
    }

    @Test
    public void asUserWithWrongLoginDataShouldNotLoginToEmailBoxTest() {
        manager.formPage()
                .loadPage(url);
        manager.formPage()
                .enterAddressEmail(addressEmail)
                .enterPassword(password)
                .closeRodoMsgIfIsVisible()
                .clickLoginButton();
        Assert.assertTrue(
                manager.formPage().errorMsgIsVisible()
        );
    }
}
