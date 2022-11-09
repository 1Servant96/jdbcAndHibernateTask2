package peaksoft.dao;

import peaksoft.entity.User;

import java.util.List;

public interface UserDao {

    void createUsersTable();

//    void createUsersTable(User user);

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
