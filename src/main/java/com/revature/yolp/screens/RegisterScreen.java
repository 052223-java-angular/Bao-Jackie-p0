package com.revature.yolp.screens;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.yolp.models.User;
import com.revature.yolp.services.RouterService;
import com.revature.yolp.services.UserService;
import com.revature.yolp.utils.Session;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterScreen implements IScreen {
    private final UserService userService;
    private final RouterService router;
    private Session session;
    private static final Logger logger = LogManager.getLogger(RegisterScreen.class);

    @Override
    public void start(Scanner scan) {
        String username = "";
        String password = "";

        logger.info("Start registration process...");

        exit: {
            while (true) {
                clearScreen();
                System.out.println("Welcome to the register screen!");

                // get username
                username = getUsername(scan);

                logger.info("username: {}", username);

                if (username.equals("x")) {
                    logger.info("Exit registration screen.");
                    break exit;
                }

                // get password
                password = getPassword(scan);

                if (password.equals("x")) {
                    logger.info("Exit registration screen.");
                    break exit;
                }

                // confirm user's info
                clearScreen();
                System.out.println("Please confirm your credentials:");
                System.out.println("\nUsername: " + username);
                System.out.println("Password: " + password);
                System.out.print("\nEnter (y/n): ");

                switch (scan.nextLine()) {
                    case "y":
                        logger.info("User confirm credentials are correct.");
                        User createdUser = userService.register(username, password);
                        session.setSession(createdUser);
                        router.navigate("/menu", scan);
                        break exit;
                    case "n":
                        logger.info("Restarting registration process...");
                        clearScreen();
                        System.out.println("Restarting process...");
                        System.out.print("\nPress enter to continue...");
                        scan.nextLine();
                        break;
                    default:
                        logger.warn("Invalid option!");
                        clearScreen();
                        System.out.println("Invalid option!");
                        System.out.print("\nPress enter to continue...");
                        scan.nextLine();
                        break;
                }
            }
        }
    }

    /*
     * ------------------------ Helper methods ------------------------------
     */

    public String getUsername(Scanner scan) {
        String username = "";

        while (true) {
            System.out.print("\nEnter a username (x to cancel): ");
            username = scan.nextLine();

            if (username.equalsIgnoreCase("x")) {
                return "x";
            }

            if (!userService.isValidUsername(username)) {
                logger.warn("Invalid username for: {}", username);
                clearScreen();
                System.out.println("Username needs to be 8-20 characters long.");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }

            if (!userService.isUniqueUsername(username)) {
                logger.warn("Username is not unique for: {}", username);
                clearScreen();
                System.out.println("Username is not unique!");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }

            break;
        }

        return username;
    }

    public String getPassword(Scanner scan) {
        String password = "";
        String confirmPassword = "";

        while (true) {
            System.out.print("\nEnter a password (x to cancel): ");
            password = scan.nextLine();

            if (password.equalsIgnoreCase("x")) {
                return "x";
            }

            if (!userService.isValidPassword(password)) {
                clearScreen();
                System.out.println("Password needs to be minimum 8 characters, at least 1 letter and 1 number");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }

            System.out.print("\nPlease confirm password (x to cancel): ");
            confirmPassword = scan.nextLine();

            if (confirmPassword.equalsIgnoreCase("x")) {
                return "x";
            }

            if (!userService.isSamePassword(password, confirmPassword)) {
                clearScreen();
                System.out.println("Passwords do not match");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }

            break;
        }

        return password;
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
