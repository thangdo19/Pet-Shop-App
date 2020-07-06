package com.petshop.bll.logic;

import com.petshop.bll.interfaces.ShopGettable;
import com.petshop.dal.DatabaseConnector;
import com.petshop.dto.Shop;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShopGetter implements ShopGettable {
  public static ShopGetter instance;
  private final static String shopQuery = (
    "SELECT *\n" +
    "FROM Shop\n"
  );

  private ShopGetter() {}

  private Shop createShopByResultSet(ResultSet result) {
    try {
      String id = result.getString("shop_id");
      String location = result.getString("location");

      return new Shop(id, location);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  private List<Shop> getShops(String query) {
    try {
      var shops = new ArrayList<Shop>();
      var result = DatabaseConnector.getInstance().getRecords(query);

      while (result.next()) {
        var shop = this.createShopByResultSet(result);
        if (shop != null)
          shops.add(shop);
      }

      DatabaseConnector.getInstance().closeConnection();
      return shops;
    } catch (Exception e) {
      e.printStackTrace();
    }

    DatabaseConnector.getInstance().closeConnection();
    return null;
  }

  @Override
  public List<Shop> getAllShops() {
    return this.getShops(shopQuery);
  }

  @Override
  public Shop getShopByLocation(String location) {
    try {
      var result = this.getShops(shopQuery + String.format("WHERE location = '%s'", location));
      var shop = result.get(0);

      if (shop == null)
        throw new Exception();

      DatabaseConnector.getInstance().closeConnection();
      return shop;
    } catch (Exception e) {
      e.printStackTrace();
    }

    DatabaseConnector.getInstance().closeConnection();
    return null;
  }

  public static ShopGetter getInstance() {
    if (instance == null)
      instance = new ShopGetter();
    return instance;
  }
}






