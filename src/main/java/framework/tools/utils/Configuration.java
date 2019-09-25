package framework.tools.utils;


import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.text.MessageFormat;
import java.util.Properties;

public class Configuration {

    private static final String FRAMEWORK_PROPERTIES = "framework.properties";
    private static Configuration instance = null;

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    public static String getProperty(String key) {
        final String property = System.getProperty(key);
        if (property == null) {
            throw new InvalidParameterException(MessageFormat.format("Missing value for key '{0}'!", key));
        }
        return property;
    }

    public static void setProperty(String key, String value) {
        System.setProperty(key, value);
    }

    public String getPropertyFromFile(String key) {
        Properties properties = new Properties();
        InputStream inputStream;
        inputStream = this.getClass().getClassLoader().getResourceAsStream(FRAMEWORK_PROPERTIES);

        try {
            properties.load(inputStream);
        } catch (IOException e) {
            //no-op
        }
        return properties.getProperty(key);
    }
}
