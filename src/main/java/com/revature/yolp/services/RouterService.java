package com.revature.yolp.services;

import java.util.Scanner;

import com.revature.yolp.daos.RoleDAO;
import com.revature.yolp.daos.UserDAO;
import com.revature.yolp.screens.HomeScreen;
import com.revature.yolp.screens.MenuScreen;
import com.revature.yolp.screens.RegisterScreen;
import com.revature.yolp.utils.Session;

import lombok.AllArgsConstructor;

/**
 * The RouterService class handles navigation in the Yolp Application.
 */
@AllArgsConstructor
public class RouterService {
    private Session session;

    /**
     * Navigates to the specified path.
     *
     * @param path the path to navigate to
     * @param scan the scanner for user input
     */
    public void navigate(String path, Scanner scan) {
        switch (path) {
            case "/home":
                // Navigate to the HomeScreen
                new HomeScreen(this).start(scan);
                break;
            case "/login":
                // TODO: Implement login screen
                break;
            case "/menu":
                // Navigate to the MenuScreen
                new MenuScreen(session).start(scan);
                break;
            case "/register":
                // Navigate to the RegisterScreen
                new RegisterScreen(getUserService(), this, session).start(scan);
                break;
            case "/review":
                // TODO: Implement review screen
                break;
            default:
                // Invalid path
                break;
        }
    }

    /* ---------------- Helper Methods ------------------------ */

    /**
     * Creates and returns a new instance of UserService.
     *
     * @return a new instance of UserService
     */
    private UserService getUserService() {
        return new UserService(new UserDAO(), getRoleService());
    }

    /**
     * Creates and returns a new instance of RoleService.
     *
     * @return a new instance of RoleService
     */
    private RoleService getRoleService() {
        return new RoleService(new RoleDAO());
    }
}
