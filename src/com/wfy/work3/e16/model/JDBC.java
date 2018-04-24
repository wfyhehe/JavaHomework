package com.wfy.work3.e16.model;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class JDBC {

    private static final String URL = "jdbc:mysql://localhost:3306/db?characterEncoding=utf8" +
            "&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection = null;

    public static Connection getConnection() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Connection failed!");
        }
        return connection;
    }

    public static void print(List<People> list) {
        for (People people : list) {
            System.out.print("Id:" + people.getId() + "\t");
            System.out.print("name:" + people.getName() + "\t");
            System.out.println();
        }
    }

    public static void print(People people) {
        System.out.print("Id:" + people.getId() + "\t");
        System.out.print("name:" + people.getName() + "\t");
        System.out.print("sex:" + people.getSex() + "\t");
        System.out.print("number:" + people.getNumber() + "\t");
        System.out.print("email:" + people.getEmail() + "\t");
        System.out.print("address:" + people.getAddress() + "\t");
    }
}
