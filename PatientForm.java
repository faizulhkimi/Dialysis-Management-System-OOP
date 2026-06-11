import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;

public class PatientForm extends JFrame implements ActionListener {

    JLabel lblID, lblName, lblPhone, lblGender, lblAge,
           lblHeight, lblWeight, lblBloodType, lblType,
           lblSchedule, lblTime, lblMachine, lblTreatment,
           lblMO, lblDA;

    JTextField txtID, txtName, txtPhone, txtAge, txtHeight,
               txtWeight, txtTreatment;

    JComboBox<String> cmbGender, cmbBloodType, cmbType,
                      cmbSchedule, cmbTime, cmbMachine,
                      cmbMO, cmbDA;

    JButton btnAdd, btnClear, btnView, btnDelete;

    public PatientForm() {

        setTitle("Patient Management");
        setSize(600, 700);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(18, 2, 5, 5));

        lblID = new JLabel("Patient ID");
        lblName = new JLabel("Patient Name");
        lblPhone = new JLabel("Phone Number");
        lblGender = new JLabel("Gender");
        lblAge = new JLabel("Age");
        lblHeight = new JLabel("Height (cm)");
        lblWeight = new JLabel("Weight (kg)");
        lblBloodType = new JLabel("Blood Type");
        lblType = new JLabel("Dialysis Type");
        lblSchedule = new JLabel("Schedule Pattern");
        lblTime = new JLabel("Session Time");
        lblMachine = new JLabel("Machine Number");
        lblTreatment = new JLabel("Treatment ID");
        lblMO = new JLabel("Medical Officer ID");
        lblDA = new JLabel("Dialysis Assistant ID");

        txtID = new JTextField();
        txtName = new JTextField();
        txtPhone = new JTextField();
        txtAge = new JTextField();
        txtHeight = new JTextField();
        txtWeight = new JTextField();
        txtTreatment = new JTextField();

        String[] gender = {"Male", "Female"};
        cmbGender = new JComboBox<>(gender);

        String[] blood = {"A", "B", "AB", "O"};
        cmbBloodType = new JComboBox<>(blood);

        String[] type = {"Hemodialysis", "Peritoneal Dialysis"};
        cmbType = new JComboBox<>(type);

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

        String[] mo = {"MO001", "MO002", "MO003"};
        cmbMO = new JComboBox<>(mo);

        String[] da = {"DA001", "DA002", "DA003"};
        cmbDA = new JComboBox<>(da);

        btnAdd = new JButton("Add");
        btnClear = new JButton("Clear");
        btnView = new JButton("View Patients");
        btnDelete = new JButton("Delete Patient");

        btnAdd.setBackground(Color.BLUE);
        btnAdd.setForeground(Color.WHITE);

        btnClear.setBackground(Color.BLUE);
        btnClear.setForeground(Color.WHITE);

        btnView.setBackground(Color.BLUE);
        btnView.setForeground(Color.WHITE);

        btnDelete.setBackground(Color.BLUE);
        btnDelete.setForeground(Color.WHITE);

        add(lblID); add(txtID);
        add(lblName); add(txtName);
        add(lblPhone); add(txtPhone);
        add(lblGender); add(cmbGender);
        add(lblAge); add(txtAge);
        add(lblHeight); add(txtHeight);
        add(lblWeight); add(txtWeight);
        add(lblBloodType); add(cmbBloodType);
        add(lblType); add(cmbType);
        add(lblSchedule); add(cmbSchedule);
        add(lblTime); add(cmbTime);
        add(lblMachine); add(cmbMachine);
        add(lblTreatment); add(txtTreatment);
        add(lblMO); add(cmbMO);
        add(lblDA); add(cmbDA);
        add(btnAdd); add(btnClear);
        add(btnDelete); add(btnView);

        btnAdd.addActionListener(this);
        btnClear.addActionListener(this);
        btnView.addActionListener(this);
        btnDelete.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btnAdd) {

            SoundPatientForm.playClick();

            String id = txtID.getText();
            String name = txtName.getText();
            String phone = txtPhone.getText();

            String gender = cmbGender.getSelectedItem().toString();
            String bloodType = cmbBloodType.getSelectedItem().toString();
            String dialysis = cmbType.getSelectedItem().toString();
            String schedule = cmbSchedule.getSelectedItem().toString();
            String time = cmbTime.getSelectedItem().toString();
            String machine = cmbMachine.getSelectedItem().toString();
            String treatmentID = txtTreatment.getText();
            String moID = cmbMO.getSelectedItem().toString();
            String daID = cmbDA.getSelectedItem().toString();

            if(id.isEmpty() || name.isEmpty() || phone.isEmpty() ||
               txtAge.getText().isEmpty() ||
               txtHeight.getText().isEmpty() ||
               txtWeight.getText().isEmpty() ||
               treatmentID.isEmpty()) {

                JOptionPane.showMessageDialog(null, "Please fill all fields!");
                return;
            }

            try {

                int age = Integer.parseInt(txtAge.getText());
                double height = Double.parseDouble(txtHeight.getText());
                double weight = Double.parseDouble(txtWeight.getText());

                Patient patient = new Patient(
                    id, name, phone,
                    gender, age, height, weight,
                    bloodType, dialysis,
                    schedule, time, machine,
                    treatmentID,
                    moID, daID
                );

                savePatientData(patient);

                JOptionPane.showMessageDialog(
                    null,
                    "Patient Added\n\n" +
                    patient.displayInfo()
                );

            } catch(NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                    null,
                    "Age, height and weight must be numbers!"
                );
            }
        }

        if(e.getSource() == btnClear) {

            SoundPatientForm.playClick();

            txtID.setText("");
            txtName.setText("");
            txtPhone.setText("");
            txtAge.setText("");
            txtHeight.setText("");
            txtWeight.setText("");
            txtTreatment.setText("");
        }

        if(e.getSource() == btnView) {

            SoundPatientForm.playClick();
            viewPatientData();
        }

        if(e.getSource() == btnDelete) {

            SoundPatientForm.playClick();

            String id = txtID.getText();

            if(id.isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,
                    "Please enter Patient ID to delete!"
                );
                return;
            }

            deletePatientData(id);
        }
    }

    public void savePatientData(Patient patient) {

        try {

            PrintWriter tulisOutput =
                new PrintWriter(new FileWriter("patients.txt", true));

            tulisOutput.println(patient.displayInfo());
            tulisOutput.println("--------------------------------");
            tulisOutput.close();

        } catch(IOException ex) {

            JOptionPane.showMessageDialog(
                null,
                "Error saving patient data!"
            );
        }
    }

    public void viewPatientData() {

        try {

            Scanner bacaFile =
                new Scanner(new File("patients.txt"));

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
                panel.add(new JLabel("No patient data found."));
            }

            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setPreferredSize(new Dimension(550, 450));

            JOptionPane.showMessageDialog(
                null,
                scrollPane,
                "Patient List",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch(IOException ex) {

            JOptionPane.showMessageDialog(
                null,
                "Error reading patient data!"
            );
        }
    }

    public void deletePatientData(String id) {

        try {

            Scanner bacaFile =
                new Scanner(new File("patients.txt"));

            PrintWriter tulisOutput =
                new PrintWriter(new FileWriter("tempPatients.txt"));

            boolean jumpa = false;

            while(bacaFile.hasNextLine()) {

                String line1 = bacaFile.nextLine();

                if(line1.equals("Patient ID             : " + id)) {

                    jumpa = true;

                    for(int i = 0; i < 15; i++) {
                        if(bacaFile.hasNextLine()) {
                            bacaFile.nextLine();
                        }
                    }

                } else {

                    tulisOutput.println(line1);
                }
            }

            bacaFile.close();
            tulisOutput.close();

            Scanner bacaTemp =
                new Scanner(new File("tempPatients.txt"));

            PrintWriter tulisBaru =
                new PrintWriter(new FileWriter("patients.txt"));

            while(bacaTemp.hasNextLine()) {
                tulisBaru.println(bacaTemp.nextLine());
            }

            bacaTemp.close();
            tulisBaru.close();

            if(jumpa) {
                JOptionPane.showMessageDialog(
                    null,
                    "Patient deleted successfully!"
                );
            } else {
                JOptionPane.showMessageDialog(
                    null,
                    "Patient ID not found!"
                );
            }

        } catch(IOException ex) {

            JOptionPane.showMessageDialog(
                null,
                "Error deleting patient data!"
            );
        }
    }
}