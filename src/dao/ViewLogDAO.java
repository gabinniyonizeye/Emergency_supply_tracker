package dao;

import model.ViewLog;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public interface ViewLogDAO {
    boolean logAction(ViewLog viewLog);
    List<ViewLog> getAllLogs();
    List<ViewLog> getLogsByTable(String tableName);
    List<ViewLog> getLogsByAction(String actionType);
    List<ViewLog> getLogsByUser(String user);
}
