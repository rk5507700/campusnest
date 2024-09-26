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

    private ImageIcon BgImage = new ImageIcon("src/gui/imgs/MainPageBG.jpeg");
    private Image BgImage_C = BgImage.getImage();



    public CampusNestGUI() {
        registrationService = new RegistrationService();
        propertyService = new PropertyService();
        createSelectionPage();
        setTitle("CampusNest");
        setSize(720, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createSelectionPage() {
        getContentPane().removeAll();

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new GridBagLayout());
        
        JLabel titleLabel = new JLabel("Welcome to CampusNest");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        backgroundPanel.addComponent(titleLabel, 0, 0, 2, 1);

        JButton registerUserButton = new JButton("Register as Tenant User");
        registerUserButton.setFont(new Font("Arial", Font.BOLD, 15));
        registerUserButton.addActionListener(e -> createUserRegistrationPage());
        backgroundPanel.addComponent(registerUserButton, 0, 1, 2, 1);
        
        JButton registerOwnerButton = new JButton("Register as Property Owner");
        registerOwnerButton.setFont(new Font("Arial", Font.BOLD, 15));
        registerOwnerButton.addActionListener(e -> createOwnerRegistrationPage());
        backgroundPanel.addComponent(registerOwnerButton, 0, 2, 2, 1);
        
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 15));
        loginButton.addActionListener(e -> createLoginSelectionPage());
        backgroundPanel.addComponent(loginButton, 0, 3, 2, 1);
        
        setContentPane(backgroundPanel);
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
        registerButton.setFont(new Font("Arial", Font.BOLD, 15));
        registerButton.addActionListener(new RegisterUserListener());
        resultLabel = new JLabel();
        
        addComponent(nameLabel, 0, 0, 1, 1);
        addComponent(nameField, 1, 0, 1, 1);
        addComponent(emailLabel, 0, 1, 1, 1);
        addComponent(emailField, 1, 1, 1, 1);
        addComponent(passwordLabel, 0, 2, 1, 1);
        addComponent(passwordField, 1, 2, 1, 1);
        addComponent(registerButton, 0, 4, 2, 1);
        addComponent(resultLabel, 0, 6, 2, 1);
        
        createBackButton(0, 5);
        
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
        
        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 15));
        registerButton.addActionListener(new RegisterPropertyOwnerListener());
        resultLabel = new JLabel();
        
        addComponent(ownerNameLabel, 0, 0, 1, 1);
        addComponent(ownerNameField, 1, 0, 1, 1);
        addComponent(ownerEmailLabel, 0, 1, 1, 1);
        addComponent(ownerEmailField, 1, 1, 1, 1);
        addComponent(ownerPasswordLabel, 0, 2, 1, 1);
        addComponent(ownerPasswordField, 1, 2, 1, 1);
        addComponent(new JScrollPane(propertyDetailsField), 1, 3, 1, 2);
        addComponent(registerButton, 0, 6, 2, 1);
        addComponent(resultLabel, 0, 8, 2, 1);
        
        createBackButton(0, 7);
        
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
        userLoginButton.setFont(new Font("Arial", Font.BOLD, 15));
        userLoginButton.addActionListener(e -> createLoginPage("user"));
        addComponent(userLoginButton, 0, 1, 2, 1);
        
        JButton ownerLoginButton = new JButton("Property Owner");
        ownerLoginButton.setFont(new Font("Arial", Font.BOLD, 15));
        ownerLoginButton.addActionListener(e -> createLoginPage("owner"));
        addComponent(ownerLoginButton, 0, 2, 2, 1);
        
        
        createBackButton(0, 3);
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
        loginButton.setFont(new Font("Arial", Font.BOLD, 15));
        loginButton.addActionListener(new LoginListener(userType));
        loginResultLabel = new JLabel();
        
        addComponent(emailLabel, 0, 0, 1, 1);
        addComponent(loginEmailField, 1, 0, 1, 1);
        addComponent(passwordLabel, 0, 1, 1, 1);
        addComponent(loginPasswordField, 1, 1, 1, 1);
        addComponent(loginButton, 0, 3, 2, 1);
        addComponent(loginResultLabel, 0, 4, 2, 1);
        
        createBackButton(0, 6);
        
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
            email = email.trim().toLowerCase();
            String password = new String(ownerPasswordField.getPassword());
            
            boolean isRegistered = registrationService.registerPropertyOwner(name, email, password);
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
    
    private void createBackButton(int x, int y) {
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 15));
        backButton.addActionListener(e -> createSelectionPage());
        addComponent(backButton, x, y, 2, 1);
    }

    private class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(BgImage_C, 0, 0, getWidth(), getHeight(), this);
        }
        public void addComponent(Component comp, int gridx, int gridy, int gridwidth, int gridheight) {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = gridx;
            gbc.gridy = gridy;
            gbc.gridwidth = gridwidth;
            gbc.gridheight = gridheight;
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.anchor = GridBagConstraints.CENTER;
            add(comp, gbc);
        }
    }

    public static void main(String[] args) {
        new CampusNestGUI();
    }
}