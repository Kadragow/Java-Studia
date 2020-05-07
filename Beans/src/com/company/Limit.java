package com.company;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class Limit implements VetoableChangeListener {

    private int min,max;
    Limit(int min, int max)
    {
        this.min =min;
        this.max =max;

    }

    public void vetoableChange(PropertyChangeEvent e) throws PropertyVetoException {


        Integer newVal = (Integer) e.getNewValue();
        int val = newVal;
        if (val < min || val > max)
            throw new PropertyVetoException("Unacceptable change of value", e);
    }
}