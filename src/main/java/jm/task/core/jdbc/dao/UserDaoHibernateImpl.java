package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import javax.management.Query;
import java.lang.annotation.Native;
import java.lang.module.Configuration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import static jm.task.core.jdbc.util.Util.getSessionFactory;


public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS table_users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(45) NOT NULL, " +
                "lastname VARCHAR(45) NOT NULL, " +
                "age INT NOT NULL, PRIMARY KEY (id))";

        session.createSQLQuery(sql).addEntity(User.class).executeUpdate();

        transaction.commit();
        session.close();

    }

    @Override
    public void dropUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.createSQLQuery("DROP TABLE IF EXISTS table_users")
                .addEntity(User.class).executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();


        NativeQuery query = session.createSQLQuery("INSERT INTO table_users (name, lastname, age) VALUES (?, ?, ?)")
                .addEntity(User.class);
        query.setParameter(1, name);
        query.setParameter(2, lastName);
        query.setParameter(3, age);
        query.executeUpdate();


        transaction.commit();
        session.close();

    }

    @Override
    public void removeUserById(long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createSQLQuery("DELETE FROM table_users WHERE id = " + id)
                .addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> resList = new ArrayList<>();
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        resList = session.createSQLQuery("SELECT * FROM table_users")
                .addEntity(User.class).list();

        if (resList.size() > 0) {
            return resList;
        }
        transaction.commit();
        session.close();
        return null;
    }

    @Override
    public void cleanUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createSQLQuery("DELETE FROM table_users")
                .addEntity(User.class);

        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
