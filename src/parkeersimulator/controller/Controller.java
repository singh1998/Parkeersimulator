package parkeersimulator.controller;

import parkeersimulator.model.Model;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller extends AbstractController {
    private JButton button;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    public Controller(Model model) {
        super(model);
        Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

        button = new JButton("One minute");
        button.addActionListener(e->model.oneStep());
        this.add(button);

        button2 = new JButton("Hundred minutes");
        button2.addActionListener(e-> model.hundredSteps());
        this.add(button2);

        button3 = new JButton("One hour");
        button3.addActionListener(e-> model.oneHour());
        this.add(button3);

        button4 = new JButton("One day");
        button4.addActionListener(e-> model.oneDay());
        this.add(button4);

        this.setLayout(new GridLayout(2,2));

        button.setBackground(new Color(255, 191, 0));
        button2.setBackground(new Color(255, 191, 0));
        button3.setBackground(new Color(255, 191, 0));
        button4.setBackground(new Color(255, 191, 0));

        button.setBorder(raisedetched);
        button2.setBorder(raisedetched);
        button3.setBorder(raisedetched);
        button4.setBorder(raisedetched);
    }


}
