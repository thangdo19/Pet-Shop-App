package com.petshop.bll.logic;

import com.petshop.bll.interfaces.PetGettable;
import com.petshop.dal.DatabaseConnector;
import com.petshop.dto.Pet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetGetter implements PetGettable {
  public static PetGetter instance;
  private final static String petQuery = (
    "SELECT\n" +
    "\tid, location as shopLocation, type_name as type,\n" +
    "\tname, gender, age\n" +
    "FROM Pet\n" +
    "JOIN Shop ON Pet.shop_id = Shop.shop_id\n" +
    "JOIN PetType ON Pet.type_id = PetType.type_id\n"
  );

  private PetGetter() {}

  private List<Pet> getPets(String query) {
    try {
      var pets = new ArrayList<Pet>();
      var resultSet = DatabaseConnector.getInstance().getRecords(query);

      while (resultSet.next()) {
        var pet = createPetByResultSet(resultSet);
        if (pet != null)
          pets.add(pet);
      }

      DatabaseConnector.getInstance().closeConnection();
      return pets;
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }

    DatabaseConnector.getInstance().closeConnection();
    return null;
  }

  private Pet createPetByResultSet(ResultSet resultSet) {
    try {
      String id = resultSet.getString("id");
      String shopLocation = resultSet.getString("shopLocation");
      String type = resultSet.getString("type");
      String name = resultSet.getString("name");
      String gender = resultSet.getString("gender");
      int age = resultSet.getInt("age");

      return new Pet(id, shopLocation, type, name, gender, age);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Pet> getAllPets() {
    return this.getPets(petQuery);
  }

  @Override
  public List<Pet> getPetsByShopLocation(String location) {
    return this.getPets(
      petQuery + String.format("WHERE location = '%s'", location)
    );
  }

  @Override
  public List<Pet> getPetsByType(String type) {
    return this.getPets(
      petQuery + String.format("WHERE type_name = '%s'", type)
    );
  }

  @Override
  public List<Pet> getPetsByTypeAndShopLocation(String type, String location) {
    return this.getPets(
      petQuery + String.format("WHERE type_name = '%s' " +
                                "AND location = '%s'", type, location)
    );
  }

  public static PetGetter getInstance() {
    if (instance == null)
      instance = new PetGetter();
    return instance;
  }
}