package ru.urfu.tissue.utils;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {
    private final Properties props = new Properties();
    private final String USER = "msmdmyzv";
    private final String PASSWORD = "00axuz7edYBRdCy7nlSOsO2eypKWcAq8";
    private final String URL = "jdbc:postgresql://baasu.db.elephantsql.com:5432/msmdmyzv";
    private Connection connection;


    public Connection createConnection() throws SQLException {
        if (this.connection==null || this.connection.isClosed()) {
            this.props.setProperty("user", USER);
            this.props.setProperty("password", PASSWORD);
            this.props.setProperty("ssl","false");
            Connection connection = null;

            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Ошибка загрузки класса");
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(URL, props);
            return connection;
        } else {
            return this.connection;
        }

    }

}