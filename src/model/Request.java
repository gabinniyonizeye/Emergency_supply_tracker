// Request.java (in model package)
package model;

import java.util.Date;

public class Request {
    private int requestId;
    private String requesterFullName;
    private String requesterLocation;
    private String itemName;
    private String unit;
    private int quantity;
    private Date requestDate;
    private String status;
    private String priority;
    private String contactInfo;

    public Request() {}

    // Getters and setters
    public int getRequestId() { return requestId; }
    public void setRequestId(int requestId) { this.requestId = requestId; }
    public String getRequesterFullName() { return requesterFullName; }
    public void setRequesterFullName(String requesterFullName) { this.requesterFullName = requesterFullName; }
    public String getRequesterLocation() { return requesterLocation; }
    public void setRequesterLocation(String requesterLocation) { this.requesterLocation = requesterLocation; }
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public Date getRequestDate() { return requestDate; }
    public void setRequestDate(Date requestDate) { this.requestDate = requestDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }
    
    // Alias methods for compatibility
    public String getFullName() { return requesterFullName; }
    public String getLocation() { return requesterLocation; }
}