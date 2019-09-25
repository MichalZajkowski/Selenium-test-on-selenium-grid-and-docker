package framework.tools.utils.dataprovider;

public enum UrlProvider {

    ONET("https://konto.onet.pl/login.html"),
    DOCKER_INTERNET("http://localhost:5000");

    private final String url;

    UrlProvider(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
