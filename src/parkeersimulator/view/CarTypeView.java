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
    private JLabel total;

    public CarTypeView(Model model){
        super(model);
        lightgray=new JLabel();
        gray=new JLabel();
        red=new JLabel();
        blue=new JLabel();



        paid=new JLabel();
        subscribed=new JLabel();
        paidreserved=new JLabel();
        reserved=new JLabel();
        total=new JLabel();
        spots=new JLabel();
        add(spots);

        setLayout(new GridLayout(5,4));
        add(red);
        add(paid);
        add(new JLabel());
        add(new JLabel());
        add(blue);
        add(subscribed);
        add(new JLabel());
        add(new JLabel());
        add(gray);
        add(paidreserved);
        add(new JLabel());
        add(new JLabel());
        add(lightgray);
        add(reserved);
        add(new JLabel());
        add(new JLabel());


        add(spots);
        add(total);
        add(new JPanel());
        add(new JPanel());

        gray.setBackground(Color.gray);
        gray.setOpaque(true);
        lightgray.setBackground(Color.lightGray);
        lightgray.setOpaque(true);
        blue.setBackground(Color.blue);
        blue.setOpaque(true);
        red.setBackground(Color.red);
        red.setOpaque(true);

        spots.setBorder(new EmptyBorder(0,20,0,0));
        paid.setBorder(new EmptyBorder(0,20,0,0));
        subscribed.setBorder(new EmptyBorder(0,20,0,0));
        paidreserved.setBorder(new EmptyBorder(0,20,0,0));
        reserved.setBorder(new EmptyBorder(0,20,0,0));
        total.setBorder(new EmptyBorder(0,20,0,0));



    }
    //

    @Override
    public void updateView() {
        paid.setText("Reguliere klanten: "+model.getAmountPaidCars());
        subscribed.setText("Klanten met abonnement: "+model.getAmountSubscribedCars()+"");
        paidreserved.setText("Klanten met reservering: "+model.getAmountReservedCars()+"");
        reserved.setText("Gereserveerde plekken: "+model.getAmountReservedSpots()+"");
        spots.setText("Plekken vrij: "+(model.getNumberOfOpenSpots()-model.getAmountReservedCars())+"/"+model.getTotalSpots());
        total.setText("Totaal aantal klanten: "+(model.getAmountPaidCars()+model.getAmountSubscribedCars()+model.getAmountReservedCars()));
        repaint();
    }
}
