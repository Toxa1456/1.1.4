package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        User user1 = new User("Tom", "Kryze", (byte) 15);
        User user2 = new User("Dim", "Gaga", (byte) 25);
        User user3 = new User("Piter", "Parker", (byte) 35);
        User user4 = new User("Dima", "Bilan", (byte) 45);

        userService.createUsersTable();
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.println("User "+ user1.getName() + " добавлен");
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.println("User "+ user2.getName() + " добавлен");
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println("User "+ user3.getName() + " добавлен");
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.println("User "+ user4.getName() + " добавлен");

        List<User> list = userService.getAllUsers();
        for(User user : list){
            System.out.println(user.toString());
        }
    }
}
