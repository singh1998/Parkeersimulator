package parkeersimulator.view;

import parkeersimulator.model.Model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RevenueView extends AbstractView{
    private JLabel dailyRevenue;
    private JLabel actualRevenue;
    private JLabel excpectedRevenueString;
    private JLabel excpectedRevenue;

    public RevenueView(Model model) {
        super(model);
        dailyRevenue=new JLabel();
        actualRevenue=new JLabel();
        excpectedRevenueString=new JLabel();
        excpectedRevenue=new JLabel();

        Font font=new Font(dailyRevenue.getFont().getName(),Font.BOLD,15);
        dailyRevenue.setFont(font);
        actualRevenue.setFont(font);
        excpectedRevenueString.setFont(font);
        excpectedRevenue.setFont(font);
        this.setLayout(new GridLayout(2,3));
        add(actualRevenue);
        add(excpectedRevenueString);
        add(dailyRevenue);
        add(new JLabel());
        add(excpectedRevenue);
        add(new JLabel());
        this.setBorder(new EmptyBorder(30,30,30,30));
        setBackground(Color.orange);
    }

    @Override
    public void updateView() {
        actualRevenue.setText("Actuele opbrengst van vandaag: € "+String.format("%.2f",model.getActualDailyRevenue()));
        excpectedRevenueString.setText("Verwachte opbrengst op basis van");
        excpectedRevenue.setText("alle huidige geparkeerde klanten: € "+String.format("%.2f",model.getExpectedRevenue()));
        dailyRevenue.setText("Totale opbrengst van de vorige dag: € " + String.format("%.2f",model.getDailyRevenue()));
        repaint();
    }

}
