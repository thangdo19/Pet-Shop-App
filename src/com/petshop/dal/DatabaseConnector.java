package com.petshop.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnector {
  private static DatabaseConnector instance;
  private Connection connection;

  private final String url = "jdbc:sqlserver://localhost:1433;databaseName=PetShop";
  private final String user = "thang";
  private final String password = "thang207";

  private DatabaseConnector() {}

  public void closeConnection() {
    try {
      if (connection != null)
        connection.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public ResultSet getRecords(String query) {
    try {
      connection = DriverManager.getConnection(url, user, password);
      var statement = connection.prepareStatement(query);
      return statement.executeQuery();
    }
    catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public int executeNonQuery(String command) {
    try {
      connection = DriverManager.getConnection(url, user, password);
      var statement = connection.prepareStatement(command);
      return statement.executeUpdate();
    }
    catch (SQLException throwables) {
      throwables.printStackTrace();
      return 0;
    }
  }

  public static DatabaseConnector getInstance() {
    if (instance == null)
      instance = new DatabaseConnector();
    return instance;
  }
}
