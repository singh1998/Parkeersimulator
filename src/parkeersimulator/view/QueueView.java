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





    public QueueView(Model model){
        super(model);
        queueInfo=new JLabel("Rij informatie");
        ignoreInfo=new JLabel("Aantal klanten dat vandaag verder reed omdat ze de ingangsrij te lang vonden");
        Font font=new Font(queueInfo.getFont().getName(),Font.BOLD,15);
        queueInfo.setFont(font);
        ignoreInfo.setFont(font);

        nonSubcriptionCustomers=new JLabel();
        subscriptionCustomers=new JLabel();
        payingCustomers=new JLabel();
        leavingCustomers=new JLabel();


        ignoringPaidCustomerAmount=new JLabel();
        ignoringSubscribedCustomerAmount=new JLabel();
        ignoringTotal=new JLabel();
        previousIgnoringTotal=new JLabel();



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
         setBackground(new Color(225,225,208));
    }
    @Override
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
