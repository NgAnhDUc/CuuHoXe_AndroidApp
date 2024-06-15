package com.example.appcuuhoxe.models;

public class LocatonModel {
    double Latitude;
    double Longtitude;
    String adrress;

    public LocatonModel() {
    }

    public LocatonModel(double latitude, double longtitude, String adrress) {
        Latitude = latitude;
        Longtitude = longtitude;
        this.adrress = adrress;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongtitude() {
        return Longtitude;
    }

    public void setLongtitude(double longtitude) {
        Longtitude = longtitude;
    }

    public String getAdrress() {
        return adrress;
    }

    public void setAdrress(String adrress) {
        this.adrress = adrress;
    }
}
