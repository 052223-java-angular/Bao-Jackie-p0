package com.revature.yolp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.yolp.models.Session;
import com.revature.yolp.services.RouterService;

public class YolpApplication {
  public static void main(String args[]) throws ClassNotFoundException, IOException, SQLException {
    Scanner scan = new Scanner(System.in);
    RouterService router = new RouterService(new Session());
    router.navigate("/home", scan);
    scan.close();
  }
}
