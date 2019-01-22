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
    public Controller(Model model) {
        super(model);
        Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

        button = new JButton("One step");
        button.addActionListener(e->model.oneStep());
        this.add(button);

        button2 = new JButton("Hundred steps");
        button2.addActionListener(e-> model.hundredSteps());
        this.add(button2);

        this.setLayout(new GridLayout(2,1));

        button.setBackground(new Color(66, 134, 244));
        button2.setBackground(new Color(66, 134, 244));

        button.setBorder(raisedetched);
        button2.setBorder(raisedetched);
    }


}
