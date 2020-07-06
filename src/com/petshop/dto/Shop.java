package com.petshop.dto;

import java.util.Objects;

public class Shop {
  private String id;
  private String location;

  public Shop(String id, String location) {
    this.id = id;
    this.location = location;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Shop shop = (Shop) o;
    return Objects.equals(id, shop.id) &&
      Objects.equals(location, shop.location);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, location);
  }

  @Override
  public String toString() {
    return "Shop no: " + id + ", location: " + location;
  }

  public String getId() {
    return id;
  }

  public String getLocation() {
    return location;
  }
}

