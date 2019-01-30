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

    /**
     * Constructer of TimeView
     * @param model a variable of Model type.
     */
    public TimeView(Model model){
        super(model);

        minute=new JLabel(); // Makes a JLabel for minutes
        minute.setForeground(new Color(201,201,201)); // Makes the color(with RGB-values) of the text
        minute.setBackground(new Color(32,34,37)); // Makes the color(with RGB-values) of the background
        minute.setOpaque(true);
        hour=new JLabel(); // Makes a JLabel for hours
        hour.setForeground(new Color(201,201,201)); // Makes the color(with RGB-values) of the text
        hour.setBackground(new Color(32,34,37)); // Makes the color(with RGB-values) of the background
        hour.setOpaque(true);
        day=new JLabel(); // Makes a JLabel for days
        day.setForeground(new Color(201,201,201)); // Makes the color(with RGB-values) of the text
        day.setBackground(new Color(32,34,37)); // Makes the color(with RGB-values) of the background
        day.setOpaque(true);
        step=new JLabel(); // Makes a JLabel for steps
        step.setForeground(new Color(201,201,201)); // Makes the color(with RGB-values) of the text
        step.setBackground(new Color(32,34,37)); // Makes the color(with RGB-values) of the background
        step.setOpaque(true);
        timeInfo=new JLabel(); // Makes a JLabel for timeInfo
        timeInfo.setForeground(new Color(201,201,201)); // Makes the color(with RGB-values) of the text
        timeInfo.setBackground(new Color(32,34,37)); // Makes the color(with RGB-values) of the background
        timeInfo.setOpaque(true);
        step.setFont(new FontClass());
        minute.setFont(new FontClass());
        hour.setFont(new FontClass());
        day.setFont(new FontClass());
        timeInfo.setFont(new FontClass());


        setLayout(new GridLayout(1,4)); // Gives this view a GridLayout with 1 row and 4 columns
        // Adds the JLabels to the GridLayout
        add(timeInfo);
        add(hour);
        add(minute);
        add(day);
        add(step);

        //Adds a border to the JLabels
        step.setBorder(new EmptyBorder(5,0,0,0));
        minute.setBorder(new EmptyBorder(5,0,0,0));
        hour.setBorder(new EmptyBorder(5,0,0,0));
        day.setBorder(new EmptyBorder(5,0,0,0));
        timeInfo.setBorder(new EmptyBorder(5,0,0,0));

        // Sets the value of steps 0
        steps = 0;
    }
    @Override
    // Updates the QueueView
    public void updateView() {

            timeInfo.setText("Tijd:");
            step.setText("Stappen in deze week: " +String.format("%4d",model.getSteps()));
             hour.setText("Uren: "+String.format("%2d",model.getHours()));
            minute.setText("Minuten: "+String.format("%2d",model.getMinutes()));

            day.setText("Dag: "+model.getDay());

            repaint();
        }
}
