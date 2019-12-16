package com.kpi.bank.system.services.dao.service;

import com.kpi.bank.system.controller.Controller;
import com.kpi.bank.system.model.Card;
import com.kpi.bank.system.services.dao.entity.DAO;
import com.kpi.bank.system.services.dao.connector.MySqlPoolConnector;
import com.kpi.bank.system.services.utils.date.DateFormator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardService implements DAO<Card> {

    private static final String DATABASE_NAME = "bank_system";
    private static final String TABLE_NAME = "card";

    private Connection connection;
     {
        try{
            connection = MySqlPoolConnector.getConnection();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    private Card createCardFromResultSet(ResultSet resultSet) throws SQLException {
        Card card = new Card();
        card.setId(resultSet.getInt("ID"));
        card.setUserId(resultSet.getInt("USER_ID"));
        card.setStartDate(resultSet.getDate("START_DATE"));
        card.setEndDate(resultSet.getDate("END_DATE"));
        card.setBalance(resultSet.getFloat("BALANCE"));
        card.setCreditLimit(resultSet.getFloat("CREDIT_LIMIT"));

        return card;
    }

    @Override
    public void insert(Card card) throws SQLException {

        String sql = String.format(
                "INSERT INTO %s.%s (ID, USER_ID, START_DATE, END_DATE, BALANCE, CREDIT_LIMIT) VALUES(?, ?, ?, ?, ?, ?)",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, card.getId());
            preparedStatement.setInt(2, card.getUserId());
            preparedStatement.setString(3, DateFormator.formatByDefaultFormator(card.getStartDate()));
            preparedStatement.setString(4, DateFormator.formatByDefaultFormator(card.getEndDate()));
            preparedStatement.setFloat(5, card.getBalance());
            preparedStatement.setFloat(6, card.getCreditLimit());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<Card> loadAll() throws SQLException {
        List<Card> cardsList = new ArrayList<>();

        String sql = String.format(
                "SELECT ID, USER_ID, START_DATE, END_DATE, BALANCE, CREDIT_LIMIT FROM %s.%s",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Card card = createCardFromResultSet(resultSet);
                cardsList.add(card);
            }
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return cardsList;
    }

    @Override
    public Card loadById(Integer id) throws SQLException {

        String sql = String.format(
                "SELECT ID, USER_ID, START_DATE, END_DATE, BALANCE, CREDIT_LIMIT FROM %s.%s WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );
        Card card = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            card = createCardFromResultSet(resultSet);


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return card;
    }

    @Override
    public void update(Card card) throws SQLException {

        String sql = String.format(
                "UPDATE %s.%s SET USER_ID=?, START_DATE=?, END_DATE=?, BALANCE=?, CREDIT_LIMIT=? WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, card.getUserId());
            preparedStatement.setString(2, DateFormator.formatByDefaultFormator(card.getStartDate()));
            preparedStatement.setString(3, DateFormator.formatByDefaultFormator(card.getEndDate()));
            preparedStatement.setFloat(4, card.getBalance());
            preparedStatement.setFloat(5, card.getCreditLimit());
            preparedStatement.setInt(6, card.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    @Override
    public void remove(Card card) throws SQLException {

        String sql = String.format(
                "DELETE FROM %s.%s WHERE ID=?",
                DATABASE_NAME,
                TABLE_NAME
        );
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, card.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
