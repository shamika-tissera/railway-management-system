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
public class General_Ticket extends Ticket{
    public General_Ticket(String id_no, String routeID, String date, String source, String destination, double price){
        super(id_no, routeID, date, source, destination, price);
    }
    protected Ticket generateTicket(){
        return null;
        
    }

    @Override
    protected void updateDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
