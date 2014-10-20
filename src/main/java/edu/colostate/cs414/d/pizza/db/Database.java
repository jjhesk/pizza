package edu.colostate.cs414.d.pizza.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tim
 */
public class Database {

    public static final String CONFIG_DEFAULT = "db.properties";
    public static final String CONFIG_PRODUCTION = "db.prod.properties";
    public static final String CONFIG_TEST = "db.test.properties";

    private final Logger logger = LoggerFactory.getLogger(Database.class);

    private static Database instance;

    private Connection connection;

    private Database() {
        // attempt to load the configuration file
        Properties config = new Properties();
        try (InputStream in = openConfigFile()) {
            config.load(in);
        } catch (IOException ex) {
            logger.error("Unable to read database configuration.", ex);
            
            throw new IllegalStateException(
                    "Database configuration not provided.");
        }

        // init connection here
        String url = config.getProperty("url");
        String user = config.getProperty("user");
        String password = config.getProperty("password");

        try {
            logger.info("Connecting to database at {}", url);
            connection = DriverManager.getConnection(url, user, password);
            logger.debug("Connected");
        } catch (SQLException e) {
            logger.error("Unable to connect to database", e);
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private String getConfigFileName() {
        String env = System.getenv("ENV");
        if (env == null) {
            return CONFIG_DEFAULT;
        }

        env = env.toLowerCase();
        if (env.equals("prod") || env.equals("production")) {
            return CONFIG_PRODUCTION;
        } else if (env.equals("test")) {
            return CONFIG_TEST;
        }

        logger.error(
                "Unknown environment {} specified, using default config: {}",
                env, CONFIG_DEFAULT);

        return CONFIG_DEFAULT;
    }

    private InputStream openConfigFile() throws IOException {
        String config = System.getenv("CONFIG");
        if (config != null) {
            logger.debug("Using config file at {}", config);
            return new FileInputStream(config);
        }

        config = getConfigFileName();
        logger.debug("Searching for config file: {}", config);

        // check on classpath (db.properties in src/main/resources/)
        InputStream is = getClass().getResourceAsStream(config);
        if (is != null) {
            logger.debug("Config file {} found in classpath", config);
            return is;
        }

        // check in working directory
        File f = new File(config);
        if (!f.exists()) {
            logger.debug("Config file {} found in current directory", config);
            throw new FileNotFoundException("Config file not found: " + config);
        }

        return new FileInputStream(f);
    }

    public Connection getConnection() {
        return connection;
    }

    //Take out later used for testing
     
    public static void main(String args[]) throws SQLException {

        Database database = new Database();

        PreparedStatement preparedStatement = database.connection.prepareStatement("SELECT * FROM User");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
        }
    }
     
}
