package dao;

import model.Inventory;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InventoryDAOImpl implements InventoryDAO {
    
  @Override
public List<Inventory> getAllInventory() {
    List<Inventory> inventoryList = new ArrayList<>();
    String sql = "SELECT * FROM donations WHERE quantity > 0 ORDER BY expire_date ASC";
    
    try (Connection conn = DatabaseConnection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        
        while (rs.next()) {
            Inventory inventory = extractInventoryFromResultSet(rs);
            inventoryList.add(inventory);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error retrieving inventory: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    return inventoryList;
}
    
    @Override
    public List<Inventory> getInventoryByCategory(String category) {
        List<Inventory> inventoryList = new ArrayList<>();
        String sql = "SELECT * FROM donations WHERE category = ? AND quantity > 0 ORDER BY expire_date ASC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, category);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Inventory inventory = extractInventoryFromResultSet(rs);
                inventoryList.add(inventory);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving inventory by category: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return inventoryList;
    }
    
    @Override
    public List<Inventory> getInventoryByStatus(String status) {
        List<Inventory> inventoryList = new ArrayList<>();
        String sql = "SELECT * FROM donations WHERE quantity > 0 ORDER BY expire_date ASC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Inventory inventory = extractInventoryFromResultSet(rs);
                if (inventory.getStatus().equals(status)) {
                    inventoryList.add(inventory);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving inventory by status: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return inventoryList;
    }
    
    @Override
    public List<Inventory> getInventoryGroupedBy(String groupBy) {
        return getAllInventory();
    }
    
    @Override
    public List<Inventory> getInventoryTotals() {
        List<Inventory> totalsList = new ArrayList<>();
        String sql = "SELECT item_name, category, unit, SUM(quantity) as total_quantity " +
                    "FROM donations WHERE quantity > 0 " +
                    "GROUP BY item_name, category, unit " +
                    "ORDER BY item_name";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Inventory inventory = new Inventory();
                inventory.setItemName(rs.getString("item_name"));
                inventory.setCategory(rs.getString("category"));
                inventory.setUnit(rs.getString("unit"));
                inventory.setQuantity(rs.getInt("total_quantity"));
                totalsList.add(inventory);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving inventory totals: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return totalsList;
    }
    
    @Override
    public List<Inventory> getCategoryTotals() {
        List<Inventory> totalsList = new ArrayList<>();
        String sql = "SELECT category, unit, SUM(quantity) as total_quantity " +
                    "FROM donations WHERE quantity > 0 " +
                    "GROUP BY category, unit " +
                    "ORDER BY category";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Inventory inventory = new Inventory();
                inventory.setCategory(rs.getString("category"));
                inventory.setUnit(rs.getString("unit"));
                inventory.setQuantity(rs.getInt("total_quantity"));
                totalsList.add(inventory);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving category totals: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return totalsList;
    }
    
    @Override
public void refreshInventoryFromDonations() {
    JOptionPane.showMessageDialog(null, 
        "Inventory is automatically updated from donations!\n" +
        "The inventory view always shows the latest data.", 
        "Info", JOptionPane.INFORMATION_MESSAGE);
}
    
private Inventory extractInventoryFromResultSet(ResultSet rs) throws SQLException {
    Inventory inventory = new Inventory();
    inventory.setDonationId(rs.getInt("donation_id"));
    inventory.setItemName(rs.getString("item_name"));
    inventory.setCategory(rs.getString("category"));
    inventory.setQuantity(rs.getInt("quantity"));
    inventory.setUnit(rs.getString("unit"));
    inventory.setLocation(rs.getString("location"));
    inventory.setExpireDate(rs.getDate("expire_date"));
    inventory.setDonationDate(rs.getTimestamp("created_at"));
    inventory.setDonorInfo(rs.getString("notes"));
    
    // Calculate status based on expire_date
    java.sql.Date expireDate = rs.getDate("expire_date");
    if (expireDate != null) {
        java.util.Date currentDate = new java.util.Date();
        long diff = expireDate.getTime() - currentDate.getTime();
        long daysDiff = diff / (1000 * 60 * 60 * 24);
        
        if (daysDiff < 0) {
            inventory.setStatus("Expired");
        } else if (daysDiff <= 30) {
            inventory.setStatus("Near Expired");
        } else {
            inventory.setStatus("Safe");
        }
    } else {
        inventory.setStatus("Safe");
    }
    
    return inventory;
}
}