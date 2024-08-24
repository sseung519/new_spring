package conn;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static final String CONFIG_FILE = "dbinfo.properties";
    private static DBConnection instance;
    private final Properties dbProperties;

    private DBConnection() {
        this.dbProperties = loadProperties();
    }

    // Singleton (Lazy Initialization)
    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                dbProperties.getProperty("DB_URL"),
                dbProperties.getProperty("DB_USER"),
                dbProperties.getProperty("DB_PASSWD")
        );
    }

    private Properties loadProperties() {
        Properties props = new Properties();
        try {
            ClassPathResource resource = new ClassPathResource(CONFIG_FILE);
            props.load(resource.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database configuration", e);
        }
        return props;
    }

}