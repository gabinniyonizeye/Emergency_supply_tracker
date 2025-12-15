// DistributionDAOImpl.java (in dao package)
package dao;

import model.Distribution;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DistributionDAOImpl implements DistributionDAO {
    
    @Override
    public boolean addDistribution(Distribution distribution) {
        String sql = "INSERT INTO distributions (request_id, item_name, unit, distributed_quantity, distribution_location, distribution_date, status, distributed_by) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, distribution.getRequestId());
            pstmt.setString(2, distribution.getItemName());
            pstmt.setString(3, distribution.getUnit());
            pstmt.setInt(4, distribution.getDistributedQuantity());
            pstmt.setString(5, distribution.getDistributionLocation());
            pstmt.setTimestamp(6, new Timestamp(distribution.getDistributionDate().getTime()));
            pstmt.setString(7, distribution.getStatus());
            pstmt.setString(8, distribution.getDistributedBy());
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                // Get the generated distribution ID
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    distribution.setDistributionId(generatedKeys.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error adding distribution: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public List<Distribution> getAllDistributions() {
        List<Distribution> distributions = new ArrayList<>();
        String sql = "SELECT * FROM distributions ORDER BY distribution_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Distribution distribution = extractDistributionFromResultSet(rs);
                distributions.add(distribution);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving distributions: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return distributions;
    }
    
    @Override
    public boolean updateDistributionStatus(int distributionId, String status) {
        String sql = "UPDATE distributions SET status = ? WHERE distribution_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, status);
            pstmt.setInt(2, distributionId);
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Distribution status updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating distribution status: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean deleteDistribution(int distributionId) {
        String sql = "DELETE FROM distributions WHERE distribution_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, distributionId);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Distribution deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting distribution: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public Distribution getDistributionByRequestId(int requestId) {
        String sql = "SELECT * FROM distributions WHERE request_id = ?";
        Distribution distribution = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, requestId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                distribution = extractDistributionFromResultSet(rs);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving distribution: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return distribution;
    }
    
    @Override
    public Distribution getDistributionById(int distributionId) {
        String sql = "SELECT * FROM distributions WHERE distribution_id = ?";
        Distribution distribution = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, distributionId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                distribution = extractDistributionFromResultSet(rs);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving distribution: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return distribution;
    }
    
    private Distribution extractDistributionFromResultSet(ResultSet rs) throws SQLException {
        Distribution distribution = new Distribution();
        distribution.setDistributionId(rs.getInt("distribution_id"));
        distribution.setRequestId(rs.getInt("request_id"));
        distribution.setItemName(rs.getString("item_name"));
        distribution.setUnit(rs.getString("unit"));
        distribution.setDistributedQuantity(rs.getInt("distributed_quantity"));
        distribution.setDistributionLocation(rs.getString("distribution_location"));
        distribution.setDistributionDate(rs.getTimestamp("distribution_date"));
        distribution.setStatus(rs.getString("status"));
        distribution.setDistributedBy(rs.getString("distributed_by"));
        return distribution;
    }
}