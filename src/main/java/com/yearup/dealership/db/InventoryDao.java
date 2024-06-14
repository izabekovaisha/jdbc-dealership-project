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
        PreparedStatement addVehicleStatement = connection.prepareStatement("INSERT INTO inventory (VIN, dealership_id) VALUES (?, ?)")) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeVehicleFromInventory(String vin) {
        // TODO: Implement the logic to remove a vehicle from the inventory
    }
}
