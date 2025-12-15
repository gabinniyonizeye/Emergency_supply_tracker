// RequestDAO.java (in dao package)
package dao;

import model.Request;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public interface RequestDAO {
    boolean addRequest(Request request);
    List<Request> getAllRequests();
    boolean updateRequest(Request request);
    boolean deleteRequest(int requestId);
    Request getRequestById(int requestId);
    List<Request> getPendingRequests();
}