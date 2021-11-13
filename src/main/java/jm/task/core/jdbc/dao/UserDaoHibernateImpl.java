package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    private void createTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS User" +
                    "(id BIGINT NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(30) NOT NULL, lastName VARCHAR(30) NOT NULL," +
                    " age TINYINT NOT NULL, PRIMARY KEY (id))").executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void dropTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS User").executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void save(String name, String lastName, byte age) {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.save(new User(name, lastName, age));
            session.beginTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeById(long id) {
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(session.load(User.class, id));
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<User> getAll() {
        List<User> list = null;
        try {
            Session session = Util.getSessionFactory().openSession();
            list = session.createQuery("from User").list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private void cleanTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createUsersTable() {
        createTable();
    }

    @Override
    public void dropUsersTable() {
        dropTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        save(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        removeById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return getAll();
    }

    @Override
    public void cleanUsersTable() {
        cleanTable();
    }
}
