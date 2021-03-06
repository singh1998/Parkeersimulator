/**
 * This class describes information about the queue's in the simulation
 *
 * @version 2.0
 * @Author Park-It
 */
package parkeersimulator.view;

import parkeersimulator.model.Model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class QueueView extends AbstractView{
    private JLabel queueInfo;
    private JLabel nonSubcriptionCustomers;
    private JLabel subscriptionCustomers;
    private JLabel payingCustomers;
    private JLabel leavingCustomers;

    private JLabel ignoreInfo;
    private JLabel ignoringPaidCustomerAmount;
    private JLabel ignoringSubscribedCustomerAmount;
    private JLabel ignoringTotal;
    private JLabel previousIgnoringTotal;



    /**
     * Constructor for objects of class QueueView
     */
    public QueueView(Model model){
        super(model);







        queueInfo=new JLabel("Rij informatie");
        ignoreInfo=new JLabel("Aantal klanten dat vandaag verder reed omdat ze de ingangsrij te lang vonden");





        nonSubcriptionCustomers=new JLabel();
        subscriptionCustomers=new JLabel();
        payingCustomers=new JLabel();
        leavingCustomers=new JLabel();


        ignoringPaidCustomerAmount=new JLabel();
        ignoringSubscribedCustomerAmount=new JLabel();
        ignoringTotal=new JLabel();
        previousIgnoringTotal=new JLabel();

        // Adds a font to the JLabels
        Font font = new Font("Century Gothic",Font.BOLD,35);
        Font font2 = new Font("Century Gothic",Font.BOLD, 20);
        queueInfo.setFont(font);
        ignoreInfo.setFont(font2);
        nonSubcriptionCustomers.setFont(new FontClass());
        subscriptionCustomers.setFont(new FontClass());
        payingCustomers.setFont(new FontClass());
        leavingCustomers.setFont(new FontClass());
        ignoringPaidCustomerAmount.setFont(new FontClass());
        ignoringSubscribedCustomerAmount.setFont(new FontClass());
        ignoringTotal.setFont(new FontClass());
        previousIgnoringTotal.setFont(new FontClass());

        queueInfo.setForeground(new Color(201,201,201));
        ignoreInfo.setForeground(new Color(201,201,201));
        nonSubcriptionCustomers.setForeground(new Color(201,201,201));
        subscriptionCustomers.setForeground(new Color(201,201,201));
        payingCustomers.setForeground(new Color(201,201,201));
        leavingCustomers.setForeground(new Color(201,201,201));
        ignoringPaidCustomerAmount.setForeground(new Color(201,201,201));
        ignoringSubscribedCustomerAmount.setForeground(new Color(201,201,201));
        ignoringTotal.setForeground(new Color(201,201,201));
        previousIgnoringTotal.setForeground(new Color(201,201,201));



        this.setLayout(new GridLayout(10,1));
        add(queueInfo);
        add(nonSubcriptionCustomers);
        add(subscriptionCustomers);
        add(payingCustomers);
        add(leavingCustomers);
        add(ignoreInfo);
        add (ignoringPaidCustomerAmount);
        add(ignoringSubscribedCustomerAmount);
        add(ignoringTotal);
        add(previousIgnoringTotal);


        this.setBorder(new EmptyBorder(30,30,30,30));
         setBackground(new Color(60,63,65)); // Creates grey background
    }
    @Override
    /**
     * This method updates this view
     *
     */
    public void updateView() {
        nonSubcriptionCustomers.setText("Grootte van de ingangsrij voor reguliere kanten: "+model.getPayingArrivingCars());
        subscriptionCustomers.setText("Grootte van de ingangsrij voor klanten met abonnement : "+model.getSubscribtionArrivingCars());
        payingCustomers.setText("Klanten in de rij om te betalen: "+model.getPayingCars());
        leavingCustomers.setText("Klanten in de rij om te vertrekken : "+model.getLeavingCars());

        ignoringPaidCustomerAmount.setText("Bij de rij met reguliere klanten: "+model.getPaidQueueIgnorers());
        ignoringSubscribedCustomerAmount.setText("Bij de rij met abonnementhouders: "+model.getPassQueueIgnorers());
        ignoringTotal.setText("Totaal: "+(model.getPaidQueueIgnorers()+model.getPassQueueIgnorers()));
        previousIgnoringTotal.setText("Totaal van de vorige dag: "+model.getPreviousTotalIgnoring());
        repaint();
    }


}
