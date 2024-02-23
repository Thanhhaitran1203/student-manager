package com.example.student.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {
    private static String URL = "jdbc:mysql://localhost:3306/student";
    private static String USER = "root";
    private static String PASSWORD = "1234";
    private static Connection connection;
    public static Connection getConnection(){
        if (connection == null){
            try {
                Class.forName ("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection (URL,USER,PASSWORD);
                System.out.println ("ket noi thanh cong");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException (e);
            } catch (SQLException e) {
                throw new RuntimeException (e);
            }

        }
        return connection;
    }
}
