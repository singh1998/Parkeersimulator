package parkeersimulator.main;

import parkeersimulator.controller.Controller;
import parkeersimulator.model.Model;
import parkeersimulator.view.CarParkView;
import parkeersimulator.view.CarTypeView;
import parkeersimulator.view.QueueView;

import javax.swing.*;
import java.awt.*;

public class Simulator extends JFrame {

    private Model model;
    private Controller controller;
    private CarParkView carParkView;
    private QueueView quequeView;
    private CarTypeView carTypeView;
    private JPanel main;

    public Simulator(){
        this.setTitle("parkeersimulator");
        model=new Model(3,6,30);

        carParkView = new CarParkView(model);
        quequeView=new QueueView(model);
        carTypeView=new CarTypeView(model);
        controller=new Controller(model);
        main=new JPanel();

        Container contentPane = getContentPane();
        main.add(carParkView);

        main.add(carTypeView);
        main.setLayout(new BoxLayout(main,BoxLayout.Y_AXIS));

        contentPane.add(main, BorderLayout.CENTER);
        contentPane.add(quequeView, BorderLayout.EAST);
        contentPane.add(controller,BorderLayout.WEST);

        //temporary- application stops running on frame exit
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();

        setVisible(true);

        model.notifyViews();







    }



}