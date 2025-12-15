package model;

import java.util.Date;

public class Inventory {
    private int donationId;
    private String itemName;
    private String category;
    private int quantity;
    private String unit;
    private String location;
    private Date expireDate;
    private String status;
    private Date donationDate;
    private String donorInfo;

    // Getters and Setters
    public int getDonationId() { return donationId; }
    public void setDonationId(int donationId) { this.donationId = donationId; }
    
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public Date getExpireDate() { return expireDate; }
    public void setExpireDate(Date expireDate) { this.expireDate = expireDate; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Date getDonationDate() { return donationDate; }
    public void setDonationDate(Date donationDate) { this.donationDate = donationDate; }
    
    public String getDonorInfo() { return donorInfo; }
    public void setDonorInfo(String donorInfo) { this.donorInfo = donorInfo; }
}