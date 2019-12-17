package com.kpi.bank.system;

import com.kpi.bank.system.model.Card;

import com.kpi.bank.system.model.CardReplenishment;
import com.kpi.bank.system.model.Client;
import com.kpi.bank.system.services.dao.service.CardReplenishmentService;
import com.kpi.bank.system.services.dao.service.CardService;
import com.kpi.bank.system.services.dao.service.ClientService;
import com.kpi.bank.system.services.utils.date.DateParser;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;


public class BankSystemApp {

    public static void main(final String[] argv) {

//        Card testCard = new Card();
//        testCard.setId(1);
//        testCard.setUserId(1);
//        try {
//            testCard.setStartDate(DateParser.parseByDefaultFormat("2019.03.01 13:24:54.3123"));
//            testCard.setEndDate(DateParser.parseByDefaultFormat("2019.06.25 13:24:57.4123"));
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        testCard.setBalance(34.23f);
//        testCard.setCreditLimit(34334.00f);
//
//        CardService cardService = new CardService();
//
//        try {
//            cardService.insert(testCard);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        Client client = new Client.Builder().
                setId(1)
                .setUserId(1)
                .lifePeriod(12)
                .build();

        ClientService clientService = new ClientService();

        try {
            clientService.insert(client);
            List<Client> clients =clientService.loadAll();
            for(Client item :  clients) {
                System.out.println(item);
            }
            Client byId = clientService.loadById(1);
            System.out.println(byId);

            byId.setFilePeriod(30);
            clientService.update(byId);
            System.out.println(byId);

            clientService.remove(byId);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
