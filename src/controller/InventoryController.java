package controller;

import dao.InventoryDAO;
import dao.InventoryDAOImpl;
import model.Inventory;
import java.util.List;

public class InventoryController {
    private InventoryDAO inventoryDAO;
    
    public InventoryController() {
        this.inventoryDAO = new InventoryDAOImpl();
    }
    
    public List<Inventory> getAllInventory() {
        return inventoryDAO.getAllInventory();
    }
    
    public List<Inventory> getInventoryByCategory(String category) {
        return inventoryDAO.getInventoryByCategory(category);
    }
    
    public List<Inventory> getInventoryByStatus(String status) {
        return inventoryDAO.getInventoryByStatus(status);
    }
    
    public List<Inventory> getInventoryGroupedBy(String groupBy) {
        return inventoryDAO.getInventoryGroupedBy(groupBy);
    }
    
    public List<Inventory> getInventoryTotals() {
        return inventoryDAO.getInventoryTotals();
    }
    
    public List<Inventory> getCategoryTotals() {
        return inventoryDAO.getCategoryTotals();
    }
    
    // New method to manually refresh inventory
    public void refreshInventoryFromDonations() {
        inventoryDAO.refreshInventoryFromDonations();
    }
}