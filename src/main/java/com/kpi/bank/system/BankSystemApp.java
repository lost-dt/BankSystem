package com.kpi.bank.system;

import com.kpi.bank.system.model.*;

import com.kpi.bank.system.services.dao.service.*;
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

        PhoneReplenishment client = new PhoneReplenishment.Builder().setId(1).setTransactionId(1).setPhoneNumber("49382421").build();

        PhoneReplenishmentService creditService = new PhoneReplenishmentService();

        try {
            creditService.insert(client);
            List<PhoneReplenishment> clients =creditService.loadAll();
            for(PhoneReplenishment item :  clients) {
                System.out.println(item);
            }
            PhoneReplenishment byId = creditService.loadById(1);
            System.out.println(byId);

            byId.setPhoneNumber("9791371310657483984754839245678909876543456789098765456789876545678");
            creditService.update(byId);
            System.out.println(byId);

            creditService.remove(byId);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
