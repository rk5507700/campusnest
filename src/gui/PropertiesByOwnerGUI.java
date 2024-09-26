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
        addComponent(titleLabel, 0, 0, 2, 1);

        List<String> properties = propertyService.getPropertiesByOwner(ownerEmail);
        JList<String> propertyList = new JList<>(properties.toArray(new String[0]));

        JScrollPane scrollPane = new JScrollPane(propertyList);
        addComponent(scrollPane, 0, 1, 2, 4);

        

        JButton deleteButton = new JButton("Delete Property");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 15));
        deleteButton.addActionListener(e -> {
            String selectedProperty = propertyList.getSelectedValue();
            propertyService.deleteProperty(selectedProperty);
        });
        addComponent(deleteButton, 0, 6, 2, 1);
        
        JButton VisibilityButton = new JButton("Visibilitiy");
        VisibilityButton.setFont(new Font("Arial", Font.BOLD, 15));
        VisibilityButton.addActionListener(e -> {
            String selectedProperty = propertyList.getSelectedValue();
            propertyService.updateVisibility(selectedProperty);
        });
        addComponent(VisibilityButton, 0, 7, 2, 1);
        
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 15));
        backButton.addActionListener(e -> setVisible(false));
        addComponent(backButton, 0, 8, 2, 1);
        
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
}