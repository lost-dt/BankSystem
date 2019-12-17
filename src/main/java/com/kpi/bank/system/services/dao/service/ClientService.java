package com.kpi.bank.system.services.dao.service;

import com.kpi.bank.system.controller.Controller;
import com.kpi.bank.system.model.Client;
import com.kpi.bank.system.services.dao.DAO;
import com.kpi.bank.system.services.dao.connector.MySqlPoolConnector;
import com.kpi.bank.system.services.utils.date.DateFormator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService implements DAO<Client> {

    private static final String DATABASE_NAME = "bank_system";
    private static final String TABLE_NAME = "client";

    private Connection connection;
    {
        try{
            connection = MySqlPoolConnector.getConnection();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    private Client createClientFromResultSet(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getInt("ID"));
        client.setUserId(resultSet.getInt("USER_ID"));
        client.setFilePeriod(resultSet.getInt("LIFE_PERIOD"));

        return client;
    }


    @Override
    public void insert(Client client) throws SQLException {
        String sql = String.format(
                "INSERT INTO %s.%s (ID, USER_ID, LIFE_PERIOD) VALUES(?, ?, ?)",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, client.getId());
            preparedStatement.setInt(2, client.getUserId());
            preparedStatement.setInt(3, client.getFilePeriod());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    @Override
    public List<Client> loadAll() throws SQLException {

        List<Client> clients = new ArrayList<>();

        String sql = String.format(
                "SELECT ID, USER_ID, LIFE_PERIOD FROM %s.%s",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Client client = createClientFromResultSet(resultSet);
                clients.add(client);
            }
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
        return clients;
    }

    @Override
    public Client loadById(Integer id) throws SQLException {

        String sql = String.format(
                "SELECT ID, USER_ID, LIFE_PERIOD FROM %s.%s WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );
        Client client = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            client = createClientFromResultSet(resultSet);
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
        return client;
    }

    @Override
    public void update(Client client) throws SQLException {

        String sql = String.format(
                "UPDATE %s.%s SET USER_ID=?, LIFE_PERIOD=? WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, client.getUserId());
            preparedStatement.setInt(2, client.getFilePeriod());
            preparedStatement.setInt(3, client.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    @Override
    public void remove(Client client) throws SQLException {

        String sql = String.format(
                "DELETE FROM %s.%s WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, client.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }
}
