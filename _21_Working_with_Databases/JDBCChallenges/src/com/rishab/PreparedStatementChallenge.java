package com.rishab;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

record OrderDetail(int orderDetailId, String itemDescription, int quantity) {
    public OrderDetail(String itemDescription, int quantity) {
        this(-1, itemDescription, quantity);
    }
}

record Order(int orderId, String dateString, List<OrderDetail> details) {
    public Order(String dateString) {
        this(-1, dateString, new ArrayList<>());
    }

    public void addDetail(String itemDescription, int quantity) {
        OrderDetail item = new OrderDetail(itemDescription, quantity);
        details.add(item);
    }
}

public class PreparedStatementChallenge {
    public static void main(String[] args) {

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setUser(System.getenv("MYSQL_USER"));
        dataSource.setPassword(System.getenv("MYSQL_PASSWORD"));

        List<Order> orders = readData();

        try (Connection connection = dataSource.getConnection()) {
//            String alterString = "ALTER TABLE storefront.order_details ADD COLUMN quantity INT";
//            Statement statement = connection.createStatement();
//            statement.execute(alterString);

            addOrders(connection, orders);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static List<Order> readData() {
        List<Order> orders = new ArrayList<>();

        try (Scanner scanner = new Scanner(Path.of("Orders.csv"))) {
            scanner.useDelimiter("[,\\n]");
            List<String> list = scanner.tokens().map(String::trim).toList();

            for (int i = 0; i < list.size(); i++) {
                String value = list.get(i);
                if (value.equals("order")) {
                    String date = list.get(++i);
                    orders.add(new Order(date));
                } else if (value.equals("item")) {
                    int quantity = Integer.parseInt(list.get(++i));
                    String description = list.get(++i);
                    Order order = orders.get(orders.size() - 1);
                    order.addDetail(description, quantity);
                }
            }

            orders.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return orders;
    }

    private static void addOrder(Connection connection, PreparedStatement preparedOrder,
                                 PreparedStatement preparedDetail, Order order) throws SQLException {
        try {
            connection.setAutoCommit(false);

            int orderId = -1;
            preparedOrder.setString(1, order.dateString());
            if (preparedOrder.executeUpdate() == 1) {
                ResultSet resultSet = preparedOrder.getGeneratedKeys();
                if (resultSet.next()) {
                    orderId = resultSet.getInt(1);
                    System.out.println("New order id: " + orderId);

                    if (orderId > -1) {
                        preparedDetail.setInt(1, orderId);
                        for (OrderDetail detail : order.details()) {
                            preparedDetail.setString(2, detail.itemDescription());
                            preparedDetail.setInt(3, detail.quantity());
                            preparedDetail.addBatch();
                        }

                        int[] data = preparedDetail.executeBatch();
                        int rowsInserted = Arrays.stream(data).sum();
                        if (rowsInserted != order.details().size()) {
                            throw new SQLException("Error inserting order details");
                        }
                    }
                }
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private static void addOrders(Connection connection, List<Order> orders) {
        String insertOrder = "INSERT INTO storefront.order (order_date) VALUES (?)";
        String insertDetail = "INSERT INTO storefront.order_details (order_id, item_description, quantity) VALUES (?, ?, ?)";

        try (PreparedStatement preparedOrder = connection.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement preparedDetail = connection.prepareStatement(insertDetail)) {
            orders.forEach((o) -> {
                try {
                    addOrder(connection, preparedOrder, preparedDetail, o);
                } catch (SQLException e) {
                    System.err.printf("%d (%s): %s%n", e.getErrorCode(), e.getSQLState(), e.getMessage());
                    System.err.println("Problem: " + preparedOrder);
                    System.err.println("Order: " + o);
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
