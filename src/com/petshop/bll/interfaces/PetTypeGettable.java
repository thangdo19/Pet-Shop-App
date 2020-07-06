package com.petshop.bll.interfaces;

import com.petshop.dto.PetType;

import java.util.List;

public interface PetTypeGettable {
  List<PetType> getAllPetTypes();
  PetType getPetTypeByName(String typeName);
}

