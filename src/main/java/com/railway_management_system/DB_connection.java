/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.railway_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Shamika Tissera
 */
public class DB_connection {
    public DB_connection(){
        establishConnection();
    }
    private void establishConnection(){
        
        String URL = "jdbc:mysql://localhost:3306/railway_management_system";
        String password = "";
        String user = "root";
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, user, password);
            Statement statement = connection.createStatement();
        }
        catch(SQLException e){
            System.out.println("Error when establishing connection... " + e);
        }
        catch(ClassNotFoundException e){
            System.out.println("Problem when fetching JDBC Driver " + e);
        }
    }
    
    protected void registerPassenger(String fname, String lname, String mobNo, String id, String email, 
            String uname, String password, boolean isPriority ){
        String tableRef = "insert into `railway_management_system`.`passenger`(`username`, `password`, "
                + "`email`, `mobile_no`, `credit_available`, `isPriorityPassenger`, `f_name`, `l_name`)";
        String values = "values (\"" + fname + ", \"" + lname + ", \"" + mobNo + ", \"" + id 
                + ", \"" + email +  ", \"" + uname + ", \"" + password + ", " + isPriority + ");";
    }
}
