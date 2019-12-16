package com.kpi.bank.system.services.dao.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MySqlConnector {

    private static final ResourceBundle resource = ResourceBundle.getBundle("my-sql-database-connection");

    private static final String DB_DRIVER = resource.getString("dbDriver");
    private static final String DB_URL = resource.getString("url");
    private static final String DB_USERNAME = resource.getString("user");
    private static final String DB_PASSWORD = resource.getString("password");

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
