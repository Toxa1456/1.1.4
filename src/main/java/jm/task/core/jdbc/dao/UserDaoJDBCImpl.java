package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createTable() {
        try {
            Util.statement.executeUpdate("CREATE TABLE IF NOT EXISTS User(id BIGINT NOT NULL AUTO_INCREMENT," +
                    " name VARCHAR(30) NOT NULL, lastName VARCHAR(30) NOT NULL, age TINYINT NOT NULL," +
                    " PRIMARY KEY (id))");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void dropTable() {
        try {
            Util.statement.executeUpdate("DROP TABLE IF EXISTS User");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void save(String name, String lastName, byte age) {
        try {
            Util.statement.executeUpdate("insert into User (name, lastName, age)" +
                    " values ('" + name + "', '" + lastName + "', '" + age + "' )");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void removeById(long id) {
        try {
            Util.statement.executeUpdate("DELETE FROM User WHERE id = '" + id + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try {
            ResultSet resultSet = Util.statement.executeQuery("SELECT * FROM User");
            while (resultSet.next()){
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastName"), resultSet.getByte("age"));
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
        return list;
    }

    public void cleanTable() {
        try {
            Util.statement.executeUpdate("TRUNCATE TABLE User");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }


    public void createUsersTable() {
        createTable();
    }

    public void dropUsersTable() {
        dropTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        save(name, lastName, age);
    }

    public void removeUserById(long id) {
        removeById(id);
    }

    public List<User> getAllUsers() {
        return getAll();
    }

    public void cleanUsersTable() {
        cleanTable();
    }
}
