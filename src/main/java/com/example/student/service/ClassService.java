package com.example.student.service;

import com.example.student.config.ConnectionJDBC;
import com.example.student.model.Class;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassService implements IService<Class>{
    private static final String ADD_CLASS = "insert into classroom(name) value(?);";
    private static final String FIND_CLASS_BY_ID = "select * from classroom where id=?";
    Connection connection = ConnectionJDBC.getConnection ();
    private final String SELECT_ALL_CLASS = "select * from classroom";
    private final String UPDATE_CLASS = "update classroom set name=? where id=?";
    private final String REMOVE_CLASS_BY_ID = "";

    @Override
    public List<Class> findAll() {
        List<Class> classes = new ArrayList<> ();
        try {
            PreparedStatement statement = connection.prepareStatement (SELECT_ALL_CLASS);
            ResultSet resultSet = statement.executeQuery ();
            while (resultSet.next ()){
                int id = resultSet.getInt ("id");
                String name = resultSet.getString ("name");
                classes.add (new Class (id,name));
            }
        } catch (SQLException e) {
            throw new RuntimeException (e);
        }
        return classes;
    }

    @Override
    public void add(Class Class) {
        try {
            PreparedStatement statement = connection.prepareStatement(ADD_CLASS);
            statement.setString(1,Class.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Class findById(int id) {
        Class  classRoom= null;
        try{
            PreparedStatement statement = connection.prepareStatement (FIND_CLASS_BY_ID);
            statement.setInt (1,id);
            ResultSet resultSet = statement.executeQuery ();
            while (resultSet.next ()) {
                String name = resultSet.getString ("name");
                classRoom = new Class (id,name);
            }
        }catch (SQLException e) {
            throw new RuntimeException (e);
        }
        return classRoom;
    }

    @Override
    public void update(int id, Class aClass) {
        try {
            PreparedStatement statement = connection.prepareStatement (UPDATE_CLASS);
            statement.setString (1,aClass.getName ());
            statement.setInt (2,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException (e);
        }
    }

    @Override
    public void delete(int id) {

    }
}
