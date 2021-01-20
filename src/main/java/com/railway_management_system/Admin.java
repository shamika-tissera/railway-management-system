/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.railway_management_system;

/**
 *
 * @author Shamika Tissera
 */
public class Admin extends User{

    @Override
    public boolean register() {
        DB_connection reg_passenger = new DB_connection();
        boolean status = reg_passenger.registerAdmin(id, password, username, username, username, email);
        return status;
    }

    @Override
    public boolean validateUser() {
        DB_connection newConnection = new DB_connection();
        boolean isUsernameExist = newConnection.checkUsername(username, "administrator_id_username");
        if(isUsernameExist){
            if(newConnection.checkPassword(password, "administrator")){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    
}
