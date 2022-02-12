package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

import jm.task.core.jdbc.model.User;


public class Main {
    static UserDao userDao = new UserDaoHibernateImpl();
    static User user1 = new User("Diego", "Diegovich", (byte) 34);
    static User user2 = new User("Oksana", "Oksanovna", (byte) 24);
    static User user3 = new User("Igor", "Leonidovich", (byte) 29);
    static User user4 = new User("Ivan", "Ivanovich", (byte) 45);

    public static void main(String[] args) {
        userDao.createUsersTable();
        userDao.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userDao.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userDao.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userDao.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        userDao.removeUserById(2);
        System.out.println(userDao.getAllUsers());
        userDao.cleanUsersTable();
        System.out.println(userDao.getAllUsers());
                userDao.dropUsersTable();








    }
}