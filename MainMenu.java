import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JFrame implements ActionListener {

    JButton btnPatient, btnSession, btnTreatment, btnStaff, btnExit;

    public MainMenu() {

        setTitle("Dialysis Management System");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));
        getContentPane().setBackground(Color.WHITE);

        btnPatient = new JButton("Patient Management");
        btnSession = new JButton("Session Record");
        btnTreatment = new JButton("Treatment Record");
        btnStaff = new JButton("Staff List");
        btnExit = new JButton("Exit");

        add(btnPatient);
        add(btnSession);
        add(btnTreatment);
        add(btnStaff);
        add(btnExit);

        btnPatient.addActionListener(this);
        btnSession.addActionListener(this);
        btnTreatment.addActionListener(this);
        btnStaff.addActionListener(this);
        btnExit.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btnPatient) {
            SoundPatientForm.playClick();
            new PatientForm();
        }

        if(e.getSource() == btnSession) {
            SoundPatientForm.playClick();
            new SessionForm();
        }

        if(e.getSource() == btnTreatment) {
            SoundPatientForm.playClick();
            new TreatmentForm();
        }

        if(e.getSource() == btnStaff) {
            SoundPatientForm.playClick();
            new StaffForm();
        }

        if(e.getSource() == btnExit) {
            SoundPatientForm.playClick();

            JOptionPane.showMessageDialog(
                null,
                "Thank You For Using\nDialysis Management System\n\n" +
                "We Wish You Good Health.\n" +
                "Take Care and Stay Safe."
            );

            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}