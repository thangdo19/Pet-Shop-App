package com.petshop.bll.domain;

import java.util.Scanner;

public class AppConsole {
  private static Scanner reader;

  public AppConsole() {
    if (reader == null)
      reader = new Scanner(System.in);
  }

  public String readCommand() {
    return reader.nextLine().trim().toLowerCase();
  }

  public String readLineAndTrim() {
    return reader.nextLine().trim();
  }
}


