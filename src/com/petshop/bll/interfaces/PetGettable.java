package com.petshop.bll.interfaces;

import com.petshop.dto.Pet;

import java.util.List;

public interface PetGettable {
  List<Pet> getAllPets();
  List<Pet> getPetsByShopLocation(String location);
  List<Pet> getPetsByType(String type);
  List<Pet> getPetsByTypeAndShopLocation(String type, String location);
}
