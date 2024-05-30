package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlserver://TECHNOMANCER\\Shop:1433;databaseName=Open Ended Lab DB;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "openended"; 
    private static final String PASSWORD = "openended"; 

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
