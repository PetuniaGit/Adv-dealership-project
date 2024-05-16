package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;

     private ArrayList<Vehicle> inventory;
    private ArrayList<Vehicle> vehiclesInRange;
    private ArrayList<Vehicle> vehiclesByMakeModel;
    private ArrayList<Vehicle> vehiclesByYear;
    private  ArrayList<Vehicle> vehiclesByColor;
    private ArrayList<Vehicle> vehiclesByMileage;
    private ArrayList<Vehicle> vehiclesByType;




    public Dealership(String name, String address, String phone) {
        this.name=name;
        this.address=address;
        this.phone=phone;
        inventory = new ArrayList<>();
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        // Initalize the variable.
        vehiclesInRange = new ArrayList<>();


        for (Vehicle vehicle : inventory) {
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max) {
                vehiclesInRange.add(vehicle);
            }
        }

        return vehiclesInRange;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        // Initalize the variable.
        vehiclesByMakeModel = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                vehiclesByMakeModel.add(vehicle);
            }
        }

        return vehiclesByMakeModel;
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        // Initalize the variable.
        vehiclesByYear = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getYear() >= min && vehicle.getYear() <= max) {
                vehiclesByYear.add(vehicle);
            }
        }

        return vehiclesByYear;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        // Initalize the variable.
        vehiclesByColor = new ArrayList<>();


        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                vehiclesByColor.add(vehicle);
            }
        }

        return vehiclesByColor;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        // Initalize the variable.
        vehiclesByMileage = new ArrayList<>();


        for (Vehicle vehicle : inventory) {
            if (vehicle.getOdometer() >= min && vehicle.getOdometer() <= max) {
                vehiclesByMileage.add(vehicle);
            }
        }

        return vehiclesByMileage;
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        // Initalize the variable.
        vehiclesByType = new ArrayList<>();


        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)) {
                vehiclesByType.add(vehicle);
            }
        }

        return vehiclesByType;
    }

    // Create removeVehicle method.
    public void removeVehicle(Vehicle vehicle) {
        // Initalize the variable.
        inventory= new ArrayList<>();
        inventory.remove(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return inventory;
    }

    public void addVehicle(Vehicle vehicle) {
        // Initalize the variable.
        inventory= new ArrayList<>();
        inventory.add(vehicle);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}




