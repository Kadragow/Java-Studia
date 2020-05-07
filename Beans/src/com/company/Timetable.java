package com.company;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Timetable {

    public ArrayList<Event>events;

    public Timetable() {
        events=new ArrayList<Event>();
    }

    public Date nextDate(Date d)
    {
        Calendar today = Calendar.getInstance();
        today.setTime(d);
        today.add(Calendar.DAY_OF_YEAR, 1);
        d=today.getTime();
        return d;
    }

    public Date previousDate(Date d)
    {
        Calendar today = Calendar.getInstance();
        today.setTime(d);
        today.add(Calendar.DAY_OF_YEAR, -1);
        d=today.getTime();
        return d;
    }

    public Date StringtoDate(String d) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.parse(d);
    }

    public String DatetoString(Date d) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(d);
    }

    public void  addEvent(Date d,String s)
    {
        events.add(new Event(s,d));
    }
}