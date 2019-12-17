package com.kpi.bank.system.services.dao.service;

import com.kpi.bank.system.controller.Controller;
import com.kpi.bank.system.model.User;
import com.kpi.bank.system.services.dao.DAO;
import com.kpi.bank.system.services.dao.connector.MySqlPoolConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements DAO<User> {

    private static final String DATABASE_NAME = "bank_system";
    private static final String TABLE_NAME = "user";

    private Connection connection;
    {
        try{
            connection = MySqlPoolConnector.getConnection();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("ID"));
        user.setEmail(resultSet.getString("EMAIL"));
        user.setPassword(resultSet.getString("PASSWORD"));
        user.setFirstName(resultSet.getString("FIRST_NAME"));
        user.setLastName(resultSet.getString("LAST_NAME"));
        user.setSecretWord(resultSet.getString("SECRET_WORD"));

        return user;
    }

    @Override
    public void insert(User user) throws SQLException {

        String sql = String.format(
                "INSERT INTO %s.%s (ID, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, SECRET_WORD) VALUES(?, ?, ?, ?, ?, ?)",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setString(6, user.getSecretWord());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    @Override
    public List<User> loadAll() throws SQLException {

        List<User> users = new ArrayList<>();

        String sql = String.format(
                "SELECT ID, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, SECRET_WORD FROM %s.%s",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = createUserFromResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
        return users;
    }

    @Override
    public User loadById(Integer id) throws SQLException {

        String sql = String.format(
                "SELECT ID, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, SECRET_WORD FROM %s.%s WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );

        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            user = createUserFromResultSet(resultSet);
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
        return user;
    }

    @Override
    public void update(User user) throws SQLException {

        String sql = String.format(
                "UPDATE %s.%s SET EMAIL=?, PASSWORD=?, FIRST_NAME=?, LAST_NAME=?, SECRET_WORD=? WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getSecretWord());
            preparedStatement.setInt(6, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    @Override
    public void remove(User user) throws SQLException {

        String sql = String.format(
                "DELETE FROM %s.%s WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }
}
