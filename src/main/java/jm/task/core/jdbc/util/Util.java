package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    public static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties properties = new Properties();
                properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                properties.put(Environment.URL, "jdbc:mysql://localhost:3306/mydbtest");
                properties.put(Environment.USER, "root");
                properties.put(Environment.PASS, "MainAdminZenkov");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                properties.put(Environment.SHOW_SQL, "true");

                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry =
                        new StandardServiceRegistryBuilder()
                                .applySettings(configuration.getProperties())
                                .build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("Подключение произведено!");
            } catch (Exception e) {
                System.out.println("Нет подключения!");
                e.printStackTrace();
            }

        }
        return sessionFactory;
    }


    private static String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static String USERNAME = "root";
    private static String PASSWORD = "MainAdminZenkov";

    public static Connection getConnect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Соединение с БД установлено!");
        } catch (SQLException e) {
            System.out.println("Соединение с БД установить не удалось!");
            e.printStackTrace();
        }
        return connection;
    }
}
