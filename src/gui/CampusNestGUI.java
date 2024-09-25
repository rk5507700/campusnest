package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import services.RegistrationService;

public class CampusNestGUI extends JFrame {

    private RegistrationService registrationService;

    // Components for normal user registration
    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private JLabel resultLabel;

    // Components for property owner registration
    private JTextField ownerNameField, ownerEmailField, propertyDetailsField;
    private JPasswordField ownerPasswordField;

    // Components for login
    private JTextField loginEmailField;
    private JPasswordField loginPasswordField;
    private JLabel loginResultLabel;

    public CampusNestGUI() {
        registrationService = new RegistrationService();
        setTitle("Campus Nest Registration");
        setSize(720, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null); // Center the window

        createSelectionPage();
        
        setVisible(true);
    }

    private void createSelectionPage() {
        // Clear the frame
        getContentPane().removeAll();

        JLabel label = new JLabel("Select User Type");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        addComponent(label, 0, 0, 2, 1);

        JButton normalUserButton = new JButton("Normal User");
        normalUserButton.addActionListener(e -> showNormalUserRegistration());
        addComponent(normalUserButton, 0, 1, 1, 1);

        JButton propertyOwnerButton = new JButton("Property Owner");
        propertyOwnerButton.addActionListener(e -> showPropertyOwnerRegistration());
        addComponent(propertyOwnerButton, 1, 1, 1, 1);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> showLoginPage());
        addComponent(loginButton, 0, 2, 2, 1);

        revalidate();
        repaint();
    }

    private void showNormalUserRegistration() {
        // Clear the frame
        getContentPane().removeAll();

        JLabel label = new JLabel("Register Normal User");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        addComponent(label, 0, 0, 2, 1);

        nameField = new JTextField(15);
        emailField = new JTextField(15);
        passwordField = new JPasswordField(15);
        resultLabel = new JLabel("");

        addComponent(new JLabel("Name:"), 0, 1, 1, 1);
        addComponent(nameField, 1, 1, 1, 1);
        addComponent(new JLabel("Email:"), 0, 2, 1, 1);
        addComponent(emailField, 1, 2, 1, 1);
        addComponent(new JLabel("Password:"), 0, 3, 1, 1);
        addComponent(passwordField, 1, 3, 1, 1);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new RegisterUserListener());
        addComponent(registerButton, 0, 4, 2, 1);
        
        addComponent(resultLabel, 0, 5, 2, 1);
        createBackButton();

        revalidate();
        repaint();
    }

    private void showPropertyOwnerRegistration() {
        // Clear the frame
        getContentPane().removeAll();

        JLabel label = new JLabel("Register Property Owner");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        addComponent(label, 0, 0, 2, 1);

        ownerNameField = new JTextField(15);
        ownerEmailField = new JTextField(15);
        ownerPasswordField = new JPasswordField(15);
        propertyDetailsField = new JTextField(15);
        resultLabel = new JLabel("");

        addComponent(new JLabel("Name:"), 0, 1, 1, 1);
        addComponent(ownerNameField, 1, 1, 1, 1);
        addComponent(new JLabel("Email:"), 0, 2, 1, 1);
        addComponent(ownerEmailField, 1, 2, 1, 1);
        addComponent(new JLabel("Password:"), 0, 3, 1, 1);
        addComponent(ownerPasswordField, 1, 3, 1, 1);
        addComponent(new JLabel("Property Details:"), 0, 4, 1, 1);
        addComponent(propertyDetailsField, 1, 4, 1, 1);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new RegisterPropertyOwnerListener());
        addComponent(registerButton, 0, 5, 2, 1);
        
        addComponent(resultLabel, 0, 6, 2, 1);
        createBackButton();

        revalidate();
        repaint();
    }

    private void showLoginPage() {
        // Clear the frame
        getContentPane().removeAll();

        JLabel label = new JLabel("User Login");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        addComponent(label, 0, 0, 2, 1);

        loginEmailField = new JTextField(15);
        loginPasswordField = new JPasswordField(15);
        loginResultLabel = new JLabel("");

        addComponent(new JLabel("Email:"), 0, 1, 1, 1);
        addComponent(loginEmailField, 1, 1, 1, 1);
        addComponent(new JLabel("Password:"), 0, 2, 1, 1);
        addComponent(loginPasswordField, 1, 2, 1, 1);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginUserListener());
        addComponent(loginButton, 0, 3, 2, 1);
        
        addComponent(loginResultLabel, 0, 4, 2, 1);
        createBackButton();

        revalidate();
        repaint();
    }

    private void createBackButton() {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> createSelectionPage());
        addComponent(backButton, 0, 7, 2, 1);
    }

    private void addComponent(Component component, int gridx, int gridy, int gridwidth, int gridheight) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(component, gbc);
    }

    // Listener for normal user registration
    private class RegisterUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (registrationService.registerUser(name, email, password)) {
                resultLabel.setText("Registration successful!");
            } else {
                resultLabel.setText("Email already exists!");
            }
        }
    }

    // Listener for property owner registration
    private class RegisterPropertyOwnerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = ownerNameField.getText();
            String email = ownerEmailField.getText();
            String password = new String(ownerPasswordField.getPassword());
            String propertyDetails = propertyDetailsField.getText();

            if (registrationService.registerPropertyOwner(name, email, password, propertyDetails)) {
                resultLabel.setText("Property Owner registration successful!");
            } else {
                resultLabel.setText("Registration failed!");
            }
        }
    }

    // Listener for user login
    private class LoginUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = loginEmailField.getText();
            String password = new String(loginPasswordField.getPassword());

            if (registrationService.loginUser(email, password)) {
                loginResultLabel.setText("Login successful!");
            } else {
                loginResultLabel.setText("Invalid email or password!");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CampusNestGUI::new);
    }
}