package dao;

import model.ViewLog;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ViewLogDAOImpl implements ViewLogDAO {
    
    @Override
    public boolean logAction(ViewLog viewLog) {
        String sql = "INSERT INTO view_logs (table_name, record_id, action_type, action_description, old_values, new_values, performed_by) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, viewLog.getTableName());
            pstmt.setInt(2, viewLog.getRecordId());
            pstmt.setString(3, viewLog.getActionType());
            pstmt.setString(4, viewLog.getActionDescription());
            pstmt.setString(5, viewLog.getOldValues());
            pstmt.setString(6, viewLog.getNewValues());
            pstmt.setString(7, viewLog.getPerformedBy() != null ? viewLog.getPerformedBy() : "System");
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error logging action: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public List<ViewLog> getAllLogs() {
        List<ViewLog> logs = new ArrayList<>();
        String sql = "SELECT * FROM view_logs ORDER BY performed_at DESC, log_id DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                ViewLog log = extractViewLogFromResultSet(rs);
                logs.add(log);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving logs: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return logs;
    }
    
    @Override
    public List<ViewLog> getLogsByTable(String tableName) {
        List<ViewLog> logs = new ArrayList<>();
        String sql = "SELECT * FROM view_logs WHERE table_name = ? ORDER BY performed_at DESC, log_id DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, tableName);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                ViewLog log = extractViewLogFromResultSet(rs);
                logs.add(log);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving logs by table: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return logs;
    }
    
    @Override
    public List<ViewLog> getLogsByAction(String actionType) {
        List<ViewLog> logs = new ArrayList<>();
        String sql = "SELECT * FROM view_logs WHERE action_type = ? ORDER BY performed_at DESC, log_id DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, actionType);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                ViewLog log = extractViewLogFromResultSet(rs);
                logs.add(log);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving logs by action: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return logs;
    }
    
    @Override
    public List<ViewLog> getLogsByUser(String user) {
        List<ViewLog> logs = new ArrayList<>();
        String sql = "SELECT * FROM view_logs WHERE performed_by = ? ORDER BY performed_at DESC, log_id DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, user);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                ViewLog log = extractViewLogFromResultSet(rs);
                logs.add(log);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving logs by user: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return logs;
    }
    
    private ViewLog extractViewLogFromResultSet(ResultSet rs) throws SQLException {
        ViewLog log = new ViewLog();
        log.setLogId(rs.getInt("log_id"));
        log.setTableName(rs.getString("table_name"));
        log.setRecordId(rs.getInt("record_id"));
        log.setActionType(rs.getString("action_type"));
        log.setActionDescription(rs.getString("action_description"));
        log.setOldValues(rs.getString("old_values"));
        log.setNewValues(rs.getString("new_values"));
        log.setPerformedBy(rs.getString("performed_by"));
        log.setPerformedAt(rs.getTimestamp("performed_at"));
        return log;
    }
}