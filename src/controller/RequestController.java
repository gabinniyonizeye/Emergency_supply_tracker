// RequestController.java (in controller package)
package controller;

import model.Request;
import dao.RequestDAO;
import dao.RequestDAOImpl;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.text.SimpleDateFormat;

public class RequestController {
    private RequestDAO requestDAO;
    private SimpleDateFormat dateFormat;

    public RequestController() {
        this.requestDAO = new RequestDAOImpl();
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    public boolean addRequest(String requesterFullName, String requesterLocation, String itemName, 
                            String unit, int quantity, String priority, String contactInfo) {
        try {
            Request request = new Request();
            request.setRequesterFullName(requesterFullName);
            request.setRequesterLocation(requesterLocation);
            request.setItemName(itemName);
            request.setUnit(unit);
            request.setQuantity(quantity);
            request.setRequestDate(new java.util.Date());
            request.setStatus("Pending");
            request.setPriority(priority);
            request.setContactInfo(contactInfo);
            
            return requestDAO.addRequest(request);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateRequest(int requestId, String requesterFullName, String requesterLocation, 
                               String itemName, String unit, int quantity, String priority, String contactInfo) {
        try {
            Request request = requestDAO.getRequestById(requestId);
            if (request != null) {
                request.setRequesterFullName(requesterFullName);
                request.setRequesterLocation(requesterLocation);
                request.setItemName(itemName);
                request.setUnit(unit);
                request.setQuantity(quantity);
                request.setPriority(priority);
                request.setContactInfo(contactInfo);
                
                return requestDAO.updateRequest(request);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteRequest(int requestId) {
        return requestDAO.deleteRequest(requestId);
    }

    public Request getRequestById(int requestId) {
        return requestDAO.getRequestById(requestId);
    }

    public List<Request> getAllRequests() {
        return requestDAO.getAllRequests();
    }
    
    public List<Request> getPendingRequests() {
        return requestDAO.getPendingRequests();
    }

    public DefaultTableModel getRequestsTableModel() {
        String[] columnNames = {"R. ID", "Requester Name", "Location", "Item Name", 
                               "Unit", "Quantity", "Date", "Status", "Priority"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Request request : getAllRequests()) {
            Object[] rowData = {
                request.getRequestId(),
                request.getRequesterFullName(),
                request.getRequesterLocation(),
                request.getItemName(),
                request.getUnit(),
                request.getQuantity(),
                dateFormat.format(request.getRequestDate()),
                request.getStatus(),
                request.getPriority()
            };
            model.addRow(rowData);
        }

        return model;
    }
    
    public boolean updateRequestStatus(int requestId, String status) {
        Request request = requestDAO.getRequestById(requestId);
        if (request != null) {
            request.setStatus(status);
            return requestDAO.updateRequest(request);
        }
        return false;
    }
}