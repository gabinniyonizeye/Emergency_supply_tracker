package dao;

import model.Distribution;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public interface DistributionDAO {
    boolean addDistribution(Distribution distribution);
    List<Distribution> getAllDistributions();
    boolean updateDistributionStatus(int distributionId, String status);
    boolean deleteDistribution(int distributionId);
    Distribution getDistributionByRequestId(int requestId);
    Distribution getDistributionById(int distributionId);
}