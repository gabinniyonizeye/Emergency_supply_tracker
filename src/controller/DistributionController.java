// DistributionController.java (in controller package)
package controller;

import model.Distribution;
import model.Donation;
import model.Inventory;
import model.ViewLog;
import dao.DistributionDAO;
import dao.DistributionDAOImpl;
import dao.DonationDAO;
import dao.DonationDAOImpl;
import dao.RequestDAO;
import dao.RequestDAOImpl;
import dao.InventoryDAO;
import dao.InventoryDAOImpl;
import dao.ViewLogDAO;
import dao.ViewLogDAOImpl;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class DistributionController {
    private DistributionDAO distributionDAO;
    private RequestDAO requestDAO;
    private InventoryDAO inventoryDAO;

    public DistributionController() {
        this.distributionDAO = new DistributionDAOImpl();
        this.requestDAO = new RequestDAOImpl();
        this.inventoryDAO = new InventoryDAOImpl();
    }

    public boolean addDistribution(int requestId, String itemName, String unit, 
                                 int quantity, String deliveryLocation, String distributedBy) {
        try {
            // Get request details to use requester location as delivery location
            model.Request request = requestDAO.getRequestById(requestId);
            if (request == null) {
                JOptionPane.showMessageDialog(null, "Request not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            // Use requester location as delivery location
            String actualDeliveryLocation = request.getRequesterLocation();
            
            // Check inventory availability
            if (!checkInventoryAvailability(itemName, unit, quantity)) {
                JOptionPane.showMessageDialog(null, 
                    "Not enough inventory! Available: " + getAvailableInventoryQuantity(itemName, unit) + " " + unit, 
                    "Inventory Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Reduce donation quantities FIRST using FIFO
            if (!reduceDonationQuantitiesFIFO(itemName, unit, quantity)) {
                JOptionPane.showMessageDialog(null, "Failed to reduce inventory quantities", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Create distribution object
            Distribution distribution = new Distribution();
            distribution.setRequestId(requestId);
            distribution.setItemName(itemName);
            distribution.setUnit(unit);
            distribution.setDistributedQuantity(quantity);
            distribution.setDistributionLocation(actualDeliveryLocation); // Use requester location
            distribution.setDistributionDate(new Date());
            distribution.setStatus("In Progress");
            distribution.setDistributedBy(distributedBy);

            boolean success = distributionDAO.addDistribution(distribution);
            
            if (success) {
                // Update request status to In Progress
                request.setStatus("In Progress");
                requestDAO.updateRequest(request);
                
                // Log the distribution action
                ViewLogDAO logDAO = new ViewLogDAOImpl();
                ViewLog log = new ViewLog();
                log.setTableName("distributions");
                log.setRecordId(distribution.getDistributionId());
                log.setActionType("DISTRIBUTE");
                log.setActionDescription("Distributed " + quantity + " " + unit + " of " + itemName + " to " + actualDeliveryLocation);
                log.setOldValues(null);
                log.setNewValues("{\"item_name\": \"" + itemName + "\", \"quantity\": " + quantity + ", \"unit\": \"" + unit + "\", \"location\": \"" + actualDeliveryLocation + "\"}");
                log.setPerformedBy(distributedBy);
                logDAO.logAction(log);
                
                JOptionPane.showMessageDialog(null, 
                    "Distribution created! Delivering " + quantity + " " + unit + " of " + itemName + " to " + actualDeliveryLocation, 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            
            return success;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Error creating distribution: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean reduceDonationQuantitiesFIFO(String itemName, String unit, int requiredQuantity) {
        try {
            // Get available items sorted by expiration (near expired first - FIFO)
            List<Inventory> availableItems = inventoryDAO.getAllInventory();
            List<Inventory> matchingItems = new ArrayList<>();
            
            for (Inventory item : availableItems) {
                if (item.getItemName().equals(itemName) && item.getUnit().equals(unit) && item.getQuantity() > 0) {
                    matchingItems.add(item);
                }
            }
            
            // Sort by expiration date (near expired first - FIFO logic)
            matchingItems.sort((a, b) -> {
                if (a.getExpireDate() == null && b.getExpireDate() == null) return 0;
                if (a.getExpireDate() == null) return 1;
                if (b.getExpireDate() == null) return -1;
                return a.getExpireDate().compareTo(b.getExpireDate());
            });
            
            int remainingQuantity = requiredQuantity;
            DonationDAO donationDAO = new DonationDAOImpl();
            
            for (Inventory item : matchingItems) {
                if (remainingQuantity <= 0) break;
                
                int availableQty = item.getQuantity();
                int quantityToUse = Math.min(availableQty, remainingQuantity);
                
                // Update the donation quantity
                Donation donation = donationDAO.getDonationById(item.getDonationId());
                if (donation != null) {
                    int newQuantity = availableQty - quantityToUse;
                    donation.setQuantity(newQuantity);
                    
                    if (newQuantity == 0) {
                        // If quantity becomes 0, item is completely used up
                        System.out.println("Item completely used: " + itemName + " (ID: " + item.getDonationId() + ")");
                    } else {
                        // Partial quantity used
                        System.out.println("Partial quantity used: " + quantityToUse + " from " + availableQty + " of " + itemName);
                    }
                    
                    donationDAO.updateDonation(donation);
                }
                
                remainingQuantity -= quantityToUse;
            }
            
            if (remainingQuantity > 0) {
                System.out.println("Warning: Could not fulfill complete quantity. Missing: " + remainingQuantity);
                return false;
            }
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean confirmDeliveryByRequestId(int requestId) {
        Distribution distribution = distributionDAO.getDistributionByRequestId(requestId);
        if (distribution != null) {
            boolean success = distributionDAO.updateDistributionStatus(distribution.getDistributionId(), "Delivered");
            if (success) {
                // Update request status to "Delivered"
                model.Request request = requestDAO.getRequestById(requestId);
                if (request != null) {
                    request.setStatus("Delivered");
                    requestDAO.updateRequest(request);
                    
                    // Log delivery confirmation
                    ViewLogDAO logDAO = new ViewLogDAOImpl();
                    ViewLog log = new ViewLog();
                    log.setTableName("distributions");
                    log.setRecordId(distribution.getDistributionId());
                    log.setActionType("DELIVERED");
                    log.setActionDescription("Confirmed delivery of " + distribution.getDistributedQuantity() + " " + 
                                           distribution.getUnit() + " of " + distribution.getItemName() + 
                                           " to " + distribution.getDistributionLocation());
                    log.setOldValues("{\"status\": \"In Progress\"}");
                    log.setNewValues("{\"status\": \"Delivered\"}");
                    log.setPerformedBy("System");
                    logDAO.logAction(log);
                }
            }
            return success;
        }
        return false;
    }

    public boolean deleteDistribution(int distributionId) {
        Distribution distribution = distributionDAO.getDistributionById(distributionId);
        if (distribution != null) {
            // Reset request status to "Pending"
            requestDAO.getRequestById(distribution.getRequestId()).setStatus("Pending");
            requestDAO.updateRequest(requestDAO.getRequestById(distribution.getRequestId()));
        }
        return distributionDAO.deleteDistribution(distributionId);
    }
    
    public Distribution getDistributionByRequestId(int requestId) {
        return distributionDAO.getDistributionByRequestId(requestId);
    }

    public List<Distribution> getAllDistributions() {
        return distributionDAO.getAllDistributions();
    }

    public DefaultTableModel getDistributionsTableModel() {
        String[] columnNames = {"D. ID", "R. ID", "Item Name", "Unit", "Quantity", "Location", "Status", "Distributed By"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Distribution distribution : getAllDistributions()) {
            Object[] rowData = {
                distribution.getDistributionId(),
                distribution.getRequestId(),
                distribution.getItemName(),
                distribution.getUnit(),
                distribution.getDistributedQuantity(),
                distribution.getDistributionLocation(),
                distribution.getStatus(),
                distribution.getDistributedBy()
            };
            model.addRow(rowData);
        }

        return model;
    }
    
    // Check inventory availability using your existing system
    public boolean checkInventoryAvailability(String itemName, String unit, int quantity) {
        List<Inventory> availableItems = inventoryDAO.getAllInventory();
        int totalAvailable = availableItems.stream()
            .filter(item -> item.getItemName().equals(itemName) && item.getUnit().equals(unit))
            .mapToInt(Inventory::getQuantity)
            .sum();
        return totalAvailable >= quantity;
    }
    
    public int getAvailableInventoryQuantity(String itemName, String unit) {
        List<Inventory> availableItems = inventoryDAO.getAllInventory();
        return availableItems.stream()
            .filter(item -> item.getItemName().equals(itemName) && item.getUnit().equals(unit))
            .mapToInt(Inventory::getQuantity)
            .sum();
    }
}