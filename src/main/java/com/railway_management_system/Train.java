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
    int f_stclass = 0;
    int s_ndclass = 0;
    int t_rdclass = 0;
    
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

    public int getF_stclass() {
        return f_stclass;
    }

    public void setF_stclass(int f_stclass) {
        this.f_stclass = f_stclass;
    }

    public int getS_ndclass() {
        return s_ndclass;
    }

    public void setS_ndclass(int s_ndclass) {
        this.s_ndclass = s_ndclass;
    }
    
    public int getT_rdclass() {
        return t_rdclass;
    }

    public void setT_rdclass(int t_rdclass) {
        this.t_rdclass = t_rdclass;
    }
    
    }


