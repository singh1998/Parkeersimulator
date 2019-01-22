package parkeersimulator.main;

import parkeersimulator.controller.Controller;
import parkeersimulator.model.Model;
import parkeersimulator.view.CarParkView;
import parkeersimulator.view.CarTypeView;
import parkeersimulator.view.QueueView;
import parkeersimulator.view.RevenueView;

import javax.swing.*;
import java.awt.*;

public class Simulator extends JFrame {

    private Model model;
    private Controller controller;
    private CarParkView carParkView;
    private QueueView queueView;
    private CarTypeView carTypeView;
    private JPanel main;
    private JPanel ondervlak;
    private RevenueView revenueView;

    public Simulator(){
        this.setTitle("parkeersimulator");
        model=new Model(3,6,30);

        carParkView = new CarParkView(model);
        queueView=new QueueView(model);
        carTypeView=new CarTypeView(model);
        controller=new Controller(model);
        main=new JPanel();
        revenueView = new RevenueView(model);

        Container contentPane = getContentPane();
        main.add(carParkView);
        JPanel ondervlak = new JPanel();
        main.add(ondervlak);
        ondervlak.setLayout(new FlowLayout());
        ondervlak.add(carTypeView);
        ondervlak.add(controller);
        main.setLayout(new BoxLayout(main,BoxLayout.Y_AXIS));

        contentPane.add(main, BorderLayout.CENTER);
        contentPane.add(queueView, BorderLayout.EAST);
        contentPane.add(revenueView, BorderLayout.SOUTH);

        //temporary- application stops running on frame exit
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();

        setVisible(true);

        carParkView.updateView();
        queueView.updateView();
        carTypeView.updateView();

        revenueView.updateView();









    }



}