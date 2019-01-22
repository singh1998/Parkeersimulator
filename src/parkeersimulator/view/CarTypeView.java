package parkeersimulator.view;

import parkeersimulator.model.Model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CarTypeView extends AbstractView {
   private JLabel red;
   private JLabel blue;
   private JLabel payd;
   private JLabel subscribed;
   private JLabel spots;

    public CarTypeView(Model model){
        super(model);

        red=new JLabel();
        blue=new JLabel();


        payd=new JLabel();
        subscribed=new JLabel();




        setLayout(new GridLayout(3,3));
        add(red);
        add(payd);
        add(new JLabel());
        add(blue);
        add(subscribed);
        add(new JLabel());

        spots =new JLabel();
        add(spots);
        add(new JPanel());
        add(new JPanel());

        blue.setBackground(Color.blue);
        blue.setOpaque(true);
        red.setBackground(Color.red);
        red.setOpaque(true);

        spots.setBorder(new EmptyBorder(0,20,0,0));
        payd.setBorder(new EmptyBorder(0,20,0,0));
        subscribed.setBorder(new EmptyBorder(0,20,0,0));


    }

    @Override
    public void updateView() {
        payd.setText("Reguliere klanten: "+model.getAmountPaydCars());
        subscribed.setText("Klanten met abonnement: "+model.getAmountSubscribedCars()+"");
        spots.setText("Plekken vrij: "+model.getNumberOfOpenSpots());
        super.updateView();
    }
}
