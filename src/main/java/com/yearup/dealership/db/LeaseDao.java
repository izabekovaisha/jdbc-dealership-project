package com.yearup.dealership.db;

import com.yearup.dealership.models.LeaseContract;

import javax.sql.DataSource;
import java.sql.*;

public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addLeaseContract(LeaseContract leaseContract) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO lease_contracts (VIN, lease_start, lease_end, monthly_payment) VALUES (?, ?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, leaseContract.getVin());
            statement.setDate(2, Date.valueOf(leaseContract.getLeaseStart()));
            statement.setDate(3, Date.valueOf(leaseContract.getLeaseEnd()));
            statement.setDouble(4, leaseContract.getMonthlyPayment());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys();) {
                if (generatedKeys.next()) {
                    int contractId = generatedKeys.getInt(1);
                    System.out.println("New lease contract ID: " + contractId);
                }
            }
        } catch (Exception e) {
            System.err.println("Error occurred while adding the lease contract: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
