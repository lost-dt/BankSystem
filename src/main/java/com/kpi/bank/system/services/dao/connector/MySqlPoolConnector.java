package com.kpi.bank.system.services.dao.connector;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MySqlPoolConnector {

    private static final ResourceBundle resource = ResourceBundle.getBundle("my-sql-database-connection");

    private static final String DB_DRIVER = resource.getString("dbDriver");
    private static final String DB_URL = resource.getString("url");
    private static final String DB_USERNAME = resource.getString("user");
    private static final String DB_PASSWORD = resource.getString("password");

    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;

    static {
        config.setDriverClassName(DB_DRIVER);
        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USERNAME);
        config.setPassword(DB_PASSWORD);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private MySqlPoolConnector() {

    }
}
