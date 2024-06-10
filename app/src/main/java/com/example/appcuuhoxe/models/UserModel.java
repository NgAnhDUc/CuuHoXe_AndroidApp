package com.example.appcuuhoxe.models;

import com.google.firebase.Timestamp;

public class UserModel {
    private String phone;
    private String username;
    private String password;
    private String gender;
    private String role;
    private com.google.firebase.Timestamp Timestamp;

    public UserModel() {
    }

    public UserModel(String phone, String username, String password, String gender, String role, com.google.firebase.Timestamp timestamp) {
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.role = role;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public com.google.firebase.Timestamp getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(com.google.firebase.Timestamp timestamp) {
        Timestamp = timestamp;
    }
}
