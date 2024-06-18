package com.yearup.dealership.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String vin, int dealershipId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO inventory (VIN, dealership_id) VALUES (?, ?)")) {

            statement.setString(1, vin);
            statement.setInt(2, dealershipId);
            statement.executeUpdate();

        } catch (Exception e) {
            System.err.println("Error occurred while adding the vehicle to inventory: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeVehicleFromInventory(String vin) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM inventory WHERE VIN = ?")) {

            statement.setString(1, vin);
            statement.executeUpdate();

        } catch (Exception e) {
            System.err.println("Error occurred while removing the vehicle from inventory: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
