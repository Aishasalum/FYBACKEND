//package com.birthverification.birthcertsystem.dto;
//
//import com.birthverification.birthcertsystem.model.Payment;
//import java.time.LocalDateTime;
//
//public class PaymentDTO {
//
//    private Long id;
//    private String certificateNumber;
//    private String controlNumber;
//    private Integer amount;
//    private String paymentMethod;
//    private String status;
//    private LocalDateTime paymentDate;
//    private String username; // kutoka user
//
//    // Constructor kutoka Payment entity
//    public PaymentDTO(Payment payment) {
//        this.id = payment.getId();
//        this.certificateNumber = payment.getCertificateNumber();
//        this.controlNumber = payment.getControlNumber();
//        this.amount = payment.getAmount();
//        this.paymentMethod = payment.getPaymentMethod();
//        this.status = payment.getStatus();
//        this.paymentDate = payment.getPaymentDate();
//        this.username = payment.getUser().getFullName(); // unaweza pia kutumia getEmail() ikiwa unataka
//    }
//
//    // Getters na Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getCertificateNumber() { return certificateNumber; }
//    public void setCertificateNumber(String certificateNumber) { this.certificateNumber = certificateNumber; }
//
//    public String getControlNumber() { return controlNumber; }
//    public void setControlNumber(String controlNumber) { this.controlNumber = controlNumber; }
//
//    public Integer getAmount() { return amount; }
//    public void setAmount(Integer amount) { this.amount = amount; }
//
//    public String getPaymentMethod() { return paymentMethod; }
//    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
//
//    public String getStatus() { return status; }
//    public void setStatus(String status) { this.status = status; }
//
//    public LocalDateTime getPaymentDate() { return paymentDate; }
//    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }
//
//    public String getUsername() { return username; }
//    public void setUsername(String username) { this.username = username; }
//}
