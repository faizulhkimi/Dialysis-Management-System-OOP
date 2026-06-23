import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomeScreen extends JFrame implements ActionListener {

    JLabel title1;
    JLabel title2;
    JLabel title3;
    JButton btnStart;

    public WelcomeScreen() {

        setTitle("Dialysis Management System");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        title1 = new JLabel("Welcome To");
        title2 = new JLabel("Dialysis Management System");
        title3 = new JLabel("Hai Bot");

        title1.setHorizontalAlignment(JLabel.CENTER);
        title2.setHorizontalAlignment(JLabel.CENTER);
        title3.setHorizontalAlignment(JLabel.CENTER);

        title1.setFont(new Font("Arial", Font.BOLD, 30));
        title2.setFont(new Font("Arial", Font.BOLD, 24));
        title3.setFont(new Font("Times New Roman", Font.BOLD, 18));

        JPanel centerPanel = new JPanel(new GridLayout(3,1));

        centerPanel.add(title1);
        centerPanel.add(title2);
        centerPanel.add(title3);

        btnStart = new JButton("START");
        btnStart.setFont(new Font("Arial", Font.BOLD, 30));

        btnStart.addActionListener(this);

        add(centerPanel, BorderLayout.CENTER);
        add(btnStart, BorderLayout.SOUTH);

        setVisible(true);

        // Mainkan audio relax dengan volume rendah.
       // SoundPatientForm.playLoop("Relax.wav");
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btnStart) {

          //  SoundPatientForm.playClick();
           // dispose();

            new MainMenu();
        }
    }

    public static void main(String[] args) {
        new WelcomeScreen();
    }
}
