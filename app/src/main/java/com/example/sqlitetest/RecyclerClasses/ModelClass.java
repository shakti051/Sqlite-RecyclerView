package com.example.sqlitetest.RecyclerClasses;

public class ModelClass {
    int ID,phone;String userName,userEmail;

    public ModelClass(int ID, int phone, String userName, String userEmail) {
        this.ID = ID;
        this.phone = phone;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
