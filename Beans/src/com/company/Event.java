package com.company;

import javax.xml.crypto.Data;
import java.util.Date;

public class Event {

    public String event;
    public Date date;

    public Event(){}
    public Event(String s,Date date)
    {
        event=s;
        this.date=date;
    }

    public String toString(){
        return "Description: " + event + "\tDate: " + date.toString();
    }
}