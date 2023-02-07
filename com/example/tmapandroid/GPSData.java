package com.example.tmapandroid;

public class GPSData {
    private String Key;
    private double distance;
    private double latitude;
    private double longitude;
    private String provider;

    public void setLongitude(double longitude2) {
        this.longitude = longitude2;
    }

    public void setLatitude(double latitude2) {
        this.latitude = latitude2;
    }

    public void setProvider(String provider2) {
        this.provider = provider2;
    }

    public void setKey(String key) {
        this.Key = key;
    }

    public void setLatLon(double latitude2, double longitude2) {
        this.latitude = latitude2;
        this.longitude = longitude2;
    }

    public void setDistance(double distance2) {
        this.distance = distance2;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public String getProvider() {
        return this.provider;
    }

    public String getKey() {
        return this.Key;
    }

    public double getDistance() {
        return this.distance;
    }
}
