package com.revature.yolp.services;

import java.util.Scanner;

import com.revature.yolp.daos.RoleDAO;
import com.revature.yolp.daos.UserDAO;
import com.revature.yolp.models.Session;
import com.revature.yolp.screens.HomeScreen;
import com.revature.yolp.screens.MenuScreen;
import com.revature.yolp.screens.RegisterScreen;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RouterService {
    private Session session;

    public void navigate(String path, Scanner scan) {
        switch (path) {
            case "/home":
                new HomeScreen(this).start(scan);
                break;
            case "/login":
                break;
            case "/menu":
                new MenuScreen(session).start(scan);
                break;
            case "/register":
                new RegisterScreen(getUserService(), this, session).start(scan);
                break;
            case "/review":
                break;
            default:
                break;
        }
    }

    /* ---------------- Helper Method ------------------------ */

    private UserService getUserService() {
        return new UserService(new UserDAO(), getRoleService());
    }

    private RoleService getRoleService() {
        return new RoleService(new RoleDAO());
    }
}
