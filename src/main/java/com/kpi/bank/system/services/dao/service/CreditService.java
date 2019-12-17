package com.kpi.bank.system.services.dao.service;

import com.kpi.bank.system.controller.Controller;
import com.kpi.bank.system.model.Credit;
import com.kpi.bank.system.services.dao.DAO;
import com.kpi.bank.system.services.dao.connector.MySqlPoolConnector;
import com.kpi.bank.system.services.utils.date.DateFormator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreditService implements DAO<Credit> {

    private static final String DATABASE_NAME = "bank_system";
    private static final String TABLE_NAME = "credit";

    private Connection connection;
    {
        try{
            connection = MySqlPoolConnector.getConnection();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    private Credit createCreditFromResultSet(ResultSet resultSet) throws SQLException {
        Credit client = new Credit();
        client.setId(resultSet.getInt("ID"));
        client.setCardId(resultSet.getInt("CARD_ID"));
        client.setManagerId(resultSet.getInt("MANAGER_ID"));
        client.setStartDate(resultSet.getDate("START_DATE"));
        client.setEndDate(resultSet.getDate("END_DATE"));
        client.setPercent(resultSet.getFloat("PERCENT"));
        client.setPeriodDays(resultSet.getInt("PERIOD_DAYS"));
        client.setPrice(resultSet.getFloat("PRICE"));

        return client;
    }

    @Override
    public void insert(Credit credit) throws SQLException {
        String sql = String.format(
                "INSERT INTO %s.%s (ID, CARD_ID, MANAGER_ID, START_DATE, END_DATE, PERCENT, PERIOD_DAYS, PRICE) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, credit.getId());
            preparedStatement.setInt(2, credit.getCardId());
            preparedStatement.setInt(3, credit.getManagerId());
            preparedStatement.setString(4, DateFormator.formatByDefaultFormator(credit.getStartDate()));
            preparedStatement.setString(5, DateFormator.formatByDefaultFormator(credit.getEndDate()));
            preparedStatement.setFloat(6, credit.getPercent());
            preparedStatement.setInt(7, credit.getPeriodDays());
            preparedStatement.setFloat(8, credit.getPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    @Override
    public List<Credit> loadAll() throws SQLException {

        List<Credit> credits = new ArrayList<>();

        String sql = String.format(
                "SELECT ID, CARD_ID, MANAGER_ID, START_DATE, END_DATE, PERCENT, PERIOD_DAYS, PRICE FROM %s.%s",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Credit credit = createCreditFromResultSet(resultSet);
                credits.add(credit);
            }
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
        return credits;
    }

    @Override
    public Credit loadById(Integer id) throws SQLException {

        String sql = String.format(
                "SELECT ID, CARD_ID, MANAGER_ID, START_DATE, END_DATE, PERCENT, PERIOD_DAYS, PRICE FROM %s.%s WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );
        Credit credit = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            credit = createCreditFromResultSet(resultSet);
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
        return credit;
    }

    @Override
    public void update(Credit credit) throws SQLException {

        String sql = String.format(
                "UPDATE %s.%s SET CARD_ID=?, MANAGER_ID=?, START_DATE=?, END_DATE=?, PERCENT=?, PERIOD_DAYS=?, PRICE=? WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, credit.getCardId());
            preparedStatement.setInt(2, credit.getManagerId());
            preparedStatement.setString(3, DateFormator.formatByDefaultFormator(credit.getStartDate()));
            preparedStatement.setString(4, DateFormator.formatByDefaultFormator(credit.getEndDate()));
            preparedStatement.setFloat(5, credit.getPercent());
            preparedStatement.setInt(6, credit.getPeriodDays());
            preparedStatement.setFloat(7, credit.getPrice());
            preparedStatement.setInt(8, credit.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    @Override
    public void remove(Credit credit) throws SQLException {

        String sql = String.format(
                "DELETE FROM %s.%s WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, credit.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }

    }
}
