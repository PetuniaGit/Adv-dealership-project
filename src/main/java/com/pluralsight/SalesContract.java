package com.pluralsight;

public class SalesContract extends Contract{
    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean Financeoption;
    double Price=0;
    double monthlyPayment=0;

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinanceoption() {
        return Financeoption;
    }

    public void setFinanceoption(boolean financeoption) {
        Financeoption = financeoption;
    }

    public SalesContract(String contractDate, String customerName, String customerEmail, Vehicle vehicleSold , double salesTaxAmount, double recordingFee, double processingFee, boolean isFinanced) {
        super(contractDate, customerName, customerEmail, vehicleSold);
        this.salesTaxAmount = salesTaxAmount;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.Financeoption = isFinanced;
    }


    @Override
    public double getTotalPrice() {
        if (Financeoption) {
            Price =(recordingFee+processingFee+getMonthlypayment())+(recordingFee+processingFee+getMonthlypayment())*(salesTaxAmount/100);
        } else {
            Price =(getVehicleSold().getPrice()+recordingFee+processingFee) +(getVehicleSold().getPrice()+recordingFee+processingFee)*(salesTaxAmount/100);
        }

        return Price;

    }


    @Override
    public double getMonthlypayment() {
        if (!Financeoption) {
            return 0; // If the user chose not to take out a loan the monthly payment is 0
        }
        // If the cost of the vehicle is > 10,000
        if (getVehicleSold().getPrice()>10000){
            monthlyPayment = ((getVehicleSold().getPrice()/48)*(0.00425));
        } else { // < 10,000
            monthlyPayment = ((getVehicleSold().getPrice()/24)*(0.00525));
        }
        return monthlyPayment;

    }
}
