package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {

    public void saveContract(Contract contract) {

        try (BufferedWriter bwriter = new BufferedWriter(new FileWriter("contracts.cvs"))) {

            if (contract instanceof SalesContract) {
                SalesContract SC = (SalesContract) contract;
                bwriter.write("SALE|" + SC.getContractDate() + "|" +
                        SC.getCustomerName() + "|" +
                        SC.getCustomerEmail() + "|" +
                        SC.getVehicleSold().getVin() + "|" +
                        SC.getVehicleSold().getYear() + "|" +
                        SC.getVehicleSold().getMake() + "|" +
                        SC.getVehicleSold().getModel() + "|" +
                        SC.getVehicleSold().getVehicleType() + "|" +
                        SC.getVehicleSold().getColor() + "|" +
                        SC.getVehicleSold().getOdometer() + "|" +
                        SC.getVehicleSold().getPrice() + "|" +
                        SC.getTotalPrice() + "|" +
                        SC.getSalesTaxAmount() + "|" +
                        SC.getRecordingFee() + "|" +
                        SC.getProcessingFee() + "|" +
                        (SC.isFinanceoption() ? "YES" : "NO") + "|" +
                        SC.getMonthlypayment() + "\n");

            } else if (contract instanceof LeaseContract) {
                LeaseContract lC = (LeaseContract) contract;
                bwriter.write("LEASE|" + lC.getContractDate() + "|" +
                        lC.getCustomerName() + "|" +
                        lC.getCustomerEmail() + "|" +
                        lC.getVehicleSold().getVin() + "|" +
                        lC.getVehicleSold().getYear() + "|" +
                        lC.getVehicleSold().getMake() + "|" +
                        lC.getVehicleSold().getModel() + "|" +
                        lC.getVehicleSold().getVehicleType() + "|" +
                        lC.getVehicleSold().getColor() + "|" +
                        lC.getVehicleSold().getOdometer() + "|" +
                        lC.getVehicleSold().getPrice() + "|" +
                        lC.getTotalPrice() + "|" +
                        lC.getExpectedEndingValue() + "|" +
                        lC.getLeaseFee() + "|" +
                        (lC.isFinanceOption() ? "YES" : "NO") + "|" +
                        lC.getMonthlypayment() + "\n");
            }

            bwriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
