package com.petshop;

import com.petshop.bll.Application;

public class Main {
  public static void main(String[] args) {
    var app = new Application();

    app.run();

    try {
      Thread.sleep(10_000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
