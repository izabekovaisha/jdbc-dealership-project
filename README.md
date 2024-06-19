# JDBC Dealership Project

## Overview

The JDBC Dealership Project is a Java-based application that allows users to manage vehicles and contracts in a car dealership. It uses MySQL for the database and JDBC for database operations. The application supports adding, searching, and removing vehicles, as well as adding contracts for sales and leases.

## Features

- **Vehicle Management**
    - Add a new vehicle
    - Search for vehicles by VIN
    - Remove a vehicle

- **Contract Management**
    - Add a sales contract
    - Add a lease contract

## Prerequisites

- Java Development Kit (JDK) 17 or higher
- MySQL Database
- Maven (for dependency management)
- IDE (such as IntelliJ IDEA) or command-line tools

## Setup
### Database Setup

**Install MySQL**: Ensure MySQL is installed and running on your machine.

**Create Database:**
   ```sql
   CREATE DATABASE car_dealership;
   ``` 

3. **Create Tables:**
````
USE car_dealership;

CREATE TABLE dealerships (
dealership_id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(50),
address VARCHAR(50),
phone VARCHAR(12)
);

CREATE TABLE vehicles (
VIN VARCHAR(17) PRIMARY KEY,
make VARCHAR(50),
model VARCHAR(50),
year INT,
SOLD TINYINT(1),
color VARCHAR(50),
vehicleType VARCHAR(50),
odometer INT,
price DOUBLE
);

CREATE TABLE inventory (
dealership_id INT,
VIN VARCHAR(17),
FOREIGN KEY (dealership_id) REFERENCES dealerships(dealership_id),
FOREIGN KEY (VIN) REFERENCES vehicles(VIN)
);

CREATE TABLE sales_contracts (
contract_id INT AUTO_INCREMENT PRIMARY KEY,
VIN VARCHAR(17),
sale_date DATE,
price DECIMAL(10, 2),
FOREIGN KEY (VIN) REFERENCES vehicles(VIN)
);

CREATE TABLE lease_contracts (
contract_id INT AUTO_INCREMENT PRIMARY KEY,
VIN VARCHAR(17),
lease_start DATE,
lease_end DATE,
monthly_payment DECIMAL(10, 2),
FOREIGN KEY (VIN) REFERENCES vehicles(VIN)
);
````

### Project Setup 
**Clone the Repository:**
````
git clone https://github.com/your-username/jdbc-dealership-project.git
````

**Configure Database Connection:**
* Update the database connection details in your project to match your MySQL configuration.
* Example configuration in Main.java:
````
BasicDataSource dataSource = new BasicDataSource();
dataSource.setUrl("jdbc:mysql://localhost:3306/car_dealership");
dataSource.setUsername("root");
dataSource.setPassword("your_password");
````

### Dependencies
The project uses the following dependencies:
````
<dependencies>
<dependency>
<groupId>mysql</groupId>
<artifactId>mysql-connector-java</artifactId>
<version>8.0.33</version>
</dependency>
<dependency>
<groupId>org.apache.commons</groupId>
<artifactId>commons-dbcp2</artifactId>
<version>2.9.0</version>
</dependency>
</dependencies>
````

### Running the Application in IntelliJ
Follow these steps to get your application running within IntelliJ IDEA:

1. Open IntelliJ IDEA.
2. Select "Open" and navigate to the directory where you cloned or downloaded the project.
3. After the project opens, wait for IntelliJ to index the files and set up the project.
4. Find the main class with the public static void main(String[] args) method.
5. Right-click on the file and select 'Run 'YourMainClassName.main()'' to start the application.

### Contact
For any questions or issues, please contact [izabekovaisha@gmail.com]().