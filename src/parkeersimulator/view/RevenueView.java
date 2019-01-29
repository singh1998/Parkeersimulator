package parkeersimulator.view;

import parkeersimulator.model.Model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RevenueView extends AbstractView{
    private JLabel dailyRevenue;
    private JLabel actualRevenue;
    private JLabel expectedRevenueString;
    private JLabel expectedRevenue;

    public RevenueView(Model model) {
        super(model);
       dailyRevenue=new JLabel();
        actualRevenue=new JLabel();
        expectedRevenueString=new JLabel();
        expectedRevenue=new JLabel();
        
        dailyRevenue.setForeground(new Color(255,191,0));
        actualRevenue.setForeground(new Color(255,191,0));
        expectedRevenueString.setForeground(new Color(255,191,0));
        expectedRevenue.setForeground(new Color(255,191,0));

        Font font=new Font("Century Gothic",Font.PLAIN,20);; // Zorg voor een mooier font B.S.
        dailyRevenue.setFont(font);
        actualRevenue.setFont(font);
        expectedRevenueString.setFont(font);
        expectedRevenue.setFont(font);
        this.setLayout(new GridLayout(2,3));
        add(actualRevenue);
        add(expectedRevenueString);
        add(dailyRevenue);
        add(new JLabel());
        add(expectedRevenue);
        add(new JLabel());
        this.setBorder(new EmptyBorder(30,30,30,30));
        setBackground(new Color(32,34,37)); // Creates grey background
    }

    @Override
    public void updateView() {
        actualRevenue.setText("Actuele opbrengst van vandaag: € "+String.format("%.2f",model.getActualDailyRevenue()));
        expectedRevenueString.setText("Verwachte opbrengst op basis van");
        expectedRevenue.setText("alle huidige geparkeerde klanten: € "+String.format("%.2f",model.getExpectedRevenue()));
        dailyRevenue.setText("Totale opbrengst van de vorige dag: € " + String.format("%.2f",model.getDailyRevenue()));
        repaint();
    }

}
