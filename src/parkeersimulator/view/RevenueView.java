package parkeersimulator.view;

import parkeersimulator.model.Model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RevenueView extends AbstractView{
    private JLabel dailyRevenue;

    public RevenueView(Model model) {
        super(model);
        dailyRevenue=new JLabel();
        Font font=new Font(dailyRevenue.getFont().getName(),Font.BOLD,15);
        dailyRevenue.setFont(font);
        this.setLayout(new GridLayout(1,1));
        add(dailyRevenue);
        this.setBorder(new EmptyBorder(30,30,30,30));
        setBackground(Color.orange);
    }

    @Override
    public void updateView() {
        dailyRevenue.setText("Dagopbrengst: " + model.getDailyRevenue());
        repaint();
    }

}
