package dao;

import model.Request;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class RequestDAOImpl implements RequestDAO {
    
    @Override
    public boolean addRequest(Request request) {
        String sql = "INSERT INTO requests (requester_full_name, requester_location, item_name, unit, quantity, request_date, status, priority, contact_info) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, request.getRequesterFullName());
            pstmt.setString(2, request.getRequesterLocation());
            pstmt.setString(3, request.getItemName());
            pstmt.setString(4, request.getUnit());
            pstmt.setInt(5, request.getQuantity());
            pstmt.setTimestamp(6, new Timestamp(request.getRequestDate().getTime()));
            pstmt.setString(7, request.getStatus());
            pstmt.setString(8, request.getPriority());
            pstmt.setString(9, request.getContactInfo());
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Request added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error adding request: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public List<Request> getAllRequests() {
        List<Request> requests = new ArrayList<>();
        String sql = "SELECT * FROM requests ORDER BY request_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Request request = extractRequestFromResultSet(rs);
                requests.add(request);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving requests: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return requests;
    }
    
    @Override
    public boolean updateRequest(Request request) {
        String sql = "UPDATE requests SET requester_full_name = ?, requester_location = ?, item_name = ?, unit = ?, " +
                    "quantity = ?, status = ?, priority = ?, contact_info = ? WHERE request_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, request.getRequesterFullName());
            pstmt.setString(2, request.getRequesterLocation());
            pstmt.setString(3, request.getItemName());
            pstmt.setString(4, request.getUnit());
            pstmt.setInt(5, request.getQuantity());
            pstmt.setString(6, request.getStatus());
            pstmt.setString(7, request.getPriority());
            pstmt.setString(8, request.getContactInfo());
            pstmt.setInt(9, request.getRequestId());
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Request updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating request: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean deleteRequest(int requestId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            
            // First delete related distributions
            String deleteDistributionsSql = "DELETE FROM distributions WHERE request_id = ?";
            try (PreparedStatement pstmt1 = conn.prepareStatement(deleteDistributionsSql)) {
                pstmt1.setInt(1, requestId);
                pstmt1.executeUpdate();
            }
            
            // Then delete the request
            String deleteRequestSql = "DELETE FROM requests WHERE request_id = ?";
            try (PreparedStatement pstmt2 = conn.prepareStatement(deleteRequestSql)) {
                pstmt2.setInt(1, requestId);
                int affectedRows = pstmt2.executeUpdate();
                
                if (affectedRows > 0) {
                    conn.commit();
                    JOptionPane.showMessageDialog(null, "Request and related distributions deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                } else {
                    conn.rollback();
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting request: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public Request getRequestById(int requestId) {
        String sql = "SELECT * FROM requests WHERE request_id = ?";
        Request request = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, requestId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                request = extractRequestFromResultSet(rs);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving request: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return request;
    }
    
    @Override
    public List<Request> getPendingRequests() {
        List<Request> requests = new ArrayList<>();
        String sql = "SELECT * FROM requests WHERE status = 'Pending' ORDER BY request_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Request request = extractRequestFromResultSet(rs);
                requests.add(request);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving pending requests: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return requests;
    }
    
    private Request extractRequestFromResultSet(ResultSet rs) throws SQLException {
        Request request = new Request();
        request.setRequestId(rs.getInt("request_id"));
        request.setRequesterFullName(rs.getString("requester_full_name"));
        request.setRequesterLocation(rs.getString("requester_location"));
        request.setItemName(rs.getString("item_name"));
        request.setUnit(rs.getString("unit"));
        request.setQuantity(rs.getInt("quantity"));
        request.setRequestDate(rs.getTimestamp("request_date"));
        request.setStatus(rs.getString("status"));
        request.setPriority(rs.getString("priority"));
        request.setContactInfo(rs.getString("contact_info"));
        return request;
    }
}