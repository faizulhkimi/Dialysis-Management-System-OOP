import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;

public class TreatmentForm extends JFrame implements ActionListener {

    JLabel lblTreatmentID;
    JLabel lblPatientID;
    JLabel lblMO;
    JLabel lblDA;
    JLabel lblBloodPressure;
    JLabel lblWeightBefore;
    JLabel lblWeightAfter;
    JLabel lblStatus;

    JTextField txtTreatmentID;
    JTextField txtPatientID;
    JTextField txtBloodPressure;
    JTextField txtWeightBefore;
    JTextField txtWeightAfter;

    JComboBox<String> cmbMO, cmbDA, cmbStatus;

    JButton btnSave, btnClear, btnView;

    public TreatmentForm() {

        setTitle("Treatment Record");
        setSize(550, 450);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(10, 2, 5, 5));

        lblTreatmentID = new JLabel("Treatment ID");
        lblPatientID = new JLabel("Patient ID");
        lblMO = new JLabel("Medical Officer ID");
        lblDA = new JLabel("Dialysis Assistant ID");
        lblBloodPressure = new JLabel("Blood Pressure");
        lblWeightBefore = new JLabel("Weight Before (kg)");
        lblWeightAfter = new JLabel("Weight After (kg)");
        lblStatus = new JLabel("Treatment Status");

        txtTreatmentID = new JTextField();
        txtPatientID = new JTextField();
        txtBloodPressure = new JTextField();
        txtWeightBefore = new JTextField();
        txtWeightAfter = new JTextField();

        String[] mo = {"MO001", "MO002", "MO003"};
        cmbMO = new JComboBox<>(mo);

        String[] da = {"DA001", "DA002", "DA003"};
        cmbDA = new JComboBox<>(da);

        String[] status = {"Ongoing", "Completed", "Cancelled"};
        cmbStatus = new JComboBox<>(status);

        btnSave = new JButton("Save Treatment");
        btnClear = new JButton("Clear");
        btnView = new JButton("View Treatments");

        btnSave.setBackground(Color.BLUE);
        btnSave.setForeground(Color.WHITE);

        btnClear.setBackground(Color.BLUE);
        btnClear.setForeground(Color.WHITE);

        btnView.setBackground(Color.BLUE);
        btnView.setForeground(Color.WHITE);

        add(lblTreatmentID);
        add(txtTreatmentID);

        add(lblPatientID);
        add(txtPatientID);

        add(lblMO);
        add(cmbMO);

        add(lblDA);
        add(cmbDA);

        add(lblBloodPressure);
        add(txtBloodPressure);

        add(lblWeightBefore);
        add(txtWeightBefore);

        add(lblWeightAfter);
        add(txtWeightAfter);

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

            String treatmentID = txtTreatmentID.getText();
            String patientID = txtPatientID.getText();
            String moID = cmbMO.getSelectedItem().toString();
            String daID = cmbDA.getSelectedItem().toString();
            String bp = txtBloodPressure.getText();
            String before = txtWeightBefore.getText();
            String after = txtWeightAfter.getText();
            String status = cmbStatus.getSelectedItem().toString();

            if(treatmentID.isEmpty() || patientID.isEmpty() ||
               bp.isEmpty() || before.isEmpty() || after.isEmpty()) {

                JOptionPane.showMessageDialog(null, "Please fill all fields!");
                return;
            }

            try {

                double weightBefore = Double.parseDouble(before);
                double weightAfter = Double.parseDouble(after);

                saveOrUpdateTreatmentData(
                    treatmentID, patientID, moID, daID,
                    bp, weightBefore, weightAfter, status
                );

                updatePatientTreatmentData(patientID, treatmentID);

                JOptionPane.showMessageDialog(
                    null,
                    "TREATMENT RECORD SAVED / UPDATED\n\n" +
                    "Treatment ID : " + treatmentID +
                    "\nPatient ID : " + patientID +
                    "\nMedical Officer ID : " + moID +
                    "\nDialysis Assistant ID : " + daID +
                    "\nBlood Pressure : " + bp +
                    "\nWeight Before : " + weightBefore +
                    "\nWeight After : " + weightAfter +
                    "\nStatus : " + status
                );

            } catch(NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                    null,
                    "Weight before and weight after must be numbers!"
                );
            }
        }

        if(e.getSource() == btnClear) {

            SoundPatientForm.playClick();

            txtTreatmentID.setText("");
            txtPatientID.setText("");
            txtBloodPressure.setText("");
            txtWeightBefore.setText("");
            txtWeightAfter.setText("");
        }

        if(e.getSource() == btnView) {

            SoundPatientForm.playClick();
            viewTreatmentData();
        }
    }

    public void saveOrUpdateTreatmentData(String treatmentID, String patientID,
                                          String moID, String daID,
                                          String bp, double weightBefore,
                                          double weightAfter, String status) {

        try {

            Scanner bacaFile = new Scanner(new File("treatments.txt"));
            PrintWriter tulisOutput =
                new PrintWriter(new FileWriter("tempTreatments.txt"));

            boolean jumpaTreatment = false;

            while(bacaFile.hasNextLine()) {

                String line = bacaFile.nextLine();

                if(line.equals("Treatment ID          : " + treatmentID)) {

                    jumpaTreatment = true;

                    tulisOutput.println("Treatment ID          : " + treatmentID);
                    tulisOutput.println("Patient ID            : " + patientID);
                    tulisOutput.println("Medical Officer ID    : " + moID);
                    tulisOutput.println("Dialysis Assistant ID : " + daID);
                    tulisOutput.println("Blood Pressure        : " + bp);
                    tulisOutput.println("Weight Before         : " + weightBefore + " kg");
                    tulisOutput.println("Weight After          : " + weightAfter + " kg");
                    tulisOutput.println("Treatment Status      : " + status);
                    tulisOutput.println("--------------------------------");

                    for(int i = 0; i < 8; i++) {
                        if(bacaFile.hasNextLine()) {
                            bacaFile.nextLine();
                        }
                    }

                } else {
                    tulisOutput.println(line);
                }
            }

            if(!jumpaTreatment) {
                tulisOutput.println("Treatment ID          : " + treatmentID);
                tulisOutput.println("Patient ID            : " + patientID);
                tulisOutput.println("Medical Officer ID    : " + moID);
                tulisOutput.println("Dialysis Assistant ID : " + daID);
                tulisOutput.println("Blood Pressure        : " + bp);
                tulisOutput.println("Weight Before         : " + weightBefore + " kg");
                tulisOutput.println("Weight After          : " + weightAfter + " kg");
                tulisOutput.println("Treatment Status      : " + status);
                tulisOutput.println("--------------------------------");
            }

            bacaFile.close();
            tulisOutput.close();

            Scanner bacaTemp = new Scanner(new File("tempTreatments.txt"));
            PrintWriter tulisBaru =
                new PrintWriter(new FileWriter("treatments.txt"));

            while(bacaTemp.hasNextLine()) {
                tulisBaru.println(bacaTemp.nextLine());
            }

            bacaTemp.close();
            tulisBaru.close();

        } catch(IOException ex) {

            JOptionPane.showMessageDialog(
                null,
                "Error saving or updating treatment data!"
            );
        }
    }

    public void updatePatientTreatmentData(String patientID, String treatmentID) {

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
                        line.startsWith("Treatment ID")) {

                    tulisOutput.println("Treatment ID           : " + treatmentID);
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
                "Error updating patient treatment data!"
            );
        }
    }

    public void viewTreatmentData() {

        try {

            Scanner bacaFile = new Scanner(new File("treatments.txt"));

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
                panel.add(new JLabel("No treatment data found."));
            }

            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setPreferredSize(new Dimension(520, 400));

            JOptionPane.showMessageDialog(
                null,
                scrollPane,
                "Treatment List",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch(IOException ex) {

            JOptionPane.showMessageDialog(
                null,
                "Error reading treatment data!"
            );
        }
    }
}