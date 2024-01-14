package com.rishab;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    private static final String USE_SCHEMA = "USE storefront";
    private static int MYSQL_DB_NOT_FOUND = 1049;

    public static void main(String[] args) {

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setUser(System.getenv("MYSQL_USER"));
        dataSource.setPassword(System.getenv("MYSQL_PASSWORD"));

        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getSQLStateType());
            if (!checkSchema(connection)) {
                System.out.println("Schema not found");
                setupSchema(connection);
            }

            deleteOrder(connection, 2);

//            int newOrder = addOrder(connection, new String[]{"shoes", "shirt", "socks"});
//            System.out.println("New order id: " + newOrder);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static boolean checkSchema(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(USE_SCHEMA);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());

            if (connection.getMetaData().getDatabaseProductName().equals("MySQL")
                && e.getErrorCode() == MYSQL_DB_NOT_FOUND) {
                return false;
            } else throw e;
        }
        return true;
    }

    private static void setupSchema(Connection connection) throws SQLException {
        String createSchema = "CREATE SCHEMA IF NOT EXISTS storefront";
        String createOrder = """
            CREATE TABLE IF NOT EXISTS storefront.order (
                order_id INT NOT NULL AUTO_INCREMENT,
                order_date DATETIME NOT NULL,
                PRIMARY KEY (order_id))""";
        String createOrderDetails = """
            CREATE TABLE IF NOT EXISTS storefront.order_details (
                order_detail_id INT NOT NULL AUTO_INCREMENT,
                item_description TEXT,
                order_id INT DEFAULT NULL,
                PRIMARY KEY (order_detail_id),
                KEY FK_ORDER_ID (order_id),
                CONSTRAINT FK_ORDER_ID FOREIGN KEY (order_id)
                REFERENCES storefront.order (order_id) ON DELETE CASCADE)""";

        try (Statement statement = connection.createStatement()) {
            System.out.println("Creating storefront schema...");
            statement.execute(createSchema);

            if (checkSchema(connection)) {
                statement.execute(createOrder);
                System.out.println("Successfully created order table");

                statement.execute(createOrderDetails);
                System.out.println("Successfully created order_details table");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int addOrder(Connection connection, String[] items) {
        int orderId = -1;
        int count = 0;
        String insertOrder = "INSERT INTO storefront.order (order_date) VALUES ('%s')";
        String insertOrderDetails = "INSERT INTO storefront.order_details (order_id, item_description) VALUES (%d, %s)";

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String orderDateTime = LocalDateTime.now().format(dtf);
        System.out.println(orderDateTime);

        String formattedString = insertOrder.formatted(orderDateTime);
        System.out.println(formattedString);

        String insertOrderAlternative = "INSERT INTO storefront.order (order_date) VALUES ('%1$tF %1$tT')";
        System.out.println(insertOrderAlternative.formatted(LocalDateTime.now()));

        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);

            int inserts = statement.executeUpdate(formattedString, Statement.RETURN_GENERATED_KEYS);
            if (inserts == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    orderId = resultSet.getInt(1);
                }
            }

            for (String item : items) {
                formattedString = insertOrderDetails.formatted(orderId, statement.enquoteLiteral(item));
                inserts = statement.executeUpdate(formattedString);
                count += inserts;
            }

            if (count != items.length) {
                orderId = -1;
                System.out.println("Error inserting order details");
                connection.rollback();
            } else {
                connection.commit();
            }

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }

        return orderId;
    }

    private static void deleteOrder(Connection connection, int orderId) throws SQLException {
        String deleteOrder = "DELETE FROM %s WHERE order_id = %d";
        String parentQuery = deleteOrder.formatted("storefront.order", orderId);
        String childQuery = deleteOrder.formatted("storefront.order_details", orderId);

        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            int deletedRecords = statement.executeUpdate(childQuery);
            System.out.printf("Deleted %d records from order_details table%n", deletedRecords);

            deletedRecords = statement.executeUpdate(parentQuery);
            if (deletedRecords == 1) {
                connection.commit();
                System.out.printf("Deleted %d records from order table%n", deletedRecords);
            } else {
                connection.rollback();
                System.out.printf("Error deleting order with id %d%n", orderId);
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
