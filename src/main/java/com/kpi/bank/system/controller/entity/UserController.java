package com.kpi.bank.system.controller.entity;

import com.kpi.bank.system.controller.Controller;
import com.kpi.bank.system.input.console.ConsoleStream;
import com.kpi.bank.system.model.User;
import com.kpi.bank.system.services.dao.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserController {

    private static final String ENTITY = "USER";
    private static final UserService USER_SERVICE = new UserService();

    public static void queryMenu(Controller controller) {
        while (true) {
            controller.printQueryMenuMessage(ENTITY);
            int key = controller.readKeyFromConsole(1, 6);
            switch (key) {
                case 1: {
                    User user = createFromConsole();
                    insertUser(user);
                    break;
                }
                case 2: {
                    loadAllUsers();
                    break;
                }
                case 3: {
                    loadUserById();
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

    private static User createFromConsole() {
        User.Builder userBuilder = new User.Builder();

        Controller.printInfoMessage(String.format("Enter %s ID: ", ENTITY));
        userBuilder.setId(ConsoleStream.intFromConsole());

        Controller.printInfoMessage(String.format("Enter %s Email: ", ENTITY));
        userBuilder.setEmail(ConsoleStream.stringFromConsole());

        Controller.printInfoMessage(String.format("Enter %s Password: ", ENTITY));
        userBuilder.setPassword(ConsoleStream.stringFromConsole());

        Controller.printInfoMessage(String.format("Enter %s First Name: ", ENTITY));
        userBuilder.setFirstName(ConsoleStream.stringFromConsole());

        Controller.printInfoMessage(String.format("Enter %s Last Name: ", ENTITY));
        userBuilder.setLastName(ConsoleStream.stringFromConsole());

        Controller.printInfoMessage(String.format("Enter %s Secret Word: ", ENTITY));
        userBuilder.setSecretWord(ConsoleStream.stringFromConsole());

        return userBuilder.build();

    }

    private static void insertUser(User user) {
        try {
            USER_SERVICE.insert(user);
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    private static void loadAllUsers() {
        try {
            List<User> users = USER_SERVICE.loadAll();
            for(User user : users) {
                Controller.printInfoMessage(user.toString());
            }
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }

    private static void loadUserById() {
        Controller.printInfoMessage(String.format("Enter %s ID: ", ENTITY));
        Integer id = ConsoleStream.intFromConsole();

        try {
            User user = USER_SERVICE.loadById(id);
            if(user != null) {
                Controller.printInfoMessage(user.toString());
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
            User user = USER_SERVICE.loadById(id);
            if(user != null) {
                USER_SERVICE.remove(user);
            }
            else {
                Controller.printErrorMessage(String.format("%s with ID:[%d] not found!", ENTITY, id));
            }
        } catch (SQLException e) {
            Controller.printErrorMessage(e.toString());
        }
    }
}
