package parkeersimulator.view;

import parkeersimulator.model.Model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CarTypeView extends AbstractView {
    private JLabel red;
    private JLabel blue;
    private JLabel gray;
    private JLabel lightgray;
    private JLabel reserved;
    private JLabel paidreserved;
    private JLabel paid;
    private JLabel subscribed;
    private JLabel spots;
    private JLabel field;
    private JLabel field2;
    private JLabel field3;
    private JLabel field4;
    private JLabel field5;
    private JLabel field6;
    private JLabel field7;
    private JLabel field8;
    private JLabel field9;
    private JLabel field10;
    private JLabel total;

    public CarTypeView(Model model){
        super(model);
        lightgray=new JLabel();
        gray=new JLabel();
        red=new JLabel();
        blue=new JLabel();



        paid=new JLabel(); // Makes a JLabel for paid
        paid.setForeground(new Color(201, 201, 201)); // Makes the color(with RGB-values) of the text
        paid.setBackground(new Color(49, 51, 53)); // Makes the color(with RGB-values) of the background
        paid.setOpaque(true);
        subscribed=new JLabel(); // Makes a JLabel for subscribed
        subscribed.setForeground(new Color(201,201,201)); // Makes the color(with RGB-values) of the text
        subscribed.setBackground(new Color(49,51,53)); // Makes the color(with RGB-values) of the background
        subscribed.setOpaque(true);
        paidreserved=new JLabel(); // Makes a JLabel for paidreserved
        paidreserved.setForeground(new Color(201,201,201)); // Makes the color(with RGB-values) of the text
        paidreserved.setBackground(new Color(49,51,53)); // Makes the color(with RGB-values) of the background
        paidreserved.setOpaque(true);
        reserved=new JLabel(); // Makes a JLabel for reserved
        reserved.setForeground(new Color(201,201,201)); // Makes the color(with RGB-values) of the text
        reserved.setBackground(new Color(49,51,53)); // Makes the color(with RGB-values) of the background
        reserved.setOpaque(true);
        total=new JLabel(); // Makes a JLabel for total
        total.setForeground(new Color(201,201,201)); // Makes the color(with RGB-values) of the text
        total.setBackground(new Color(49,51,53)); // Makes the color(with RGB-values) of the background
        total.setOpaque(true);
        spots=new JLabel(); // Makes a JLabel for spots
        spots.setForeground(new Color(201,201,201)); // Makes the color(with RGB-values) of the text
        spots.setBackground(new Color(49,51,53)); // Makes the color(with RGB-values) of the background
        spots.setOpaque(true);
        field=new JLabel(); // Makes a JLabel for design purposes
        field.setBackground(new Color(49,51,53)); // Makes the color(with RGB-values) of the background
        field.setOpaque(true);
        field2=new JLabel(); // Makes a JLabel for design purposes
        field2.setBackground(new Color(49,51,53)); // Makes the color(with RGB-values) of the background
        field2.setOpaque(true);
        field3=new JLabel(); // Makes a JLabel for design purposes
        field3.setBackground(new Color(49,51,53)); // Makes the color(with RGB-values) of the background
        field3.setOpaque(true);
        field4=new JLabel(); // Makes a JLabel for design purposes
        field4.setBackground(new Color(49,51,53)); // Makes the color(with RGB-values) of the background
        field4.setOpaque(true);
        field5=new JLabel(); // Makes a JLabel for design purposes
        field5.setBackground(new Color(49,51,53)); // Makes the color(with RGB-values) of the background
        field5.setOpaque(true);
        field6=new JLabel(); // Makes a JLabel for design purposes
        field6.setBackground(new Color(49,51,53)); // Makes the color(with RGB-values) of the background
        field6.setOpaque(true);
        field7=new JLabel(); // Makes a JLabel for design purposes
        field7.setBackground(new Color(49,51,53)); // Makes the color(with RGB-values) of the background
        field7.setOpaque(true);
        field8=new JLabel(); // Makes a JLabel for design purposes
        field8.setBackground(new Color(49,51,53)); // Makes the color(with RGB-values) of the background
        field8.setOpaque(true);
        field9=new JLabel(); // Makes a JLabel for design purposes
        field9.setBackground(new Color(49,51,53)); // Makes the color(with RGB-values) of the background
        field9.setOpaque(true);
        field10=new JLabel(); // Makes a JLabel for design purposes
        field10.setBackground(new Color(49,51,53)); // Makes the color(with RGB-values) of the background
        field10.setOpaque(true);
        add(spots);

        setLayout(new GridLayout(5,4)); // Gives this view a GridLayout with 5 rows and 4 columns
        //Adds the JLabels to the GridLayout
        add(red);
        add(paid);
        add(field);
        add(field2);
        add(blue);
        add(subscribed);
        add(field3);
        add(field4);
        add(gray);
        add(paidreserved);
        add(field5);
        add(field6);
        add(lightgray);
        add(reserved);
        add(field7);
        add(field8);
        add(spots);
        add(total);
        add(field9);
        add(field10);

        // Adds a font to the JLabels
        spots.setFont(new FontClass());
        paid.setFont(new FontClass());
        subscribed.setFont(new FontClass());
        paidreserved.setFont(new FontClass());
        reserved.setFont(new FontClass());
        total.setFont(new FontClass());

        gray.setBackground(Color.gray); // Makes the JLabel grey
        gray.setOpaque(true);
        lightgray.setBackground(Color.lightGray); // Makes the JLabel light grey
        lightgray.setOpaque(true);
        blue.setBackground(Color.blue); // Makes the JLabel blue
        blue.setOpaque(true);
        red.setBackground(Color.red); // Makes the JLabel red
        red.setOpaque(true);

        //Adds a border to the JLabels
        spots.setBorder(new EmptyBorder(0,20,0,0));
        paid.setBorder(new EmptyBorder(0,20,0,0));
        subscribed.setBorder(new EmptyBorder(0,20,0,0));
        paidreserved.setBorder(new EmptyBorder(0,20,0,0));
        reserved.setBorder(new EmptyBorder(0,20,0,0));
        total.setBorder(new EmptyBorder(0,20,0,0));



    }
    @Override
    // Updates the CarTypeView
    public void updateView() {
        paid.setText("Reguliere klanten: "+model.getAmountPaidCars());
        subscribed.setText("Klanten met abonnement: "+model.getAmountSubscribedCars()+"");
        paidreserved.setText("Klanten met reservering: "+model.getAmountReservedCars()+"");
        reserved.setText("Gereserveerde plekken: "+model.getAmountReservedSpots()+"");
        spots.setText("Plekken vrij: "+(model.getNumberOfOpenSpots())+"/"+model.getTotalSpots());
        total.setText("Totaal aantal klanten: "+(model.getAmountPaidCars()+model.getAmountSubscribedCars()+model.getAmountReservedCars()));
        repaint();
    }
}
