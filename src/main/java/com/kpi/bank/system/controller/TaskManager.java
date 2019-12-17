package com.kpi.bank.system.controller;

import com.kpi.bank.system.controller.entity.CardController;
import com.kpi.bank.system.controller.entity.CreditController;
import com.kpi.bank.system.controller.entity.UserController;

public class TaskManager {

    static void startMenu(Controller controller) {
        controller.printWorkInfo();

        while (true) {
            controller.printStartMenuMessage();
            int key = controller.readKeyFromConsole(1, 10);
            switch (key) {
                case 1: {
                    UserController.queryMenu(controller);
                    return;
                }
                case 2: {
                    CardController.queryMenu(controller);
                    return;
                }
                case 5: {
                    CreditController.queryMenu(controller);
                    break;
                }
                case 10: {
                    return;
                }

            }

        }
    }
}
