/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.railway_management_system;

/**
 *
 * @author Sajee Thamanga
 */
public class Train {
     
    private String TrainiD;
    private int TotCapacity;
    private int AvaiCapacity;
    private String RouteiD;
    private String type;
    
    //constructor

//    public Train(String TrainiD, int TotCapacity, int AvaiCapacity, String RouteiD, String type) {
//        this.TrainiD = TrainiD;
//        this.TotCapacity = TotCapacity;
//        this.AvaiCapacity = AvaiCapacity;
//        this.RouteiD = RouteiD;
//        this.type = type;
//    }

    public String getTrainiD() {
        return TrainiD;
    }

    public void setTrainiD(String TrainiD) {
        this.TrainiD = TrainiD;
    }

    public int getTotCapacity() {
        return TotCapacity;
    }

    public void setTotCapacity(int TotCapacity) {
        this.TotCapacity = TotCapacity;
    }

    public int getAvaiCapacity() {
        return AvaiCapacity;
    }

    public void setAvaiCapacity(int AvaiCapacity) {
        this.AvaiCapacity = AvaiCapacity;
    }

    public String getRouteiD() {
        return RouteiD;
    }

    public void setRouteiD(String RouteiD) {
        this.RouteiD = RouteiD;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    }


