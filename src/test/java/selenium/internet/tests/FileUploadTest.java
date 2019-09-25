package selenium.internet.tests;

import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import framework.tools.docker.DockerEnvironment;
import framework.tools.utils.dataprovider.UrlProvider;
import org.assertj.core.api.Assertions;
import org.junit.*;
import selenium.common.tests.BaseTest;
import selenium.internet.pages.PageObjectInternetManager;

public class FileUploadTest extends BaseTest {

    private static final String NAME_OF_FILE_FOR_UPLOAD = "uploadTest.html";
    private static final String PATH_TO_RESOURCE_FOR_UPLOAD = String.format("src/main/resources/%s", NAME_OF_FILE_FOR_UPLOAD);
    private static PageObjectInternetManager manager;
    private static String url;
    private static String containerId;
    private static DockerEnvironment dockerEnvironment;

    public FileUploadTest() {
        url = UrlProvider.DOCKER_INTERNET.getUrl();
        dockerEnvironment = new DockerEnvironment();
        manager = new PageObjectInternetManager(webDriver);
    }

    @Before
    public void startUp() throws InterruptedException, DockerException, DockerCertificateException {
        containerId = dockerEnvironment.startDockerClient();
        url = UrlProvider.DOCKER_INTERNET.getUrl();
    }

    @After
    public void tearDown() {
        webDriver.quit();
        tearDownGridIfNeeded();
        Assertions.assertThat(dockerEnvironment.stopAndRemoveDockerClient(containerId)).isTrue();
    }

    @Test
    public void registerTest() {
        manager.getMainPage().loadPage(url);
        Assertions.assertThat(manager.getMainPage().isLoaded()).isTrue();
        manager.getMainPage().clickUploadLink();
        Assertions.assertThat(manager.getFileUploadPage().isLoaded()).isTrue();
        manager.getFileUploadPage()
                .uploadFile(PATH_TO_RESOURCE_FOR_UPLOAD)
                .makeUploadButtonScaled()
                .clickUpload();
        Assertions.assertThat(manager.getFileUploadPage().isFileUploaded(NAME_OF_FILE_FOR_UPLOAD)).isTrue();
    }
}
