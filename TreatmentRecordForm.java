import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TreatmentRecordForm extends JFrame implements ActionListener {

    JTextField txtTreatmentID, txtPatientID, txtBP, txtWeight;
    JButton btnAdd, btnClear;
    JTextArea displayArea;

    static ArrayList<Treatment> treatmentList = new ArrayList<>();

    public TreatmentRecordForm() {
        setTitle("Treatment Record");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(5, 2, 5, 5));

        txtTreatmentID = new JTextField();
        txtPatientID = new JTextField();
        txtBP = new JTextField();
        txtWeight = new JTextField();

        btnAdd = new JButton("Add Treatment");
        btnClear = new JButton("Clear");

        form.add(new JLabel("Treatment ID"));
        form.add(txtTreatmentID);

        form.add(new JLabel("Patient ID"));
        form.add(txtPatientID);

        form.add(new JLabel("Blood Pressure"));
        form.add(txtBP);

        form.add(new JLabel("Weight"));
        form.add(txtWeight);

        form.add(btnAdd);
        form.add(btnClear);

        displayArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(displayArea);

        add(form, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        btnAdd.addActionListener(this);
        btnClear.addActionListener(this);

        refreshList();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnAdd) {
            Treatment t = new Treatment(
                    txtTreatmentID.getText(),
                    txtPatientID.getText(),
                    txtBP.getText(),
                    txtWeight.getText()
            );

            treatmentList.add(t);
            refreshList();

            JOptionPane.showMessageDialog(this, "Treatment Record Added");
        }

        if (e.getSource() == btnClear) {
            txtTreatmentID.setText("");
            txtPatientID.setText("");
            txtBP.setText("");
            txtWeight.setText("");
        }
    }

    public void refreshList() {
        displayArea.setText("");

        for (Treatment t : treatmentList) {
            displayArea.append(t.toString() + "\n");
        }
    }
}