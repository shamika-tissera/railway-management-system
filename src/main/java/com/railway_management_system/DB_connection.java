/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.railway_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Shamika Tissera
 */
public class DB_connection {
    public DB_connection(){
        establishConnection();
    }
    private Statement establishConnection(){
        
        String URL = "jdbc:mysql://localhost:3306/railway_management_system";
        String password = "";
        String user = "root";
        Statement statement;
        statement = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, user, password);
            statement = connection.createStatement();
            
        }
        catch(SQLException e){
            System.err.println("Error when establishing connection... " + e);
        }
        catch(ClassNotFoundException e){
            System.err.println("Problem when fetching JDBC Driver " + e);
        }
        return statement;
    }
    
    protected boolean registerPassenger(String fname, String lname, String mobNo, String id, String email, 
            String uname, String password, boolean isPriority, double credit ){
        String tableRef = "insert into `railway_management_system`.`passenger`(`username`, `password`, "
                + "`email`, `mobile_no`, `credit_available`, `isPriorityPassenger`, `f_name`, `l_name`)";
        String values = "values (\"" + uname + "\", \"" + password + "\", \"" + email + "\", \"" + mobNo 
                + "\", " + credit +  ", " + isPriority + ", \"" + fname + "\", \"" + lname + "\");";
        
        String tableRef_1 = "insert into `passengerid-username`(`id_no`, `username`)";
        String values_2 = " values (\"" + id + "\", \"" + uname + "\");";
        
        try{
            Statement statement = establishConnection();
            System.err.println(tableRef + values);
            statement.executeUpdate(tableRef + values);
            System.err.println(tableRef_1 + values_2);
            statement.executeUpdate(tableRef_1 + values_2);
            return false;
        }
        catch(SQLException e){
            System.err.println("Passenger information storage error..." + e.getMessage());
            String feedback = e.getMessage().substring(0, 16);
            System.err.println(feedback);
            return true;
        }
        
    }
    
    public boolean checkUsername(String username, String table){
        String query = "select `username` from " + table + " where `username` = \"" + username + "\"";
        try{
            Statement statement = establishConnection();
            System.out.println(query);
            ResultSet result = statement.executeQuery(query);
            result.next();
            String uname = result.getString("username");
            return true; //means username exists in the database
        }
        catch(SQLException e){
            System.err.println("Non-existent username... " + e.getMessage());
            return false;//means username does not exist in the database
        }
    }
    
    public boolean checkPassword(String password, String table){
        String query = "select `password` from " + table + " where `password` = \"" + password + "\"";
        try{
            Statement statement = establishConnection();
            System.out.println(query);
            ResultSet result = statement.executeQuery(query);
            result.next();
            String pass = result.getString("password");
            if(pass.equals(password)){
                return true;//means password exists in the database and is correct
            }
            else{
                System.err.println("Password incorrect... ");
                return false;//means password exists in the database but is incorrect
            }            
        }
        catch(SQLException e){
            System.err.println("Password incorrect..." + e.getMessage());
            return false;//means password does not exist in the database
        }
    }
    
    //Sajee
    
    class Handle_Route{
//        String dbUserName = "root";
//        String dbPassword = "";
//        String dbServerUrl = "jdbc:mysql://localhost:3306/railway_management_system?autoReconnect=true&useSSL=false";
//        String dbClassPathUrl = "com.mysql.cj.jdbc.Driver";
//        public Connection databaseConnection() {
//        Connection conn;
//        try {
//            //Load Driver
//            Class.forName(dbClassPathUrl);
//            JOptionPane.showMessageDialog(null, "Driver Loaded");
//            //Connect to database
//            conn = DriverManager.getConnection(dbServerUrl, dbUserName, dbPassword);
//            JOptionPane.showMessageDialog(null, "Connected");
//            return conn;
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//
//    }
        
        public int insert_info(String routeID, String source, String destination, double generalPrice, double priorityPrice, double distance){
            int count = 0;
            try {
                String routeInsertSQLQuery = "INSERT INTO `route`(`route_id`, `source`, `destination`, `general_price`, `priority_price`, `distance`) "
                        + "VALUES(\"" + routeID + "\", \"" + source + "\", \"" + destination + "\", " + generalPrice + ", " + priorityPrice + ", " + distance + " )";
                Statement statement = establishConnection();
                count = statement.executeUpdate(routeInsertSQLQuery);
                
                //PreparedStatement routePST = this.connx.prepareStatement(routeInsertSQLQuery);
            } catch (SQLException ex) {
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
            }
            return count;
        }
        
        public int updateRoute(String routeID, String source, String destination, double generalPrice, double priorityPrice, double distance){
            int count = 0;
            try {
                String updateSQLQuery = "UPDATE `route` SET `source` = \"" + source + "\", " + "`destination`= \"" + destination + "\", " + "`general_price`= " + generalPrice + ", `priority_price`= " + priorityPrice + ", `distance`= " + distance + " WHERE `route_id` = \"" + routeID + "\";";
                
                System.out.println(updateSQLQuery);
                
                Statement statement = establishConnection();
                count = statement.executeUpdate(updateSQLQuery);
                
                //PreparedStatement routePST = this.connx.prepareStatement(routeInsertSQLQuery);
            } catch (SQLException ex) {
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
            }
            return count;
        }
        
        public int deleteRoute(String routeID){
            int count = 0;
            try {
                String deleteSQLQuery = "DELETE FROM `route` WHERE `route_id` = \"" + routeID + "\"";
                System.out.println(deleteSQLQuery);       
                Statement statement = establishConnection();
                count = statement.executeUpdate(deleteSQLQuery);
            } catch (SQLException ex) {
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
            }
            return count;
            
        }
    }
}
