package com.rishab;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

public class Main {
    private final static String CONNECTION_STRING = "jdbc:mysql://localhost:3306/music";

    public static void main(String[] args) {

        String username = JOptionPane.showInputDialog(null, "Enter DB username");
        JPasswordField passwordField = new JPasswordField();
        int okCxl = JOptionPane.showConfirmDialog(null, passwordField, "Enter DB password",
            JOptionPane.OK_CANCEL_OPTION);
        final char[] password = (okCxl == JOptionPane.OK_OPTION) ? passwordField.getPassword() : null;

//        Connecting to the database using data source
        MysqlDataSource dataSource = new MysqlDataSource();
//        dataSource.setURL(CONNECTION_STRING);

        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("music");

//        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING, username, String.valueOf(password))) {
        try (Connection connection = dataSource.getConnection(username, String.valueOf(password))) {
            System.out.println("Connection successful");
            Arrays.fill(password, ' ');
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }

    }
}
