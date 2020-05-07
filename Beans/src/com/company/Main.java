package com.company;

import java.text.ParseException;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws ParseException {

        MyDate myDate = new MyDate();

        MyDateView myDateView = new MyDateView(myDate.getTimetable().DatetoString(new Date()) );

        Limit limit=new Limit(20,200);
        SizeView sizeView=new SizeView();

        myDate.addPropertyChangeListener2(sizeView);
        myDate.addVetoableChangeListener(limit);

        myDate.addPropertyChangeListener(myDateView);

        MyDateControlGui gui = new MyDateControlGui(myDate, myDateView,sizeView);
        gui.pack();
        gui.show();

    }
}