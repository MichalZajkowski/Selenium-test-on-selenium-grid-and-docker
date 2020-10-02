package selenium.common.tests;

import framework.grid.SeleniumGrid;
import framework.tools.utils.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    private static final Configuration configuration = Configuration.getInstance();
    private static final String DRIVER = "driver";
    private static final String REMOTE_HOST_URL = configuration.getPropertyFromFile("remoteHostURL");
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    private static final SeleniumGrid testOnGrid = new SeleniumGrid();
    private static final boolean doesGridActive = Boolean.parseBoolean(configuration.getPropertyFromFile("localhostGridEnabled"));
    protected static WebDriver webDriver;

    protected BaseTest() {
        try {
            setDriver();
        } catch (InvalidParameterException | IOException e) {
            logger.warn("Missing 'driver' property. Set driver to default");
            Configuration.setProperty(DRIVER, "firefox");
            Configuration.setProperty("webdriver.gecko.driver", configuration.getPropertyFromFile("geckoDriver"));
            webDriver = new FirefoxDriver();
        } finally {
            if (!"remote".equals(Configuration.getProperty(DRIVER))) {
                Objects.requireNonNull(webDriver).manage().window().maximize();
            }
        }
    }

    protected static void tearDownGridIfNeeded() {
        if (doesGridActive) {
            testOnGrid.stopNode();
            testOnGrid.stopHub();
            logger.info("Grid closed!");
        }
    }

    private void setDriver() throws InvalidParameterException, IOException {
        switch (Configuration.getProperty(DRIVER)) {
            case "chrome" -> setChromeDriver();
            case "firefox" -> setGeckoDriver();
            case "remote" -> setRemoteDriver();
            case "remoteOnLocalhost" -> setRemoteOnLocalhost();
        }
    }

    private void setUpGrid() {
        if (doesGridActive) {
            testOnGrid.runHub();
            testOnGrid.runNode();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                logger.error("Timeout corrupted! ", e);
            }
            logger.info("Grid set up and ready for test processing!");
        }
    }

    private void setChromeDriver() {
        Configuration.setProperty("webdriver.chrome.driver", configuration.getPropertyFromFile("chromeDriver"));
        webDriver = new ChromeDriver();
    }

    private void setGeckoDriver() {
        Configuration.setProperty("webdriver.gecko.driver", configuration.getPropertyFromFile("geckoDriver"));
        webDriver = new FirefoxDriver();
    }

    private void setRemoteDriver() {
        try {
            RemoteWebDriver remoteWebDriver = new RemoteWebDriver(new URL(REMOTE_HOST_URL), DesiredCapabilities.firefox());
            remoteWebDriver.setFileDetector(new LocalFileDetector());
            webDriver = remoteWebDriver;
        } catch (MalformedURLException e) {
            logger.error("Missing RemoteWebDriver instance! ", e);
        }
    }

    private void setRemoteOnLocalhost() {
        Configuration.setProperty("webdriver.chrome.driver", configuration.getPropertyFromFile("chromeDriver"));
        setUpGrid();
        try {
            RemoteWebDriver remoteWebDriver = new RemoteWebDriver(new URL(REMOTE_HOST_URL), DesiredCapabilities.chrome());
            remoteWebDriver.setFileDetector(new LocalFileDetector());
            webDriver = remoteWebDriver;
        } catch (MalformedURLException e) {
            logger.error("Missing RemoteWebDriver instance ", e);
        }
    }
}
