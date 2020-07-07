package com.petshop;

import com.petshop.bll.Application;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    var app = new Application(4);

//    app.updatePetAsync();
    app.run();

    while(true) {
      Scanner reader = new Scanner(System.in);
      var read = reader.nextLine();

      if (read.equals("stop")) {
        app.shutdownExecutor();
        break;
      }

      if (read.equals("remove")) {
        var id = reader.nextLine();
        app.removePetAsync(id);
      }

      if (read.equals("show")) {
        app.showAllPetsAsync();
      }

      System.out.println(read);
    }
  }
}
