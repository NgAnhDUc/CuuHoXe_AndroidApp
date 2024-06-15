package com.example.appcuuhoxe.models;

import com.google.firebase.Timestamp;

public class RecuseTeamModel {
    private String phone;
    private String username;
    private String password;
    private String state;
    private String role;
    private String address;
    private com.google.firebase.Timestamp Timestamp;

    public RecuseTeamModel() {
    }

    public RecuseTeamModel(String phone, String username, String password, String state, String role, String address, com.google.firebase.Timestamp timestamp) {
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.state = state;
        this.role = role;
        this.address = address;
        Timestamp = timestamp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public com.google.firebase.Timestamp getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(com.google.firebase.Timestamp timestamp) {
        Timestamp = timestamp;
    }
}
