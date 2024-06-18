package com.yearup.dealership.db;

import com.yearup.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO vehicles (VIN, make, model, year, SOLD, color, vehicleType, odometer, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            statement.setString(1, vehicle.getVin());
            statement.setString(2, vehicle.getMake());
            statement.setString(3, vehicle.getModel());
            statement.setInt(4, vehicle.getYear());
            statement.setBoolean(5, vehicle.isSold());
            statement.setString(6, vehicle.getColor());
            statement.setString(7, vehicle.getVehicleType());
            statement.setInt(8, vehicle.getOdometer());
            statement.setDouble(9, vehicle.getPrice());

        } catch (Exception e) {
            System.err.println("Error occurred while adding the vehicle: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeVehicle(String vin) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM vehicles WHERE VIN = ?")) {

            statement.setString(1, vin);
            statement.executeUpdate();

        } catch (Exception e) {
            System.err.println("Error occurred while removing the vehicle: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM vehicles WHERE price >= ? AND price <= ?")) {

            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);

            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    String vin = results.getString("vin");
                    String make = results.getString("make");
                    String model = results.getString("model");
                    int year = results.getInt("year");
                    boolean sold = results.getBoolean("sold");
                    String color = results.getString("color");
                    String vehicleType = results.getString("vehicleType");
                    int odometer = results.getInt("odometer");
                    double price = results.getDouble("price");

                    Vehicle vehicle = new Vehicle(vin, make, model, year, sold, color, vehicleType, odometer, price);
                    vehicles.add(vehicle);
                }
            }
        } catch (Exception e) {
            System.err.println("Error occurred while searching vehicles by price range: " + e.getMessage());
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByMakeModel(String make, String model) {
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM vehicles WHERE make = ? AND model = ?")) {

            statement.setString(1, make);
            statement.setString(2, model);

            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    String vin = results.getString("vin");
                    int year = results.getInt("year");
                    boolean sold = results.getBoolean("sold");
                    String color = results.getString("color");
                    String vehicleType = results.getString("vehicleType");
                    int odometer = results.getInt("odometer");
                    double price = results.getDouble("price");

                    Vehicle vehicle = new Vehicle(vin, make, model, year, sold, color, vehicleType, odometer, price);
                    vehicles.add(vehicle);
                }
            }
        } catch (Exception e) {
            System.err.println("Error occurred while searching vehicles by make and model: " + e.getMessage());
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        // TODO: Implement the logic to search vehicles by year range
        return new ArrayList<>();
    }

    public List<Vehicle> searchByColor(String color) {
        // TODO: Implement the logic to search vehicles by color
        return new ArrayList<>();
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        // TODO: Implement the logic to search vehicles by mileage range
        return new ArrayList<>();
    }

    public List<Vehicle> searchByType(String type) {
        // TODO: Implement the logic to search vehicles by type
        return new ArrayList<>();
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}
