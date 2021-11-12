package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static final String login = "root";
    private static final String password = "1234";
    private static final String URL = "jdbc:mysql://localhost:3306/test?useSSL=false";
    public static Connection connection;
    public static Statement statement;

    static {
        try {
            connection = DriverManager.getConnection(URL, login, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }
    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }
}
