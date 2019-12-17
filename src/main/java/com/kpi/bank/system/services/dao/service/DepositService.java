package com.kpi.bank.system.services.dao.service;

import com.kpi.bank.system.controller.Controller;
import com.kpi.bank.system.model.Deposit;
import com.kpi.bank.system.services.dao.DAO;
import com.kpi.bank.system.services.dao.connector.MySqlPoolConnector;
import com.kpi.bank.system.services.utils.date.DateFormator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepositService implements DAO<Deposit> {

    private static final String DATABASE_NAME = "bank_system";
    private static final String TABLE_NAME = "deposit";

    private Connection connection;
    {
        try{
            connection = MySqlPoolConnector.getConnection();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    private Deposit createDepositFromResultSet(ResultSet resultSet) throws SQLException {
        Deposit deposit = new Deposit();
        deposit.setId(resultSet.getInt("ID"));
        deposit.setCardId(resultSet.getInt("CARD_ID"));
        deposit.setStartDate(resultSet.getDate("START_DATE"));
        deposit.setEndDate(resultSet.getDate("END_DATE"));
        deposit.setPercent(resultSet.getFloat("PERCENT"));
        deposit.setPeriodDays(resultSet.getInt("PERIOD_DAYS"));
        deposit.setPrice(resultSet.getFloat("PRICE"));

        return deposit;
    }

    @Override
    public void insert(Deposit deposit) throws SQLException {

        String sql = String.format(
                "INSERT INTO %s.%s (ID, CARD_ID, START_DATE, END_DATE, PERCENT, PERIOD_DAYS, PRICE) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, deposit.getId());
            preparedStatement.setInt(2, deposit.getCardId());
            preparedStatement.setString(3, DateFormator.formatByDefaultFormator(deposit.getStartDate()));
            preparedStatement.setString(4, DateFormator.formatByDefaultFormator(deposit.getEndDate()));
            preparedStatement.setFloat(5, deposit.getPercent());
            preparedStatement.setInt(6, deposit.getPeriodDays());
            preparedStatement.setFloat(7, deposit.getPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    @Override
    public List<Deposit> loadAll() throws SQLException {

        List<Deposit> deposits = new ArrayList<>();

        String sql = String.format(
                "SELECT ID, CARD_ID, START_DATE, END_DATE, PERCENT, PERIOD_DAYS, PRICE FROM %s.%s",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Deposit deposit = createDepositFromResultSet(resultSet);
                deposits.add(deposit);
            }
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
        return deposits;
    }

    @Override
    public Deposit loadById(Integer id) throws SQLException {

        String sql = String.format(
                "SELECT ID, CARD_ID, START_DATE, END_DATE, PERCENT, PERIOD_DAYS, PRICE FROM %s.%s WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );
        Deposit deposit = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            deposit = createDepositFromResultSet(resultSet);
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
        return deposit;
    }

    @Override
    public void update(Deposit deposit) throws SQLException {

        String sql = String.format(
                "UPDATE %s.%s SET CARD_ID=?, START_DATE=?, END_DATE=?, PERCENT=?, PERIOD_DAYS=?, PRICE=? WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, deposit.getCardId());
            preparedStatement.setString(2, DateFormator.formatByDefaultFormator(deposit.getStartDate()));
            preparedStatement.setString(3, DateFormator.formatByDefaultFormator(deposit.getEndDate()));
            preparedStatement.setFloat(4, deposit.getPercent());
            preparedStatement.setInt(5, deposit.getPeriodDays());
            preparedStatement.setFloat(6, deposit.getPrice());
            preparedStatement.setInt(7, deposit.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    @Override
    public void remove(Deposit deposit) throws SQLException {

        String sql = String.format(
                "DELETE FROM %s.%s WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, deposit.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }
}
