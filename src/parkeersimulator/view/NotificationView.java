/**
 * This is a view that shows notifications
 *
 * @version 2.0
 * @Author Park-It
 */
package parkeersimulator.view;

import parkeersimulator.model.Model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NotificationView extends AbstractView{
    private JLabel messageTitle;
    private JLabel notification;
    private JLabel notification2;
    private JLabel notification3;
    private JLabel notification4;
    /**
     * Constructor for objects of class NotificationView
     */
    public NotificationView(Model model)
    {
        super(model);
        messageTitle = new JLabel(String.format("%-20s","Meldingen:"));
        notification = new JLabel();
        notification2 = new JLabel();
        notification3 = new JLabel();
        notification4 = new JLabel();

        messageTitle.setForeground(new Color(201,201,201));
        notification.setForeground(new Color(201,201,201));
        notification2.setForeground(new Color(201,201,201));
        notification3.setForeground(new Color(201,201,201));
        notification4.setForeground(new Color(201,201,201));

        messageTitle.setFont(new FontClass());
        notification.setFont(new FontClass());
        notification2.setFont(new FontClass());

        this.setLayout(new GridLayout(5,1));

        add(messageTitle);
        add(notification);
        add(notification2);
        add(notification3);
        add(notification4);

        this.setBorder(new EmptyBorder(0,0,0,2));
        setBackground(new Color(32,34,37)); // Creates grey background

    }

    @Override
    /**
     * This method updates this view
     *
     */
    public void updateView()
    {
        notification.setText(model.revenueMessage());
        notification2.setText(model.endDayMessage());
    }
}
