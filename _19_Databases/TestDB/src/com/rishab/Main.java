package com.rishab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:D:\\Development\\Java-Learnings\\_19_Databases\\TestDB\\" + DB_NAME;

    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public static void main(String[] args) {

//        the latest versions of sqlite-jdbc-{version}.jar aren't working, hence using sqlite-jdbc-3.32.3.2.jar(working for me)
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_STRING);
//            connection.setAutoCommit(false); // to disable auto-commit (means we have to commit manually)
            Statement statement = connection.createStatement();

            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + " (" +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PHONE + " INTEGER, " +
                COLUMN_EMAIL + " TEXT" + ")");

//            statement.execute("INSERT INTO contacts (name, phone, email) VALUES ('Bob', 635793762, 'bob@bobby.com')");
//            statement.execute("INSERT INTO contacts (name, phone, email) VALUES ('Alice', 993636472, 'alice@gmail.com')");
//            statement.execute("INSERT INTO contacts (name, phone, email) VALUES ('Joe', 836663450, 'joe@somewhere.com')");
//            statement.execute("UPDATE contacts SET phone=8888899999 WHERE name='Alice'");
//            statement.execute("DELETE FROM contacts WHERE name='Bob'");

//            statement.execute("INSERT INTO " + TABLE_CONTACTS + " (" +
//                COLUMN_NAME + ", " + COLUMN_PHONE + ", " + COLUMN_EMAIL + ") " +
//                "VALUES ('Alex', 57575757, 'alex@myemail.com')");
//            statement.execute("INSERT INTO " + TABLE_CONTACTS + " (" +
//                COLUMN_NAME + ", " + COLUMN_PHONE + ", " + COLUMN_EMAIL + ") " +
//                "VALUES ('Bob', 635793762, 'bobby@whatsMyEmail.com')");
//            statement.execute("INSERT INTO " + TABLE_CONTACTS + " (" +
//                COLUMN_NAME + ", " + COLUMN_PHONE + ", " + COLUMN_EMAIL + ") " +
//                "VALUES ('Peter', 993636472, 'peter@parker.com')");
//            statement.execute("INSERT INTO " + TABLE_CONTACTS + " (" +
//                COLUMN_NAME + ", " + COLUMN_PHONE + ", " + COLUMN_EMAIL + ") " +
//                "VALUES ('Joe', 836663450, 'joe@somewhere.com')");

            insertContact(statement, "Alex", 57575757, "alex@myemail.com");
            insertContact(statement, "Bob", 635793762, "bobby@whatsMyEmail.com");
            insertContact(statement, "Peter", 993636472, "peter@parker.com");
            insertContact(statement, "Joe", 836663450, "joe@somewhere.com");

            statement.execute("UPDATE " + TABLE_CONTACTS + " SET " +
                COLUMN_PHONE + "=77777777 WHERE " + COLUMN_NAME + "='Alex'");
            statement.execute("DELETE FROM " + TABLE_CONTACTS + " WHERE " + COLUMN_NAME + "='Joe'");

//            statement.execute("SELECT * FROM contacts");
//            ResultSet results = statement.getResultSet();

//            executeQuery() returns a ResultSet object that contains the data produced by the query
            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS);
            while (results.next()) {
                System.out.println(results.getString(COLUMN_NAME) + " " +
                    results.getInt(COLUMN_PHONE) + " " +
                    results.getString(COLUMN_EMAIL));
            }

            results.close();
            statement.close();
            connection.close();
//            using try-with-resources will automatically close the connection and statement
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }

    private static void insertContact(Statement statement, String name, int phone, String email) throws SQLException {
        statement.execute("INSERT INTO " + TABLE_CONTACTS + " (" +
            COLUMN_NAME + ", " + COLUMN_PHONE + ", " + COLUMN_EMAIL + ") " +
            "VALUES ('" + name + "', " + phone + ", '" + email + "')");
    }
}
