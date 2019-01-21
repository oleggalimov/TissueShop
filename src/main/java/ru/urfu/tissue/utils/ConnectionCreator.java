package ru.urfu.tissue.utils;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {
    private Connection connection;

    public Connection createConnection() {
        if (this.connection==null) {

            Properties props = new Properties();
            String USER = "msmdmyzv";
            props.setProperty("user", USER);
            String PASSWORD = "00axuz7edYBRdCy7nlSOsO2eypKWcAq8";
            props.setProperty("password", PASSWORD);
            props.setProperty("ssl","false");
            try {
                Class.forName("org.postgresql.Driver");
                String URL = "jdbc:postgresql://baasu.db.elephantsql.com:5432/msmdmyzv";
                this.connection = DriverManager.getConnection(URL, props);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return this.connection;
    }

}