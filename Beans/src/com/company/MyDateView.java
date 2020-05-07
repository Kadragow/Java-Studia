package com.company;

import javax.swing.*;
import java.awt.*;
import java.beans.*;
import java.text.ParseException;

public class MyDateView
        extends JLabel
        implements PropertyChangeListener {


    MyDateView() throws ParseException {

        this("");
    }

    MyDateView(String lab) throws ParseException {

        super(lab);
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.red));
        setPreferredSize(new Dimension(75, 40));
        setHorizontalAlignment(CENTER);
    }

    public void propertyChange(PropertyChangeEvent e)  {

        String oldVal = (String) e.getOldValue(),
                newVal = (String) e.getNewValue();
        System.out.println("Value changed from " + oldVal.toString() + " to " + newVal.toString());
        setText("" + newVal + "");
    }

}