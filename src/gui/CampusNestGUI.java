package gui;

import services.RegistrationService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CampusNestGUI extends JFrame {
    private RegistrationService registrationService;
    private JTextField nameField, emailField, ownerNameField, ownerEmailField, loginEmailField;
    private JPasswordField passwordField, ownerPasswordField, loginPasswordField;
    private JTextArea propertyDetailsField;
    private JLabel resultLabel, loginResultLabel;

    public CampusNestGUI() {
        registrationService = new RegistrationService();
        createSelectionPage();
        setTitle("Campus Nest");
        setSize(720, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createSelectionPage() {
        getContentPane().removeAll(); // Clear existing components
        setLayout(new GridBagLayout());
        
        JLabel titleLabel = new JLabel("Welcome to Campus Nest");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        addComponent(titleLabel, 0, 0, 2, 1);

        JButton registerUserButton = new JButton("Register as Normal User");
        registerUserButton.addActionListener(e -> createUserRegistrationPage());
        addComponent(registerUserButton, 0, 1, 2, 1);

        JButton registerOwnerButton = new JButton("Register as Property Owner");
        registerOwnerButton.addActionListener(e -> createOwnerRegistrationPage());
        addComponent(registerOwnerButton, 0, 2, 2, 1);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> createLoginPage());
        addComponent(loginButton, 0, 3, 2, 1);

        revalidate();
        repaint();
    }

    private void createUserRegistrationPage() {
        getContentPane().removeAll();
        setLayout(new GridBagLayout());

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new RegisterUserListener());
        resultLabel = new JLabel();

        addComponent(nameLabel, 0, 0, 1, 1);
        addComponent(nameField, 1, 0, 1, 1);
        addComponent(emailLabel, 0, 1, 1, 1);
        addComponent(emailField, 1, 1, 1, 1);
        addComponent(passwordLabel, 0, 2, 1, 1);
        addComponent(passwordField, 1, 2, 1, 1);
        addComponent(registerButton, 0, 4, 2, 1);
        addComponent(resultLabel, 0, 5, 2, 1);

        createBackButton();

        revalidate();
        repaint();
    }

    private void createOwnerRegistrationPage() {
        getContentPane().removeAll();
        setLayout(new GridBagLayout());

        JLabel ownerNameLabel = new JLabel("Name:");
        ownerNameField = new JTextField(20);
        JLabel ownerEmailLabel = new JLabel("Email:");
        ownerEmailField = new JTextField(20);
        JLabel ownerPasswordLabel = new JLabel("Password:");
        ownerPasswordField = new JPasswordField(20);
        JLabel propertyDetailsLabel = new JLabel("Property Details:");
        propertyDetailsField = new JTextArea(5, 20);
        
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new RegisterPropertyOwnerListener());
        resultLabel = new JLabel();

        addComponent(ownerNameLabel, 0, 0, 1, 1);
        addComponent(ownerNameField, 1, 0, 1, 1);
        addComponent(ownerEmailLabel, 0, 1, 1, 1);
        addComponent(ownerEmailField, 1, 1, 1, 1);
        addComponent(ownerPasswordLabel, 0, 2, 1, 1);
        addComponent(ownerPasswordField, 1, 2, 1, 1);
        addComponent(propertyDetailsLabel, 0, 3, 1, 1);
        addComponent(new JScrollPane(propertyDetailsField), 1, 3, 1, 2);
        addComponent(registerButton, 0, 6, 2, 1);
        addComponent(resultLabel, 0, 7, 2, 1);

        createBackButton();

        revalidate();
        repaint();
    }

    private void createLoginPage() {
        getContentPane().removeAll();
        setLayout(new GridBagLayout());

        JLabel emailLabel = new JLabel("Email:");
        loginEmailField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        loginPasswordField = new JPasswordField(20);
        
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginListener());
        loginResultLabel = new JLabel();

        addComponent(emailLabel, 0, 0, 1, 1);
        addComponent(loginEmailField, 1, 0, 1, 1);
        addComponent(passwordLabel, 0, 1, 1, 1);
        addComponent(loginPasswordField, 1, 1, 1, 1);
        addComponent(loginButton, 0, 3, 2, 1);
        addComponent(loginResultLabel, 0, 4, 2, 1);

        createBackButton();

        revalidate();
        repaint();
    }

    private void createWelcomePage(String username) {
        getContentPane().removeAll();
        setLayout(new GridBagLayout());

        JLabel welcomeLabel = new JLabel("Welcome, " + username + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        addComponent(welcomeLabel, 0, 0, 2, 1);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> createSelectionPage());
        addComponent(logoutButton, 0, 1, 2, 1);

        revalidate();
        repaint();
    }

    private void addComponent(Component comp, int gridx, int gridy, int gridwidth, int gridheight) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(comp, gbc);
    }

    private class RegisterUserListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            boolean isRegistered = registrationService.registerUser(name, email, password);
            if (isRegistered) {
                resultLabel.setText("Registration successful!");
                createWelcomePage(name); // Navigate to welcome page
            } else {
                resultLabel.setText("Registration failed. Email may already exist.");
            }
        }
    }

    private class RegisterPropertyOwnerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = ownerNameField.getText();
            String email = ownerEmailField.getText();
            String password = new String(ownerPasswordField.getPassword());
            String propertyDetails = propertyDetailsField.getText();

            boolean isRegistered = registrationService.registerPropertyOwner(name, email, password, propertyDetails);
            if (isRegistered) {
                resultLabel.setText("Property Owner registration successful!");
                createWelcomePage(name); // Navigate to welcome page
            } else {
                resultLabel.setText("Registration failed.");
            }
        }
    }

    private class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String email = loginEmailField.getText();
            String password = new String(loginPasswordField.getPassword());

            boolean isLoggedIn = registrationService.loginUser(email, password);
            if (isLoggedIn) {
                loginResultLabel.setText("Login successful!");
                createWelcomePage(email); // Navigate to welcome page
            } else {
                loginResultLabel.setText("Login failed. Invalid credentials.");
            }
        }
    }

    private void createBackButton() {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> createSelectionPage());
        addComponent(backButton, 0, 8, 2, 1);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CampusNestGUI::new);
    }
}