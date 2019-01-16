package parkeersimulator.main;

import parkeersimulator.controller.Controller;
import parkeersimulator.model.Model;
import parkeersimulator.view.CarParkView;
import parkeersimulator.view.Text;

import javax.swing.*;
import java.awt.*;

public class Simulator extends JFrame {
    private CarParkView carParkView;
    private Model model;
    private Controller controller;


    public Simulator(){
        this.setTitle("parkeersimulator");
        model=new Model(3,6,30);
        carParkView = new CarParkView(model);
        //test
        controller  = new Controller(model);
        Text carParkView3 = new Text(model);
        Text carParkView4 = new Text(model);
        Text carParkView5 = new Text(model);
        //test
        Container contentPane = getContentPane();
        //test
        contentPane.add(carParkView, BorderLayout.CENTER);
        contentPane.add(controller, BorderLayout.WEST);
        contentPane.add(carParkView3, BorderLayout.NORTH);
        contentPane.add(carParkView4, BorderLayout.EAST);
        contentPane.add(carParkView5, BorderLayout.SOUTH);
        //test

        pack();

        setVisible(true);

        carParkView.updateView();




    }



}