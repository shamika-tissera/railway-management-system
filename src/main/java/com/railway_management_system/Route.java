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
public class Route {
    private String RouteiD;
    private String Source;
    private String Destination;
    private double gen_Price;
    private double pri_Price;
    private double Dist;
    
    
    //constructor

//    public Route(String RouteiD, String Source, String Destination, double gen_Price, double pri_Price, double Dist) {
//        this.RouteiD = RouteiD;
//        this.Source = Source;
//        this.Destination = Destination;
//        this.gen_Price = gen_Price;
//        this.pri_Price = pri_Price;
//        this.Dist = Dist;
//    }
    //getters and setters

    public String getRouteiD() {
        return RouteiD;
    }

    public void setRouteiD(String RouteiD) {
        this.RouteiD = RouteiD;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String Source) {
        this.Source = Source;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String Destination) {
        this.Destination = Destination;
    }

    public double getGen_Price() {
        return gen_Price;
    }

    public void setGen_Price(double gen_Price) {
        this.gen_Price = gen_Price;
    }

    public double getPri_Price() {
        return pri_Price;
    }

    public void setPri_Price(double pri_Price) {
        this.pri_Price = pri_Price;
    }

    public double getDist() {
        return Dist;
    }

    public void setDist(double Dist) {
        this.Dist = Dist;
    }
    
}
