import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomeScreen extends JFrame implements ActionListener {

    JButton btnStart;

    public WelcomeScreen() {
        setTitle("Dialysis Management System");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("<html><center>Welcome To<br>Dialysis Management System</center></html>");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setHorizontalAlignment(JLabel.CENTER);

        btnStart = new JButton("START");
        btnStart.setFont(new Font("Arial", Font.BOLD, 18));
        btnStart.addActionListener(this);

        add(title, BorderLayout.CENTER);
        add(btnStart, BorderLayout.SOUTH);

        SoundPlayer.playLoop("Relax.wav");

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        SoundPlayer.stop();
        dispose();
        new MainMenu();
    }
}