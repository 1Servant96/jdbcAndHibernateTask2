package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {
    public UserDaoJdbcImpl() {
    }

    public void createUsersTable() {
        String query1 =
                "create table if not exists users(" +
                        "id serial primary key, " +
                        "name varchar(50), " +
                        "last_name varchar(40)," +
                        " age int);";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query1);
            System.out.println("Table has been created successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        String query2 = "drop table if exists users;";
        try (Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(query2);
            System.out.println("Table has been deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String query3 = "insert into users(name, last_name, age) values (?, ?, ?);";
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(query3)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User " + name + " has been saved into users table");
        } catch (SQLException e) {
            System.out.println("Happened exception");
        }
    }

    public void removeUserById(long id) {
        String query4 = "delete from users where id = " + id;
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query4);
            System.out.println("User deleted by id successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        String query4 = "select * from users";
        ArrayList<User> arrayList = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query4)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                arrayList.add(user);
            }
            System.out.println("You have gotten all users");

        } catch (SQLException e) {
            System.out.println("User didn't found");
        }
        return arrayList;
    }

    public void cleanUsersTable() {
        String query5 = "truncate table users";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(query5);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


}