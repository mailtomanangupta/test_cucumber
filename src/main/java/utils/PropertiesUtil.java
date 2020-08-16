package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    private static final Properties properties;

    static {
        properties = new Properties();

        try (final InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("default.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            }
        } catch (IOException e) {

        }
    }

    public static Properties getProperties() {
        return properties;
    }
}
