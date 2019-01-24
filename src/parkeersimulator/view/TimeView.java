package parkeersimulator.view;

import parkeersimulator.model.Model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TimeView extends AbstractView {
   private JLabel minute;
    private JLabel hour;
    private JLabel day;
    private JLabel step;
    private int steps;
    private JLabel timeInfo;


    public TimeView(Model model){
        super(model);

        minute=new JLabel();
        hour=new JLabel();
        day=new JLabel();
        step=new JLabel();
        timeInfo=new JLabel();
        setLayout(new GridLayout(1,4));
        add(timeInfo);
        add(hour);
        add(minute);
        add(day);
        add(step);
        step.setBorder(new EmptyBorder(5,0,0,0));
        minute.setBorder(new EmptyBorder(5,0,0,0));
        hour.setBorder(new EmptyBorder(5,0,0,0));
        day.setBorder(new EmptyBorder(5,0,0,0));
        timeInfo.setBorder(new EmptyBorder(5,0,0,0));
        steps = 0;
    }
    @Override
    public void updateView() {

            timeInfo.setText("Tijd:");
            step.setText("Stappen: " + model.getSteps());
             hour.setText("Uren: "+model.getHours());
            minute.setText("Minuten: "+model.getMinutes());

            day.setText("Dag: "+model.getDay());

            repaint();
        }
}
