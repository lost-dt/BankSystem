package com.kpi.bank.system.services.dao.service;

import com.kpi.bank.system.controller.Controller;
import com.kpi.bank.system.model.Manager;
import com.kpi.bank.system.services.dao.DAO;
import com.kpi.bank.system.services.dao.connector.MySqlPoolConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerService implements DAO<Manager> {

    private static final String DATABASE_NAME = "bank_system";
    private static final String TABLE_NAME = "manager";

    private Connection connection;
    {
        try{
            connection = MySqlPoolConnector.getConnection();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    private Manager createManagerFromResultSet(ResultSet resultSet) throws SQLException {
        Manager manager = new Manager();
        manager.setId(resultSet.getInt("ID"));
        manager.setUserId(resultSet.getInt("USER_ID"));

        return manager;
    }


    @Override
    public void insert(Manager manager) throws SQLException {

        String sql = String.format(
                "INSERT INTO %s.%s (ID, USER_ID) VALUES(?, ?)",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, manager.getId());
            preparedStatement.setInt(2, manager.getUserId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    @Override
    public List<Manager> loadAll() throws SQLException {

        List<Manager> managers = new ArrayList<>();

        String sql = String.format(
                "SELECT ID, USER_ID FROM %s.%s",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Manager manager = createManagerFromResultSet(resultSet);
                managers.add(manager);
            }
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
        return managers;
    }

    @Override
    public Manager loadById(Integer id) throws SQLException {

        String sql = String.format(
                "SELECT ID, USER_ID FROM %s.%s WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );

        Manager client = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            client = createManagerFromResultSet(resultSet);
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
        return client;
    }

    @Override
    public void update(Manager manager) throws SQLException {

        String sql = String.format(
                "UPDATE %s.%s SET USER_ID=? WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, manager.getUserId());
            preparedStatement.setInt(2, manager.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    @Override
    public void remove(Manager manager) throws SQLException {

        String sql = String.format(
                "DELETE FROM %s.%s WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, manager.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }
}
