package gui;

import services.PropertyService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class PropertiesByOwnerGUI extends JFrame {
    private PropertyService propertyService;
    private String ownerEmail;

    public PropertiesByOwnerGUI(String ownerEmail) {
        this.ownerEmail = ownerEmail;
        propertyService = new PropertyService();
        setTitle("Browse Properties");
        setSize(720, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createPropertyBrowsePage();
    }

    private void createPropertyBrowsePage() {
        getContentPane().removeAll();
        setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("Browse Properties");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        addComponent(titleLabel, 0, 0, 2, 1, 0, 0, GridBagConstraints.CENTER);

        List<String> properties = propertyService.getPropertiesByOwner(ownerEmail);
        JList<String> propertyList = new JList<>(properties.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(propertyList);
        
        // Adding scrollPane to the layout
        addComponent(scrollPane, 0, 1, 2, 3, 1, 1, GridBagConstraints.BOTH);

        JButton deleteButton = new JButton("Delete Property");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 15));
        deleteButton.addActionListener(e -> {
            String selectedProperty = propertyList.getSelectedValue();
            if (selectedProperty != null) {
                propertyService.deleteProperty(selectedProperty);
                createPropertyBrowsePage(); // Refresh the page after deletion
            } else {
                JOptionPane.showMessageDialog(this, "Please select a property to delete.");
            }
        });
        addComponent(deleteButton, 0, 4, 1, 1, 0, 0, GridBagConstraints.CENTER);

        JButton visibilityButton = new JButton("Visibility");
        visibilityButton.setFont(new Font("Arial", Font.BOLD, 15));
        visibilityButton.addActionListener(e -> {
            String selectedProperty = propertyList.getSelectedValue();
            if (selectedProperty != null) {
                propertyService.updateVisibility(selectedProperty);
                JOptionPane.showMessageDialog(this, "Visibility updated for " + selectedProperty);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a property to update visibility.");
            }
        });
        addComponent(visibilityButton, 1, 4, 1, 1, 0, 0, GridBagConstraints.CENTER);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 15));
        backButton.addActionListener(e -> setVisible(false));
        addComponent(backButton, 0, 5, 2, 1, 0, 0, GridBagConstraints.CENTER);

        revalidate();
        repaint();
    }

    // Helper method to add components with customizable GridBagConstraints
    private void addComponent(Component comp, int gridx, int gridy, int gridwidth, int gridheight,
                              double weightx, double weighty, int fill) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.fill = fill;
        gbc.anchor = GridBagConstraints.CENTER;
        add(comp, gbc);
    }
}