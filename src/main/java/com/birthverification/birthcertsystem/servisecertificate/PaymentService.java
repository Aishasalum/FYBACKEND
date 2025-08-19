//package com.birthverification.birthcertsystem.servisecertificate;
//
//import com.birthverification.birthcertsystem.dto.PaymentRequest;
//import com.birthverification.birthcertsystem.dto.PaymentConfirmationRequest;
//import com.birthverification.birthcertsystem.model.Payment;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface PaymentService {
//
//    // Tengeneza control number na kuweka payment kwa status NOT_PAID
//    Payment generateControlNumber(PaymentRequest request);
//
//    // Fanya malipo kwa kutumia control number na method
//    Payment makePayment(PaymentConfirmationRequest request);
//
//    // Pata malipo yote
//    List<Payment> getAllPayments();
//
//    // Tafuta malipo kwa control number
//    Optional<Payment> getPaymentByControlNumber(String controlNumber);
//
//    // Tafuta malipo kwa certificate number
//    Optional<Payment> getPaymentByCertificateNumber(String certificateNumber);
//
//    // Update malipo yoyote
//    Payment updatePayment(Long id, Payment updatedPayment);
//
//    // Delete malipo
//    void deletePayment(Long id);
//}


//
//package com.birthverification.birthcertsystem.servisecertificate;
//
//import com.birthverification.birthcertsystem.dto.PaymentRequest;
//import com.birthverification.birthcertsystem.dto.PaymentConfirmationRequest;
//import com.birthverification.birthcertsystem.model.Payment;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface PaymentService {
//
//    // Tengeneza control number na kuweka payment kwa status NOT_PAID
//    Payment generateControlNumber(PaymentRequest request);
//
//    // Fanya malipo kwa kutumia control number na method
//    Payment makePayment(PaymentConfirmationRequest request);
//
//    // Pata malipo yote (active)
//    List<Payment> getAllActivePayments();
//
//    // Pata malipo waliopo Recycle Bin (deleted)
//    List<Payment> getAllDeletedPayments();
//
//    // Tafuta malipo kwa control number
//    Optional<Payment> getPaymentByControlNumber(String controlNumber);
//
//    // Tafuta malipo kwa certificate number
//    Optional<Payment> getPaymentByCertificateNumber(String certificateNumber);
//
//    // Update malipo yoyote
//    Payment updatePayment(Long id, Payment updatedPayment);
//
//    // Soft delete malipo (move to Recycle Bin)
//    void softDeletePayment(Long id);
//
//    // Restore malipo kutoka Recycle Bin
//    void restorePayment(Long id);
//
//    // Permanently delete malipo
//    void permanentlyDeletePayment(Long id);
//
//    List<Payment> findByUserId(Long userId);
//}




//new


package com.birthverification.birthcertsystem.servisecertificate;

import com.birthverification.birthcertsystem.dto.PaymentRequest;
import com.birthverification.birthcertsystem.dto.PaymentConfirmationRequest;
import com.birthverification.birthcertsystem.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    // Tengeneza control number na kuweka payment kwa status NOT_PAID
    Payment generateControlNumber(PaymentRequest request);

    // Fanya malipo kwa kutumia control number na method
    Payment makePayment(PaymentConfirmationRequest request);

    // Pata malipo yote (active)
    List<Payment> getAllActivePayments();

    // Pata malipo waliopo Recycle Bin (deleted)
    List<Payment> getAllDeletedPayments();

    // Tafuta malipo kwa control number
    Optional<Payment> getPaymentByControlNumber(String controlNumber);

    // Tafuta malipo kwa certificate number
    Optional<Payment> getPaymentByCertificateNumber(String certificateNumber);

    // Update malipo yoyote
    Payment updatePayment(Long id, Payment updatedPayment);

    // Soft delete malipo (move to Recycle Bin)
    void softDeletePayment(Long id);

    // Restore malipo kutoka Recycle Bin
    void restorePayment(Long id);

    // Permanently delete malipo
    void permanentlyDeletePayment(Long id);
}