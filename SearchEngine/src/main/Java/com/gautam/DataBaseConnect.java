package com.gautam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnect {
    static Connection connection=null;
    public static Connection getConnection(){
        if(connection!=null){
            return connection;
        }
        String user="root";
        String pwd="7569";
        String db="searchenggine";
        return getConnection(user,pwd,db);
    }
    private static Connection getConnection(String user,String pwd,String db){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost/searchenggine","root","7569");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}

