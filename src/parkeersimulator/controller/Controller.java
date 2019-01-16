package parkeersimulator.controller;

import parkeersimulator.model.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller extends AbstractController {
    public Controller(Model model) {
        super(model);
        JButton button=new JButton("Hundred steps");
        this.add(button);
        button.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    model.hundredSteps();

                }
            }



        );


    }


}
