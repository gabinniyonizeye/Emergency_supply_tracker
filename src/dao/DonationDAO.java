package dao;

import model.Donation;
import java.util.List;

public interface DonationDAO {
    boolean addDonation(Donation donation);
    List<Donation> getAllDonations();
    boolean updateDonation(Donation donation);
    boolean deleteDonation(int donationId);
    boolean deleteDonationOnly(int donationId);  // Add this method
    Donation getDonationById(int donationId);
}