package com.wfy.work4.e13.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Actions {
    public void add(People people) throws Exception {
        Connection connection = JDBC.getConnection();
        String sql = " insert into contacts(id,name,number,sex,email,address) "
                + " values(?,?,?,?,?,?) ";
        PreparedStatement pStatement = connection.prepareStatement(sql);
        pStatement.setInt(1, people.getId());
        pStatement.setString(2, people.getName());
        pStatement.setString(3, people.getNumber());
        pStatement.setString(4, people.getSex());
        pStatement.setString(5, people.getEmail());
        pStatement.setString(6, people.getAddress());
        pStatement.execute();
    }

    public void delete(String name) throws Exception {
        Connection connection = JDBC.getConnection();
        String sql = " delete from contacts where name = ? ";
        PreparedStatement pStatement = connection.prepareStatement(sql);
        pStatement.setString(1, name);
        pStatement.execute();
    }

    public void update(People people, int id) throws Exception {
        Connection connection = JDBC.getConnection();
        String sql = " update contacts set name=?,number=?,sex=?,email=?,address=?"
                + " where id=? ";
        PreparedStatement pStatement = connection.prepareStatement(sql);
        pStatement.setInt(6, id);
        pStatement.setString(1, people.getName());
        pStatement.setString(2, people.getNumber());
        pStatement.setString(3, people.getSex());
        pStatement.setString(4, people.getEmail());
        pStatement.setString(5, people.getAddress());
        pStatement.execute();
    }

    public List<People> query() throws Exception {
        List<People> list = new ArrayList<>();
        People people;
        Connection connection = JDBC.getConnection();
        String sql = " select id,name from contacts ";
        PreparedStatement pStatement = connection.prepareStatement(sql);
        ResultSet rSet = pStatement.executeQuery();
        while (rSet.next()) {
            people = new People();
            people.setId(rSet.getInt("id"));
            people.setName(rSet.getString("name"));
            list.add(people);
        }
        return list;
    }

    public People queryString(String name) throws Exception {
        People people = new People();
        Connection connection = JDBC.getConnection();
        String sql = " select * from contacts where name = ? ";
        PreparedStatement pStatement = connection.prepareStatement(sql);
        pStatement.setString(1, name);
        ResultSet rSet = pStatement.executeQuery();
        rSet.next();
        people.setEmail(rSet.getString("email"));
        people.setId(rSet.getInt("id"));
        people.setName(rSet.getString("name"));
        people.setNumber(rSet.getString("number"));
        people.setSex(rSet.getString("sex"));
        people.setAddress(rSet.getString("address"));
        return people;
    }

    public People queryId(int id) throws Exception {
        People people = new People();
        Connection connection = JDBC.getConnection();
        String sql = "select * from contacts where id = ? ";
        PreparedStatement pStatement = connection.prepareStatement(sql);
        pStatement.setInt(1, id);
        ResultSet rSet = pStatement.executeQuery();
        if (!rSet.next()) {
            return null;
        }
        people.setEmail(rSet.getString("email"));
        people.setId(rSet.getInt("id"));
        people.setName(rSet.getString("name"));
        people.setNumber(rSet.getString("number"));
        people.setSex(rSet.getString("sex"));
        people.setAddress(rSet.getString("address"));
        return people;
    }

}
