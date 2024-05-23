package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlserver://TECHNOMANCER\\Shop:1433;databaseName=javafx_db;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "javafx"; 
    private static final String PASSWORD = "javafx"; 

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
