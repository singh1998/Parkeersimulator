package parkeersimulator.main;

import parkeersimulator.controller.Controller;
import parkeersimulator.model.Model;
import parkeersimulator.view.*;

import javax.swing.*;
import java.awt.*;

public class Simulator extends JFrame {

    private Model model;
    private Controller controller;
    private CarParkView carParkView;
    private QueueView quequeView;
    private CarTypeView carTypeView;
    private TimeView timeView;
    private JPanel main;
    private JPanel ondervlak;
    private RevenueView revenueView;

    public Simulator(){
        this.setTitle("parkeersimulator");
        model=new Model(3,6,30);

        carParkView = new CarParkView(model);
        quequeView=new QueueView(model);
        carTypeView=new CarTypeView(model);
        timeView=new TimeView(model);
        controller=new Controller(model);
        main=new JPanel();
        revenueView = new RevenueView(model);

        Container contentPane = getContentPane();
        main.add(timeView);
        main.add(carParkView);
        JPanel ondervlak = new JPanel();
        main.add(ondervlak);

        ondervlak.add(carTypeView);
        ondervlak.add(controller);
        ondervlak.setLayout(new FlowLayout());

        main.setLayout(new BoxLayout(main,BoxLayout.Y_AXIS));

        contentPane.add(main, BorderLayout.CENTER);
        contentPane.add(quequeView, BorderLayout.EAST);
        contentPane.add(revenueView, BorderLayout.SOUTH);

        //temporary- application stops running on frame exit
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();

        setVisible(true);

        model.notifyViews();







    }



}