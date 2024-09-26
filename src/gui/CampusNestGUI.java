package gui;

import services.RegistrationService;
import services.PropertyService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CampusNestGUI extends JFrame {
    private RegistrationService registrationService;
    private PropertyService propertyService;
    private JTextField nameField, emailField, ownerNameField, ownerEmailField, loginEmailField;
    private JPasswordField passwordField, ownerPasswordField, loginPasswordField;
    private JTextArea propertyDetailsField;
    private JLabel resultLabel, loginResultLabel;

    public CampusNestGUI() {
        registrationService = new RegistrationService();
        propertyService = new PropertyService();
        createSelectionPage();
        setTitle("Campus Nest");
        setSize(720, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createSelectionPage() {
        getContentPane().removeAll();
        setLayout(new GridBagLayout());
        
        JLabel titleLabel = new JLabel("Welcome to Campus Nest");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        addComponent(titleLabel, 0, 0, 2, 1);

        JButton registerUserButton = new JButton("Register as Tenant User");
        registerUserButton.addActionListener(e -> createUserRegistrationPage());
        addComponent(registerUserButton, 0, 1, 2, 1);

        JButton registerOwnerButton = new JButton("Register as Property Owner");
        registerOwnerButton.addActionListener(e -> createOwnerRegistrationPage());
        addComponent(registerOwnerButton, 0, 2, 2, 1);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> createLoginSelectionPage());
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

    private void createLoginSelectionPage() {
        getContentPane().removeAll();
        setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("Login as:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        addComponent(titleLabel, 0, 0, 2, 1);

        JButton userLoginButton = new JButton("Tenant User");
        userLoginButton.addActionListener(e -> createLoginPage("user"));
        addComponent(userLoginButton, 0, 1, 2, 1);

        JButton ownerLoginButton = new JButton("Property Owner");
        ownerLoginButton.addActionListener(e -> createLoginPage("owner"));
        addComponent(ownerLoginButton, 0, 2, 2, 1);

        revalidate();
        repaint();
    }

    private void createLoginPage(String userType) {
        getContentPane().removeAll();
        setLayout(new GridBagLayout());

        JLabel emailLabel = new JLabel("Email:");
        loginEmailField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        loginPasswordField = new JPasswordField(20);
        
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginListener(userType));
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

    private void createTenantDashboard(String email) {
        getContentPane().removeAll();
        TenantDashboardGUI tenantDashboard = new TenantDashboardGUI(email);
        tenantDashboard.setVisible(true);
        setVisible(false);
    }

    private void createOwnerDashboard(String email) {
        getContentPane().removeAll();
        OwnerDashboardGUI ownerDashboard = new OwnerDashboardGUI(email);
        ownerDashboard.setVisible(true);
        setVisible(false);
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
                createTenantDashboard(email);
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
                createOwnerDashboard(email);
            } else {
                resultLabel.setText("Registration failed.");
            }
        }
    }

    private class LoginListener implements ActionListener {
        private String userType;

        public LoginListener(String userType) {
            this.userType = userType;
        }

        public void actionPerformed(ActionEvent e) {
            String email = loginEmailField.getText();
            String password = new String(loginPasswordField.getPassword());

            boolean isLoggedIn = registrationService.login(email, password, userType);
            if (isLoggedIn) {
                loginResultLabel.setText("Login successful!");
                if (userType.equals("user")) {
                    createTenantDashboard(email);
                } else {
                    createOwnerDashboard(email);
                }
            } else {
                loginResultLabel.setText("Login failed. Please check your credentials.");
            }
        }
    }

    private void createBackButton() {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> createSelectionPage());
        addComponent(backButton, 0, 6, 2, 1);
    }

    public static void main(String[] args) {
        new CampusNestGUI();
    }
}