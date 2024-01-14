package com.rishab;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Properties props = new Properties();
        try {
            props.load(Files.newInputStream(Path.of("music.properties"), StandardOpenOption.READ));
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        MysqlDataSource datasource = new MysqlDataSource();
        datasource.setServerName(props.getProperty("SERVER_NAME"));
        datasource.setPort(Integer.parseInt(props.getProperty("PORT")));
        datasource.setDatabaseName(props.getProperty("DATABASE_NAME"));

//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter an Artist Id: ");
//        int artistId = Integer.parseInt(scanner.nextLine());
        try {
            datasource.setMaxRows(10);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String query = "SELECT * FROM music.artists LIMIT 10";
//        String query = """
//            WITH RankedRows AS (SELECT *, ROW_NUMBER() OVER (ORDER BY artist_id) AS row_num FROM music.artists)
//            SELECT * FROM RankedRows WHERE row_num <= 10""";

        try (Connection connection = datasource.getConnection(
            props.getProperty("USER"), System.getenv("MYSQL_PASS"));
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            ResultSetMetaData metadata = resultSet.getMetaData();
//            for (int i = 1; i <= metadata.getColumnCount(); i++) {
//                System.out.printf("%d. %-15s | %s%n",
//                    i, metadata.getColumnName(i), metadata.getColumnTypeName(i));
//            }

//            System.out.println("=".repeat(100));
            for (int i = 1; i <= metadata.getColumnCount(); i++) {
                System.out.printf("%-15s", metadata.getColumnName(i).toUpperCase());
            }
            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= metadata.getColumnCount(); i++) {
                    System.out.printf("%-15s", resultSet.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
