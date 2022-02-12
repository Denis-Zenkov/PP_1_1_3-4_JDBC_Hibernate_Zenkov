package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection connection = Util.getConnect();

    private final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS table_users" +
            "(id BIGINT NOT NULL AUTO_INCREMENT, " +
            "name VARCHAR(45) NOT NULL, " +
            "lastname VARCHAR(45) NOT NULL, " +
            "age INT NOT NULL, PRIMARY KEY (id))";

    public UserDaoJDBCImpl() {

    }


    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE);
            System.out.println("Таблица создана! Ура!");
        } catch (SQLException e) {
            System.out.println("Таблица не создана! Увы!");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS table_users");
            System.out.println("Таблица удалена!");
        } catch (SQLException e) {
            System.out.println("Не удалось удалить таблицу!");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement pStatement = connection.prepareStatement("INSERT INTO table_users (name, lastname, age) VALUES (?, ?, ?)")) {
            pStatement.setString(1, name);
            pStatement.setString(2, lastName);
            pStatement.setInt(3, age);
            pStatement.executeUpdate();
            System.out.println("User c именем: " + name + " добавлен в базу!");
        } catch (SQLException e) {
            System.out.println("User c именем: " + name + " в базу добавить не удалось!");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM table_users WHERE id = " + id);
            System.out.println("User с id: " + id + " удален успешно!");
        } catch (SQLException e) {
            System.out.println("User с id: " + id + " не удален!");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> resList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM table_users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                resList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Userы не получены!");
            e.printStackTrace();
        }
        return resList;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE table_users");
            System.out.println("Таблица успешно очищена!");
        } catch (SQLException e) {
            System.out.println("Таблица не очищена!");
            e.printStackTrace();
        }
    }
}
