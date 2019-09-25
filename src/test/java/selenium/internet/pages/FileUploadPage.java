package selenium.internet.pages;

import framework.tools.utils.Custom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenium.common.pages.BasePage;
import java.io.File;

public class FileUploadPage extends BasePage<FileUploadPage> {

    private static Logger logger = LoggerFactory.getLogger(Custom.class);

    @FindBy(id = "file-upload")
    private WebElement fileUploadController;

    @FindBy(id = "file-submit")
    private WebElement uploadButton;

    public FileUploadPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected FileUploadPage getThis() {
        return this;
    }

    public FileUploadPage uploadFile(String filePath) {
        File f = new File(filePath);
        logger.info(f.getAbsoluteFile().toString());
        fileUploadController.sendKeys(f.getAbsolutePath());
        return getThis();
    }

    public FileUploadPage clickUpload() {
        uploadButton.click();
        return getThis();
    }

    public FileUploadPage makeUploadButtonScaled() {
        makeElementScaled(uploadButton);
        return getThis();
    }

    public boolean isFileUploaded(String fileName) {
        String fileUploadConfirmation = "//div[@id='uploaded-files' and contains(text(), '%s')]";
        return custom.isElementPresent(By.xpath(String.format(fileUploadConfirmation, fileName)));
    }

    @Override
    public boolean isLoaded() {
        return custom.isElementVisible(fileUploadController);
    }
}
