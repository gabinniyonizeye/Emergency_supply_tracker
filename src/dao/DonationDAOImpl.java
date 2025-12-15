package dao;

import model.Donation;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.ViewLog;

public class DonationDAOImpl implements DonationDAO {
    @Override
public boolean addDonation(Donation donation) {
    String sql = "INSERT INTO donations (item_name, category, quantity, unit, expire_date, location, notes) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        
        pstmt.setString(1, donation.getItemName());
        pstmt.setString(2, donation.getCategory());
        pstmt.setInt(3, donation.getQuantity());
        pstmt.setString(4, donation.getUnit());
        pstmt.setDate(5, new java.sql.Date(donation.getExpireDate().getTime()));
        pstmt.setString(6, donation.getLocation());
        pstmt.setString(7, donation.getNotes());
        
        int affectedRows = pstmt.executeUpdate();
        if (affectedRows > 0) {
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int donationId = generatedKeys.getInt(1);
                
                ViewLogDAO logDAO = new ViewLogDAOImpl();
                ViewLog log = new ViewLog();
                log.setTableName("donations");
                log.setRecordId(donationId);
                log.setActionType("INSERT");
                log.setActionDescription("Added new donation: " + donation.getItemName() + " (" + donation.getQuantity() + " " + donation.getUnit() + ")");
                log.setOldValues(null);
                log.setNewValues("{\"item_name\": \"" + donation.getItemName() + "\", \"quantity\": " + donation.getQuantity() + ", \"unit\": \"" + donation.getUnit() + "\"}");
                log.setPerformedBy("System");
                logDAO.logAction(log);
            }
            
            JOptionPane.showMessageDialog(null, "Donation added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error adding donation: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    return false;
}
    
   @Override
public List<Donation> getAllDonations() {
    List<Donation> donations = new ArrayList<>();
    String sql = "SELECT * FROM donations WHERE quantity > 0 AND (notes IS NULL OR notes NOT LIKE '%[REMOVED_FROM_DONATIONS]%') ORDER BY created_at DESC";
    
    try (Connection conn = DatabaseConnection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        
        while (rs.next()) {
            Donation donation = extractDonationFromResultSet(rs);
            donations.add(donation);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error retrieving donations: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    return donations;
}
    
    @Override
public boolean updateDonation(Donation donation) {
    Donation oldDonation = getDonationById(donation.getDonationId());
    
    String sql = "UPDATE donations SET item_name = ?, category = ?, quantity = ?, unit = ?, expire_date = ?, location = ?, notes = ? WHERE donation_id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, donation.getItemName());
        pstmt.setString(2, donation.getCategory());
        pstmt.setInt(3, donation.getQuantity());
        pstmt.setString(4, donation.getUnit());
        pstmt.setDate(5, new java.sql.Date(donation.getExpireDate().getTime()));
        pstmt.setString(6, donation.getLocation());
        pstmt.setString(7, donation.getNotes());
        pstmt.setInt(8, donation.getDonationId());
        
        int affectedRows = pstmt.executeUpdate();
        if (affectedRows > 0) {
            ViewLogDAO logDAO = new ViewLogDAOImpl();
            ViewLog log = new ViewLog();
            log.setTableName("donations");
            log.setRecordId(donation.getDonationId());
            log.setActionType("UPDATE");
            log.setActionDescription("Updated donation: " + donation.getItemName());
            log.setOldValues("{\"item_name\": \"" + oldDonation.getItemName() + "\", \"quantity\": " + oldDonation.getQuantity() + ", \"unit\": \"" + oldDonation.getUnit() + "\"}");
            log.setNewValues("{\"item_name\": \"" + donation.getItemName() + "\", \"quantity\": " + donation.getQuantity() + ", \"unit\": \"" + donation.getUnit() + "\"}");
            log.setPerformedBy("System");
            logDAO.logAction(log);
            
            System.out.println("Donation updated successfully!");
            return true;
        }
    } catch (SQLException e) {
        System.out.println("Error updating donation: " + e.getMessage());
        e.printStackTrace();
    }
    return false;
}
    
   @Override
public boolean deleteDonation(int donationId) {
    String sql = "DELETE FROM donations WHERE donation_id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, donationId);
        
        int affectedRows = pstmt.executeUpdate();
        if (affectedRows > 0) {
            JOptionPane.showMessageDialog(null, "Donation deleted completely!", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error deleting donation: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    return false;
}

@Override
public boolean deleteDonationOnly(int donationId) {
    Donation oldDonation = getDonationById(donationId);
    String sql = "UPDATE donations SET notes = COALESCE(notes, '') || '[REMOVED_FROM_DONATIONS]' WHERE donation_id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, donationId);
        
        int affectedRows = pstmt.executeUpdate();
        if (affectedRows > 0) {
            ViewLogDAO logDAO = new ViewLogDAOImpl();
            ViewLog log = new ViewLog();
            log.setTableName("donations");
            log.setRecordId(donationId);
            log.setActionType("REMOVE_DONATION");
            log.setActionDescription("Removed donation from donation list: " + oldDonation.getItemName() + " (remains in inventory)");
            log.setOldValues("{\"status\": \"active\"}");
            log.setNewValues("{\"status\": \"removed\"}");
            log.setPerformedBy("System");
            logDAO.logAction(log);
            
            JOptionPane.showMessageDialog(null, "Donation removed from donation list!", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error removing donation: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    return false;
}

    @Override
    public Donation getDonationById(int donationId) {
        String sql = "SELECT * FROM donations WHERE donation_id = ?";
        Donation donation = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, donationId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                donation = extractDonationFromResultSet(rs);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving donation: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return donation;
    }
    
    private Donation extractDonationFromResultSet(ResultSet rs) throws SQLException {
        Donation donation = new Donation();
        donation.setDonationId(rs.getInt("donation_id"));
        donation.setItemName(rs.getString("item_name"));
        donation.setCategory(rs.getString("category"));
        donation.setQuantity(rs.getInt("quantity"));
        donation.setUnit(rs.getString("unit"));
        donation.setExpireDate(rs.getDate("expire_date"));
        donation.setLocation(rs.getString("location"));
        donation.setDonationDate(rs.getTimestamp("created_at"));
        donation.setDonorInfo(rs.getString("notes"));
        return donation;
    }
}