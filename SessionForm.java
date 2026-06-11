import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;

public class SessionForm extends JFrame implements ActionListener {

    JLabel lblSessionID, lblPatientID, lblDate,
           lblSchedule, lblTime, lblMachine, lblStatus;

    JTextField txtSessionID, txtPatientID, txtDate;

    JComboBox<String> cmbSchedule, cmbTime, cmbMachine, cmbStatus;

    JButton btnSave, btnClear, btnView;

    public SessionForm() {

        setTitle("Dialysis Session Record");
        setSize(550, 430);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(9, 2, 5, 5));

        lblSessionID = new JLabel("Session ID");
        lblPatientID = new JLabel("Patient ID");
        lblDate = new JLabel("Session Date (DD/MM/YYYY)");
        lblSchedule = new JLabel("Schedule Pattern");
        lblTime = new JLabel("Session Time");
        lblMachine = new JLabel("Machine Number");
        lblStatus = new JLabel("Session Status");

        txtSessionID = new JTextField();
        txtPatientID = new JTextField();
        txtDate = new JTextField();

        String[] schedule = {
            "Monday-Wednesday-Friday",
            "Tuesday-Thursday-Saturday"
        };
        cmbSchedule = new JComboBox<>(schedule);

        String[] time = {
            "8:00 AM",
            "12:00 PM",
            "4:00 PM"
        };
        cmbTime = new JComboBox<>(time);

        String[] machine = {
            "Machine 1",
            "Machine 2",
            "Machine 3",
            "Machine 4"
        };
        cmbMachine = new JComboBox<>(machine);

        String[] status = {
            "Scheduled",
            "Completed",
            "Cancelled"
        };
        cmbStatus = new JComboBox<>(status);

        btnSave = new JButton("Save Session");
        btnClear = new JButton("Clear");
        btnView = new JButton("View Sessions");

        btnSave.setBackground(Color.BLUE);
        btnSave.setForeground(Color.WHITE);
        btnClear.setBackground(Color.BLUE);
        btnClear.setForeground(Color.WHITE);
        btnView.setBackground(Color.BLUE);
        btnView.setForeground(Color.WHITE);

        add(lblSessionID);
        add(txtSessionID);

        add(lblPatientID);
        add(txtPatientID);

        add(lblDate);
        add(txtDate);

        add(lblSchedule);
        add(cmbSchedule);

        add(lblTime);
        add(cmbTime);

        add(lblMachine);
        add(cmbMachine);

        add(lblStatus);
        add(cmbStatus);

        add(btnSave);
        add(btnClear);

        add(btnView);
        add(new JLabel(""));

        btnSave.addActionListener(this);
        btnClear.addActionListener(this);
        btnView.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btnSave) {

            SoundPatientForm.playClick();

            String sessionID = txtSessionID.getText();
            String patientID = txtPatientID.getText();
            String date = txtDate.getText();

            String schedule = cmbSchedule.getSelectedItem().toString();
            String time = cmbTime.getSelectedItem().toString();
            String machine = cmbMachine.getSelectedItem().toString();
            String status = cmbStatus.getSelectedItem().toString();

            if(sessionID.isEmpty() || patientID.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields!");
                return;
            }

            saveOrUpdateSessionData(sessionID, patientID, date,
                                    schedule, time, machine, status);

            updatePatientSessionData(patientID, schedule, time, machine);

            JOptionPane.showMessageDialog(
                null,
                "SESSION RECORD SAVED / UPDATED\n\n" +
                "Session ID : " + sessionID +
                "\nPatient ID : " + patientID +
                "\nDate       : " + date +
                "\nSchedule   : " + schedule +
                "\nTime       : " + time +
                "\nMachine    : " + machine +
                "\nStatus     : " + status
            );
        }

        if(e.getSource() == btnClear) {
            SoundPatientForm.playClick();

            txtSessionID.setText("");
            txtPatientID.setText("");
            txtDate.setText("");
        }

        if(e.getSource() == btnView) {
            SoundPatientForm.playClick();
            viewSessionData();
        }
    }

    public void saveOrUpdateSessionData(String sessionID, String patientID,
                                        String date, String schedule,
                                        String time, String machine,
                                        String status) {

        try {

            Scanner bacaFile = new Scanner(new File("sessions.txt"));
            PrintWriter tulisOutput =
                new PrintWriter(new FileWriter("tempSessions.txt"));

            boolean jumpaSession = false;

            while(bacaFile.hasNextLine()) {

                String line = bacaFile.nextLine();

                if(line.equals("Session ID       : " + sessionID)) {

                    jumpaSession = true;

                    tulisOutput.println("Session ID       : " + sessionID);
                    tulisOutput.println("Patient ID       : " + patientID);
                    tulisOutput.println("Session Date     : " + date);
                    tulisOutput.println("Schedule Pattern : " + schedule);
                    tulisOutput.println("Session Time     : " + time);
                    tulisOutput.println("Machine Number   : " + machine);
                    tulisOutput.println("Status           : " + status);
                    tulisOutput.println("--------------------------------");

                    for(int i = 0; i < 7; i++) {
                        if(bacaFile.hasNextLine()) {
                            bacaFile.nextLine();
                        }
                    }

                } else {
                    tulisOutput.println(line);
                }
            }

            if(!jumpaSession) {
                tulisOutput.println("Session ID       : " + sessionID);
                tulisOutput.println("Patient ID       : " + patientID);
                tulisOutput.println("Session Date     : " + date);
                tulisOutput.println("Schedule Pattern : " + schedule);
                tulisOutput.println("Session Time     : " + time);
                tulisOutput.println("Machine Number   : " + machine);
                tulisOutput.println("Status           : " + status);
                tulisOutput.println("--------------------------------");
            }

            bacaFile.close();
            tulisOutput.close();

            Scanner bacaTemp = new Scanner(new File("tempSessions.txt"));
            PrintWriter tulisBaru =
                new PrintWriter(new FileWriter("sessions.txt"));

            while(bacaTemp.hasNextLine()) {
                tulisBaru.println(bacaTemp.nextLine());
            }

            bacaTemp.close();
            tulisBaru.close();

        } catch(IOException ex) {

            JOptionPane.showMessageDialog(
                null,
                "Error saving or updating session data!"
            );
        }
    }

    public void updatePatientSessionData(String patientID,
                                         String schedule,
                                         String time,
                                         String machine) {

        try {

            Scanner bacaFile = new Scanner(new File("patients.txt"));
            PrintWriter tulisOutput =
                new PrintWriter(new FileWriter("tempPatients.txt"));

            boolean jumpaPatient = false;
            boolean dalamPatient = false;

            while(bacaFile.hasNextLine()) {

                String line = bacaFile.nextLine();

                if(line.equals("Patient ID             : " + patientID)) {
                    jumpaPatient = true;
                    dalamPatient = true;
                    tulisOutput.println(line);
                }
                else if(dalamPatient &&
                        line.startsWith("Schedule Pattern")) {

                    tulisOutput.println("Schedule Pattern       : " + schedule);
                }
                else if(dalamPatient &&
                        line.startsWith("Session Time")) {

                    tulisOutput.println("Session Time           : " + time);
                }
                else if(dalamPatient &&
                        line.startsWith("Machine Number")) {

                    tulisOutput.println("Machine Number         : " + machine);
                }
                else if(line.equals("--------------------------------")) {

                    tulisOutput.println(line);
                    dalamPatient = false;
                }
                else {
                    tulisOutput.println(line);
                }
            }

            bacaFile.close();
            tulisOutput.close();

            Scanner bacaTemp = new Scanner(new File("tempPatients.txt"));
            PrintWriter tulisBaru =
                new PrintWriter(new FileWriter("patients.txt"));

            while(bacaTemp.hasNextLine()) {
                tulisBaru.println(bacaTemp.nextLine());
            }

            bacaTemp.close();
            tulisBaru.close();

            if(!jumpaPatient) {
                JOptionPane.showMessageDialog(
                    null,
                    "Warning: Patient ID not found in patients.txt"
                );
            }

        } catch(IOException ex) {

            JOptionPane.showMessageDialog(
                null,
                "Error updating patient data!"
            );
        }
    }

    public void viewSessionData() {

        try {

            Scanner bacaFile = new Scanner(new File("sessions.txt"));

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(0, 1));

            boolean adaData = false;

            while(bacaFile.hasNextLine()) {

                String line = bacaFile.nextLine();

                if(!line.isEmpty()) {
                    panel.add(new JLabel(line));
                    adaData = true;
                }
            }

            bacaFile.close();

            if(!adaData) {
                panel.add(new JLabel("No session data found."));
            }

            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setPreferredSize(new Dimension(520, 400));

            JOptionPane.showMessageDialog(
                null,
                scrollPane,
                "Session List",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch(IOException ex) {

            JOptionPane.showMessageDialog(
                null,
                "Error reading session data!"
            );
        }
    }
}