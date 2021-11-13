package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Util {
    private static final String login = "root";
    private static final String password = "1234";
    private static final String URL = "jdbc:mysql://localhost:3306/test?useSSL=false";
    public static Connection connection;
    public static Statement statement;
    public static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null){
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.put(Environment.URL, URL);
            settings.put(Environment.USER, login);
            settings.put(Environment.PASS, password);
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.HBM2DDL_AUTO, "update");
            Configuration configuration = new Configuration().setProperties(settings);
            configuration.addAnnotatedClass(User.class);

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
        return sessionFactory;
    }

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
