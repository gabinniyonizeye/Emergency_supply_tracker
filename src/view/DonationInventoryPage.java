/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author GABIN-STUDIO
 */

import controller.DonationController;
import controller.InventoryController;
import model.Donation;
import model.Inventory;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DonationInventoryPage extends javax.swing.JPanel {
     private DonationController donationController;
    private InventoryController inventoryController;
    private DefaultTableModel donationTableModel;
    private DefaultTableModel inventoryTableModel;
    private DefaultTableModel totalTableModel;
    private int selectedDonationId = -1;
    /**
     * Creates new form DonationInventory
     */
    public DonationInventoryPage() {
        initComponents();
        donationController = new DonationController();
        inventoryController = new InventoryController();
        initializeTables();
        setupEventListeners();
        enhanceAppearance();
        // Load data lazily
        javax.swing.SwingUtilities.invokeLater(() -> {
            refreshDonationTable();
            refreshInventoryTable();
        });
    }
    
    private void enhanceAppearance() {
        // Ultra-professional command center colors
        java.awt.Color commandRed = new java.awt.Color(185, 28, 28);        // Critical operations
        java.awt.Color commandGreen = new java.awt.Color(21, 128, 61);      // Success operations
        java.awt.Color commandBlue = new java.awt.Color(30, 64, 175);       // Information systems
        java.awt.Color commandOrange = new java.awt.Color(194, 65, 12);     // Warning systems
        java.awt.Color darkBackground = new java.awt.Color(15, 23, 42);     // Command center dark
        java.awt.Color lightPanel = new java.awt.Color(30, 41, 59);         // Panel background
        java.awt.Color textWhite = new java.awt.Color(248, 250, 252);       // Professional text
        
        // Set command center background
        setBackground(darkBackground);
        panelDonation.setBackground(lightPanel);
        panelInventory.setBackground(lightPanel);
        panelTotalByItem.setBackground(lightPanel);
        
        // Simple panel borders without titles
        panelDonation.setBorder(javax.swing.BorderFactory.createLineBorder(commandBlue, 2));
        panelInventory.setBorder(javax.swing.BorderFactory.createLineBorder(commandGreen, 2));
        
        // Ultra-professional command buttons
        styleCommandButton(btnAdd, commandGreen, "✚ REGISTER SUPPLY");
        styleCommandButton(btnUpdate, commandBlue, "✎ MODIFY RECORD");
        styleCommandButton(btnDeleteDonation, commandRed, "✖ REMOVE ENTRY");
        styleCommandButton(btnRefresh, commandBlue, "⟲ SYNC DATABASE");
        styleCommandButton(btnInventoryDeleteForExpiredOnly, commandOrange, "⚠ PURGE EXPIRED");
        
        // Style input fields
        styleInputField(txtItemName);
        styleInputField(txtQuantity);
        styleInputField(txtLocation);
        
        // Style combo boxes
        styleComboBox(comboBoxUnit);
        styleComboBox(comboBoxCategory);
        styleComboBox(comboBoxSortBy);
        ;
        
        // Professional labels
        styleLabel(labelItemName, "Item Name:", textWhite);
        styleLabel(labelQuantiy, "Quantity:", textWhite);
        styleLabel(labelUnit, "Unit:", textWhite);
        styleLabel(labelCategory, "Category:", textWhite);
        styleLabel(labelExpire, "Expire Date:", textWhite);
        styleLabel(jLabel2, "Location:", textWhite);
        styleLabel(labelSortBy, "Sort by:", textWhite);
      
        
        // Style tables
        styleTable(tableNewDonation);
        styleTable(tableInventory);
        styleTable(tableTotalSummary);
        
        // Style date chooser
        JDateChooserExpireDate.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        JDateChooserExpireDate.setBorder(javax.swing.BorderFactory.createLineBorder(commandBlue, 1));
    }
    
    private void styleCommandButton(javax.swing.JButton button, java.awt.Color color, String text) {
        // Keep original button text, just apply styling
        // button.setText(text);
        button.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(java.awt.Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(color.darker(), 1),
            javax.swing.BorderFactory.createEmptyBorder(8, 16, 8, 16)));
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        // Advanced command center hover effects
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.brighter());
                button.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(color.brighter().brighter(), 2),
                    javax.swing.BorderFactory.createEmptyBorder(7, 15, 7, 15)));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
                button.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(color.darker(), 1),
                    javax.swing.BorderFactory.createEmptyBorder(8, 16, 8, 16)));
            }
        });
    }
    
    private void styleInputField(javax.swing.JTextField field) {
        field.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        field.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 212, 218), 1),
            javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        field.setBackground(new java.awt.Color(248, 249, 250));
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
        table.setRowHeight(25);
        table.setGridColor(new java.awt.Color(222, 226, 230));
        table.setSelectionBackground(new java.awt.Color(0, 123, 255, 50));
        table.setBackground(new java.awt.Color(248, 249, 250));
    }
   private void initializeTables() {
        // Initialize donation table (left side)
        String[] donationColumns = {"ID", "Item Name", "Category", "Unit", "Qty", "Expire Date", "Location"};
        donationTableModel = new DefaultTableModel(donationColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableNewDonation.setModel(donationTableModel);
        
        // Initialize inventory table (right side) - show individual donations
        String[] inventoryColumns = {"ID", "Item Name", "Category", "Unit", "Qty", "Location", "Expire Date", "Status"};
        inventoryTableModel = new DefaultTableModel(inventoryColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableInventory.setModel(inventoryTableModel);
        
        // Initialize total table (bottom right) - show totals only
        String[] totalColumns = {"Item Name", "Category", "Unit", "Total Qty"};
        totalTableModel = new DefaultTableModel(totalColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableTotalSummary.setModel(totalTableModel);
    }
    private void setupEventListeners() {
        // Add mouse listener to donation table for row selection
        tableNewDonation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int selectedRow = tableNewDonation.getSelectedRow();
                if (selectedRow >= 0) {
                    int donationId = (int) donationTableModel.getValueAt(selectedRow, 0);
                    Donation donation = donationController.getDonationById(donationId);
                    if (donation != null) {
                        loadDonationToForm(donation);
                    }
                }
            }
        });
    }

private void refreshDonationTable() {
        donationTableModel.setRowCount(0);
        List<Donation> donations = donationController.getAllDonations();
        
        for (Donation donation : donations) {
            donationTableModel.addRow(new Object[]{
                donation.getDonationId(),
                donation.getItemName(),
                donation.getCategory(),
                donation.getUnit(),
                donation.getQuantity(),
                donation.getExpireDate(),
                donation.getLocation()
            });
        }
    }



private void refreshInventoryTable() {
    inventoryTableModel.setRowCount(0);
    List<Inventory> inventory = inventoryController.getAllInventory();
    
    for (Inventory item : inventory) {
        inventoryTableModel.addRow(new Object[]{
            item.getDonationId(),      // o getDonationId()
            item.getItemName(),
            item.getCategory(),
            item.getUnit(),
            item.getQuantity(),        // Changed back to getQuantity()
            item.getLocation(),        // Changed back to getLocation()
            item.getExpireDate(),      // Changed back to getExpireDate()
            item.getStatus()
        });
    }
    
    // Refresh total summary separately
    refreshTotalSummary();
}

private void refreshTotalSummary() {
    totalTableModel.setRowCount(0);
    
    // Always show totals by item name
    List<Inventory> totals = inventoryController.getInventoryTotals();
    for (Inventory item : totals) {
        totalTableModel.addRow(new Object[]{
            item.getItemName(),
            item.getCategory(),
            item.getUnit(),
            item.getQuantity()
        });
    }
}

private void clearForm() {
        txtItemName.setText("");
        txtQuantity.setText("");
        txtLocation.setText("");
        comboBoxUnit.setSelectedIndex(0);
        comboBoxCategory.setSelectedIndex(0);
        JDateChooserExpireDate.setDate(null);
        selectedDonationId = -1;
    }

private void loadDonationToForm(Donation donation) {
        txtItemName.setText(donation.getItemName());
        txtQuantity.setText(String.valueOf(donation.getQuantity()));
        txtLocation.setText(donation.getLocation());
        
        // Set combo box selections
        comboBoxUnit.setSelectedItem(donation.getUnit());
        comboBoxCategory.setSelectedItem(donation.getCategory());
        
        // Set date
        JDateChooserExpireDate.setDate(donation.getExpireDate());
        
        selectedDonationId = donation.getDonationId();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titledBorder1 = javax.swing.BorderFactory.createTitledBorder("");
        panelDonation = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableNewDonation = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        panelAddNewDonationInsertion = new javax.swing.JPanel();
        labelItemName = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        labelQuantiy = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        labelUnit = new javax.swing.JLabel();
        labelExpire = new javax.swing.JLabel();
        JDateChooserExpireDate = new com.toedter.calendar.JDateChooser();
        labelCategory = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnDeleteDonation = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        comboBoxUnit = new javax.swing.JComboBox<>();
        comboBoxCategory = new javax.swing.JComboBox<>();
        txtLocation = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelInventory = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableInventory = new javax.swing.JTable();
        btnInventoryDeleteForExpiredOnly = new javax.swing.JButton();
        labelSortBy = new javax.swing.JLabel();
        comboBoxSortBy = new javax.swing.JComboBox<>();
        panelTotalByItem = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableTotalSummary = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 255));

        panelDonation.setBackground(new java.awt.Color(204, 204, 255));
        panelDonation.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 24))); // NOI18N
        panelDonation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableNewDonation.setBackground(new java.awt.Color(204, 204, 255));
        tableNewDonation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Name", "Category", "Unit", "Qty", "Expire Date", "Location"
            }
        ));
        tableNewDonation.setSelectionForeground(new java.awt.Color(204, 204, 255));
        tableNewDonation.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tableNewDonation);

        panelDonation.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 600, 470));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        panelDonation.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 34, -1, -1));

        panelAddNewDonationInsertion.setToolTipText("");
        panelAddNewDonationInsertion.setOpaque(false);

        labelItemName.setText("Item Name:");

        txtItemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtItemNameActionPerformed(evt);
            }
        });

        labelQuantiy.setText("quantity:");

        txtQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityActionPerformed(evt);
            }
        });

        labelUnit.setText("Unit:");

        labelExpire.setText("Expire date:");

        labelCategory.setText("category:");

        btnAdd.setBackground(new java.awt.Color(204, 204, 204));
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDeleteDonation.setBackground(new java.awt.Color(204, 204, 204));
        btnDeleteDonation.setText("Delete");
        btnDeleteDonation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteDonationActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(204, 204, 204));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        comboBoxUnit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
            "   ","kg", "g", "sack", "ctn", "L", "mL", "jerrycan", "btl",
            "tab", "pc", "set", "roll", "bundle", "pr", "box", "pkt",
            "RWF", "MM"
        }));

        comboBoxCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
            "     ","Food",
            "WASH",
            "Shelter",
            "Clothing",
            "Medical",
            "Household",
            "Cash",
            "Rescue"
        }
    ));

    txtLocation.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtLocationActionPerformed(evt);
        }
    });

    jLabel2.setText("Location");

    javax.swing.GroupLayout panelAddNewDonationInsertionLayout = new javax.swing.GroupLayout(panelAddNewDonationInsertion);
    panelAddNewDonationInsertion.setLayout(panelAddNewDonationInsertionLayout);
    panelAddNewDonationInsertionLayout.setHorizontalGroup(
        panelAddNewDonationInsertionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelAddNewDonationInsertionLayout.createSequentialGroup()
            .addGroup(panelAddNewDonationInsertionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelAddNewDonationInsertionLayout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addGroup(panelAddNewDonationInsertionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelAddNewDonationInsertionLayout.createSequentialGroup()
                            .addComponent(labelItemName)
                            .addGap(6, 6, 6)
                            .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelAddNewDonationInsertionLayout.createSequentialGroup()
                            .addGroup(panelAddNewDonationInsertionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelExpire)
                                .addComponent(jLabel2))
                            .addGap(6, 6, 6)
                            .addGroup(panelAddNewDonationInsertionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(JDateChooserExpireDate, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(txtLocation))))
                    .addGap(18, 18, 18)
                    .addGroup(panelAddNewDonationInsertionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelQuantiy, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelCategory, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGap(2, 2, 2)
                    .addGroup(panelAddNewDonationInsertionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelAddNewDonationInsertionLayout.createSequentialGroup()
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(labelUnit)
                            .addGap(12, 12, 12)
                            .addComponent(comboBoxUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(comboBoxCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(panelAddNewDonationInsertionLayout.createSequentialGroup()
                    .addGap(280, 280, 280)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnDeleteDonation, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(50, 50, 50))
    );

    panelAddNewDonationInsertionLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAdd, btnDeleteDonation, btnUpdate});

    panelAddNewDonationInsertionLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {comboBoxCategory, txtQuantity});

    panelAddNewDonationInsertionLayout.setVerticalGroup(
        panelAddNewDonationInsertionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelAddNewDonationInsertionLayout.createSequentialGroup()
            .addGroup(panelAddNewDonationInsertionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(labelItemName)
                .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(labelQuantiy)
                .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(labelUnit)
                .addComponent(comboBoxUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(20, 20, 20)
            .addGroup(panelAddNewDonationInsertionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(labelCategory)
                .addComponent(JDateChooserExpireDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(labelExpire)
                .addComponent(comboBoxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelAddNewDonationInsertionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelAddNewDonationInsertionLayout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addGroup(panelAddNewDonationInsertionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDeleteDonation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(panelAddNewDonationInsertionLayout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelAddNewDonationInsertionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))))
    );

    panelAddNewDonationInsertionLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {JDateChooserExpireDate, txtItemName, txtLocation});

    panelAddNewDonationInsertionLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {comboBoxCategory, comboBoxUnit, txtQuantity});

    panelDonation.add(panelAddNewDonationInsertion, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 80, 590, -1));

    jLabel4.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
    jLabel4.setForeground(new java.awt.Color(255, 255, 255));
    jLabel4.setText("Donation Management");
    panelDonation.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

    panelInventory.setBackground(new java.awt.Color(204, 204, 255));
    panelInventory.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 24))); // NOI18N

    jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

    btnRefresh.setBackground(new java.awt.Color(204, 204, 204));
    btnRefresh.setText("Refresh");
    btnRefresh.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRefreshActionPerformed(evt);
        }
    });

    tableInventory.setBackground(new java.awt.Color(204, 204, 255));
    tableInventory.setModel(new javax.swing.table.DefaultTableModel(
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
            {null, null, null, null, null, null, null, null}
        },
        new String [] {
            "ID", "Item Name", "category", "Unit", "Qty", "Expired Date", "Status", "Location"
        }
    ));
    tableInventory.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
    tableInventory.setGridColor(new java.awt.Color(255, 255, 255));
    tableInventory.setInheritsPopupMenu(true);
    jScrollPane2.setViewportView(tableInventory);

    btnInventoryDeleteForExpiredOnly.setBackground(new java.awt.Color(204, 204, 204));
    btnInventoryDeleteForExpiredOnly.setText("Delete");
    btnInventoryDeleteForExpiredOnly.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnInventoryDeleteForExpiredOnlyActionPerformed(evt);
        }
    });

    labelSortBy.setText("Sort by:");

    comboBoxSortBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ","Expire Date","Id","Item Name", "Location" }));
    comboBoxSortBy.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            comboBoxSortByActionPerformed(evt);
        }
    });

    panelTotalByItem.setBackground(new java.awt.Color(204, 204, 204));
    panelTotalByItem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TOTAL", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
    panelTotalByItem.setOpaque(false);

    jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

    tableTotalSummary.setBackground(new java.awt.Color(204, 204, 255));
    tableTotalSummary.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null}
        },
        new String [] {
            "Item Name", "Category", "Unit", "Qty"
        }
    ));
    jScrollPane3.setViewportView(tableTotalSummary);

    javax.swing.GroupLayout panelTotalByItemLayout = new javax.swing.GroupLayout(panelTotalByItem);
    panelTotalByItem.setLayout(panelTotalByItemLayout);
    panelTotalByItemLayout.setHorizontalGroup(
        panelTotalByItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelTotalByItemLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel10)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane3))
    );
    panelTotalByItemLayout.setVerticalGroup(
        panelTotalByItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelTotalByItemLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addContainerGap())
        .addGroup(panelTotalByItemLayout.createSequentialGroup()
            .addGap(22, 22, 22)
            .addComponent(jLabel10)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jLabel3.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
    jLabel3.setForeground(new java.awt.Color(255, 255, 255));
    jLabel3.setText("Inventory Management");

    javax.swing.GroupLayout panelInventoryLayout = new javax.swing.GroupLayout(panelInventory);
    panelInventory.setLayout(panelInventoryLayout);
    panelInventoryLayout.setHorizontalGroup(
        panelInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelInventoryLayout.createSequentialGroup()
            .addGroup(panelInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelInventoryLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelInventoryLayout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(407, 407, 407)
                            .addComponent(btnInventoryDeleteForExpiredOnly)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnRefresh)
                            .addGap(39, 39, 39))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInventoryLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(labelSortBy)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(comboBoxSortBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(43, 43, 43))))
                .addComponent(panelTotalByItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
        .addGroup(panelInventoryLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel3)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    panelInventoryLayout.setVerticalGroup(
        panelInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelInventoryLayout.createSequentialGroup()
            .addGap(6, 6, 6)
            .addComponent(jLabel3)
            .addGroup(panelInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelInventoryLayout.createSequentialGroup()
                    .addGap(4, 4, 4)
                    .addComponent(jLabel8))
                .addGroup(panelInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRefresh)
                    .addComponent(btnInventoryDeleteForExpiredOnly)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(panelTotalByItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(panelInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelSortBy)
                .addComponent(comboBoxSortBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(57, 57, 57))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(panelDonation, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(panelInventory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(25, 25, 25)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(panelDonation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelInventory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
    );
    }// </editor-fold>//GEN-END:initComponents

    private void btnInventoryDeleteForExpiredOnlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventoryDeleteForExpiredOnlyActionPerformed
        // TODO add your handling code here:
        int selectedRow = tableInventory.getSelectedRow();
    if (selectedRow >= 0) {
        int donationId = (int) inventoryTableModel.getValueAt(selectedRow, 0);
        String status = (String) inventoryTableModel.getValueAt(selectedRow, 7);
        String itemName = (String) inventoryTableModel.getValueAt(selectedRow, 1);
        
        if ("Expired".equals(status)) {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Delete this expired item completely?\nItem: " + itemName, 
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                if (donationController.deleteDonation(donationId)) {
                    refreshDonationTable();
                    refreshInventoryTable(); // Refresh both since we deleted completely
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                "You can only delete expired items from this button.", 
                "Invalid Selection", JOptionPane.WARNING_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, 
            "Please select an expired item.", 
            "No Selection", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_btnInventoryDeleteForExpiredOnlyActionPerformed

    private void comboBoxSortByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSortByActionPerformed
        String sortBy = comboBoxSortBy.getSelectedItem().toString().trim();
        inventoryTableModel.setRowCount(0);
        List<Inventory> inventory = inventoryController.getAllInventory();
        
        // Sort based on selection
        if (sortBy.equals("Expire Date")) {
            inventory.sort((a, b) -> {
                if (a.getExpireDate() == null && b.getExpireDate() == null) return 0;
                if (a.getExpireDate() == null) return 1;
                if (b.getExpireDate() == null) return -1;
                return a.getExpireDate().compareTo(b.getExpireDate());
            });
        } else if (sortBy.equals("Id")) {
            inventory.sort((a, b) -> Integer.compare(a.getDonationId(), b.getDonationId()));
        } else if (sortBy.equals("Item Name")) {
            inventory.sort((a, b) -> a.getItemName().compareTo(b.getItemName()));
        } else if (sortBy.equals("Location")) {
            inventory.sort((a, b) -> a.getLocation().compareTo(b.getLocation()));
        }
        
        for (Inventory item : inventory) {
            inventoryTableModel.addRow(new Object[]{
                item.getDonationId(),
                item.getItemName(),
                item.getCategory(),
                item.getUnit(),
                item.getQuantity(),
                item.getLocation(),
                item.getExpireDate(),
                item.getStatus()
            });
        }
    }//GEN-LAST:event_comboBoxSortByActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:

        refreshInventoryTable();

        // Optional: Manually refresh inventory from donations if needed
        // inventoryController.refreshInventoryFromDonations();

        // Data refreshed silently
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void txtLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLocationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLocationActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        if (selectedDonationId == -1) {
            JOptionPane.showMessageDialog(this,
                "Please select a donation to update (click on a row in donations table).",
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String itemName = txtItemName.getText().trim();
            String quantityStr = txtQuantity.getText().trim();
            String unit = comboBoxUnit.getSelectedItem().toString().trim();
            String category = comboBoxCategory.getSelectedItem().toString().trim();
            java.util.Date expireDate = JDateChooserExpireDate.getDate();
            String location = txtLocation.getText().trim();

            // Validate that fields are not empty
            if (unit.equals("   ") || category.equals("     ")) {
                JOptionPane.showMessageDialog(this, "Please select both Unit and Category!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate input using controller
            if (donationController.validateDonation(itemName, quantityStr, expireDate, location)) {
                int quantity = Integer.parseInt(quantityStr);

                Donation donation = new Donation(itemName, category, quantity, unit, expireDate, location, "");
                donation.setDonationId(selectedDonationId);

                if (donationController.updateDonation(donation)) {
                    clearForm();
                    refreshDonationTable();
                    refreshInventoryTable();
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error updating donation: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteDonationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteDonationActionPerformed
        int selectedRow = tableNewDonation.getSelectedRow();
        if (selectedRow >= 0) {
            int donationId = (int) donationTableModel.getValueAt(selectedRow, 0);
            String itemName = (String) donationTableModel.getValueAt(selectedRow, 1);

            int confirm = JOptionPane.showConfirmDialog(this,
                "Remove this donation from donation list?\nItem: " + itemName + "\nIt will remain in inventory.",
                "Confirm Remove", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (donationController.deleteDonationOnly(donationId)) {
                    clearForm();
                    refreshDonationTable();
                    // Don't refresh inventory - it should remain unchanged
                }
            }
        } else {
            JOptionPane.showMessageDialog(this,
                "Please select a donation to remove.",
                "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteDonationActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        try {
            String itemName = txtItemName.getText().trim();
            String quantityStr = txtQuantity.getText().trim();
            String unit = comboBoxUnit.getSelectedItem().toString().trim();
            String category = comboBoxCategory.getSelectedItem().toString().trim();
            java.util.Date expireDate = JDateChooserExpireDate.getDate();
            String location = txtLocation.getText().trim();

            // Validate that fields are not empty
            if (unit.equals("   ") || category.equals("     ")) {
                JOptionPane.showMessageDialog(this, "Please select both Unit and Category!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate input using controller
            if (donationController.validateDonation(itemName, quantityStr, expireDate, location)) {
                int quantity = Integer.parseInt(quantityStr);

                Donation donation = new Donation(itemName, category, quantity, unit, expireDate, location, "");

                if (donationController.addDonation(donation)) {
                    clearForm();
                    refreshDonationTable();
                    refreshInventoryTable();
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding donation: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityActionPerformed

    private void txtItemNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtItemNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtItemNameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser JDateChooserExpireDate;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDeleteDonation;
    private javax.swing.JButton btnInventoryDeleteForExpiredOnly;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnUpdate;
    public javax.swing.JComboBox<String> comboBoxCategory;
    private javax.swing.JComboBox<String> comboBoxSortBy;
    private javax.swing.JComboBox<String> comboBoxUnit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelCategory;
    private javax.swing.JLabel labelExpire;
    private javax.swing.JLabel labelItemName;
    private javax.swing.JLabel labelQuantiy;
    private javax.swing.JLabel labelSortBy;
    private javax.swing.JLabel labelUnit;
    private javax.swing.JPanel panelAddNewDonationInsertion;
    private javax.swing.JPanel panelDonation;
    private javax.swing.JPanel panelInventory;
    private javax.swing.JPanel panelTotalByItem;
    private javax.swing.JTable tableInventory;
    private javax.swing.JTable tableNewDonation;
    private javax.swing.JTable tableTotalSummary;
    private javax.swing.border.TitledBorder titledBorder1;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtLocation;
    private javax.swing.JTextField txtQuantity;
    // End of variables declaration//GEN-END:variables
}
