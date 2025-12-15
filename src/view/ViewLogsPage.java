/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.ViewLogDAO;
import dao.ViewLogDAOImpl;
import model.ViewLog;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 *
 * @author GABIN-STUDIO
 */
public class ViewLogsPage extends javax.swing.JPanel {
    
    private ViewLogDAO viewLogDAO;
    private DefaultTableModel tableModel;

    /**
     * Creates new form NewJPanel
     */
    public ViewLogsPage() {
        initComponents();
        viewLogDAO = new ViewLogDAOImpl();
        setupTable();
        enhanceAppearance();
        // Load logs lazily
        javax.swing.SwingUtilities.invokeLater(() -> {
            refreshLogs();
        });
    }
    
    private void enhanceAppearance() {
        // Advanced intelligence system colors
        java.awt.Color intelBlue = new java.awt.Color(30, 64, 175);         // Intelligence blue
        java.awt.Color darkBackground = new java.awt.Color(15, 23, 42);     // Command center dark
        java.awt.Color lightPanel = new java.awt.Color(30, 41, 59);         // Panel background
        java.awt.Color textWhite = new java.awt.Color(248, 250, 252);       // Professional text
        java.awt.Color headerBg = new java.awt.Color(51, 65, 85);           // Header background
        
        // Set intelligence center background
        setBackground(darkBackground);
        setBorder(javax.swing.BorderFactory.createTitledBorder(
            javax.swing.BorderFactory.createLineBorder(intelBlue, 2),
            "System Activity Logs",
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.TOP,
            new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16),
            textWhite));
        
        // System title
        jLabel1.setText("System Activity Logs");
        jLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 20));
        jLabel1.setForeground(textWhite);
        
        // Refresh button
        jButton1.setText("Refresh");
        jButton1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        jButton1.setBackground(intelBlue);
        jButton1.setForeground(java.awt.Color.WHITE);
        jButton1.setFocusPainted(false);
        jButton1.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(intelBlue.darker(), 1),
            javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        // Button hover effects
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1.setBackground(intelBlue.brighter());
                jButton1.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(intelBlue.brighter().brighter(), 2),
                    javax.swing.BorderFactory.createEmptyBorder(9, 19, 9, 19)));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1.setBackground(intelBlue);
                jButton1.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(intelBlue.darker(), 1),
                    javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20)));
            }
        });
        
        // Professional table styling
        jTable1.setFont(new java.awt.Font("Consolas", java.awt.Font.PLAIN, 12));
        jTable1.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        jTable1.getTableHeader().setBackground(headerBg);
        jTable1.getTableHeader().setForeground(textWhite);
        jTable1.setRowHeight(24);
        jTable1.setGridColor(new java.awt.Color(71, 85, 105));
        jTable1.setSelectionBackground(new java.awt.Color(30, 64, 175, 80));
        jTable1.setBackground(new java.awt.Color(248, 249, 250));
        jTable1.setForeground(new java.awt.Color(33, 37, 41));
        jTable1.setShowVerticalLines(true);
        jTable1.setShowHorizontalLines(true);
        
        // Style scroll pane
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 212, 218), 1));
        jScrollPane1.getViewport().setBackground(new java.awt.Color(248, 249, 250));
    }
    
    private void setupTable() {
        String[] columns = {"Log ID", "Table", "Record ID", "Action", "Description", "Performed By", "Timestamp"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable1.setModel(tableModel);
    }
    
    private void refreshLogs() {
        try {
            tableModel.setRowCount(0);
            List<ViewLog> logs = viewLogDAO.getAllLogs();
            
            for (ViewLog log : logs) {
                tableModel.addRow(new Object[]{
                    log.getLogId(),
                    log.getTableName(),
                    log.getRecordId(),
                    log.getActionType(),
                    log.getActionDescription(),
                    log.getPerformedBy() != null ? log.getPerformedBy() : "System",
                    log.getPerformedAt()
                });
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error loading logs: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 255));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 255), 1, true));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Log ID", "Table", "Record ID", "Action", "Description", "Performed By", "Timestamp"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel1.setText("System Activity Logs");

        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addContainerGap())
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1297, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        refreshLogs();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
