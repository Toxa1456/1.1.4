package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

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
    private static SessionFactory sessionFactory = null;
    private static ServiceRegistry serviceRegistry = null;


    public static SessionFactory getSessionFactory() {
        try {
            if (sessionFactory == null) {
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, URL);
                settings.put(Environment.USER, login);
                settings.put(Environment.PASS, password);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                Configuration configuration = new Configuration().setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    public static void close () {
        if (sessionFactory != null) {
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
            serviceRegistry = null;
        }
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
