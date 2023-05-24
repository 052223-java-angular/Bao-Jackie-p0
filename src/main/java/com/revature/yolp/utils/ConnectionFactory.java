package com.revature.yolp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The ConnectionFactory class is responsible for creating and managing the
 * database connection.
 */
public class ConnectionFactory {
    private static ConnectionFactory instance;
    private Connection connection;

    /**
     * Private constructor to enforce singleton pattern and establish the database
     * connection.
     *
     * @throws IOException            if an I/O error occurs
     * @throws SQLException           if a database error occurs
     * @throws ClassNotFoundException if the specified class cannot be found
     */
    private ConnectionFactory() throws IOException, SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Properties props = getProperties();
        connection = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"),
                props.getProperty("password"));
    }

    /**
     * Returns the singleton instance of ConnectionFactory.
     *
     * @return the ConnectionFactory instance
     * @throws ClassNotFoundException if the specified class cannot be found
     * @throws IOException            if an I/O error occurs
     * @throws SQLException           if a database error occurs
     */
    public static ConnectionFactory getInstance() throws ClassNotFoundException, IOException, SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new ConnectionFactory();
        }
        return instance;
    }

    /**
     * Returns the database connection.
     *
     * @return the Connection object
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Retrieves the database connection properties from the application.properties
     * file.
     *
     * @return the Properties object containing the connection properties
     * @throws IOException if an I/O error occurs
     */
    private Properties getProperties() throws IOException {
        Properties props = new Properties();

        try (InputStream iStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (iStream == null) {
                throw new IOException("Unable to find application.properties");
            }
            props.load(iStream);
        }

        return props;
    }
}
