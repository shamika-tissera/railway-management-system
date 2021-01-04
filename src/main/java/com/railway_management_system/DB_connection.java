/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.railway_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Shamika Tissera
 */
public class DB_connection {
    public DB_connection(String statement){
        establishConnection();
    }
    private void establishConnection(){
        
        String URL = "jdbc:mysql://localhost:3306/railway_management_system";
        String password = "";
        String user = "root";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, user, password);
        }
        catch(SQLException e){
            System.out.println("Error when establishing connection... " + e);
        }
        catch(ClassNotFoundException e){
            System.out.println("Problem when fetching JDBC Driver " + e);
        }
    }
    
    private void executeQuery(String statement){
            
    }
}
