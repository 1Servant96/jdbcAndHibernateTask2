package peaksoft.dao;

//import com.sun.istack.NotNull;

import com.sun.istack.NotNull;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.configurations.HibernateConfig;
import peaksoft.entity.User;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public final SessionFactory sessionFactory = HibernateConfig.buildSessionFactory();
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.createSQLQuery("""
                create table if not exists usersnew(
                id serial primary key,
                name varchar(50),
                lastName varchar(50),
                age int )""").executeUpdate();

            session.getTransaction().commit();
            System.out.println("users table was successfully created");

        }catch (IllegalArgumentException e) {
            System.out.println("No such table");
        }

    }

    @Override
    public void dropUsersTable() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(User.class);
            session.getTransaction().commit();
            System.out.println("User table was successfully deleted");
        }
        catch (IllegalArgumentException e) {
            System.out.println("No such table");
        }
    }

    @Override
    @NotNull
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            session.getTransaction().commit();
            System.out.println("The user with " + user.getId() + "was successfully added to base");
        }
        catch (MappingException e) {
            System.out.println("No couldn't save the user");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = session.find(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
            System.out.println("User with id "+ id + "was successfully deleted");
        }catch (IllegalArgumentException e) {
            System.out.println("No such table");
        }
    }

    @Override
    public List<User> getAllUsers() {
//        List<User> userlist;
        List<User> userlist = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession();) {
            session.beginTransaction();
            userlist = session.createQuery(" from User").getResultList();
            session.getTransaction().commit();
            System.out.println(userlist.size() + "have(has) been found");
            return userlist;
        }catch (IllegalArgumentException e) {
            System.out.println("No such table");
        }
        return userlist;
    }

    @Override
    public void cleanUsersTable() {
    try (Session session = HibernateConfig.getSessionFactory().openSession()){
        session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        session.getTransaction().commit();
        System.out.println("All users successfully deleted!");
    }catch (IllegalArgumentException e) {
        System.out.println("No such table");
    }
    }
}
