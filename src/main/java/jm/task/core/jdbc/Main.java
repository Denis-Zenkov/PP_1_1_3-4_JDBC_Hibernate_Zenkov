package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    static UserService userService = new UserServiceImpl();
    static User user1 = new User("Diego", "Diegovich", (byte) 34);
    static User user2 = new User("Oksana", "Oksanovna", (byte) 24);
    static User user3 = new User("Igor", "Leonidovich", (byte) 29);
    static User user4 = new User("Ivan", "Ivanovich", (byte) 45);
    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.println(userService.getAllUsers());
        userService.removeUserById(2);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
