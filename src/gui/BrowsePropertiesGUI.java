package gui;

import services.PropertyService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BrowsePropertiesGUI extends JFrame {
    private PropertyService propertyService;
    private String userEmail;

    public BrowsePropertiesGUI(String userEmail) {
        this.userEmail = userEmail;
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

        // Title label
        JLabel titleLabel = new JLabel("Browse Properties");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        addComponent(titleLabel, 0, 0, 2, 1, 0, 0, GridBagConstraints.CENTER);

        // List of properties with a scroll pane
        List<String> properties = propertyService.getAllProperties();
        JList<String> propertyList = new JList<>(properties.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(propertyList);
        addComponent(scrollPane, 0, 1, 2, 4, 1, 1, GridBagConstraints.BOTH); // Added weight and fill to allow expansion

        // "Add to Favorites" button
        JButton addToFavoritesButton = new JButton("Add to Favorites");
        addToFavoritesButton.setFont(new Font("Arial", Font.BOLD, 15));
        addToFavoritesButton.addActionListener(e -> {
            String selectedProperty = propertyList.getSelectedValue();
            if (selectedProperty != null) {
                propertyService.addToFavorites(userEmail, selectedProperty);
                JOptionPane.showMessageDialog(this, "Property added to favorites.");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a property to add to favorites.");
            }
        });
        addComponent(addToFavoritesButton, 0, 6, 2, 1, 0, 0, GridBagConstraints.CENTER);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 15));
        backButton.addActionListener(e -> setVisible(false));
        addComponent(backButton, 0, 7, 2, 1, 0, 0, GridBagConstraints.CENTER);

        revalidate();
        repaint();
    }

    // Helper method to add components with configurable GridBagConstraints
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