package com.example.student.service;

import com.example.student.config.ConnectionJDBC;
import com.example.student.model.Student;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements IService<Student>{
    Connection connection = ConnectionJDBC.getConnection ();
    private static final String UPDATE_STUDENT = "update students set name=?, email=?, date_of_birth=?, address=?,phone_number=?,class_id=? where id=?";
    public static final String ADD_STUDENT = "insert into students(name,email,date_of_birth,address,phone_number,class_id) values (?,?,?,?,?,?)";
    private final String SELECT_ALL_STUDENT = "select * from students";
    private final String FIND_STUDENT_BY_ID = "select * from students where id=?";
    private final String REMOVE_STUDENT_BY_ID = "delete from students where id=?;";
    private final String FIND_BY_NAME = "select * from students s where s.name like '";
    private String close = "';";


    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<> ();
        try {
            PreparedStatement statement = connection.prepareStatement (SELECT_ALL_STUDENT);
            ResultSet resultSet = statement.executeQuery ();
            while (resultSet.next ()){
                int id = resultSet.getInt ("id");
                String name = resultSet.getString ("name");
                String email = resultSet.getString ("email");
                LocalDate dateOfBirth = LocalDate.parse (resultSet.getString ("date_of_birth"), DateTimeFormatter.ISO_DATE);
                String address = resultSet.getString ("address");
                int phoneNumber = resultSet.getInt ("phone_number");
                int classId = resultSet.getInt ("class_id");
                students.add (new Student (id,name,email,dateOfBirth,address,phoneNumber,classId));
            }

        } catch (SQLException e) {
            throw new RuntimeException (e);
        }
        return students;
    }

    @Override
    public void add(Student student) {
        try {
            PreparedStatement statement = connection.prepareStatement(ADD_STUDENT);
            statement.setString (1,student.getName ());
            statement.setString (2,student.getEmail ());
            statement.setDate (3, Date.valueOf (student.getDateOfBirth ()));
            statement.setString (4,student.getAddress ());
            statement.setInt (5,student.getPhoneNumber ());
            statement.setInt (6,student.getClassId ());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student findById(int id) {
        Student student = null;
        try {
            PreparedStatement statement = connection.prepareStatement (FIND_STUDENT_BY_ID);
            statement.setInt (1,id);
            ResultSet resultSet = statement.executeQuery ();
            while (resultSet.next ()){
                String name = resultSet.getString ("name");
                String email = resultSet.getString ("email");
                LocalDate dateOfBirth = LocalDate.parse (resultSet.getString ("date_of_birth"), DateTimeFormatter.ISO_DATE);
                String address = resultSet.getString ("address");
                int phoneNumber = resultSet.getInt ("phone_number");
                int classId = resultSet.getInt ("class_id");
                student = new Student (id,name,email,dateOfBirth,address,phoneNumber,classId);
            }
        } catch (SQLException e) {
            throw new RuntimeException (e);
        }
        return student;
    }

    @Override
    public void update(int id, Student student) {
        try {
            PreparedStatement statement = connection.prepareStatement (UPDATE_STUDENT);
            statement.setString (1,student.getName ());
            statement.setString (2,student.getEmail ());
            statement.setDate (3, Date.valueOf (student.getDateOfBirth ()));
            statement.setString (4,student.getAddress ());
            statement.setInt (5,student.getPhoneNumber ());
            statement.setInt (6,student.getClassId ());
            statement.setInt (7,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException (e);
        }
    }
    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement =connection.prepareStatement (REMOVE_STUDENT_BY_ID);
            statement.setInt (1,id);
            statement.executeUpdate ();
        } catch (SQLException e) {
            throw new RuntimeException (e);
        }
    }
    public List<Student> findByName (String findName){
        List<Student> students = new ArrayList<> ();
        try {
            PreparedStatement statement = connection.prepareStatement (FIND_BY_NAME +findName+close);
            ResultSet resultSet = statement.executeQuery ();
            while (resultSet.next ()){
                int id = resultSet.getInt ("id");
                String name = resultSet.getString ("name");
                String email = resultSet.getString ("email");
                LocalDate dateOfBirth = LocalDate.parse (resultSet.getString ("date_of_birth"), DateTimeFormatter.ISO_DATE);
                String address = resultSet.getString ("address");
                int phoneNumber = resultSet.getInt ("phone_number");
                int classId = resultSet.getInt ("class_id");
                students.add (new Student (id,name,email,dateOfBirth,address,phoneNumber,classId));
            }

        } catch (SQLException e) {
            throw new RuntimeException (e);
        }
        return students;
    }
}
