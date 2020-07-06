package com.petshop.bll.logic;

import com.petshop.bll.interfaces.PetModdable;
import com.petshop.bll.interfaces.PetTypeGettable;
import com.petshop.bll.interfaces.ShopGettable;
import com.petshop.dal.DatabaseConnector;
import com.petshop.dto.Pet;

public class PetModder implements PetModdable {
  public static PetModder instance;

  private PetModder() {}

  private String takeInsertCommand(Pet pet) {
    try {
      ShopGettable shopGettable = ShopGetter.getInstance();
      PetTypeGettable petTypeGettable = PetTypeGetter.getInstance();

      String id = pet.getId();
      String shopID = shopGettable
        .getShopByLocation(pet.getShopLocation())
        .getId();
      String typeID = petTypeGettable
        .getPetTypeByName(pet.getType())
        .getId();
      String name = pet.getName();
      int age = pet.getAge();
      String gender = pet.getGender();

      return String
        .format("INSERT INTO Pet VALUES ('%s', '%s', '%s', '%s', %s, '%s')",
          id, shopID, typeID, name, age, gender);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private String takeUpdateCommand(String id, Pet newPet) {
    try {
      ShopGettable shopGettable = ShopGetter.getInstance();
      PetTypeGettable petTypeGettable = PetTypeGetter.getInstance();

      String shopID = shopGettable
        .getShopByLocation(newPet.getShopLocation())
        .getId();
      String typeID = petTypeGettable
        .getPetTypeByName(newPet.getType())
        .getId();
      String name = newPet.getName();
      int age = newPet.getAge();
      String gender = newPet.getGender();

      return String
        .format(
          "UPDATE Pet " +
          "SET " +
          "   shop_id = '%s', " +
          "   type_id = '%s', " +
          "   name = '%s', " +
          "   age = %s, " +
          "   gender = '%s' " +
          "WHERE id = '%s'"
          , shopID, typeID, name, age, gender, id
        );
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  private String takeDeleteCommand(String petID) {
    return String
      .format("DELETE FROM Pet WHERE id = '%s'", petID);
  }

  private boolean executeDatabase(String command) {
    try {
      var result = DatabaseConnector.getInstance().executeNonQuery(command);
      DatabaseConnector.getInstance().closeConnection();

      if (result != 0)
        return true;
      return false;
    } catch (Exception e) {
      e.printStackTrace();
    }

    DatabaseConnector.getInstance().closeConnection();
    return false;
  }

  @Override
  public boolean addPet(Pet pet) {
    var insertCommand = takeInsertCommand(pet);

    if (insertCommand == null)
      return false;

    return this.executeDatabase(insertCommand);
  }

  @Override
  public boolean updatePet(String id, Pet newPet) {
    var updateCommand = takeUpdateCommand(id, newPet);

    if (updateCommand == null)
      return false;

    return this.executeDatabase(updateCommand);
  }

  @Override
  public boolean deletePet(String petID) {
    return this.executeDatabase(takeDeleteCommand(petID));
  }

  public static PetModder getInstance() {
    if (instance == null)
      instance = new PetModder();
    return instance;
  }
}


