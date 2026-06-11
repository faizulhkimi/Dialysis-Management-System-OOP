import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PatientForm extends JFrame implements ActionListener {

    JTextField txtID, txtName, txtPhone;
    JComboBox<String> cmbType;
    JButton btnAdd, btnSearch, btnDelete, btnClear;
    JTextArea displayArea;

    static ArrayList<Patient> patientList = new ArrayList<>();

    public PatientForm() {
        setTitle("Patient Management");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(5, 2, 5, 5));

        txtID = new JTextField();
        txtName = new JTextField();
        txtPhone = new JTextField();

        cmbType = new JComboBox<>(new String[]{"Hemodialysis", "Peritoneal Dialysis"});

        btnAdd = new JButton("Add Patient");
        btnSearch = new JButton("Search Patient");
        btnDelete = new JButton("Delete Patient");
        btnClear = new JButton("Clear");

        form.add(new JLabel("Patient ID"));
        form.add(txtID);

        form.add(new JLabel("Patient Name"));
        form.add(txtName);

        form.add(new JLabel("Phone Number"));
        form.add(txtPhone);

        form.add(new JLabel("Dialysis Type"));
        form.add(cmbType);

        form.add(btnAdd);
        form.add(btnSearch);

        JPanel bottom = new JPanel(new GridLayout(1, 2));
        bottom.add(btnDelete);
        bottom.add(btnClear);

        displayArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(displayArea);

        add(form, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        btnAdd.addActionListener(this);
        btnSearch.addActionListener(this);
        btnDelete.addActionListener(this);
        btnClear.addActionListener(this);

        refreshList();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnAdd) {
            if (txtID.getText().isEmpty() || txtName.getText().isEmpty() || txtPhone.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields");
                return;
            }

            Patient p = new Patient(
                    txtID.getText(),
                    txtName.getText(),
                    txtPhone.getText(),
                    cmbType.getSelectedItem().toString()
            );

            patientList.add(p);
            refreshList();
            JOptionPane.showMessageDialog(this, "Patient Added Successfully");
        }

        if (e.getSource() == btnSearch) {
            String id = txtID.getText();

            for (Patient p : patientList) {
                if (p.getPatientID().equals(id)) {
                    JOptionPane.showMessageDialog(this, "Patient Found\n\n" + p);
                    return;
                }
            }

            JOptionPane.showMessageDialog(this, "Patient Not Found");
        }

        if (e.getSource() == btnDelete) {
            String id = txtID.getText();

            for (int i = 0; i < patientList.size(); i++) {
                if (patientList.get(i).getPatientID().equals(id)) {
                    patientList.remove(i);
                    refreshList();
                    JOptionPane.showMessageDialog(this, "Patient Deleted");
                    return;
                }
            }

            JOptionPane.showMessageDialog(this, "Patient Not Found");
        }

        if (e.getSource() == btnClear) {
            txtID.setText("");
            txtName.setText("");
            txtPhone.setText("");
        }
    }

    public void refreshList() {
        displayArea.setText("");

        for (Patient p : patientList) {
            displayArea.append(p.toString() + "\n");
        }
    }
}