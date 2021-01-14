
package com.railway_management_system;


/**
 *
 * @author Shamika Tissera
 */
public class Passenger extends User{
    private boolean isPriorityCustomer; // status
    private double credit_available;
    
    public Passenger(){
        credit_available = 0.0; //At the time of registration, there is no credit
    }
    
    public void setIsPriorityPassenger(boolean isPriorityCustomer){
        this.isPriorityCustomer = isPriorityCustomer;
    } 
    
    public boolean register(){
        DB_connection reg_passenger = new DB_connection();
        boolean status = reg_passenger.registerPassenger(first_name, last_name, mobile, id, email, 
                username, password, isPriorityCustomer, credit_available );
        return status;
    }
    
    @Override
    public boolean validateUser(){
        DB_connection newConnection = new DB_connection();
        boolean isUsernameExist = newConnection.checkUsername(username, "passenger");
        if(isUsernameExist){
            if(newConnection.checkPassword(password, "passenger")){
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