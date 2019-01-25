package parkeersimulator.view;

import parkeersimulator.model.Model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CarTypeView extends AbstractView {
   private JLabel red;
   private JLabel blue;
   private JLabel paid;
   private JLabel subscribed;
   private JLabel spots;
   private JLabel total;

    public CarTypeView(Model model){
        super(model);

        red=new JLabel();
        blue=new JLabel();


        paid=new JLabel();
        subscribed=new JLabel();
        spots =new JLabel();
        total=new JLabel();



        setLayout(new GridLayout(3,4));
        add(red);
        add(paid);
        add(new JLabel());
        add(new JLabel());
        add(blue);
        add(subscribed);
        add(new JLabel());
        add(new JLabel());

        add(spots);
        add(total);
        add(new JLabel());
        add(new JLabel());

        blue.setBackground(Color.blue);
        blue.setOpaque(true);
        red.setBackground(Color.red);
        red.setOpaque(true);

        spots.setBorder(new EmptyBorder(0,20,0,0));
        paid.setBorder(new EmptyBorder(0,20,0,0));
        subscribed.setBorder(new EmptyBorder(0,20,0,0));
        total.setBorder(new EmptyBorder(0,20,0,0));


    }
    //

    @Override
    public void updateView() {
        paid.setText("Reguliere klanten: "+model.getAmountPaidCars());
        subscribed.setText("Klanten met abonnement: "+model.getAmountSubscribedCars()+"");
        spots.setText("Plekken vrij: "+model.getNumberOfOpenSpots()+"/"+model.getTotalSpots());
        total.setText("Totaal: "+(model.getAmountPaidCars()+model.getAmountSubscribedCars()));
        repaint();
    }
}
