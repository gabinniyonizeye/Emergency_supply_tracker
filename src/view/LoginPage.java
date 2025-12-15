package view;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private UserDAO userDAO;
    
    public LoginPage() {
        userDAO = new UserDAOImpl();
        initComponents();
        enhanceAppearance();
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }
    
    private void enhanceAppearance() {
        // Professional colors
        java.awt.Color primaryRed = new java.awt.Color(185, 28, 28);
        java.awt.Color accentBlue = new java.awt.Color(30, 64, 175);
        
        // Create gradient background
        setResizable(false);
        getContentPane().setBackground(new java.awt.Color(15, 23, 42));     // Dark professional bg
        
        // Input field styling
        styleAdvancedInput(txtUsername, "", accentBlue);
          styleAdvancedInput(txtPassword, "", accentBlue);
        
        // Login button styling
        btnLogin.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        btnLogin.setBackground(primaryRed);
        btnLogin.setForeground(java.awt.Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(primaryRed.darker(), 1),
            javax.swing.BorderFactory.createEmptyBorder(12, 24, 12, 24)));
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        // Advanced button effects
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(new java.awt.Color(220, 38, 38));
                btnLogin.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 68, 68), 2),
                    javax.swing.BorderFactory.createEmptyBorder(11, 23, 11, 23)));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(primaryRed);
                btnLogin.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(primaryRed.darker(), 1),
                    javax.swing.BorderFactory.createEmptyBorder(12, 24, 12, 24)));
            }
        });
    }
    
    private void styleAdvancedInput(javax.swing.JTextField field, String placeholder, java.awt.Color accentColor) {
        field.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        field.setBackground(new java.awt.Color(248, 249, 250));
        field.setForeground(new java.awt.Color(33, 37, 41));
        field.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 213, 219), 1),
            javax.swing.BorderFactory.createEmptyBorder(10, 16, 10, 16)));
        
        // Add focus effects
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                field.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(accentColor, 2),
                    javax.swing.BorderFactory.createEmptyBorder(9, 15, 9, 15)));
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                field.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 213, 219), 1),
                    javax.swing.BorderFactory.createEmptyBorder(10, 16, 10, 16)));
            }
        });
    }
    
    private void initComponents() {
        setTitle("Emergency Supply Tracker - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        
        // Create centered login panel
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setOpaque(false);
        loginPanel.setPreferredSize(new Dimension(500, 400));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Title
        JLabel lblTitle = new JLabel("Emergency Supply Tracker");
        lblTitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 22));
        lblTitle.setForeground(java.awt.Color.WHITE);
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        loginPanel.add(lblTitle, gbc);
        
        // Subtitle
        JLabel lblSubtitle = new JLabel("Login to Access System");
        lblSubtitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        lblSubtitle.setForeground(new java.awt.Color(148, 163, 184));
        lblSubtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 10, 20, 10);
        loginPanel.add(lblSubtitle, gbc);
        
        // User Icon
        JLabel iconLabel = new JLabel();
        try {
            javax.swing.ImageIcon userIcon = new javax.swing.ImageIcon(getClass().getResource("/resources/icons/user.png"));
            java.awt.Image img = userIcon.getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
            iconLabel.setIcon(new javax.swing.ImageIcon(img));
        } catch (Exception e) {
            // Ignore if icon not found
        }
        iconLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        loginPanel.add(iconLabel, gbc);
        
        // Reset insets
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Username
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        lblUsername.setForeground(java.awt.Color.WHITE);
        loginPanel.add(lblUsername, gbc);
        
        txtUsername = new JTextField(18);
        gbc.gridx = 1;
        loginPanel.add(txtUsername, gbc);
        
        // Password
        gbc.gridx = 0; gbc.gridy = 4;
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        lblPassword.setForeground(java.awt.Color.WHITE);
        loginPanel.add(lblPassword, gbc);
        
        txtPassword = new JPasswordField(18);
        gbc.gridx = 1;
        loginPanel.add(txtPassword, gbc);
        
        // Login button
        btnLogin = new JButton("Login");
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        loginPanel.add(btnLogin, gbc);
        
        // Add login panel to center of frame
        add(loginPanel, BorderLayout.CENTER);
        
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        
        // Enter key support
        txtPassword.addActionListener(e -> login());
    }
    
    private void login() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username and password", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        User user = userDAO.authenticateUser(username, password);
        if (user != null) {
            // Open dashboard and close login
            SwingUtilities.invokeLater(() -> {
                new DashBoardPage().setVisible(true);
                dispose();
            });
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            txtPassword.setText("");
            txtUsername.requestFocus();
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginPage().setVisible(true);
        });
    }
}