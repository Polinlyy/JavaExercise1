import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Locker extends JFrame {

    private JPasswordField passcodeField;
    private JButton enterButton;
    private JLabel messageLabel;
    private String savedPasscode; // Stores the saved passcode (in-memory)

    public Locker() {
        super("Locker");
        initializeComponents();
    }

    private void initializeComponents() {
        passcodeField = new JPasswordField(15);
        enterButton = new JButton("Enter");
        messageLabel = new JLabel("");

        // Layout
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("Passcode: "));
        panel.add(passcodeField);
        panel.add(enterButton);
        panel.add(messageLabel);

        this.getContentPane().add(panel);

        // Action Listeners
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredPasscode = new String(passcodeField.getPassword());
                verifyPasscode(enteredPasscode);
            }
        });

        this.setSize(400, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void verifyPasscode(String enteredPasscode) {
        if (savedPasscode == null) {
            // No passcode set yet, prompt to set one
            int choice = JOptionPane.showConfirmDialog(this, "No passcode set. Set one now?", "Set Passcode", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                setPasscode();
            }
        } else if (savedPasscode.equals(enteredPasscode)) {
            messageLabel.setText("Correct Password");
            // Simulate opening the locker (replace with your desired action)
            JOptionPane.showMessageDialog(this, "Locker Opened!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearPasscodeField();
        } else {
            messageLabel.setText("Incorrect Password");
        }
    }

    private void setPasscode() {
        String newPasscode = JOptionPane.showInputDialog(this, "Enter your desired passcode:", "Set Passcode");
        if (newPasscode != null && !newPasscode.isEmpty()) {
            savedPasscode = newPasscode;
            messageLabel.setText("Passcode set successfully.");
        } else {
            messageLabel.setText("Passcode cannot be empty.");
        }
        clearPasscodeField();
    }

    private void clearPasscodeField() {
        passcodeField.setText("");
    }

    public static void main(String[] args) {
        new Locker();
    }
}