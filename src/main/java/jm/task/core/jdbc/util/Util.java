package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static String USERNAME = "root";
    private static String PASSWORD = "MainAdminZenkov";

    public static Connection getConnect(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            System.out.println("Соединение с БД установлено!");
        } catch (SQLException e) {
            System.out.println("Соединение с БД установить не удалось!");
            e.printStackTrace();
        }
        return connection;
    }
}
