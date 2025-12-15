package view;

import dao.InventoryDAOImpl;
import dao.DonationDAOImpl;
import dao.RequestDAOImpl;
import model.Inventory;
import javax.swing.*;
import java.util.List;
import java.awt.*;

public class HomePage extends JPanel {
    private InventoryDAOImpl inventoryDAO;
    private DonationDAOImpl donationDAO;
    private RequestDAOImpl requestDAO;
    
    public HomePage() {
        initComponents();
        enhanceAppearance();
        // Load data lazily to improve performance
        javax.swing.SwingUtilities.invokeLater(() -> {
            inventoryDAO = new InventoryDAOImpl();
            donationDAO = new DonationDAOImpl();
            requestDAO = new RequestDAOImpl();
            refreshData();
        });
    }
    
    private void refreshData() {
        // Update card values with actual data
        try {
            java.awt.Component[] components = getComponents();
            if (components.length > 1 && components[1] instanceof javax.swing.JPanel) {
                javax.swing.JPanel statsPanel = (javax.swing.JPanel) components[1];
                java.awt.Component[] cards = statsPanel.getComponents();
                
                // Update each card with real data
                updateCard(cards[0], getTotalItems());
                updateCard(cards[1], getTotalDonations());
                updateCard(cards[2], getPendingRequests());
                updateCard(cards[3], getLowStockItems());
            }
        } catch (Exception e) {
            // Ignore errors during refresh
        }
    }
    
    private void updateCard(java.awt.Component card, String value) {
        if (card instanceof javax.swing.JPanel) {
            javax.swing.JPanel panel = (javax.swing.JPanel) card;
            java.awt.Component[] components = panel.getComponents();
            for (java.awt.Component comp : components) {
                if (comp instanceof javax.swing.JLabel) {
                    javax.swing.JLabel label = (javax.swing.JLabel) comp;
                    if (label.getFont().getSize() == 48) { // This is the value label
                        label.setText(value);
                        break;
                    }
                }
            }
        }
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        
        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        JLabel titleLabel = new JLabel("Dashboard Overview");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        
        // Stats cards panel
        JPanel statsPanel = new JPanel(new GridLayout(2, 2, 40, 40));
        statsPanel.setOpaque(false);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        
        // Create stat cards with placeholder data (will be updated lazily)
        statsPanel.add(createStatCard("Total Items", "...", new Color(59, 130, 246)));
        statsPanel.add(createStatCard("Total Donations", "...", new Color(16, 185, 129)));
        statsPanel.add(createStatCard("Pending Requests", "...", new Color(245, 158, 11)));
        statsPanel.add(createStatCard("Low Stock Items", "...", new Color(239, 68, 68)));
        
        add(titlePanel, BorderLayout.NORTH);
        add(statsPanel, BorderLayout.CENTER);
    }
    
    private JPanel createStatCard(String title, String value, Color color) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(30, 41, 59));
        card.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        
        // Add icon
        JLabel iconLabel = new JLabel();
        try {
            String iconName = getIconName(title);
            if (iconName != null) {
                ImageIcon icon = new ImageIcon(getClass().getResource("/resources/icons/" + iconName));
                Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                iconLabel.setIcon(new ImageIcon(img));
            }
        } catch (Exception e) {
            // Ignore if icon not found
        }
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        titleLabel.setForeground(new Color(156, 163, 175));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 48));
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        card.add(Box.createVerticalGlue());
        card.add(iconLabel);
        card.add(Box.createRigidArea(new Dimension(0, 15)));
        card.add(titleLabel);
        card.add(Box.createRigidArea(new Dimension(0, 15)));
        card.add(valueLabel);
        card.add(Box.createVerticalGlue());
        
        return card;
    }
    
    private String getIconName(String title) {
        if (title.contains("Total Items")) return "Items.png";
        if (title.contains("Total Donations")) return "donation.png";
        if (title.contains("Pending Requests")) return "requests.png";
        if (title.contains("Low Stock")) return "low-stock.png";
        return null;
    }
    
    private void enhanceAppearance() {
        setBackground(new Color(15, 23, 42));
    }
    
    private String getTotalItems() {
        try {
            List<Inventory> items = inventoryDAO.getInventoryTotals();
            return String.valueOf(items.size());
        } catch (Exception e) {
            return "0";
        }
    }
    
    private String getTotalDonations() {
        try {
            return String.valueOf(donationDAO.getAllDonations().size());
        } catch (Exception e) {
            return "0";
        }
    }
    
    private String getPendingRequests() {
        try {
            return String.valueOf(requestDAO.getPendingRequests().size());
        } catch (Exception e) {
            return "0";
        }
    }
    
    private String getLowStockItems() {
        try {
            List<Inventory> items = inventoryDAO.getInventoryTotals();
            long lowStock = items.stream().filter(item -> item.getQuantity() <= 10).count();
            return String.valueOf(lowStock);
        } catch (Exception e) {
            return "0";
        }
    }
}