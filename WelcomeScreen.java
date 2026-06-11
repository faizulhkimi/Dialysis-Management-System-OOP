import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomeScreen extends JFrame implements ActionListener {

    JLabel title1, title2;
    JButton btnStart;

    public WelcomeScreen() {

        setTitle("Dialysis Management System");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        title1 = new JLabel("Welcome To");
        title2 = new JLabel("Dialysis Management System");

        title1.setHorizontalAlignment(JLabel.CENTER);
        title2.setHorizontalAlignment(JLabel.CENTER);

        title1.setFont(new Font("Arial", Font.BOLD, 30));
        title2.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel centerPanel = new JPanel(new GridLayout(2,1));

        centerPanel.add(title1);
        centerPanel.add(title2);

        btnStart = new JButton("START");
        btnStart.setFont(new Font("Arial", Font.BOLD, 18));

        btnStart.addActionListener(this);

        add(centerPanel, BorderLayout.CENTER);
        add(btnStart, BorderLayout.SOUTH);

        setVisible(true);

        // Mainkan audio relax
        SoundPatientForm.playLoop("Relax.wav");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btnStart) {

            SoundPatientForm.playLoop("Relax.wav");

            dispose();

            new MainMenu();
        }
    }

    public static void main(String[] args) {
        new WelcomeScreen();
    }
}