import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;

public class SessionForm extends JFrame implements ActionListener {

    JLabel lblSessionID, lblPatientID, lblDate, lblTime, lblMachine;

    JTextField txtSessionID, txtPatientID, txtDate;

    JComboBox<String> cmbTime, cmbMachine;

    JButton btnBook, btnClear;

    public SessionForm() {

        setTitle("Dialysis Session Booking");
        setSize(500,350);
        setLayout(new GridLayout(6,2,5,5));
        setLocationRelativeTo(null);

        lblSessionID = new JLabel("Session ID");
        lblPatientID = new JLabel("Patient ID");
        lblDate = new JLabel("Date (DD/MM/YYYY)");
        lblTime = new JLabel("Time");
        lblMachine = new JLabel("Machine Number");

        txtSessionID = new JTextField();
        txtPatientID = new JTextField();
        txtDate = new JTextField();

        String[] times = {
                "8:00 AM",
                "12:00 PM",
                "4:00 PM"
        };

        cmbTime = new JComboBox<>(times);

        String[] machines = {
                "Machine 1",
                "Machine 2",
                "Machine 3",
                "Machine 4"
        };

        cmbMachine = new JComboBox<>(machines);

        btnBook = new JButton("Book Session");
        btnClear = new JButton("Clear");

        btnBook.setBackground(Color.BLUE);
        btnBook.setForeground(Color.WHITE);

        btnClear.setBackground(Color.BLUE);
        btnClear.setForeground(Color.WHITE);

        add(lblSessionID);
        add(txtSessionID);

        add(lblPatientID);
        add(txtPatientID);

        add(lblDate);
        add(txtDate);

        add(lblTime);
        add(cmbTime);

        add(lblMachine);
        add(cmbMachine);

        add(btnBook);
        add(btnClear);

        btnBook.addActionListener(this);
        btnClear.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // FUNCTION BUTTON BOOK SESSION
        if(e.getSource() == btnBook) {

            String sessionID = txtSessionID.getText();
            String patientID = txtPatientID.getText();
            String date = txtDate.getText();

            String time =
                    cmbTime.getSelectedItem().toString();

            String machine =
                    cmbMachine.getSelectedItem().toString();

            // Validation
            if(sessionID.isEmpty() ||
               patientID.isEmpty() ||
               date.isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "Please fill all fields!"
                );

                return;
            }

            JOptionPane.showMessageDialog(
                    null,
                    "SESSION SUCCESSFULLY BOOKED\n\n" +
                    "Session ID : " + sessionID +
                    "\nPatient ID : " + patientID +
                    "\nDate : " + date +
                    "\nTime : " + time +
                    "\nMachine : " + machine
            );
        }

        // FUNCTION BUTTON CLEAR
        if(e.getSource() == btnClear) {

            txtSessionID.setText("");
            txtPatientID.setText("");
            txtDate.setText("");
        }
    }
}