package parkeersimulator.main;

import parkeersimulator.controller.Controller;
import parkeersimulator.model.Model;
import parkeersimulator.view.CarParkView;
import parkeersimulator.view.QueueView;

import javax.swing.*;
import java.awt.*;

public class Simulator extends JFrame {

    private Model model;
    private Controller controller;
    private CarParkView carParkView;
    private QueueView quequeView;


    public Simulator(){
        this.setTitle("parkeersimulator");
        model=new Model(3,6,30);
        carParkView = new CarParkView(model);
        quequeView=new QueueView(model);
        controller=new Controller(model);

        Container contentPane = getContentPane();
        contentPane.add(carParkView, BorderLayout.CENTER);
        contentPane.add(quequeView, BorderLayout.EAST);
        contentPane.add(controller,BorderLayout.WEST);
        //temporary- application stops running on frame exit
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();

        setVisible(true);

        carParkView.updateView();
        quequeView.updateView();





    }



}