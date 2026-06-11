import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;

public class BillingForm extends JFrame implements ActionListener {

    JLabel lblPatientID,
           lblTreatmentCost,
           lblMedicationCost;

    JTextField txtPatientID,
               txtTreatmentCost,
               txtMedicationCost;

    JButton btnCalculate,
            btnClear;

    public BillingForm() {

        setTitle("Billing System");
        setSize(500,300);
        setLayout(new GridLayout(4,2,5,5));
        setLocationRelativeTo(null);

        lblPatientID = new JLabel("Patient ID");
        lblTreatmentCost = new JLabel("Treatment Cost");
        lblMedicationCost = new JLabel("Medication Cost");

        txtPatientID = new JTextField();
        txtTreatmentCost = new JTextField();
        txtMedicationCost = new JTextField();

        btnCalculate = new JButton("Calculate Bill");
        btnClear = new JButton("Clear");

        btnCalculate.setBackground(Color.BLUE);
        btnCalculate.setForeground(Color.WHITE);

        btnClear.setBackground(Color.BLUE);
        btnClear.setForeground(Color.WHITE);

        add(lblPatientID);
        add(txtPatientID);

        add(lblTreatmentCost);
        add(txtTreatmentCost);

        add(lblMedicationCost);
        add(txtMedicationCost);

        add(btnCalculate);
        add(btnClear);

        btnCalculate.addActionListener(this);
        btnClear.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btnCalculate) {

            double treatment =
                Double.parseDouble(
                    txtTreatmentCost.getText());

            double medication =
                Double.parseDouble(
                    txtMedicationCost.getText());

            double total =
                treatment + medication;

            JOptionPane.showMessageDialog(
                null,
                "Total Bill = RM " + total
            );
        }

        if(e.getSource() == btnClear) {

            txtPatientID.setText("");
            txtTreatmentCost.setText("");
            txtMedicationCost.setText("");
        }
    }
}