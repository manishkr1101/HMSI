package com.nitp.manish.hmsi;

public class PaymentDetails {

    String refId;
    String tollName;
    String vehicleNumber;
    String vehicleType;
    String paidAmount;
    String paymentStatus;
    long time;

    public PaymentDetails(){

    }

    public PaymentDetails(String refId, String tollName, String vehicleNumber, String vehicleType, String paidAmount, String paymentStatus,long time) {
        this.refId = refId;
        this.tollName = tollName;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.paidAmount = paidAmount;
        this.paymentStatus = paymentStatus;
        this.time = time;
    }

    public String getRefId() {
        return refId;
    }

    public String getTollName() {
        return tollName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public long getTime() {
        return time;
    }
}
