import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame implements ActionListener {

    JButton btnPatient, btnSession, btnTreatment, btnBilling, btnExit;

    public MainMenu() {

        setTitle("Dialysis Management System");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));
        getContentPane().setBackground(Color.WHITE);

        btnPatient = new JButton("Patient Management");
        btnSession = new JButton("Session Booking");
        btnTreatment = new JButton("Treatment Record");
        btnBilling = new JButton("Billing");
        btnExit = new JButton("Exit");

        add(btnPatient);
        add(btnSession);
        add(btnTreatment);
        add(btnBilling);
        add(btnExit);

        btnPatient.addActionListener(this);
        btnSession.addActionListener(this);
        btnTreatment.addActionListener(this);
        btnBilling.addActionListener(this);
        btnExit.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btnPatient) {
            new PatientForm();
        }

        if(e.getSource() == btnSession) {
            new SessionForm();
        }

        if(e.getSource() == btnTreatment) {
            new TreatmentForm();
        }

        if(e.getSource() == btnBilling) {
            new BillingForm();
        }

        if(e.getSource() == btnExit) {

    JOptionPane.showMessageDialog(
        null,
        "Thank You For Using\nDialysis Management System\n\n" +
        "We Wish You Good Health.\n" +
        "Take Care and Stay Safe."
    );

    System.exit(0);
}
    }

    public static void main ( String[] args) {
        new MainMenu();
    }

    
}