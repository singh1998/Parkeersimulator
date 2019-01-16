package parkeersimulator.view;

import parkeersimulator.model.Model;

import javax.swing.*;

public class Text extends AbstractView{

    public Text(Model model) {
        super(model);
        JLabel label=new JLabel("Hello");
        label.setSize(500,800);
        this.add(label);
    }
}
