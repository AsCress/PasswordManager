package com.example.passwordmanager;

public class PasswordModel {
    String website;
    String password;
    String username;

    public String getSavedWebsite() {
        return website;
    }

    public String getSavedPassword() {
        return password;
    }

    public String getSavedUsername(){return username;}

    public void setSavedWebsite(String website) {
        this.website = website;
    }

    public void setSavedPassword(String password) {
        this.password = password;
    }

    public void setSavedUsername(String username) {this.username = username;}

    public PasswordModel(String website, String username, String password)
    {
        this.website = website;
        this.username = username;
        this.password = password;
    }
}
