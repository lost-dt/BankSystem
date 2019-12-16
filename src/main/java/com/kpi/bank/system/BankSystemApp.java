package com.kpi.bank.system;

import com.kpi.bank.system.model.Card;

import com.kpi.bank.system.services.dao.service.CardService;
import com.kpi.bank.system.services.utils.date.DateParser;

import java.sql.SQLException;
import java.text.ParseException;


public class BankSystemApp {

    public static void main(final String[] argv) {

        Card testCard = new Card();
        testCard.setId(1);
        testCard.setUserId(1);
        try {
            testCard.setStartDate(DateParser.parseByDefaultFormat("2019.03.01 13:24:54.3123"));
            testCard.setEndDate(DateParser.parseByDefaultFormat("2019.06.25 13:24:57.4123"));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        testCard.setBalance(34.23f);
        testCard.setCreditLimit(34334.00f);

        CardService cardService = new CardService();

        try {
            cardService.insert(testCard);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
