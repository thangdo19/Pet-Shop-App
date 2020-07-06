package com.petshop.bll.interfaces;

import com.petshop.dto.Shop;

import java.util.List;

public interface ShopGettable {
  List<Shop> getAllShops();
  Shop getShopByLocation(String location);
}

