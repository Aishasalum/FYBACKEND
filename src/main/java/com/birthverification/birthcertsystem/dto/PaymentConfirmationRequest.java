package com.birthverification.birthcertsystem.dto;

public class PaymentConfirmationRequest {
    private String controlNumber;
    private String paymentMethod;

    // Getters and Setters
    public String getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
