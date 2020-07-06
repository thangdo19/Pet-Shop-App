package com.petshop.bll.interfaces;

import com.petshop.dto.Pet;

public interface PetModdable {
  boolean addPet(Pet pet);
  boolean updatePet(String id, Pet newPet);
  boolean deletePet(String petID);
}

