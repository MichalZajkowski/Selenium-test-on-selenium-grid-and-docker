package selenium.internet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.common.pages.BasePage;

import java.io.File;

public class FileUploadPage extends BasePage<FileUploadPage> {

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

    @Override
    public boolean isLoaded() {
        return getCustom().isElementVisible(fileUploadController);
    }

    public FileUploadPage uploadFile(String filePath) {
        fileUploadController.sendKeys(new File(filePath).getAbsolutePath());
        return getThis();
    }

    public void clickUpload() {
        uploadButton.click();
        getThis();
    }

    public FileUploadPage makeUploadButtonScaled() {
        makeElementScaled(uploadButton);
        return getThis();
    }

    public boolean isFileUploaded(String fileName) {
        String fileUploadConfirmation = "//div[@id='uploaded-files' and contains(text(), '%s')]";
        return getCustom().isElementPresent(By.xpath(String.format(fileUploadConfirmation, fileName)));
    }
}
