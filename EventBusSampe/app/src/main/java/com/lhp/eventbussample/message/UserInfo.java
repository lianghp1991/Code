package com.lhp.eventbussample.message;

import androidx.annotation.NonNull;

public class UserInfo {
    public String name;
    public String password;

    public UserInfo(String name, String password){
        this.name = name;
        this.password = password;
    }

    public UserInfo getUserInfo(){
        return new UserInfo(name,password);
    }

    @NonNull
    @Override
    public String toString() {
        return "name : " + name + " password : " + password;
    }
}
