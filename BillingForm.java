import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BillingForm extends JFrame implements ActionListener {

    JTextField txtBillID, txtPatientID, txtTreatmentCost, txtMedicineCost;
    JButton btnGenerate, btnClear;
    JTextArea displayArea;

    static ArrayList<Billing> billList = new ArrayList<>();

    public BillingForm() {
        setTitle("Billing System");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(5, 2, 5, 5));

        txtBillID = new JTextField();
        txtPatientID = new JTextField();
        txtTreatmentCost = new JTextField();
        txtMedicineCost = new JTextField();

        btnGenerate = new JButton("Generate Bill");
        btnClear = new JButton("Clear");

        form.add(new JLabel("Bill ID"));
        form.add(txtBillID);

        form.add(new JLabel("Patient ID"));
        form.add(txtPatientID);

        form.add(new JLabel("Treatment Cost"));
        form.add(txtTreatmentCost);

        form.add(new JLabel("Medicine Cost"));
        form.add(txtMedicineCost);

        form.add(btnGenerate);
        form.add(btnClear);

        displayArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(displayArea);

        add(form, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        btnGenerate.addActionListener(this);
        btnClear.addActionListener(this);

        refreshList();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnGenerate) {
            try {
                double treatmentCost = Double.parseDouble(txtTreatmentCost.getText());
                double medicineCost = Double.parseDouble(txtMedicineCost.getText());

                Billing b = new Billing(
                        txtBillID.getText(),
                        txtPatientID.getText(),
                        treatmentCost,
                        medicineCost
                );

                billList.add(b);
                refreshList();

                JOptionPane.showMessageDialog(this, "Bill Generated Successfully");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid cost number");
            }
        }

        if (e.getSource() == btnClear) {
            txtBillID.setText("");
            txtPatientID.setText("");
            txtTreatmentCost.setText("");
            txtMedicineCost.setText("");
        }
    }

    public void refreshList() {
        displayArea.setText("");

        for (Billing b : billList) {
            displayArea.append(b.toString() + "\n----------------------\n");
        }
    }
}