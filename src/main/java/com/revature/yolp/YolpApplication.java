package com.revature.yolp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.yolp.services.RouterService;
import com.revature.yolp.utils.Session;

/**
 * The main class for the Yolp Application.
 */
public class YolpApplication {
  private static final Logger logger = LogManager.getLogger(YolpApplication.class);

  /**
   * The entry point of the Yolp Application.
   *
   * @param args command line arguments
   * @throws ClassNotFoundException if the specified class cannot be found
   * @throws IOException            if an I/O error occurs
   * @throws SQLException           if a database error occurs
   */
  public static void main(String args[]) throws ClassNotFoundException, IOException, SQLException {
    logger.info("------------------ START APPLICATION -----------------------------");
    Scanner scan = new Scanner(System.in);

    // Create a new RouterService with a Session
    RouterService router = new RouterService(new Session());

    // Navigate to the "/home" route using the router and scanner
    router.navigate("/home", scan);

    logger.info("------------------ END APPLICATION -----------------------------");

    scan.close();
  }
}
