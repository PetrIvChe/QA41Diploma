package ru.netology.data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {


    private static Connection getConnection() throws SQLException {
        String url = System.getProperty("url");
        String user = System.getProperty("user");
        String password = System.getProperty("password");
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return null;
    }


    @SneakyThrows
    public static String getStatusCredit() {
        try (val conn = getConnection();
             val countStmt = conn.createStatement()) {
            val creditStatus = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
            val resultSet = countStmt.executeQuery(creditStatus);
            if (resultSet.next()) {
                return resultSet.getString("status");
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    public static void deleteTables() {
        val deleteCreditRequest = "DELETE FROM credit_request_entity";
        val deleteOrderEntity = "DELETE FROM order_entity";
        val deletePaymentEntity = "DELETE FROM payment_entity";
        val runner = new QueryRunner();
        try (val conn = getConnection()
        ) {
            runner.update(conn, deleteCreditRequest);
            runner.update(conn, deleteOrderEntity);
            runner.update(conn, deletePaymentEntity);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}

