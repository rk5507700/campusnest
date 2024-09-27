package gui;

import services.PropertyService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TenantDashboardGUI extends JFrame {
    private PropertyService propertyService;
    private String userEmail;

    public TenantDashboardGUI(String email) {
        this.userEmail = email;
        propertyService = new PropertyService();
        setTitle("Tenant Dashboard");
        setSize(720, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createDashboard();
    }

    private void createDashboard() {
        getContentPane().removeAll();
        setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("Tenant Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        addComponent(titleLabel, 0, 0, 2, 1, 0, 0, GridBagConstraints.CENTER);

        JButton browseButton = new JButton("Browse Properties");
        browseButton.setFont(new Font("Arial", Font.BOLD, 15));
        browseButton.addActionListener(e -> browseProperties());
        addComponent(browseButton, 0, 1, 2, 1, 0, 0, GridBagConstraints.CENTER);
        
        JButton viewFavoritesButton = new JButton("View Favorite Properties");
        viewFavoritesButton.setFont(new Font("Arial", Font.BOLD, 15));
        viewFavoritesButton.addActionListener(e -> viewFavorites());
        addComponent(viewFavoritesButton, 0, 2, 2, 1, 0, 0, GridBagConstraints.CENTER);
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 15));
        logoutButton.addActionListener(e -> logout());
        addComponent(logoutButton, 0, 3, 2, 1, 0, 0, GridBagConstraints.CENTER);
        
        revalidate();
        repaint();
    }
    
    private void browseProperties() {
        BrowsePropertiesGUI browsePropertiesGUI = new BrowsePropertiesGUI(userEmail);
        browsePropertiesGUI.setVisible(true);
    }
    
    private void viewFavorites() {
        getContentPane().removeAll();
        setLayout(new GridBagLayout());
        
        // Fetching favorite properties
        List<String> favorites = propertyService.getFavoriteProperties(userEmail);
        JList<String> favoriteList = new JList<>(favorites.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(favoriteList);

        // Adjusting layout constraints for scroll pane to allow it to expand
        addComponent(scrollPane, 0, 0, 2, 4, 1, 1, GridBagConstraints.BOTH);

        // Back button to return to the dashboard
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 15));
        backButton.addActionListener(e -> createDashboard());
        addComponent(backButton, 0, 5, 2, 1, 0, 0, GridBagConstraints.CENTER);
        
        revalidate();
        repaint();
    }

    private void logout() {
        CampusNestGUI mainGui = new CampusNestGUI();
        mainGui.setVisible(true);
        setVisible(false);
    }
    
    // Helper method to add components with weight and fill options for better layout management
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