package com.petshop.bll;

import com.petshop.bll.interfaces.PetModdable;
import com.petshop.bll.logic.PetModder;
import com.petshop.dto.Pet;

public class Application {
  public Application() {}

  public void run() {
    var pet = new Pet("DOG004", "Ha Noi", "Dog", "Sol", "Male", 2);
    var newPet = new Pet("DOG004", "TP Ho Chi Minh", "Dog", "Sol", "Male", 2);

    PetModdable petModdable = PetModder.getInstance();
    System.out.println(petModdable.updatePet(pet.getId(), newPet));
//    System.out.println(petModdable.deletePet(pet.getId()));
//    System.out.println(petModdable.addPet(pet));
  }
}

