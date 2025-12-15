/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.RequestController;
import controller.DistributionController;
import model.Request;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RequestDistributionPage extends javax.swing.JPanel {
 private RequestController requestController;
    private DistributionController distributionController;
    private String currentUser;

    /**
     * Creates new form NewJPanel
     */
    public RequestDistributionPage() {
        initComponents();
        this.currentUser = currentUser;
        
        // Initialize controllers
        this.requestController = new RequestController();
        this.distributionController = new DistributionController();
        
        enhanceAppearance();
        // Initialize UI lazily
        javax.swing.SwingUtilities.invokeLater(() -> {
            initializeUI();
        });
    }
    
    private void enhanceAppearance() {
        // Emergency theme colors
        java.awt.Color emergencyRed = new java.awt.Color(220, 53, 69);
        java.awt.Color emergencyGreen = new java.awt.Color(40, 167, 69);
        java.awt.Color emergencyBlue = new java.awt.Color(0, 123, 255);
        java.awt.Color emergencyOrange = new java.awt.Color(255, 140, 0);
        java.awt.Color lightBackground = new java.awt.Color(248, 249, 250);
        java.awt.Color darkText = new java.awt.Color(33, 37, 41);
        
        // Set tactical operations background
        setBackground(new java.awt.Color(15, 23, 42));
        panelManageRequest.setBackground(new java.awt.Color(30, 41, 59));
        panelViewRequest.setBackground(new java.awt.Color(30, 41, 59));
        panelDistribution.setBackground(new java.awt.Color(30, 41, 59));
        panelRequesterInfo.setBackground(new java.awt.Color(30, 41, 59));
        panelItemDetails.setBackground(new java.awt.Color(30, 41, 59));
        panelManageDistribution.setBackground(new java.awt.Color(30, 41, 59));
        
        // Professional titles
        jLabel1.setText("Manage Request");
        jLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        jLabel1.setForeground(java.awt.Color.WHITE);
        
        jLabel2.setText("View Requests");
        jLabel2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        jLabel2.setForeground(java.awt.Color.WHITE);
        
        jLabel12.setText("Manage Distribution");
        jLabel12.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        jLabel12.setForeground(java.awt.Color.WHITE);
        
        // Advanced tactical operation buttons
        styleTacticalButton(btnAddRequest, new java.awt.Color(21, 128, 61), "✚ INITIATE REQUEST");
        styleTacticalButton(btnUpdateRequest, new java.awt.Color(30, 64, 175), "✎ MODIFY MISSION");
        styleTacticalButton(btnDeleteRequest, new java.awt.Color(185, 28, 28), "✖ ABORT REQUEST");
        styleTacticalButton(btnRefresh, new java.awt.Color(30, 64, 175), "🔄 SYNC OPERATIONS");
        
        // Advanced logistics buttons
        styleTacticalButton(btnAddDistribution, new java.awt.Color(194, 65, 12), "🚁 DEPLOY SUPPLIES");
        styleTacticalButton(btnDeliveryComfirm, new java.awt.Color(21, 128, 61), "✓ CONFIRM DELIVERY");
        styleTacticalButton(btnDeleteDistribution, new java.awt.Color(185, 28, 28), "✖ CANCEL MISSION");
        
        // Style input fields
        styleInputField(txtfullNameRequest, "");
        styleInputField(txtLocationRequest, "");
        styleInputField(txtItemName, "");
        styleInputField(txtQuantity, "");
        
        styleInputField(txtFullNameDistribution, "");
        styleInputField(txtLocationDistribution, "");
        styleInputField(txtItemNameDistribution, "");
        styleInputField(txtQuantityDistribution, "");
        styleInputField(txtUnitDistribution, "");
        
        // Style combo boxes
        styleComboBox(comboBoxUnit);
        styleComboBox(comboBoxRequesterId);
        
        // Professional labels
        styleLabel(jLabel3, "Full Name:", java.awt.Color.WHITE);
        styleLabel(jLabel4, "Location:", java.awt.Color.WHITE);
        styleLabel(jLabel7, "Item Name:", java.awt.Color.WHITE);
        styleLabel(jLabel9, "Quantity:", java.awt.Color.WHITE);
        styleLabel(jLabel5, "Unit:", java.awt.Color.WHITE);
        
        // Style tables
        styleTable(tableRequests);
        styleTable(jTable1);
        
        // Style panels with borders
        panelRequesterInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(
            javax.swing.BorderFactory.createLineBorder(emergencyBlue, 1),
            "Requester Information",
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.TOP,
            new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12),
            emergencyBlue));
            
        panelItemDetails.setBorder(javax.swing.BorderFactory.createTitledBorder(
            javax.swing.BorderFactory.createLineBorder(emergencyOrange, 1),
            "Item Details",
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.TOP,
            new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12),
            emergencyOrange));
    }
    
    private void styleTacticalButton(javax.swing.JButton button, java.awt.Color color, String text) {
        // Keep original button text, just apply styling
        // button.setText(text);
        button.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        button.setBackground(color);
        button.setForeground(java.awt.Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(color.darker(), 1),
            javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        // Professional hover effects
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.brighter());
                button.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(color.brighter().brighter(), 2),
                    javax.swing.BorderFactory.createEmptyBorder(5, 11, 5, 11)));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
                button.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(color.darker(), 1),
                    javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));
            }
        });
    }
    
    private void styleInputField(javax.swing.JTextField field, String placeholder) {
        field.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        field.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 212, 218), 1),
            javax.swing.BorderFactory.createEmptyBorder(4, 8, 4, 8)));
        field.setBackground(new java.awt.Color(248, 249, 250));
        field.setText("");
    }
    
    private void styleComboBox(javax.swing.JComboBox<?> combo) {
        combo.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        combo.setBackground(new java.awt.Color(248, 249, 250));
        combo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 212, 218), 1));
    }
    
    private void styleLabel(javax.swing.JLabel label, String text, java.awt.Color color) {
        label.setText(text);
        label.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        label.setForeground(color);
    }
    
    private void styleTable(javax.swing.JTable table) {
        table.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        table.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        table.getTableHeader().setBackground(new java.awt.Color(233, 236, 239));
        table.getTableHeader().setForeground(new java.awt.Color(33, 37, 41));
        table.setRowHeight(22);
        table.setGridColor(new java.awt.Color(222, 226, 230));
        table.setSelectionBackground(new java.awt.Color(0, 123, 255, 50));
        table.setBackground(new java.awt.Color(248, 249, 250));
    }
private void initializeUI() {
        // Initialize tables
        refreshRequestsTable();
        refreshDistributionsTable();
        updateRequesterIdComboBox();
        
        // Add action listeners
        comboBoxRequesterId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRequesterIdSelected();
            }
        });
        
        // Add table selection listener for requests
        tableRequests.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                onRequestSelected();
            }
        });
    }

    private void onRequesterIdSelected() {
        String selectedId = (String) comboBoxRequesterId.getSelectedItem();
        if (selectedId != null && !selectedId.equals("No pending requests")) {
            try {
                int requestId = Integer.parseInt(selectedId);
                Request request = requestController.getRequestById(requestId);
                if (request != null) {
                    txtFullNameDistribution.setText(request.getFullName());
                    txtLocationDistribution.setText(request.getLocation());
                    txtItemNameDistribution.setText(request.getItemName());
                    txtQuantityDistribution.setText(String.valueOf(request.getQuantity()));
                    txtUnitDistribution.setText(request.getUnit());
                }
            } catch (NumberFormatException ex) {
                // Ignore for placeholder items
            }
        }
    }
    
    private void onRequestSelected() {
        int selectedRow = tableRequests.getSelectedRow();
        if (selectedRow != -1) {
            try {
                int requestId = (int) tableRequests.getValueAt(selectedRow, 0);
                Request request = requestController.getRequestById(requestId);
                if (request != null) {
                    // Populate the request fields for editing
                    txtfullNameRequest.setText(request.getFullName());
                    txtLocationRequest.setText(request.getLocation());
                    txtItemName.setText(request.getItemName());
                    txtQuantity.setText(String.valueOf(request.getQuantity()));
                    
                    // Set unit in combo box
                    for (int i = 0; i < comboBoxUnit.getItemCount(); i++) {
                        if (comboBoxUnit.getItemAt(i).equals(request.getUnit())) {
                            comboBoxUnit.setSelectedIndex(i);
                            break;
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void refreshRequestsTable() {
        tableRequests.setModel(requestController.getRequestsTableModel());
    }

    private void refreshDistributionsTable() {
        jTable1.setModel(distributionController.getDistributionsTableModel());
    }

    private void updateRequesterIdComboBox() {
        comboBoxRequesterId.removeAllItems();
        
        // Add pending requests to the combo box
        for (Request request : requestController.getPendingRequests()) {
            comboBoxRequesterId.addItem(String.valueOf(request.getRequestId()));
        }
        
        // If no items, add a placeholder
        if (comboBoxRequesterId.getItemCount() == 0) {
            comboBoxRequesterId.addItem("No pending requests");
        }
    }
 private void clearRequestFields() {
        txtfullNameRequest.setText("");
        txtLocationRequest.setText("");
        txtItemName.setText("");
        txtQuantity.setText("");
        comboBoxUnit.setSelectedIndex(0);
    }

    private void clearDistributionFields() {
        txtFullNameDistribution.setText("");
        txtLocationDistribution.setText("");
        txtItemNameDistribution.setText("");
        txtQuantityDistribution.setText("");
        txtUnitDistribution.setText("");
        if (comboBoxRequesterId.getItemCount() > 0) {
            comboBoxRequesterId.setSelectedIndex(0);
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

        panelManageRequest = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelRequesterInfo = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtfullNameRequest = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtLocationRequest = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        panelItemDetails = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comboBoxUnit = new javax.swing.JComboBox<>();
        btnAddRequest = new javax.swing.JButton();
        btnUpdateRequest = new javax.swing.JButton();
        btnDeleteRequest = new javax.swing.JButton();
        panelViewRequest = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableRequests = new javax.swing.JTable();
        btnRefresh = new javax.swing.JButton();
        panelDistribution = new javax.swing.JPanel();
        panelManageDistribution = new javax.swing.JPanel();
        comboBoxRequesterId = new javax.swing.JComboBox<>();
        txtFullNameDistribution = new javax.swing.JTextField();
        txtLocationDistribution = new javax.swing.JTextField();
        txtItemNameDistribution = new javax.swing.JTextField();
        txtQuantityDistribution = new javax.swing.JTextField();
        txtUnitDistribution = new javax.swing.JTextField();
        btnAddDistribution = new javax.swing.JButton();
        btnDeliveryComfirm = new javax.swing.JButton();
        btnDeleteDistribution = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 255));

        panelManageRequest.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelManageRequest.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Manage Request");

        panelRequesterInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RequsterInfo", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("SansSerif", 1, 14))); // NOI18N
        panelRequesterInfo.setOpaque(false);

        jLabel3.setText("Full Name:");

        txtfullNameRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfullNameRequestActionPerformed(evt);
            }
        });

        jLabel4.setText("Location:");

        txtLocationRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLocationRequestActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        javax.swing.GroupLayout panelRequesterInfoLayout = new javax.swing.GroupLayout(panelRequesterInfo);
        panelRequesterInfo.setLayout(panelRequesterInfoLayout);
        panelRequesterInfoLayout.setHorizontalGroup(
            panelRequesterInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRequesterInfoLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(panelRequesterInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(37, 37, 37)
                .addGroup(panelRequesterInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLocationRequest, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfullNameRequest, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
            .addGroup(panelRequesterInfoLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRequesterInfoLayout.setVerticalGroup(
            panelRequesterInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRequesterInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(panelRequesterInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtfullNameRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(panelRequesterInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtLocationRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(46, 46, 46))
        );

        panelItemDetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item Details", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("SansSerif", 1, 14))); // NOI18N
        panelItemDetails.setOpaque(false);

        jLabel7.setText("Item Name:");

        txtItemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtItemNameActionPerformed(evt);
            }
        });

        jLabel9.setText("Quantity:");

        txtQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        jLabel5.setText("Unit:");

        comboBoxUnit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
            "   ","kg", "g", "sack", "ctn", "L", "mL", "jerrycan", "btl",
            "tab", "pc", "set", "roll", "bundle", "pr", "box", "pkt",
            "RWF", "MM"
        }));

        javax.swing.GroupLayout panelItemDetailsLayout = new javax.swing.GroupLayout(panelItemDetails);
        panelItemDetails.setLayout(panelItemDetailsLayout);
        panelItemDetailsLayout.setHorizontalGroup(
            panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItemDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addGroup(panelItemDetailsLayout.createSequentialGroup()
                        .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtItemName, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                            .addComponent(txtQuantity))))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        panelItemDetailsLayout.setVerticalGroup(
            panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItemDetailsLayout.createSequentialGroup()
                .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelItemDetailsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10)
                        .addGap(30, 30, 30)
                        .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(panelItemDetailsLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(comboBoxUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGap(18, 18, 18)
                .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel9)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAddRequest.setText("Add");
        btnAddRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRequestActionPerformed(evt);
            }
        });

        btnUpdateRequest.setText("Update");
        btnUpdateRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateRequestActionPerformed(evt);
            }
        });

        btnDeleteRequest.setText("Delete");
        btnDeleteRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteRequestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelManageRequestLayout = new javax.swing.GroupLayout(panelManageRequest);
        panelManageRequest.setLayout(panelManageRequestLayout);
        panelManageRequestLayout.setHorizontalGroup(
            panelManageRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManageRequestLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelManageRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelManageRequestLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelManageRequestLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(panelManageRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelManageRequestLayout.createSequentialGroup()
                                .addComponent(panelRequesterInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(panelItemDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelManageRequestLayout.createSequentialGroup()
                                .addGap(174, 174, 174)
                                .addComponent(btnAddRequest)
                                .addGap(6, 6, 6)
                                .addComponent(btnUpdateRequest)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeleteRequest)))
                        .addGap(23, 23, 23))))
        );
        panelManageRequestLayout.setVerticalGroup(
            panelManageRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManageRequestLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelManageRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelItemDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRequesterInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelManageRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddRequest)
                    .addComponent(btnUpdateRequest)
                    .addComponent(btnDeleteRequest))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        panelViewRequest.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelViewRequest.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelViewRequest.setFocusCycleRoot(true);
        panelViewRequest.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("View Requests");

        tableRequests.setBackground(new java.awt.Color(204, 204, 255));
        tableRequests.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "D. ID", "R. ID", "Item Name", "Unit", "Quantity", "Location", "Status", "Distributed By"
            }
        ));
        tableRequests.setGridColor(new java.awt.Color(255, 255, 255));
        tableRequests.setOpaque(false);
        jScrollPane1.setViewportView(tableRequests);
        if (tableRequests.getColumnModel().getColumnCount() > 0) {
            tableRequests.getColumnModel().getColumn(3).setResizable(false);
        }

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelViewRequestLayout = new javax.swing.GroupLayout(panelViewRequest);
        panelViewRequest.setLayout(panelViewRequestLayout);
        panelViewRequestLayout.setHorizontalGroup(
            panelViewRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewRequestLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelViewRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelViewRequestLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRefresh)))
                .addContainerGap())
        );
        panelViewRequestLayout.setVerticalGroup(
            panelViewRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewRequestLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelViewRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnRefresh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelDistribution.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelDistribution.setOpaque(false);

        panelManageDistribution.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelManageDistribution.setOpaque(false);
        panelManageDistribution.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comboBoxRequesterId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select your request ID" }));
        comboBoxRequesterId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxRequesterIdActionPerformed(evt);
            }
        });
        panelManageDistribution.add(comboBoxRequesterId, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 28, 77, -1));

        txtFullNameDistribution.setText("Full Name");
        panelManageDistribution.add(txtFullNameDistribution, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 63, 133, -1));

        txtLocationDistribution.setText("Location");
        txtLocationDistribution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLocationDistributionActionPerformed(evt);
            }
        });
        panelManageDistribution.add(txtLocationDistribution, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 63, 129, -1));

        txtItemNameDistribution.setText("Item Name");
        txtItemNameDistribution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtItemNameDistributionActionPerformed(evt);
            }
        });
        panelManageDistribution.add(txtItemNameDistribution, new org.netbeans.lib.awtextra.AbsoluteConstraints(337, 63, 109, -1));

        txtQuantityDistribution.setText("Quantiy");
        panelManageDistribution.add(txtQuantityDistribution, new org.netbeans.lib.awtextra.AbsoluteConstraints(453, 63, 74, -1));

        txtUnitDistribution.setText("Unit");
        txtUnitDistribution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnitDistributionActionPerformed(evt);
            }
        });
        panelManageDistribution.add(txtUnitDistribution, new org.netbeans.lib.awtextra.AbsoluteConstraints(534, 63, 64, -1));

        btnAddDistribution.setText("Add");
        btnAddDistribution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDistributionActionPerformed(evt);
            }
        });
        panelManageDistribution.add(btnAddDistribution, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, -1, -1));

        btnDeliveryComfirm.setText("Distributed");
        btnDeliveryComfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeliveryComfirmActionPerformed(evt);
            }
        });
        panelManageDistribution.add(btnDeliveryComfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, -1, -1));

        btnDeleteDistribution.setText("Delete");
        btnDeleteDistribution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteDistributionActionPerformed(evt);
            }
        });
        panelManageDistribution.add(btnDeleteDistribution, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, -1, -1));

        jTable1.setBackground(new java.awt.Color(204, 204, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "D. ID", "R. ID", "Item Name", "Unit", "Quantity", "Location", "Status"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setOpaque(false);
        jScrollPane2.setViewportView(jTable1);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("Manage Distribution");

        javax.swing.GroupLayout panelDistributionLayout = new javax.swing.GroupLayout(panelDistribution);
        panelDistribution.setLayout(panelDistributionLayout);
        panelDistributionLayout.setHorizontalGroup(
            panelDistributionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDistributionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDistributionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDistributionLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panelManageDistribution, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        panelDistributionLayout.setVerticalGroup(
            panelDistributionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDistributionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelManageDistribution, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelManageRequest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelViewRequest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelDistribution, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelDistribution, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelManageRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(panelViewRequest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtItemNameDistributionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtItemNameDistributionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtItemNameDistributionActionPerformed

    private void txtUnitDistributionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnitDistributionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUnitDistributionActionPerformed

    private void btnAddDistributionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDistributionActionPerformed
        // TODO add your handling code here:
         try {
            String selectedId = (String) comboBoxRequesterId.getSelectedItem();
            if (selectedId == null || selectedId.equals("No pending requests")) {
                JOptionPane.showMessageDialog(this, "No pending requests available", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int requestId = Integer.parseInt(selectedId);
            String itemName = txtItemNameDistribution.getText().trim();
            String unit = txtUnitDistribution.getText().trim();
            String quantityText = txtQuantityDistribution.getText().trim();
            String location = txtLocationDistribution.getText().trim();

            if (itemName.isEmpty() || unit.isEmpty() || quantityText.isEmpty() || location.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all distribution fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int quantity = Integer.parseInt(quantityText);
            
            boolean success = distributionController.addDistribution(requestId, itemName, unit, quantity, location, currentUser);
            
            if (success) {
                refreshRequestsTable();
                refreshDistributionsTable();
                updateRequesterIdComboBox();
                clearDistributionFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add distribution", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAddDistributionActionPerformed

    private void btnDeliveryComfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeliveryComfirmActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a distribution to confirm", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int requestId = (int) jTable1.getValueAt(selectedRow, 1); // Request ID is in column 1
        
        boolean success = distributionController.confirmDeliveryByRequestId(requestId);
        
        if (success) {
            refreshRequestsTable();
            refreshDistributionsTable();
            updateRequesterIdComboBox();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to confirm delivery", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDeliveryComfirmActionPerformed

    private void comboBoxRequesterIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxRequesterIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxRequesterIdActionPerformed

    private void txtLocationDistributionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLocationDistributionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLocationDistributionActionPerformed

    private void btnDeleteRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteRequestActionPerformed
        // TODO add your handling code here:
        int selectedRow = tableRequests.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a request to delete", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int requestId = (int) tableRequests.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete this request?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = requestController.deleteRequest(requestId);

            if (success) {
                clearRequestFields();
                refreshRequestsTable();
                refreshDistributionsTable();
                updateRequesterIdComboBox();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete request", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDeleteRequestActionPerformed

    private void btnUpdateRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateRequestActionPerformed
        // TODO add your handling code here:
        int selectedRow = tableRequests.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a request to update", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int requestId = (int) tableRequests.getValueAt(selectedRow, 0);
            String fullName = txtfullNameRequest.getText().trim();
            String location = txtLocationRequest.getText().trim();
            String itemName = txtItemName.getText().trim();
            String unit = (String) comboBoxUnit.getSelectedItem();
            int quantity = Integer.parseInt(txtQuantity.getText().trim());

            // Using existing priority and contact info
            Request existingRequest = requestController.getRequestById(requestId);
            String priority = existingRequest != null ? existingRequest.getPriority() : "Medium";
            String contactInfo = existingRequest != null ? existingRequest.getContactInfo() : "";

            boolean success = requestController.updateRequest(requestId, fullName, location, itemName, unit, quantity, priority, contactInfo);

            if (success) {
                refreshRequestsTable();
                refreshDistributionsTable();
                updateRequesterIdComboBox();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update request", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateRequestActionPerformed

    private void btnAddRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRequestActionPerformed
        // TODO add your handling code here:
        try {
            String fullName = txtfullNameRequest.getText().trim();
            String location = txtLocationRequest.getText().trim();
            String itemName = txtItemName.getText().trim();
            String unit = (String) comboBoxUnit.getSelectedItem();
            String quantityText = txtQuantity.getText().trim();

            // Validation
            if (fullName.isEmpty() || location.isEmpty() || itemName.isEmpty() || quantityText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int quantity = Integer.parseInt(quantityText);
            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be positive", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Using "Medium" as default priority and empty contact info for now
            boolean success = requestController.addRequest(fullName, location, itemName, unit, quantity, "Medium", "");

            if (success) {
                clearRequestFields();
                refreshRequestsTable();
                updateRequesterIdComboBox();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add request", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAddRequestActionPerformed

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityActionPerformed

    private void txtItemNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtItemNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtItemNameActionPerformed

    private void txtLocationRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLocationRequestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLocationRequestActionPerformed

    private void txtfullNameRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfullNameRequestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfullNameRequestActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        refreshRequestsTable();
        refreshDistributionsTable();
        updateRequesterIdComboBox();
        // Data refreshed silently
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnDeleteDistributionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteDistributionActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a distribution to delete", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int distributionId = (int) jTable1.getValueAt(selectedRow, 0); // Distribution ID is in column 0
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete this distribution?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = distributionController.deleteDistribution(distributionId);

            if (success) {
                refreshRequestsTable();
                refreshDistributionsTable();
                updateRequesterIdComboBox();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete distribution", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_btnDeleteDistributionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddDistribution;
    private javax.swing.JButton btnAddRequest;
    private javax.swing.JButton btnDeleteDistribution;
    private javax.swing.JButton btnDeleteRequest;
    private javax.swing.JButton btnDeliveryComfirm;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnUpdateRequest;
    private javax.swing.JComboBox<String> comboBoxRequesterId;
    private javax.swing.JComboBox<String> comboBoxUnit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel panelDistribution;
    private javax.swing.JPanel panelItemDetails;
    private javax.swing.JPanel panelManageDistribution;
    private javax.swing.JPanel panelManageRequest;
    private javax.swing.JPanel panelRequesterInfo;
    private javax.swing.JPanel panelViewRequest;
    private javax.swing.JTable tableRequests;
    private javax.swing.JTextField txtFullNameDistribution;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtItemNameDistribution;
    private javax.swing.JTextField txtLocationDistribution;
    private javax.swing.JTextField txtLocationRequest;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtQuantityDistribution;
    private javax.swing.JTextField txtUnitDistribution;
    private javax.swing.JTextField txtfullNameRequest;
    // End of variables declaration//GEN-END:variables
}
