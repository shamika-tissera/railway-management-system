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
public abstract class Ticket {
    String id_no; 
    String routeID; 
    String date; 
    String source; 
    String destination; 
    double price;
    String ticket_id;
    
    public Ticket(String id_no, String routeID, String date, String source, String destination, double price){
        this.date = date;
        this.destination = destination;
        this.id_no = id_no;
        this.price = price;
        this.routeID = routeID;
        this.source = source;
    }
    protected abstract Ticket generateTicket();
    
    protected abstract void updateDatabase();
}
