package by.htp.kirova.task2.java.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Creates a property of available property files in the resources directory.
 *
 * @author Kseniya Kirava
 * @since Oct 12, 2018
 */
public final class PropertyCreator {

    /**
     * Instance of {@code org.apache.log4j.Logger} is used for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(PropertyCreator.class);

    /**
     * The private constructor by default because this class is not intended
     * to create an instance of the class or inheritance.
     */
    private PropertyCreator() {
    }

    /**
     * Load properties to HashMap from properties in resources directory.
     *
     * @param fileName name of a property file.
     * @return persistent set of properties.
     */
    public static Properties createProperties(String fileName) {
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream resource = classLoader.getResourceAsStream(fileName)) {
            properties.load(resource);
        } catch (IOException e) {
            LOGGER.error("Property file not found or invalid path.", e);
            throw new IllegalArgumentException("Property file not found or invalid path", e);
        }
        return properties;
    }
}
