package gui;

import services.PropertyService;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import utils.DatabaseHelper;

public class OwnerDashboardGUI extends JFrame {
    private PropertyService propertyService;
    private String ownerEmail;
    private String name;
    private DatabaseHelper dbHelper;

    public OwnerDashboardGUI(String email) {
        this.ownerEmail = email;
        propertyService = new PropertyService();
        setTitle("Property Owner Dashboard");
        setSize(720, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createDashboard();

    }

    private void browseProperties(){
        PropertiesByOwnerGUI propertiesByOwnerGUI = new PropertiesByOwnerGUI(ownerEmail);
        propertiesByOwnerGUI.setVisible(true);
    }

    private void createDashboard() {
        getContentPane().removeAll();
        setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("Property Owner Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        addComponent(titleLabel, 0, 0, 2, 1);
        
        JButton addPropertyButton = new JButton("List New Property");
        addPropertyButton.setFont(new Font("Arial", Font.BOLD, 15));
        addPropertyButton.addActionListener(e -> listNewPropertyPage());
        addComponent(addPropertyButton, 0, 1, 2, 1);
        
        JButton browseButton = new JButton("Browse Properties");
        browseButton.setFont(new Font("Arial", Font.BOLD, 15));
        browseButton.addActionListener(e -> browseProperties());
        addComponent(browseButton, 0, 2, 2, 1);
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 15));
        logoutButton.addActionListener(e -> logout());
        addComponent(logoutButton, 0, 4, 2, 1);
        
        revalidate();
        repaint();
    }
    
    private void listNewPropertyPage() {
        // Logic to list a new property
        getContentPane().removeAll();
        setLayout(new GridBagLayout());
        
        JLabel listNewPropertyLabel = new JLabel("List New Property");
        listNewPropertyLabel.setFont(new Font("Arial", Font.BOLD, 24));
        addComponent(listNewPropertyLabel, 0, 0, 2, 1);
        
        JLabel propertyDetailsLabel = new JLabel("Property Details:");
        JTextArea propertyDetailsField = new JTextArea(5, 20);
        addComponent(new JScrollPane(propertyDetailsField), 0, 1, 1, 1);
        
        JButton addButton = new JButton("Add Property");
        addButton.setFont(new Font("Arial", Font.BOLD, 15));
        addButton.addActionListener(e -> {
            String property = propertyDetailsField.getText();
            propertyService.addNewProperty(ownerEmail, property);
        });
        addComponent(addButton,0, 2, 1,1);
        
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 15));
        backButton.addActionListener(e -> createDashboard());
        addComponent(backButton,0, 3, 1, 1);
        
        revalidate();
        repaint();
        
        
    }
    
    
    private void logout() {
        CampusNestGUI mainGui = new CampusNestGUI();
        mainGui.setVisible(true);
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
}