package parkeersimulator.main;

import parkeersimulator.controller.Controller;
import parkeersimulator.controller.MenuBarController;
import parkeersimulator.model.Model;
import parkeersimulator.view.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
    private MenuBarController menuBar;

    public Simulator(){
        this.setBackground(new Color(43,43,43)); // Makes the background dark grey
        this.setTitle("parkeersimulator");
        model=new Model(3,4,42);
        //504 places lays closest to 500. 500 precisely can not be done with 3 floors
        //previously, it was 540 total: 3 floors, 6 rows, 30 places

        carParkView = new CarParkView(model);
        quequeView=new QueueView(model);
        carTypeView=new CarTypeView(model);
        timeView=new TimeView(model);
        controller=new Controller(model);
        main=new JPanel();
        revenueView = new RevenueView(model);
        menuBar=new MenuBarController(model);
        setJMenuBar(menuBar);

        Container contentPane = getContentPane();

        main.setLayout(new BorderLayout());
        main.add(timeView,BorderLayout.NORTH);
        main.add(carParkView,BorderLayout.CENTER);
        ondervlak = new JPanel();

        ondervlak.setBackground(new Color(49,51,53));
        main.add(ondervlak,BorderLayout.SOUTH);


        ondervlak.add(carTypeView);
        ondervlak.add(controller);
        ondervlak.setLayout(new FlowLayout());



        contentPane.add(main, BorderLayout.CENTER);
        contentPane.add(quequeView, BorderLayout.EAST);
        contentPane.add(revenueView, BorderLayout.SOUTH);

        //temporary- application stops running on frame exit
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        model.notifyViews();










    }



}