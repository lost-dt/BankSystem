package com.kpi.bank.system.controller.entity;

import com.kpi.bank.system.controller.Controller;
import com.kpi.bank.system.controller.algorithm.PercentCounterStable;
import com.kpi.bank.system.input.console.ConsoleStream;
import com.kpi.bank.system.model.Credit;
import com.kpi.bank.system.services.dao.service.CreditService;
import com.kpi.bank.system.services.utils.date.DateParser;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class CreditController {

    private static final String ENTITY = "CREDIT";
    private static final CreditService USER_SERVICE = new CreditService();

    public static void queryMenu(Controller controller) {
        while (true) {
            controller.printQueryMenuMessage(ENTITY);
            int key = controller.readKeyFromConsole(1, 6);
            switch (key) {
                case 1: {
                    Credit credit = createFromConsole();
                    credit.setPercent(new PercentCounterStable().creditPercent(credit));
                    insertCredit(credit);
                    break;
                }
                case 2: {
                    loadAllCredits();
                    break;
                }
                case 3: {
                    loadCreditById();
                    break;
                }
                case 4: {
                    removeCredit();
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

    private static Credit createFromConsole() {
        Credit credit = new Credit();

        Controller.printInfoMessage(String.format("Enter %s ID: ", ENTITY));
        credit.setId(ConsoleStream.intFromConsole());

        Controller.printInfoMessage(String.format("Enter %s Card ID: ", ENTITY));
        credit.setCardId(ConsoleStream.intFromConsole());

        Controller.printInfoMessage(String.format("Enter %s Manager ID: ", ENTITY));
        credit.setManagerId(ConsoleStream.intFromConsole());

        Controller.printInfoMessage(String.format("Enter %s Start Date: ", ENTITY));
        try {
            credit.setStartDate(DateParser.parseByDefaultFormat(ConsoleStream.stringFromConsole()));
        } catch (ParseException e) {
            Controller.printErrorMessage(e.toString());
        }

        Controller.printInfoMessage(String.format("Enter %s End Date: ", ENTITY));
        try {
            credit.setEndDate(DateParser.parseByDefaultFormat(ConsoleStream.stringFromConsole()));
        } catch (ParseException e) {
            Controller.printErrorMessage(e.toString());
        }

        Controller.printInfoMessage(String.format("Enter %s Period Days: ", ENTITY));
        credit.setPeriodDays(ConsoleStream.intFromConsole());


        Controller.printInfoMessage(String.format("Enter %s Price: ", ENTITY));
        credit.setPrice(ConsoleStream.floatFromConsole());

        return credit;

    }

    private static void insertCredit(Credit credit) {
        try {
            USER_SERVICE.insert(credit);
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    private static void loadAllCredits() {
        try {
            List<Credit> credits = USER_SERVICE.loadAll();
            for(Credit user : credits) {
                Controller.printInfoMessage(user.toString());
            }
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    private static void loadCreditById() {
        Controller.printInfoMessage(String.format("Enter %s ID: ", ENTITY));
        Integer id = ConsoleStream.intFromConsole();

        try {
            Credit credit = USER_SERVICE.loadById(id);
            if(credit != null) {
                Controller.printInfoMessage(credit.toString());
            }
            else {
                Controller.printErrorMessage(String.format("%s with ID:[%d] not found!", ENTITY, id));
            }

        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    private static void removeCredit() {
        Controller.printInfoMessage(String.format("Enter %s ID: ", ENTITY));
        Integer id = ConsoleStream.intFromConsole();

        try {
            Credit credit = USER_SERVICE.loadById(id);
            if(credit != null) {
                USER_SERVICE.remove(credit);
            }
            else {
                Controller.printErrorMessage(String.format("%s with ID:[%d] not found!", ENTITY, id));
            }
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }
}
