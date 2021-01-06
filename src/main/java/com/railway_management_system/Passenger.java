
package com.railway_management_system;


/**
 *
 * @author Shamika Tissera
 */
public class Passenger extends User{
    private boolean isPriorityCustomer; // status
    private double credit_available;
    
    public Passenger(){
        credit_available = 0.0;
    }
    
    public void setIsPriorityPassenger(boolean isPriorityCustomer){
        this.isPriorityCustomer = isPriorityCustomer;
    } 
    
    public void register(){
        DB_connection reg_passenger = new DB_connection();
        reg_passenger.registerPassenger(first_name, last_name, mobile, id, email, username, password, isPriorityCustomer );
        
    }
}
