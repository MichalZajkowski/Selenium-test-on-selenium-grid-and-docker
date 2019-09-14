package selenium.interia.tests;

import framework.tools.utils.dataprovider.LoginDataProvider;
import framework.tools.utils.dataprovider.UrlProvider;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import selenium.common.tests.BaseTest;
import selenium.interia.pages.PageObjectGoogleFlightsManager;

public class ShouldNotLoginTest extends BaseTest {

    private static String url;
    private static String addressEmail;
    private static String password;
    private PageObjectGoogleFlightsManager manager;


    public ShouldNotLoginTest() {
        manager = new PageObjectGoogleFlightsManager(webDriver);
    }

    @BeforeClass
    public static void setUp() {
        url = UrlProvider.INTERIA.getUrl();
        addressEmail = LoginDataProvider.DEPARTURE_CITY.getCityName();
        password = LoginDataProvider.ARRIVAL_CITY.getCityName();
    }

    @AfterClass
    public static void treadDown() {
        webDriver.quit();
        tearDownGridIfNeeded();
    }

    @Test
    public void shouldNotLogin() {
        manager.formPage()
                .loadPage(url);
        System.out.println("load page");
        manager.formPage()
                .enterAddressEmail(addressEmail);
        System.out.println("input email");

        manager.formPage()
                .enterPassword(password);
        System.out.println("input passowrd");

        manager.formPage()
                .clickLoginButton();
        System.out.println("click login button");

        Assert.assertTrue(manager.formPage().errorMsgIsVisible());
        System.out.println("asercja");

    }
}
