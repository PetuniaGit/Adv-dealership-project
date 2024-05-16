package com.pluralsight;

import java.util.Scanner;

public class UserInterface {
    static Scanner scanner = new Scanner(System.in);
    private Dealership dealership;

    public void display() {
        init();
        while (true) {
            System.out.println("Menu Screen");
            System.out.println(" Enter 1 to Search by Price");
            System.out.println(" Enter 2 to  Search by Make and Model");
            System.out.println(" Enter 3 to  Search by Year");
            System.out.println(" Enter 4 to  Search by Color");
            System.out.println(" Enter 5 to  Search by Mileage");
            System.out.println(" Enter 6 to Search by Vehicle Type");
            System.out.println(" Enter 7 to  Display All Vehicles");
            System.out.println(" Enter 8 to  Add a Vehicle");
            System.out.println(" Enter 9 to  Remove a Vehicle");
            System.out.println(" Enter 0 to  Exit");

            try { // Enforce input type
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        processGetByPriceRequest();
                        break;
                    case 2:
                        processGetByMakeModelRequest();
                        break;
                    case 3:
                        processGetByYearRequest();
                        break;
                    case 4:
                        processGetByColorRequest();
                        break;
                    case 5:
                        processGetByMileageRequest();
                        break;
                    case 6:
                        processGetByVehicleType();
                        break;
                    case 7:
                        processGetAllVehiclesRequest();
                        break;
                    case 8:
                        processAddVehicleRequest();
                        break;
                    case 9:
                        processRemoveVehicleRequest();
                        break;
                    case 0:
                        System.exit(0);
                    default: // Enforce input range
                        System.out.println("Invalid input\n");
                }
            } catch (Exception e) {
                System.out.println("Invalid input\nPlease enter a number\n");
                scanner.nextLine(); // Prevent an infinite loop
            }
        }
    }

    public void processGetByPriceRequest() {
        // ask for price range
        System.out.print("Enter the minimum price of the vehicle.");
        double min = scanner.nextDouble();
        System.out.print("Enter the maximum price of the vehicle.");
        double max = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline

        // Call the search method
        System.out.println(dealership.getVehiclesByPrice(min, max));
    }

    public void processGetByMakeModelRequest() {
        // Prompt
        System.out.print("Enter the make ");
        String make = scanner.nextLine();
        System.out.print("Enter the model : ");
        String model = scanner.nextLine();

        // Display
        System.out.println(dealership.getVehiclesByMakeModel(make, model));
    }
    public void processGetByColorRequest() {
        // Prompt
        System.out.print("Enter a color : ");
        String color = scanner.nextLine();

        // Display
        System.out.println(dealership.getVehiclesByColor(color));
    }


    public void processGetByYearRequest() {
        // Prompt
        System.out.print("Enter the minimum year : ");
        int min = scanner.nextInt();
        System.out.print("Enter the maximum year : ");
        int max = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        // Display
        System.out.println(dealership.getVehiclesByYear(min, max));
    }

    public void processGetByMileageRequest() {
        // Prompt
        System.out.print("Enter minimum mileage : ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum mileage : ");
        int max = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        // Display
        System.out.println(dealership.getVehiclesByMileage(min, max));
    }

    public void processGetByVehicleType() {
        // Prompt
        System.out.print("Enter the vehicle type : ");
        String vehicleType = scanner.nextLine();

        // Display
        System.out.println(dealership.getVehiclesByType(vehicleType));}


        public void processGetAllVehiclesRequest() {
            // Display vehicles in the inventory
            System.out.println(dealership.getAllVehicles());
        }
    public void processAddVehicleRequest() {
        // Prompt
        System.out.println("Please enter the following information to add a vehicle.");

        System.out.print("Enter the Vin : ");
        int vin = scanner.nextInt();
        System.out.print(" Enter the Year : ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        System.out.print("Enter the Make : ");
        String make = scanner.nextLine();
        System.out.print("Enter the Model : ");
        String model = scanner.nextLine();
        System.out.print("Enter the Vehicle Type : ");
        String vehicleType = scanner.nextLine();
        System.out.print("Enter the Color : ");
        String color = scanner.nextLine();

        System.out.print("Enter the Odometer reading : ");
        int odometer = scanner.nextInt();
        System.out.print("Enter the Price : ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline

        // Create the new vehicle object
        Vehicle vehicle = new Vehicle(vin,year,make,model,vehicleType,color,odometer,price);

        // Add the vehicle to the dealership
        dealership.addVehicle(vehicle);

        // Print out a confirmation message
        System.out.println("Vehicle added successfully");

        // Save the dealership changes
        DealershipFileManager.saveDealership(dealership);
    }
    public void processRemoveVehicleRequest() {
        // Prompt for the vin of the vehicle to remove
        System.out.print("Enter the VIN of the vehicle you want to remove : ");
        int vin = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        // Loop through the inventory for the vehicle with the input VIN number
        for (Vehicle vehicle : this.dealership.getAllVehicles()){
            if (vehicle.getVin() == vin){
                // Remove the vehicle
                dealership.removeVehicle(vehicle);
                // Print out a confirmation message
                System.out.println("Vehicle removed successfully");

            }
        }

        // Save the dealership changes
        DealershipFileManager.saveDealership(dealership);
    }
    private void init() {
        this.dealership = DealershipFileManager.getDealership();
    }

    }



