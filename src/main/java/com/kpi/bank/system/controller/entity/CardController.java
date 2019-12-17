package com.kpi.bank.system.controller.entity;

import com.kpi.bank.system.controller.Controller;
import com.kpi.bank.system.input.console.ConsoleStream;
import com.kpi.bank.system.model.Card;
import com.kpi.bank.system.services.dao.service.CardService;
import com.kpi.bank.system.services.utils.date.DateParser;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class CardController {

    private static final String ENTITY = "CARD";
    private static final CardService USER_SERVICE = new CardService();

    public static void queryMenu(Controller controller) {
        while (true) {
            controller.printQueryMenuMessage(ENTITY);
            int key = controller.readKeyFromConsole(1, 6);
            switch (key) {
                case 1: {
                    Card card = createFromConsole();
                    insertCard(card);
                    break;
                }
                case 2: {
                    loadAllCards();
                    break;
                }
                case 3: {
                    loadCardById();
                    break;
                }
                case 4: {
                    removeUser();
                    break;
                }
                case 5: {
                    controller.startMenu();
                    return;
                }
                case 6: {
                    return;
                }
            }
        }
    }

    private static Card createFromConsole() {
        Card card = new Card();

        Controller.printInfoMessage(String.format("Enter %s ID: ", ENTITY));
        card.setId(ConsoleStream.intFromConsole());

        Controller.printInfoMessage(String.format("Enter %s User ID: ", ENTITY));
        card.setUserId(ConsoleStream.intFromConsole());

        Controller.printInfoMessage(String.format("Enter %s Start Date: ", ENTITY));
        try {
            card.setStartDate(DateParser.parseByDefaultFormat(ConsoleStream.stringFromConsole()));
        } catch (ParseException e) {
            Controller.printErrorMessage(e.toString());
        }

        Controller.printInfoMessage(String.format("Enter %s End Date: ", ENTITY));
        try {
            card.setEndDate(DateParser.parseByDefaultFormat(ConsoleStream.stringFromConsole()));
        } catch (ParseException e) {
            Controller.printErrorMessage(e.toString());
        }

        Controller.printInfoMessage(String.format("Enter %s Balance: ", ENTITY));
        card.setBalance(ConsoleStream.floatFromConsole());

        Controller.printInfoMessage(String.format("Enter %s Credit Limit: ", ENTITY));
        card.setCreditLimit(ConsoleStream.floatFromConsole());

        return card;

    }

    private static void insertCard(Card card) {
        try {
            USER_SERVICE.insert(card);
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    private static void loadAllCards() {
        try {
            List<Card> cards = USER_SERVICE.loadAll();
            for(Card card : cards) {
                Controller.printInfoMessage(card.toString());
            }
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    private static void loadCardById() {
        Controller.printInfoMessage(String.format("Enter %s ID: ", ENTITY));
        Integer id = ConsoleStream.intFromConsole();

        try {
            Card card = USER_SERVICE.loadById(id);
            if(card != null) {
                Controller.printInfoMessage(card.toString());
            }
            else {
                Controller.printErrorMessage(String.format("%s with ID:[%d] not found!", ENTITY, id));
            }

        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    private static void removeUser() {
        Controller.printInfoMessage(String.format("Enter %s ID: ", ENTITY));
        Integer id = ConsoleStream.intFromConsole();

        try {
            Card card = USER_SERVICE.loadById(id);
            if(card != null) {
                USER_SERVICE.remove(card);
            }
            else {
                Controller.printErrorMessage(String.format("%s with ID:[%d] not found!", ENTITY, id));
            }
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

}
