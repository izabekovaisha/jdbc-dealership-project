package com.yearup.dealership.db;

import com.yearup.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.sql.*;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO sales_contracts (VIN, sale_date, price) VALUES (?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, salesContract.getVin());
            statement.setDate(2, Date.valueOf(salesContract.getSaleDate()));
            statement.setDouble(3, salesContract.getPrice());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys();) {
                if (generatedKeys.next()) {
                    int contractId = generatedKeys.getInt(1);
                    System.out.println("New sales contract ID: " + contractId);
                }
            }
        } catch (Exception e) {
            System.err.println("Error occurred while adding the sales contract: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
