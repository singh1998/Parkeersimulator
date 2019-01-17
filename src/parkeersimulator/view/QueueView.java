package parkeersimulator.view;

import parkeersimulator.model.Model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class QueueView extends AbstractView{
    private JLabel nonSubcriptionCustomers;
    private JLabel subscriptionCustomers;
    private JLabel payingCustomers;
    private JLabel leavingCustomers;
    private JLabel queueInfo;




    public QueueView(Model model){
        super(model);
        queueInfo=new JLabel("Rij informatie");
        Font font=new Font(queueInfo.getFont().getName(),Font.BOLD,15);
        queueInfo.setFont(font);
        nonSubcriptionCustomers=new JLabel();
        subscriptionCustomers=new JLabel();
        payingCustomers=new JLabel();
        leavingCustomers=new JLabel();
        this.setLayout(new GridLayout(5,1));
        add(queueInfo);
        add(nonSubcriptionCustomers);
        add(subscriptionCustomers);
        add(payingCustomers);
        add(leavingCustomers);
        this.setBorder(new EmptyBorder(30,30,30,30));
         setBackground(Color.green);
    }
    @Override
    public void updateView() {
        nonSubcriptionCustomers.setText("Klanten in de wachtrij zonder abonnement: "+model.getPayingArrivingCars());
        subscriptionCustomers.setText("Klanten in de wachtrij met abonnement: "+model.getSubscribtionArrivingCars());
        payingCustomers.setText("Klanten in de rij om te betalen: "+model.getPayingCars());
        leavingCustomers.setText("Klanten in de rij om te vertrekken : "+model.getLeavingCars());

        super.updateView();
    }


}
