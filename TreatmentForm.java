import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;

public class TreatmentForm extends JFrame implements ActionListener {

    JLabel lblPatientID, lblBloodPressure,
           lblWeightBefore, lblWeightAfter;

    JTextField txtPatientID,
               txtBloodPressure,
               txtWeightBefore,
               txtWeightAfter;

    JButton btnSave, btnClear;

    public TreatmentForm() {

        setTitle("Treatment Record");
        setSize(500,300);
        setLayout(new GridLayout(5,2,5,5));
        setLocationRelativeTo(null);

        lblPatientID = new JLabel("Patient ID");
        lblBloodPressure = new JLabel("Blood Pressure");
        lblWeightBefore = new JLabel("Weight Before");
        lblWeightAfter = new JLabel("Weight After");

        txtPatientID = new JTextField();
        txtBloodPressure = new JTextField();
        txtWeightBefore = new JTextField();
        txtWeightAfter = new JTextField();

        btnSave = new JButton("Save Record");
        btnClear = new JButton("Clear");

        btnSave.setBackground(Color.BLUE);
        btnSave.setForeground(Color.WHITE);

        btnClear.setBackground(Color.BLUE);
        btnClear.setForeground(Color.WHITE);

        add(lblPatientID);
        add(txtPatientID);

        add(lblBloodPressure);
        add(txtBloodPressure);

        add(lblWeightBefore);
        add(txtWeightBefore);

        add(lblWeightAfter);
        add(txtWeightAfter);

        add(btnSave);
        add(btnClear);

        btnSave.addActionListener(this);
        btnClear.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btnSave) {

            String patientID = txtPatientID.getText();
            String bp = txtBloodPressure.getText();
            String before = txtWeightBefore.getText();
            String after = txtWeightAfter.getText();

            if(patientID.isEmpty() ||
               bp.isEmpty() ||
               before.isEmpty() ||
               after.isEmpty()) {

                JOptionPane.showMessageDialog(
                    null,
                    "Please fill all fields!"
                );

                return;
            }

            JOptionPane.showMessageDialog(
                null,
                "TREATMENT RECORD SAVED\n\n" +
                "Patient ID : " + patientID +
                "\nBlood Pressure : " + bp +
                "\nWeight Before : " + before +
                "\nWeight After : " + after
            );
        }

        if(e.getSource() == btnClear) {

            txtPatientID.setText("");
            txtBloodPressure.setText("");
            txtWeightBefore.setText("");
            txtWeightAfter.setText("");
        }
    }
}