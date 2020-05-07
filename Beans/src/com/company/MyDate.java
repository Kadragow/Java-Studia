package com.company;

import java.beans.*;
import java.io.*;
import java.text.ParseException;
import java.util.Date;

//import static jdk.nashorn.internal.objects.NativeDate.setDate;

public class MyDate implements Serializable {

    private String date="";

    private Timetable timetable;

    public Timetable getTimetable() {
        return timetable;
    }


    private String name="Bean";
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int size=200;

    public int getSize() {
        return size;
    }

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private VetoableChangeSupport vetos = new VetoableChangeSupport(this);

    public synchronized void addVetoableChangeListener(VetoableChangeListener l) {
        vetos.addVetoableChangeListener(l);
    }

    public synchronized void removeVetoableChangeListener(VetoableChangeListener l) {
        vetos.removeVetoableChangeListener(l);
    }

    public synchronized void setSize(int size) throws PropertyVetoException {

        int oldsize = this.size ;
        vetos.fireVetoableChange("size", Integer.valueOf(oldsize), Integer.valueOf(size));

        this.size=size;
        propertyChangeSupport.firePropertyChange("size", Integer.valueOf(oldsize), Integer.valueOf(this.size));

    }

    public synchronized void addPropertyChangeListener2(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public synchronized void removePropertyChangeListener2(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }


    // A helper object for registering listeners
    // and firing change events at them.
    private PropertyChangeSupport propertyChange = new PropertyChangeSupport(this);


    public MyDate() {

        this("Beans");
    }


    public MyDate(String name) {

        timetable=new Timetable();
        try {
            setDate(timetable.DatetoString(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        setName(name);
    }


    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChange.addPropertyChangeListener(listener);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChange.removePropertyChangeListener(l);
    }


    public void increment() {
        Date d= null;
        try {
            d = timetable.StringtoDate(getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        d=timetable.nextDate(d);
        try {
            setDate(timetable.DatetoString(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void decrement() {

        Date d= null;
        try {
            d = timetable.StringtoDate(getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        d=timetable.previousDate(d);
        try {
            setDate(timetable.DatetoString(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public String getDate() {
        return date;
    }

    public synchronized void setDate(String date) {
        String oldValue = this.date;
        this.date = date;

        propertyChange.firePropertyChange("date", oldValue, date);
    }

}