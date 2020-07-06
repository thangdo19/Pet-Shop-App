package com.petshop.bll.logic;

import com.petshop.bll.interfaces.PetTypeGettable;
import com.petshop.dal.DatabaseConnector;
import com.petshop.dto.PetType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetTypeGetter implements PetTypeGettable {
  private static PetTypeGetter instance;
  private static final String petTypeQuery = (
    "SELECT * " +
    "FROM PetType "
    );

  private PetTypeGetter() {}

  private PetType createPetTypeByResultSet(ResultSet resultSet) {
    try {
      String id = resultSet.getString("type_id");
      String typeName = resultSet.getString("type_name");

      return new PetType(id, typeName);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    return null;
  }

  private List<PetType> getPetTypes(String query) {
    try {
      var petTypes = new ArrayList<PetType>();
      var resultSet = DatabaseConnector.getInstance()
        .getRecords(query);

      while (resultSet.next()) {
        var petType = this.createPetTypeByResultSet(resultSet);

        if (petType != null)
          petTypes.add(petType);
      }

      DatabaseConnector.getInstance().closeConnection();
      return petTypes;
    } catch (Exception e) {
      e.printStackTrace();
    }

    DatabaseConnector.getInstance().closeConnection();
    return null;
  }

  @Override
  public List<PetType> getAllPetTypes() {
    return this.getPetTypes(petTypeQuery);
  }

  @Override
  public PetType getPetTypeByName(String typeName) {
    return this.getPetTypes(
      petTypeQuery + String.format("WHERE type_name = '%s' ", typeName))
      .get(0);
  }

  public static PetTypeGetter getInstance() {
    if (instance == null)
      instance = new PetTypeGetter();
    return instance;
  }
}



