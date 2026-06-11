import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;

public class PatientForm extends JFrame implements ActionListener {

    JLabel lblID, lblName, lblPhone, lblType;

    JTextField txtID, txtName, txtPhone;

    JComboBox<String> cmbType;

    JButton btnAdd, btnClear;

    public PatientForm() {

        setTitle("Patient Management");
        setSize(400,300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6,2,5,5));

        

        lblID = new JLabel("Patient ID");
        lblName = new JLabel("Patient Name");
        lblPhone = new JLabel("Phone Number");
        lblType = new JLabel("Dialysis Type");
        

        txtID = new JTextField();
        txtName = new JTextField();
        txtPhone = new JTextField();

        String[] type = {"Hemodialysis", "Peritoneal Dialysis"};
        cmbType = new JComboBox<>(type);

        btnAdd = new JButton("Add");
        btnClear = new JButton("Clear");
        btnAdd.setBackground(Color.BLUE);
        btnAdd.setForeground(Color.WHITE);
        btnClear.setBackground(Color.BLUE);
        btnClear.setForeground(Color.WHITE);

        add(lblID);
        add(txtID);

        add(lblName);
        add(txtName);

        add(lblPhone);
        add(txtPhone);

        add(lblType);
        add(cmbType);

        add(btnAdd);
        add(btnClear);

        btnAdd.addActionListener(this);
        btnClear.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btnAdd) {

            String id = txtID.getText();
            String name = txtName.getText();
            String phone = txtPhone.getText();
            String dialysis = cmbType.getSelectedItem().toString();

            JOptionPane.showMessageDialog(null,
                    "Patient Added\n\n" +
                    "ID : " + id +
                    "\nName : " + name +
                    "\nPhone : " + phone +
                    "\nType : " + dialysis);
        }

        if(e.getSource() == btnClear) {

            txtID.setText("");
            txtName.setText("");
            txtPhone.setText("");
        }
    }
}