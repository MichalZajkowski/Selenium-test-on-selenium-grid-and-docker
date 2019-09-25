package framework.tools.utils.dataprovider;

public enum LoginDataProvider {

    USER_LOGIN_WITH_WRONG_DATA("test@test.pl", "testing");

    private final String userEmailAddress;
    private final String password;

    LoginDataProvider(String userEmailAddress, String password) {
        this.userEmailAddress = userEmailAddress;
        this.password = password;
    }

    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    public String getPassword() {
        return password;
    }
}
