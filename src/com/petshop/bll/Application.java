package com.petshop.bll;

import com.petshop.bll.interfaces.PetGettable;
import com.petshop.bll.interfaces.PetModdable;
import com.petshop.bll.logic.PetGetter;
import com.petshop.bll.logic.PetModder;
import com.petshop.dto.Pet;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {
  private ExecutorService executor;

  public Application(int nThreads) {
    // create n worker threads for this executor
    executor = Executors.newFixedThreadPool(nThreads);
  }

  public void showAllPetsAsync() {
    CompletableFuture.runAsync(() -> {
        PetGettable petGettable = PetGetter.getInstance();
        System.out.println(petGettable.getAllPets());
      }
      , this.executor
    );
  }

  public void addPetAsync(Pet pet) {
    CompletableFuture.runAsync(() -> {
      PetModdable petModdable = PetModder.getInstance();
      System.out.println(petModdable.addPet(pet));
      }
      , this.executor
    );
  }

  public void updatePetAsync(String id, Pet desPet) {
    CompletableFuture.runAsync(() -> {
        PetModdable petModdable = PetModder.getInstance();
        System.out.println(petModdable.updatePet(id, desPet));
      }
      , this.executor
    );
  }

  public void removePetAsync(String id) {
    CompletableFuture
      .supplyAsync(() -> {
          PetModdable petModdable = PetModder.getInstance();
          return petModdable.deletePet(id);
        }
        , this.executor
      )
      .thenAccept(isRemoved -> {
        if (isRemoved)
          System.out.println("Removing Successfully");
        else
          System.out.println("Removing Unsuccessfully");
      });
  }

  public void shutdownExecutor() {
    if (executor != null)
      executor.shutdown();
  }
}