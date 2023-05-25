package com.revature.yolp.screens;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.yolp.services.RouterService;

import lombok.AllArgsConstructor;

/**
 * The HomeScreen class represents the home screen of the Yolp Application.
 * It implements the IScreen interface.
 */
@AllArgsConstructor
public class HomeScreen implements IScreen {
    private final RouterService router;
    private static final Logger logger = LogManager.getLogger(HomeScreen.class);

    @Override
    public void start(Scanner scan) {
        String input = "";

        logger.info("Navigated to home screen.");

        exit: {
            while (true) {
                clearScreen();
                System.out.println("Welcome to YOLP!");
                System.out.println("\n[1] Login screen");
                System.out.println("[2] Register screen");
                System.out.println("[x] Exit");

                System.out.print("\nEnter: ");
                input = scan.nextLine();

                switch (input.toLowerCase()) {
                    case "1":
                        logger.info("Navigating to Login screen.");
                        // TODO: Implement login screen
                        break;
                    case "2":
                        logger.info("Navigating to register screen.");
                        // Navigate to the RegisterScreen
                        router.navigate("/register", scan);
                        break;
                    case "x":
                        logger.info("Exit home screen.");
                        System.out.println("\nGoodbye!");
                        break exit;
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

    /**
     * Clears the console screen.
     */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
