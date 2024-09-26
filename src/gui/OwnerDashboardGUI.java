package gui;

import services.PropertyService;

import javax.swing.*;
import java.awt.*;

public class OwnerDashboardGUI extends JFrame {
    private PropertyService propertyService;
    private String ownerEmail;

    public OwnerDashboardGUI(String email) {
        this.ownerEmail = email;
        propertyService = new PropertyService();
        setTitle("Property Owner Dashboard");
        setSize(720, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createDashboard();
    }

    private void createDashboard() {
        getContentPane().removeAll();
        setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("Property Owner Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        addComponent(titleLabel, 0, 0, 2, 1);

        JButton addPropertyButton = new JButton("List New Property");
        addPropertyButton.addActionListener(e -> listNewProperty());
        addComponent(addPropertyButton, 0, 1, 2, 1);

        JButton deletePropertyButton = new JButton("Delete Property");
        deletePropertyButton.addActionListener(e -> deleteProperty());
        addComponent(deletePropertyButton, 0, 2, 2, 1);

        JButton hidePropertyButton = new JButton("Hide/Show Property");
        hidePropertyButton.addActionListener(e -> hideProperty());
        addComponent(hidePropertyButton, 0, 3, 2, 1);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> logout());
        addComponent(logoutButton, 0, 4, 2, 1);

        revalidate();
        repaint();
    }

    private void listNewProperty() {
        // Logic to list a new property
    }

    private void deleteProperty() {
        // Logic to delete a property
    }

    private void hideProperty() {
        // Logic to hide or show a property
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