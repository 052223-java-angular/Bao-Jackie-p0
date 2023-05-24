package com.revature.yolp.screens;

import java.util.Scanner;

import com.revature.yolp.models.Session;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MenuScreen implements IScreen {
    private Session session;

    @Override
    public void start(Scanner scan) {
        System.out.println("Welcome to the menuy screen " + session.getUsername() + "!");
        scan.nextLine();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
