package dao;

import model.Inventory;
import java.util.List;

public interface InventoryDAO {
    List<Inventory> getAllInventory();
    List<Inventory> getInventoryByCategory(String category);
    List<Inventory> getInventoryByStatus(String status);
    List<Inventory> getInventoryGroupedBy(String groupBy);
    List<Inventory> getInventoryTotals();
    List<Inventory> getCategoryTotals();
    
    // New method to manually refresh inventory from donations
    void refreshInventoryFromDonations();
}