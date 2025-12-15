package controller;

import dao.DonationDAO;
import dao.DonationDAOImpl;
import model.Donation;
import java.util.List;
import java.util.Date;

public class DonationController {
    private DonationDAO donationDAO;
    
    public DonationController() {
        this.donationDAO = new DonationDAOImpl();
    }
    
    // Business validation rules
    public boolean validateDonation(String itemName, String quantity, Date expireDate, String location) {
        // Rule 1: Required fields validation
        if (itemName == null || itemName.trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, "Item name is required!", "Validation Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Rule 2: Quantity must be positive
        try {
            int qty = Integer.parseInt(quantity);
            if (qty <= 0) {
                javax.swing.JOptionPane.showMessageDialog(null, "Quantity must be positive!", "Validation Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Quantity must be a valid number!", "Validation Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Rule 3: Expiration date cannot be in the past
        if (expireDate != null && expireDate.before(new Date())) {
            javax.swing.JOptionPane.showMessageDialog(null, "Expiration date cannot be in the past!", "Validation Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Rule 4: Location is required
        if (location == null || location.trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, "Location is required!", "Validation Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    public boolean addDonation(Donation donation) {
        return donationDAO.addDonation(donation);
    }
    
    public List<Donation> getAllDonations() {
        return donationDAO.getAllDonations();
    }
    
    public boolean updateDonation(Donation donation) {
        return donationDAO.updateDonation(donation);
    }
    
    public boolean deleteDonation(int donationId) {
        return donationDAO.deleteDonation(donationId);
    }
    
    public Donation getDonationById(int donationId) {
        return donationDAO.getDonationById(donationId);
    }
    public boolean deleteDonationOnly(int donationId) {
    return donationDAO.deleteDonationOnly(donationId);
}
}