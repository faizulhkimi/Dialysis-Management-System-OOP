import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SessionBookingForm extends JFrame implements ActionListener {

    JTextField txtSessionID, txtPatientID, txtDate, txtTime;
    JButton btnBook, btnClear;
    JTextArea displayArea;

    static ArrayList<Session> sessionList = new ArrayList<>();

    public SessionBookingForm() {
        setTitle("Session Booking");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(5, 2, 5, 5));

        txtSessionID = new JTextField();
        txtPatientID = new JTextField();
        txtDate = new JTextField();
        txtTime = new JTextField();

        btnBook = new JButton("Book Session");
        btnClear = new JButton("Clear");

        form.add(new JLabel("Session ID"));
        form.add(txtSessionID);

        form.add(new JLabel("Patient ID"));
        form.add(txtPatientID);

        form.add(new JLabel("Date"));
        form.add(txtDate);

        form.add(new JLabel("Time"));
        form.add(txtTime);

        form.add(btnBook);
        form.add(btnClear);

        displayArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(displayArea);

        add(form, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        btnBook.addActionListener(this);
        btnClear.addActionListener(this);

        refreshList();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnBook) {
            Session s = new Session(
                    txtSessionID.getText(),
                    txtPatientID.getText(),
                    txtDate.getText(),
                    txtTime.getText()
            );

            sessionList.add(s);
            refreshList();

            JOptionPane.showMessageDialog(this, "Session Booked Successfully");
        }

        if (e.getSource() == btnClear) {
            txtSessionID.setText("");
            txtPatientID.setText("");
            txtDate.setText("");
            txtTime.setText("");
        }
    }

    public void refreshList() {
        displayArea.setText("");

        for (Session s : sessionList) {
            displayArea.append(s.toString() + "\n");
        }
    }
}