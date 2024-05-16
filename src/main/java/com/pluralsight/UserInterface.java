package com.pluralsight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
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
            System.out.println(" Enter 10 to  buy a Vehicle");
            System.out.println(" Enter 11 to  lease a Vehicle");
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
                    case 10:
                        sellVehicle();
                        break;
                    case 11:
                        leaseVehicle();
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


    public void sellVehicle() {
        LocalDate ldt = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = ldt.format(dtf);


        // Initialize the variables.
        ContractDataManager contractDataManager = new ContractDataManager();


        while (true) {
            // Ask the user to enter the VIN.
            System.out.print("Enter VIN of the vehicle to sell: ");
            int vin;
            try {
                vin = scanner.nextInt();
                scanner.nextLine();
                // Print error if invalid input.
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid VIN.");
                scanner.nextLine();
                continue;
            }

            // Search for the vehicle.
            Vehicle SoldVehicle = null;
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                if (vehicle.getVin() == vin) {
                    SoldVehicle = vehicle;
                    break;
                }
            }

            // If no results, print message and ask the user to try again.
            if (SoldVehicle == null) {
                System.out.println("Vehicle with VIN " + vin + " not found.");
                continue;
            }

            // Ask the user for the customer name.
            System.out.print("Enter customer name: ");
            String customerName = scanner.nextLine();

            // Ask the user for the customer email.
            System.out.print("Enter customer email: ");
            String customerEmail = scanner.nextLine();

            // Ask the user for the total price.
            System.out.print("Enter total price: ");
            double totalPrice;
            try {
                totalPrice = scanner.nextDouble();
                scanner.nextLine();
                // Print error if invalid input.
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid price.");
                scanner.nextLine();
                continue;
            }

            // Calculate the sales tax.
            double salesTax = totalPrice * SalesContract.salesTaxRate;

            // Calculate the processing fee.
            double processingFee = (totalPrice < 10000) ? SalesContract.Under10Kfee : SalesContract.Over10Kfee;

            // Call offerAddOns method.


            // Create the SalesContract object.
            SalesContract salesContract = new SalesContract(formattedDate, customerName, customerEmail, SoldVehicle, totalPrice, salesTax, processingFee);

            // Add the selected AddOns.


            // Save the contract.
            contractDataManager.saveContract(salesContract);

            // Remove vehicle.
            dealership.removeVehicle(SoldVehicle);

            // Print success message.
            System.out.println("\nVehicle successfully sold!");
            break;
        }


    }
    private void leaseVehicle() {
        LocalDate ldt = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = ldt.format(dtf);
        // Initialize the variable.
        ContractDataManager contractDataManager = new ContractDataManager();

        while (true) {
            // Ask the user to enter the VIN.
            System.out.print("Enter VIN of the vehicle to lease: ");
            int vin;
            try {
                vin = scanner.nextInt();
                scanner.nextLine();
                // Print error if invalid input.
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid VIN.");
                scanner.nextLine();
                continue;
            }

            // Search for the vehicle.
            Vehicle LeasedVehicle = null;
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                if (vehicle.getVin() == vin) {
                    LeasedVehicle = vehicle;
                    break;
                }
            }

            // If no results, print message and ask the user to try again.
            if (LeasedVehicle == null) {
                System.out.println("Vehicle with VIN " + vin + " not found.");
                continue;
            }

            // Ask the user for the customer name.
            System.out.print("Enter customer name: ");
            String customerName = scanner.nextLine();

            // Ask the user for the customer email.
            System.out.print("Enter customer email: ");
            String customerEmail = scanner.nextLine();

            // Ask the user for the total price.
            System.out.print("Enter total price: ");
            double totalPrice;
            try {
                totalPrice = scanner.nextDouble();
                scanner.nextLine();
                // Print error if invalid input.
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid price.");
                scanner.nextLine();
                continue;
            }

            // Calculate the lease fee.
            double leaseFee = totalPrice * LeaseContract.leaseFeeRate;

            // Calculate the monthly payment.
            double monthlyPayment = totalPrice * LeaseContract.interestRate / (1 - Math.pow(1 + LeaseContract.interestRate, -LeaseContract.loanTerm));

            // Create the LeaseContract object.
            LeaseContract leaseContract = new LeaseContract(formattedDate, customerName, customerEmail, LeasedVehicle, leaseFee, monthlyPayment);

            // Save the contract.
            contractDataManager.saveContract(leaseContract);

            // Remove vehicle.
            dealership.removeVehicle(LeasedVehicle);

            // Print success message.
            System.out.println("\nVehicle successfully leased!");
            break;
        }
    }

    }



