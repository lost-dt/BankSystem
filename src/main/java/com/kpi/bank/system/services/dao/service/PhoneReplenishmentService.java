package com.kpi.bank.system.services.dao.service;

import com.kpi.bank.system.controller.Controller;
import com.kpi.bank.system.model.PhoneReplenishment;
import com.kpi.bank.system.services.dao.DAO;
import com.kpi.bank.system.services.dao.connector.MySqlPoolConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneReplenishmentService implements DAO<PhoneReplenishment> {

    private static final String DATABASE_NAME = "bank_system";
    private static final String TABLE_NAME = "phone_replenishment";

    private Connection connection;
    {
        try{
            connection = MySqlPoolConnector.getConnection();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    private PhoneReplenishment createPhoneReplenishmentFromResultSet(ResultSet resultSet) throws SQLException {
        PhoneReplenishment phoneReplenishment = new PhoneReplenishment();
        phoneReplenishment.setId(resultSet.getInt("ID"));
        phoneReplenishment.setTransactionId(resultSet.getInt("TRANSACTION_ID"));
        phoneReplenishment.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));

        return phoneReplenishment;
    }

    @Override
    public void insert(PhoneReplenishment phoneReplenishment) throws SQLException {

        String sql = String.format(
                "INSERT INTO %s.%s (ID, TRANSACTION_ID, PHONE_NUMBER) VALUES(?, ?, ?)",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, phoneReplenishment.getId());
            preparedStatement.setInt(2, phoneReplenishment.getTransactionId());
            preparedStatement.setString(3, phoneReplenishment.getPhoneNumber());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    @Override
    public List<PhoneReplenishment> loadAll() throws SQLException {

        List<PhoneReplenishment> phoneReplenishments = new ArrayList<>();

        String sql = String.format(
                "SELECT ID, TRANSACTION_ID, PHONE_NUMBER FROM %s.%s",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                PhoneReplenishment phoneReplenishment = createPhoneReplenishmentFromResultSet(resultSet);
                phoneReplenishments.add(phoneReplenishment);
            }
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
        return phoneReplenishments;
    }

    @Override
    public PhoneReplenishment loadById(Integer id) throws SQLException {

        String sql = String.format(
                "SELECT ID, TRANSACTION_ID, PHONE_NUMBER FROM %s.%s WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );
        PhoneReplenishment phoneReplenishment = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            phoneReplenishment = createPhoneReplenishmentFromResultSet(resultSet);
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
        return phoneReplenishment;
    }

    @Override
    public void update(PhoneReplenishment phoneReplenishment) throws SQLException {

        String sql = String.format(
                "UPDATE %s.%s SET TRANSACTION_ID=?, PHONE_NUMBER=? WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, phoneReplenishment.getTransactionId());
            preparedStatement.setString(2, phoneReplenishment.getPhoneNumber());
            preparedStatement.setInt(3, phoneReplenishment.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    @Override
    public void remove(PhoneReplenishment phoneReplenishment) throws SQLException {

        String sql = String.format(
                "DELETE FROM %s.%s WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, phoneReplenishment.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }
}
