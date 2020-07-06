package com.petshop.dto;

public class Pet {
  private String id;
  private String shopLocation;
  private String type;
  private String name;
  private String gender;
  private int age;

  public Pet(String id, String shopLocation,
             String type, String name, String gender, int age) {
    this.id = id;
    this.shopLocation = shopLocation;
    this.type = type;
    this.name = name;
    this.gender = gender;
    this.age = age;
  }

  @Override
  public String toString() {
    return type + ": " + name;
  }

  public String getId() {
    return id;
  }

  public String getShopLocation() {
    return shopLocation;
  }

  public String getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public String getGender() {
    return gender;
  }

  public int getAge() {
    return age;
  }
}
