package parkeersimulator.controller;

import parkeersimulator.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller extends AbstractController {
        private JButton button;
        private JButton button2;
    public Controller(Model model) {
        super(model);

        button = new JButton("One step");
        button.addActionListener(e->model.oneStep());
        this.add(button);

        button2 = new JButton("Hundred steps");
        button2.addActionListener(e-> model.hundredSteps());
        this.add(button2);

        this.setLayout(new GridLayout(2,1));

    }


}
