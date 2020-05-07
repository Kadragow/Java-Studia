package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.ParseException;
import java.util.Date;

public class MyDateControlGui extends JFrame implements ActionListener,PropertyEditor {

    MyDate counter;
    JButton binc = new JButton("Increment day");
    JButton bdec = new JButton("Decrement day");
    JButton add = new JButton("Set new event");

    JTextField dateTextField = new JTextField(5);
    JTextField windowNameTextField = new JTextField(5);
    JTextField sizeTextField = new JTextField(5);
    JTextArea area;//= new JTextArea(10,10);
    JLabel dateDisplayLabel;


    MyDateControlGui(MyDate c, MyDateView clab, SizeView sizeView)  {
        counter = c;
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());

        dateDisplayLabel =clab;
        cp.add(dateDisplayLabel);

        binc.addActionListener(this);
        cp.add(binc);

        bdec.addActionListener(this);
        cp.add(bdec);

        dateTextField.addActionListener(this);
        cp.add(dateTextField);

        windowNameTextField.addActionListener(this);
        cp.add(windowNameTextField);

        sizeTextField.addActionListener(this);
        cp.add(sizeTextField);

        add.addActionListener(this);
        cp.add(add);
        area=sizeView;
        cp.add(area);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        show();
    }

    public void actionPerformed(ActionEvent e) {

        try {
            if (e.getSource() == dateTextField) {
                String date;
                date = dateTextField.getText();
                counter.setDate(date);
                return;
            }
            if (e.getSource() == windowNameTextField) {
                counter.setName(windowNameTextField.getText());
                this.setTitle(counter.getName());
                return;
            }

            if (e.getSource() == sizeTextField) {
                counter.setSize(Integer.parseInt(sizeTextField.getText()));

                return;
            }
            String cmd = e.getActionCommand();
            if (cmd.equals("Increment day"))
                counter.increment();
            else  if (cmd.equals("Decrement day"))
                counter.decrement();

            else   if (cmd.equals("Set new event")) {

                Date d=counter.getTimetable().StringtoDate(dateDisplayLabel.getText());

                counter.getTimetable().addEvent(d,area.getText());
                System.out.println("Added event: \n\tDescription: " + area.getText() + "\n\tDate: " + d.toString());
                System.out.println("All events:");
                for(Event event : counter.getTimetable().events){
                    System.out.println(event.toString());
                }
            }
            else
                System.out.println("Unrecognized command");

        }catch(PropertyVetoException exc)  {
            System.out.println( "" + exc);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void setValue(Object value) {

    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public boolean isPaintable() {
        return false;
    }

    @Override
    public void paintValue(Graphics gfx, Rectangle box) {

    }

    @Override
    public String getJavaInitializationString() {
        return null;
    }

    @Override
    public String getAsText() {
        return null;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

    }

    @Override
    public String[] getTags() {
        return new String[0];
    }

    @Override
    public Component getCustomEditor() {
        return this;
    }

    @Override
    public boolean supportsCustomEditor() {
        return false;
    }
}