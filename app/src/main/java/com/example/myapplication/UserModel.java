package com.example.myapplication;

public class UserModel extends android.app.Application {
    String login;
    public UserModel(){}


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
