// Distribution.java (in model package)
package model;

import java.util.Date;

public class Distribution {
    private int distributionId;
    private int requestId;
    private String itemName;
    private String unit;
    private int distributedQuantity;
    private String distributionLocation;
    private Date distributionDate;
    private String status;
    private String distributedBy;

    public Distribution() {}

    // Getters and setters
    public int getDistributionId() { return distributionId; }
    public void setDistributionId(int distributionId) { this.distributionId = distributionId; }
    public int getRequestId() { return requestId; }
    public void setRequestId(int requestId) { this.requestId = requestId; }
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public int getDistributedQuantity() { return distributedQuantity; }
    public void setDistributedQuantity(int distributedQuantity) { this.distributedQuantity = distributedQuantity; }
    public String getDistributionLocation() { return distributionLocation; }
    public void setDistributionLocation(String distributionLocation) { this.distributionLocation = distributionLocation; }
    public Date getDistributionDate() { return distributionDate; }
    public void setDistributionDate(Date distributionDate) { this.distributionDate = distributionDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDistributedBy() { return distributedBy; }
    public void setDistributedBy(String distributedBy) { this.distributedBy = distributedBy; }
}