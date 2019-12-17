package com.kpi.bank.system.services.dao.service;

import com.kpi.bank.system.controller.Controller;
import com.kpi.bank.system.model.CardReplenishment;
import com.kpi.bank.system.services.dao.connector.MySqlPoolConnector;
import com.kpi.bank.system.services.dao.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardReplenishmentService implements DAO<CardReplenishment> {

    private static final String DATABASE_NAME = "bank_system";
    private static final String TABLE_NAME = "card_replenishment";

    private Connection connection;
    {
        try{
            connection = MySqlPoolConnector.getConnection();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    private CardReplenishment createCardReplenishmentFromResultSet(ResultSet resultSet) throws SQLException {
        CardReplenishment cardReplenishment = new CardReplenishment();
        cardReplenishment.setId(resultSet.getInt("ID"));
        cardReplenishment.setTransactionId(resultSet.getInt("TRANSACTION_ID"));
        cardReplenishment.setCardNumber(resultSet.getString("CARD_NUMBER"));

        return cardReplenishment;
    }

    @Override
    public void insert(CardReplenishment cardReplenishment) throws SQLException {
        String sql = String.format(
                "INSERT INTO %s.%s (ID, TRANSACTION_ID, CARD_NUMBER) VALUES(?, ?, ?)",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, cardReplenishment.getId());
            preparedStatement.setInt(2, cardReplenishment.getTransactionId());
            preparedStatement.setString(3, cardReplenishment.getCardNumber());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    @Override
    public List<CardReplenishment> loadAll() throws SQLException {
        List<CardReplenishment> cardReplenishments = new ArrayList<>();

        String sql = String.format(
                "SELECT ID, TRANSACTION_ID, CARD_NUMBER FROM %s.%s",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                CardReplenishment cardFromResultSet = createCardReplenishmentFromResultSet(resultSet);
                cardReplenishments.add(cardFromResultSet);
            }
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
        return cardReplenishments;
    }

    @Override
    public CardReplenishment loadById(Integer id) throws SQLException {

        String sql = String.format(
                "SELECT ID, TRANSACTION_ID, CARD_NUMBER FROM %s.%s WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );
        CardReplenishment cardReplenishment = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            cardReplenishment = createCardReplenishmentFromResultSet(resultSet);
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
        return cardReplenishment;
    }

    @Override
    public void update(CardReplenishment cardReplenishment) throws SQLException {

        String sql = String.format(
                "UPDATE %s.%s SET TRANSACTION_ID=?, CARD_NUMBER=? WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, cardReplenishment.getTransactionId());
            preparedStatement.setString(2, cardReplenishment.getCardNumber());
            preparedStatement.setInt(3, cardReplenishment.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }

    }

    @Override
    public void remove(CardReplenishment cardReplenishment) throws SQLException {

        String sql = String.format(
                "DELETE FROM %s.%s WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, cardReplenishment.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }

    }
}
