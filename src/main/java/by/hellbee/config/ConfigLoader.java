package by.hellbee.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader()
                .getResourceAsStream("simulation.properties")) {
            if (input != null) {
                PROPERTIES.load(input);
            }
        } catch (Exception e) {
            System.err.println("Ошибка загрузки simulation.properties, берутся значения по умолчанию.");
        }
    }

    public static int getInt(String key, int defaultValue) {
        String val = PROPERTIES.getProperty(key);
        if (val != null) {
            try {
                return Integer.parseInt(val.trim());
            } catch (NumberFormatException ignored) {
            }
        }
        return defaultValue;
    }

    public static String getString(String key, String defaultValue) {
        String val = PROPERTIES.getProperty(key);
        return (val != null && !val.isBlank()) ? val.trim() : defaultValue;
    }
}