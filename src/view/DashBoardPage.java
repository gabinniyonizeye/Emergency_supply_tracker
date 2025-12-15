/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import view.*;
import model.*;
import dao.*;

public class DashBoardPage extends javax.swing.JFrame {

    /**
     * Creates new form DashBoardPage
     */
    private void loadPage(javax.swing.JPanel pagePanel) {
    jScrollPaneMain.setViewportView(pagePanel);
}
private void loadPageIntoDashboardPanel(javax.swing.JPanel page) {
    jScrollPaneMain.setViewportView(page);
    jScrollPaneMain.revalidate();
    jScrollPaneMain.repaint();
}


    public DashBoardPage() {
        initComponents();
        enhanceAppearance();
        // Load home page lazily on first access
        javax.swing.SwingUtilities.invokeLater(() -> {
            loadPageIntoDashboardPanel(new HomePage());
        });
    }
    
    private void enhanceAppearance() {
        // Set window properties
        setTitle("Emergency Supply Tracker");
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        
        // Advanced professional colors
        java.awt.Color primaryBlue = new java.awt.Color(30, 64, 175);       // Supply management
        java.awt.Color primaryGreen = new java.awt.Color(21, 128, 61);      // Distribution ops
        java.awt.Color primaryOrange = new java.awt.Color(194, 65, 12);     // Intelligence reports
        java.awt.Color primaryRed = new java.awt.Color(185, 28, 28);        // System logout
        java.awt.Color darkBg = new java.awt.Color(15, 23, 42);             // Professional dark
        
        // Set professional background
        getContentPane().setBackground(darkBg);
        jPanel2.setBackground(darkBg);
        
        // Professional navigation buttons
        btnHome.setText("Home");
        try {
            javax.swing.ImageIcon homeIcon = new javax.swing.ImageIcon(getClass().getResource("/resources/icons/home-button.png"));
            java.awt.Image img = homeIcon.getImage().getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
            btnHome.setIcon(new javax.swing.ImageIcon(img));
        } catch (Exception e) {
            // Ignore if icon not found
        }
        btnHome.setFont(new java.awt.Font("Calibri", java.awt.Font.BOLD, 16));
        btnHome.setBackground(new java.awt.Color(67, 56, 202));
        btnHome.setForeground(java.awt.Color.WHITE);
        btnHome.setFocusPainted(false);
        btnHome.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(67, 56, 202).darker(), 1),
            javax.swing.BorderFactory.createEmptyBorder(15, 20, 15, 20)));
        btnHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        styleAdvancedButton(btnManageDonationInventory, primaryBlue, 
            "Manage Donations & Inventory", "Add, update, and track supplies");
        styleAdvancedButton(btnManageRequestDistribution, primaryGreen, 
            "Manage Requests & Distribution", "Handle supply requests and distributions");
        styleAdvancedButton(btnViewReport, primaryOrange, 
            "View Reports", "System logs and analytics");
        styleAdvancedButton(btnLogout, primaryRed, 
            "Logout", "Exit application");
        
        // Header
        jLabel1.setText("Emergency Supply Tracker");
        jLabel1.setFont(new java.awt.Font("Calibri", java.awt.Font.BOLD, 40));
        jLabel1.setForeground(java.awt.Color.WHITE);
    }
    
    private void styleAdvancedButton(javax.swing.JButton button, java.awt.Color color, String title, String subtitle) {
        button.setText("<html><div style='text-align: center;'><b>" + title + "</b><br><small>" + subtitle + "</small></div></html>");
        button.setFont(new java.awt.Font("Calibri", java.awt.Font.BOLD, 16));
        button.setBackground(color);
        button.setForeground(java.awt.Color.WHITE);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBorderPainted(true);
        button.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(color.darker(), 1),
            javax.swing.BorderFactory.createEmptyBorder(15, 20, 15, 20)));
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        // Advanced hover effects with glow
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.brighter());
                button.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(color.brighter().brighter(), 2),
                    javax.swing.BorderFactory.createEmptyBorder(14, 19, 14, 19)));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
                button.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(color.darker(), 1),
                    javax.swing.BorderFactory.createEmptyBorder(15, 20, 15, 20)));
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPaneMain = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panelControler = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnManageDonationInventory = new javax.swing.JButton();
        btnManageRequestDistribution = new javax.swing.JButton();
        btnViewReport = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(51, 51, 51));

        jScrollPaneMain.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPaneMain.setForeground(new java.awt.Color(204, 204, 255));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1669, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
        );

        jScrollPaneMain.setViewportView(jPanel1);

        panelControler.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelControler.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        btnManageDonationInventory.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnManageDonationInventory.setText("Manage Donations & Inventory");
        btnManageDonationInventory.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnManageDonationInventory.setBorderPainted(false);
        btnManageDonationInventory.setDebugGraphicsOptions(javax.swing.DebugGraphics.LOG_OPTION);
        btnManageDonationInventory.setHideActionText(true);
        btnManageDonationInventory.setOpaque(false);
        btnManageDonationInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageDonationInventoryActionPerformed(evt);
            }
        });

        btnManageRequestDistribution.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnManageRequestDistribution.setText("Manage Requets & Distribution");
        btnManageRequestDistribution.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnManageRequestDistribution.setBorderPainted(false);
        btnManageRequestDistribution.setDebugGraphicsOptions(javax.swing.DebugGraphics.LOG_OPTION);
        btnManageRequestDistribution.setHideActionText(true);
        btnManageRequestDistribution.setOpaque(false);
        btnManageRequestDistribution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageRequestDistributionActionPerformed(evt);
            }
        });

        btnViewReport.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnViewReport.setText("View Reports");
        btnViewReport.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnViewReport.setBorderPainted(false);
        btnViewReport.setDebugGraphicsOptions(javax.swing.DebugGraphics.LOG_OPTION);
        btnViewReport.setHideActionText(true);
        btnViewReport.setOpaque(false);
        btnViewReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewReportActionPerformed(evt);
            }
        });

        btnLogout.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnHome.setText("HOME");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(658, 658, 658))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnManageDonationInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnManageRequestDistribution, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnViewReport, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnLogout)
                        .addGap(112, 112, 112))))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnManageDonationInventory, btnManageRequestDistribution, btnViewReport});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btnViewReport)
                            .addComponent(btnManageRequestDistribution)
                            .addComponent(btnManageDonationInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLogout)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnHome, btnManageDonationInventory, btnManageRequestDistribution, btnViewReport});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(842, 842, 842)
                .addComponent(panelControler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPaneMain)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneMain, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelControler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnManageDonationInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageDonationInventoryActionPerformed
        // TODO add your handling code here:
        loadPageIntoDashboardPanel(new DonationInventoryPage());
    }//GEN-LAST:event_btnManageDonationInventoryActionPerformed

    private void btnViewReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewReportActionPerformed
        // TODO add your handling code here:
        loadPageIntoDashboardPanel(new ViewLogsPage());
         // temporary placeholder
    // when ViewReportPage is created:
    // loadPage(new ViewReportPage());
    }//GEN-LAST:event_btnViewReportActionPerformed

    private void btnManageRequestDistributionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageRequestDistributionActionPerformed
        // TODO add your handling code here:
        loadPageIntoDashboardPanel(new RequestDistributionPage());
    }//GEN-LAST:event_btnManageRequestDistributionActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // Return to login page
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginPage().setVisible(true);
            dispose();
        });
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        loadPageIntoDashboardPanel(new HomePage());
    }//GEN-LAST:event_btnHomeActionPerformed

    // Main method removed - Dashboard can only be accessed through login

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnManageDonationInventory;
    private javax.swing.JButton btnManageRequestDistribution;
    private javax.swing.JButton btnViewReport;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPaneMain;
    private javax.swing.JPanel panelControler;
    // End of variables declaration//GEN-END:variables
}
