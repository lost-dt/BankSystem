package com.kpi.bank.system.services.dao.service;

import com.kpi.bank.system.controller.Controller;
import com.kpi.bank.system.model.Transaction;
import com.kpi.bank.system.services.dao.DAO;
import com.kpi.bank.system.services.dao.connector.MySqlPoolConnector;
import com.kpi.bank.system.services.utils.date.DateFormator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionService implements DAO<Transaction> {

    private static final String DATABASE_NAME = "bank_system";
    private static final String TABLE_NAME = "transaction";

    private Connection connection;
    {
        try{
            connection = MySqlPoolConnector.getConnection();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    private Transaction createTransactionFromResultSet(ResultSet resultSet) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(resultSet.getInt("ID"));
        transaction.setCardId(resultSet.getInt("CARD_ID"));
        transaction.setPrice(resultSet.getFloat("PRICE"));
        transaction.setStartTime(resultSet.getDate("START_TIME"));
        transaction.setEndTime(resultSet.getDate("END_TIME"));

        return transaction;
    }

    @Override
    public void insert(Transaction transaction) throws SQLException {

        String sql = String.format(
                "INSERT INTO %s.%s (ID, CARD_ID, PRICE, START_TIME, END_TIME) VALUES(?, ?, ?, ?, ?)",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, transaction.getId());
            preparedStatement.setInt(2, transaction.getCardId());
            preparedStatement.setFloat(3, transaction.getPrice());
            preparedStatement.setString(4, DateFormator.formatByDefaultFormator(transaction.getStartTime()));
            preparedStatement.setString(5, DateFormator.formatByDefaultFormator(transaction.getEndTime()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    @Override
    public List<Transaction> loadAll() throws SQLException {

        List<Transaction> transactions = new ArrayList<>();

        String sql = String.format(
                "SELECT ID, CARD_ID, PRICE, START_TIME, END_TIME FROM %s.%s",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Transaction transaction = createTransactionFromResultSet(resultSet);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
        return transactions;
    }

    @Override
    public Transaction loadById(Integer id) throws SQLException {

        String sql = String.format(
                "SELECT ID, CARD_ID, PRICE, START_TIME, END_TIME FROM %s.%s WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );

        Transaction transaction = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            transaction = createTransactionFromResultSet(resultSet);
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
        return transaction;
    }

    @Override
    public void update(Transaction transaction) throws SQLException {

        String sql = String.format(
                "UPDATE %s.%s SET CARD_ID=?, PRICE=?, START_TIME=?, END_TIME=? WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {


            preparedStatement.setInt(1, transaction.getCardId());
            preparedStatement.setFloat(2, transaction.getPrice());
            preparedStatement.setString(3, DateFormator.formatByDefaultFormator(transaction.getStartTime()));
            preparedStatement.setString(4, DateFormator.formatByDefaultFormator(transaction.getEndTime()));
            preparedStatement.setInt(5, transaction.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    @Override
    public void remove(Transaction transaction) throws SQLException {

        String sql = String.format(
                "DELETE FROM %s.%s WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, transaction.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }
}
