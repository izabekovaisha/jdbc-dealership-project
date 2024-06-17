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
        // TODO: Implement the logic to add a vehicle to the inventory
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO inventory (VIN, dealership_id) VALUES (?, ?)")) {

            statement.setString(1, vin);
            statement.setInt(2, dealershipId);

            int rows = statement.executeUpdate();
            System.out.println(rows > 0 ? "Vehicle added to inventory successfully!" : "Failed to add vehicle to inventory.");

        } catch (Exception e) {
            System.err.println("Error occurred while adding the vehicle to inventory: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeVehicleFromInventory(String vin) {
        // TODO: Implement the logic to remove a vehicle from the inventory
    }
}
