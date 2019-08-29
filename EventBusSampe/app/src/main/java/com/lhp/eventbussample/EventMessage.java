package com.lhp.eventbussample;

public class EventMessage {
    public String name;
    public String password;

    public EventMessage(String name,String password){
        this.name = name;
        this.password = password;
    }
}
