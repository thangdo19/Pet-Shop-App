package com.petshop.bll;

import com.petshop.bll.domain.AppConsole;
import com.petshop.dto.Pet;

public class Demo {
  public static void demo() {
    var app = new Application(4);
    var console = new AppConsole();

    while(true) {
      var read = console.readCommand();

      if (read.equals("stop")) {
        app.shutdownExecutor();
        break;
      }

      switch (read) {
        case "show" -> app.showAllPetsAsync();
        case "add" -> {
          // simulate a pet
          var pet = new Pet(
            "DOG004", "Ha Noi", "Dog", "Sol", "Male", 2);
          app.addPetAsync(pet);
        }
        case "remove" -> {
          System.out.print("id: ");
          var id = console.readLineAndTrim();
          app.removePetAsync(id);
        }
        case "update" -> {
          System.out.print("id: ");
          var id = console.readLineAndTrim();
          // create a test Pet
          var newPet = new Pet(
            "DOG004", "TP Ho Chi Minh", "Dog", "Sol", "Male", 2);

          app.updatePetAsync(newPet.getId(), newPet);
        }
        default -> System.out.println("unknown command");
      }
    }
  }
}
