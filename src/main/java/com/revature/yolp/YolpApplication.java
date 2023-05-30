package com.revature.yolp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import com.revature.yolp.services.RouterService;
import com.revature.yolp.utils.Session;

/**
 * The main class for the Yolp Application.
 */
public class YolpApplication {

  /**
   * The entry point of the Yolp Application.
   *
   * @param args command line arguments
   * @throws ClassNotFoundException if the specified class cannot be found
   * @throws IOException            if an I/O error occurs
   * @throws SQLException           if a database error occurs
   */
  public static void main(String args[]) throws ClassNotFoundException, IOException, SQLException {
    Scanner scan = new Scanner(System.in);

    Session session = new Session();
    // Create a new RouterService with a Session
    RouterService router = new RouterService(session);

    // Navigate to the "/home" route using the router and scanner
    router.navigate("/home", scan);

    scan.close();
  }
}
