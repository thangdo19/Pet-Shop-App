package com.petshop.dto;

public class PetType {
  private String id;
  private String typeName;

  public PetType(String id, String typeName) {
    this.id = id;
    this.typeName = typeName;
  }

  @Override
  public String toString() {
    return "PetType{" +
      "id='" + id + '\'' +
      ", typeName='" + typeName + '\'' +
      '}';
  }

  public String getId() {
    return id;
  }

  public String getTypeName() {
    return typeName;
  }
}
