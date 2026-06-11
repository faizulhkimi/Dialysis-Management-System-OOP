import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;

public class StaffForm extends JFrame implements ActionListener {

    JButton btnViewMO, btnViewDA;

    public StaffForm() {

        setTitle("Staff List");
        setSize(500, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1, 10, 10));

        btnViewMO = new JButton("View Medical Officers");
        btnViewDA = new JButton("View Dialysis Assistants");

        btnViewMO.setBackground(Color.BLUE);
        btnViewMO.setForeground(Color.WHITE);

        btnViewDA.setBackground(Color.BLUE);
        btnViewDA.setForeground(Color.WHITE);

        add(btnViewMO);
        add(btnViewDA);

        btnViewMO.addActionListener(this);
        btnViewDA.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btnViewMO) {
            SoundPatientForm.playClick();
            viewStaffData("medicalOfficers.txt", "Medical Officer List");
        }

        if(e.getSource() == btnViewDA) {
            SoundPatientForm.playClick();
            viewStaffData("dialysisAssistants.txt", "Dialysis Assistant List");
        }
    }

    public void viewStaffData(String fileName, String title) {

        try {

            Scanner bacaFile = new Scanner(new File(fileName));

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
                panel.add(new JLabel("No staff data found."));
            }

            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setPreferredSize(new Dimension(500, 400));

            JOptionPane.showMessageDialog(
                null,
                scrollPane,
                title,
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch(IOException ex) {

            JOptionPane.showMessageDialog(
                null,
                "Error reading staff data!"
            );
        }
    }
}