//package com.birthverification.birthcertsystem.service;
//
//import com.birthverification.birthcertsystem.dto.PaymentConfirmationRequest;
//import com.birthverification.birthcertsystem.dto.PaymentRequest;
//import com.birthverification.birthcertsystem.model.Payment;
//import com.birthverification.birthcertsystem.repository.PaymentRepository;
//import com.birthverification.birthcertsystem.servisecertificate.PaymentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//import java.util.Random;
//
//@Service
//public class PaymentServiceImplementation implements PaymentService {
//
//    @Autowired
//    private PaymentRepository paymentRepository;
//
//    private static final int AMOUNT = 20000;
//
//    @Override
//    public Payment generateControlNumber(PaymentRequest request) {
//        String controlNumber = generateUniqueControlNumber();
//
//        Payment payment = new Payment();
//        payment.setUsername(request.getUsername());
//        payment.setCertificateNumber(request.getCertificateNumber());
//        payment.setControlNumber(controlNumber);
//        payment.setAmount(AMOUNT);
//        payment.setStatus("NOT_PAID");
//        payment.setPaymentDate(null); // Not yet paid
//
//        return paymentRepository.save(payment);
//    }
//
//    @Override
//    public Payment makePayment(PaymentConfirmationRequest request) {
//        Optional<Payment> paymentOpt = paymentRepository.findByControlNumber(request.getControlNumber());
//
//        if (paymentOpt.isEmpty()) {
//            throw new RuntimeException("Control number not found.");
//        }
//
//        Payment payment = paymentOpt.get();
//
//        if ("PAID".equalsIgnoreCase(payment.getStatus())) {
//            throw new RuntimeException("This payment is already completed.");
//        }
//
//        payment.setPaymentMethod(request.getPaymentMethod());
//        payment.setStatus("PAID");
//        payment.setPaymentDate(LocalDateTime.now());
//
//        return paymentRepository.save(payment);
//    }
//
//    @Override
//    public List<Payment> getAllPayments() {
//        return paymentRepository.findAll();
//    }
//
//    @Override
//    public Optional<Payment> getPaymentByControlNumber(String controlNumber) {
//        return paymentRepository.findByControlNumber(controlNumber);
//    }
//
//    @Override
//    public Optional<Payment> getPaymentByCertificateNumber(String certificateNumber) {
//        return paymentRepository.findByCertificateNumber(certificateNumber);
//    }
//
//    @Override
//    public Payment updatePayment(Long id, Payment updatedPayment) {
//        return paymentRepository.findById(id).map(existing -> {
//            existing.setUsername(updatedPayment.getUsername());
//            existing.setCertificateNumber(updatedPayment.getCertificateNumber());
//            existing.setControlNumber(updatedPayment.getControlNumber());
//            existing.setAmount(updatedPayment.getAmount());
//            existing.setPaymentMethod(updatedPayment.getPaymentMethod());
//            existing.setStatus(updatedPayment.getStatus());
//            existing.setPaymentDate(updatedPayment.getPaymentDate());
//            return paymentRepository.save(existing);
//        }).orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
//    }
//
//    @Override
//    public void deletePayment(Long id) {
//        if (!paymentRepository.existsById(id)) {
//            throw new RuntimeException("Payment not found for deletion.");
//        }
//        paymentRepository.deleteById(id);
//    }
//
//    // Helper method to generate unique control number
//    private String generateUniqueControlNumber() {
//        String controlNumber;
//        Random random = new Random();
//        do {
//            controlNumber = "CN" + (100000 + random.nextInt(900000));
//        } while (paymentRepository.findByControlNumber(controlNumber).isPresent());
//
//        return controlNumber;
//    }
//}


//package com.birthverification.birthcertsystem.service;
//
//import com.birthverification.birthcertsystem.dto.PaymentConfirmationRequest;
//import com.birthverification.birthcertsystem.dto.PaymentRequest;
//import com.birthverification.birthcertsystem.model.Payment;
//import com.birthverification.birthcertsystem.repository.PaymentRepository;
//import com.birthverification.birthcertsystem.servisecertificate.PaymentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//import java.util.Random;
//
//@Service
//public class PaymentServiceImplementation implements PaymentService {
//
//    @Autowired
//    private PaymentRepository paymentRepository;
//
//    private static final int AMOUNT = 20000;
//
//    @Override
//    public Payment generateControlNumber(PaymentRequest request) {
//        String controlNumber = generateUniqueControlNumber();
//
//        Payment payment = new Payment();
//        payment.setUsername(request.getUsername());
//        payment.setCertificateNumber(request.getCertificateNumber());
//        payment.setControlNumber(controlNumber);
//        payment.setAmount(AMOUNT);
//        payment.setStatus("NOT_PAID");
//        payment.setPaymentDate(null); // Not yet paid
//        payment.setDeleted(false); // Ensure active on creation
//
//        return paymentRepository.save(payment);
//    }
//
//    @Override
//    public Payment makePayment(PaymentConfirmationRequest request) {
//        Optional<Payment> paymentOpt = paymentRepository.findByControlNumber(request.getControlNumber());
//
//        if (paymentOpt.isEmpty()) {
//            throw new RuntimeException("Control number not found.");
//        }
//
//        Payment payment = paymentOpt.get();
//
//        if ("PAID".equalsIgnoreCase(payment.getStatus())) {
//            throw new RuntimeException("This payment is already completed.");
//        }
//
//        payment.setPaymentMethod(request.getPaymentMethod());
//        payment.setStatus("PAID");
//        payment.setPaymentDate(LocalDateTime.now());
//
//        return paymentRepository.save(payment);
//    }
//
//    @Override
//    public List<Payment> getAllActivePayments() {
//        return paymentRepository.findByDeletedFalse();
//    }
//
//    @Override
//    public List<Payment> getAllDeletedPayments() {
//        return paymentRepository.findByDeletedTrue();
//    }
//
//    @Override
//    public Optional<Payment> getPaymentByControlNumber(String controlNumber) {
//        return paymentRepository.findByControlNumber(controlNumber);
//    }
//
//    @Override
//    public Optional<Payment> getPaymentByCertificateNumber(String certificateNumber) {
//        return paymentRepository.findByCertificateNumber(certificateNumber);
//    }
//
//    @Override
//    public Payment updatePayment(Long id, Payment updatedPayment) {
//        return paymentRepository.findById(id).map(existing -> {
//            existing.setUsername(updatedPayment.getUsername());
//            existing.setCertificateNumber(updatedPayment.getCertificateNumber());
//            existing.setControlNumber(updatedPayment.getControlNumber());
//            existing.setAmount(updatedPayment.getAmount());
//            existing.setPaymentMethod(updatedPayment.getPaymentMethod());
//            existing.setStatus(updatedPayment.getStatus());
//            existing.setPaymentDate(updatedPayment.getPaymentDate());
//            return paymentRepository.save(existing);
//        }).orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
//    }
//
//    // Soft delete (move to Recycle Bin)
//    @Override
//    public void softDeletePayment(Long id) {
//        paymentRepository.findById(id).ifPresentOrElse(payment -> {
//            payment.setDeleted(true);
//            paymentRepository.save(payment);
//        }, () -> {
//            throw new RuntimeException("Payment not found with id: " + id);
//        });
//    }
//
//    // Restore from Recycle Bin
//    @Override
//    public void restorePayment(Long id) {
//        paymentRepository.findById(id).ifPresentOrElse(payment -> {
//            payment.setDeleted(false);
//            paymentRepository.save(payment);
//        }, () -> {
//            throw new RuntimeException("Payment not found with id: " + id);
//        });
//    }
//
//    // Permanently delete from DB
//    @Override
//    public void permanentlyDeletePayment(Long id) {
//        if (!paymentRepository.existsById(id)) {
//            throw new RuntimeException("Payment not found for permanent deletion.");
//        }
//        paymentRepository.deleteById(id);
//    }
//
//    // Helper method to generate unique control number
//    private String generateUniqueControlNumber() {
//        String controlNumber;
//        Random random = new Random();
//        do {
//            controlNumber = "CN" + (100000 + random.nextInt(900000));
//        } while (paymentRepository.findByControlNumber(controlNumber).isPresent());
//
//        return controlNumber;
//    }
//}



package com.birthverification.birthcertsystem.service;

import com.birthverification.birthcertsystem.dto.PaymentConfirmationRequest;
import com.birthverification.birthcertsystem.dto.PaymentRequest;
import com.birthverification.birthcertsystem.model.Payment;
import com.birthverification.birthcertsystem.repository.PaymentRepository;
import com.birthverification.birthcertsystem.servisecertificate.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class PaymentServiceImplementation implements PaymentService {

    private final PaymentRepository paymentRepository;
    private static final int AMOUNT = 20000;

    @Autowired
    public PaymentServiceImplementation(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment generateControlNumber(PaymentRequest request) {
        validatePaymentRequest(request);

        Payment payment = new Payment();
        payment.setUsername(request.getUsername().trim());
        payment.setCertificateNumber(request.getCertificateNumber().trim());
        payment.setControlNumber(generateUniqueControlNumber());
        payment.setAmount(AMOUNT);
        payment.setStatus("NOT_PAID");
        payment.setPaymentDate(null);
        payment.setDeleted(false);

        return paymentRepository.save(payment);
    }

    @Override
    public Payment makePayment(PaymentConfirmationRequest request) {
        validatePaymentConfirmationRequest(request);

        Payment payment = paymentRepository.findByControlNumber(request.getControlNumber())
                .orElseThrow(() -> new RuntimeException("Payment with control number " + request.getControlNumber() + " not found"));

        if ("PAID".equalsIgnoreCase(payment.getStatus())) {
            throw new RuntimeException("Payment already completed");
        }

        payment.setPaymentMethod(request.getPaymentMethod().trim());
        payment.setStatus("PAID");
        payment.setPaymentDate(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Payment> getAllActivePayments() {
        return paymentRepository.findByDeletedFalse();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Payment> getAllDeletedPayments() {
        return paymentRepository.findByDeletedTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Payment> getPaymentByControlNumber(String controlNumber) {
        return paymentRepository.findByControlNumber(controlNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Payment> getPaymentByCertificateNumber(String certificateNumber) {
        return paymentRepository.findByCertificateNumber(certificateNumber);
    }

    @Override
    public Payment updatePayment(Long id, Payment updatedPayment) {
        validatePaymentForUpdate(updatedPayment);

        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment with ID " + id + " not found"));

        // Update only allowed fields
        existingPayment.setUsername(updatedPayment.getUsername().trim());
        existingPayment.setCertificateNumber(updatedPayment.getCertificateNumber().trim());
        existingPayment.setAmount(updatedPayment.getAmount());
        existingPayment.setPaymentMethod(updatedPayment.getPaymentMethod().trim());
        existingPayment.setStatus(updatedPayment.getStatus());

        // Never update these fields through this method:
        // - controlNumber (should remain constant)
        // - paymentDate (only updated via makePayment)
        // - deleted (only updated via softDelete/restore)

        return paymentRepository.save(existingPayment);
    }

    @Override
    public void softDeletePayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment with ID " + id + " not found"));

        if (payment.isDeleted()) {
            throw new RuntimeException("Payment already deleted");
        }

        payment.setDeleted(true);
        paymentRepository.save(payment);
    }

    @Override
    public void restorePayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment with ID " + id + " not found"));

        if (!payment.isDeleted()) {
            throw new RuntimeException("Payment is not deleted");
        }

        payment.setDeleted(false);
        paymentRepository.save(payment);
    }

    @Override
    public void permanentlyDeletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new RuntimeException("Payment with ID " + id + " not found");
        }
        paymentRepository.deleteById(id);
    }

    // ============== PRIVATE HELPER METHODS ==============

    private String generateUniqueControlNumber() {
        String controlNumber;
        Random random = new Random();
        do {
            controlNumber = "CN" + (100000 + random.nextInt(900000));
        } while (paymentRepository.findByControlNumber(controlNumber).isPresent());

        return controlNumber;
    }

    private void validatePaymentRequest(PaymentRequest request) {
        if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
            throw new RuntimeException("Username is required");
        }

        if (request.getCertificateNumber() == null || request.getCertificateNumber().trim().isEmpty()) {
            throw new RuntimeException("Certificate number is required");
        }
    }

    private void validatePaymentConfirmationRequest(PaymentConfirmationRequest request) {
        if (request.getControlNumber() == null || request.getControlNumber().trim().isEmpty()) {
            throw new RuntimeException("Control number is required");
        }

        if (request.getPaymentMethod() == null || request.getPaymentMethod().trim().isEmpty()) {
            throw new RuntimeException("Payment method is required");
        }
    }

    private void validatePaymentForUpdate(Payment payment) {
        if (payment.getUsername() == null || payment.getUsername().trim().isEmpty()) {
            throw new RuntimeException("Username is required");
        }

        if (payment.getCertificateNumber() == null || payment.getCertificateNumber().trim().isEmpty()) {
            throw new RuntimeException("Certificate number is required");
        }

        if (payment.getAmount() == null || payment.getAmount() <= 0) {
            throw new RuntimeException("Valid amount is required");
        }

        if (payment.getStatus() == null ||
                (!"PAID".equalsIgnoreCase(payment.getStatus()) && !"NOT_PAID".equalsIgnoreCase(payment.getStatus()))) {
            throw new RuntimeException("Invalid payment status");
        }
    }


    //newwww

}




////mpyaaaa
//
//
//package com.birthverification.birthcertsystem.service;
//
//import com.birthverification.birthcertsystem.dto.PaymentConfirmationRequest;
//import com.birthverification.birthcertsystem.dto.PaymentRequest;
//import com.birthverification.birthcertsystem.model.Payment;
//import com.birthverification.birthcertsystem.repository.PaymentRepository;
//import com.birthverification.birthcertsystem.servisecertificate.PaymentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//import java.util.Random;
//
//@Service
//@Transactional
//public class PaymentServiceImplementation implements PaymentService {
//
//    private final PaymentRepository paymentRepository;
//    private static final int AMOUNT = 20000;
//
//    @Autowired
//    public PaymentServiceImplementation(PaymentRepository paymentRepository) {
//        this.paymentRepository = paymentRepository;
//    }
//
//    @Override
//    public Payment generateControlNumber(PaymentRequest request) {
//        validatePaymentRequest(request);
//
//        Payment payment = new Payment();
//        payment.setUsername(request.getUsername().trim());
//        payment.setCertificateNumber(request.getCertificateNumber().trim());
//        payment.setControlNumber(generateUniqueControlNumber());
//        payment.setAmount(AMOUNT);
//        payment.setStatus("NOT_PAID");
//        payment.setPaymentDate(null);
//        payment.setDeleted(false);
//
//        return paymentRepository.save(payment);
//    }
//
//    @Override
//    public Payment makePayment(PaymentConfirmationRequest request) {
//        validatePaymentConfirmationRequest(request);
//
//        Payment payment = paymentRepository.findByControlNumber(request.getControlNumber())
//                .orElseThrow(() -> new RuntimeException("Payment with control number " + request.getControlNumber() + " not found"));
//
//        if ("PAID".equalsIgnoreCase(payment.getStatus())) {
//            throw new RuntimeException("Payment already completed");
//        }
//
//        payment.setPaymentMethod(request.getPaymentMethod().trim());
//        payment.setStatus("PAID");
//        payment.setPaymentDate(LocalDateTime.now());
//
//        return paymentRepository.save(payment);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<Payment> getAllActivePayments() {
//        return paymentRepository.findByDeletedFalse();
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<Payment> getAllDeletedPayments() {
//        return paymentRepository.findByDeletedTrue();
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Optional<Payment> getPaymentByControlNumber(String controlNumber) {
//        return paymentRepository.findByControlNumber(controlNumber);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Optional<Payment> getPaymentByCertificateNumber(String certificateNumber) {
//        return paymentRepository.findByCertificateNumber(certificateNumber);
//    }
//
//    @Override
//    public Payment updatePayment(Long id, Payment updatedPayment) {
//        validatePaymentForUpdate(updatedPayment);
//
//        Payment existingPayment = paymentRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Payment with ID " + id + " not found"));
//
//        // Update only allowed fields
//        existingPayment.setUsername(updatedPayment.getUsername().trim());
//        existingPayment.setCertificateNumber(updatedPayment.getCertificateNumber().trim());
//        existingPayment.setAmount(updatedPayment.getAmount());
//        existingPayment.setPaymentMethod(updatedPayment.getPaymentMethod().trim());
//        existingPayment.setStatus(updatedPayment.getStatus());
//
//        // Never update these fields through this method:
//        // - controlNumber (should remain constant)
//        // - paymentDate (only updated via makePayment)
//        // - deleted (only updated via softDelete/restore)
//
//        return paymentRepository.save(existingPayment);
//    }
//
//    @Override
//    public void softDeletePayment(Long id) {
//        Payment payment = paymentRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Payment with ID " + id + " not found"));
//
//        if (payment.isDeleted()) {
//            throw new RuntimeException("Payment already deleted");
//        }
//
//        payment.setDeleted(true);
//        paymentRepository.save(payment);
//    }
//
//    @Override
//    public void restorePayment(Long id) {
//        Payment payment = paymentRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Payment with ID " + id + " not found"));
//
//        if (!payment.isDeleted()) {
//            throw new RuntimeException("Payment is not deleted");
//        }
//
//        payment.setDeleted(false);
//        paymentRepository.save(payment);
//    }
//
//    @Override
//    public void permanentlyDeletePayment(Long id) {
//        if (!paymentRepository.existsById(id)) {
//            throw new RuntimeException("Payment with ID " + id + " not found");
//        }
//        paymentRepository.deleteById(id);
//    }
//
//    // ============== PRIVATE HELPER METHODS ==============
//
//    private String generateUniqueControlNumber() {
//        String controlNumber;
//        Random random = new Random();
//        do {
//            controlNumber = "CN" + (100000 + random.nextInt(900000));
//        } while (paymentRepository.findByControlNumber(controlNumber).isPresent());
//
//        return controlNumber;
//    }
//
//    private void validatePaymentRequest(PaymentRequest request) {
//        if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
//            throw new RuntimeException("Username is required");
//        }
//
//        if (request.getCertificateNumber() == null || request.getCertificateNumber().trim().isEmpty()) {
//            throw new RuntimeException("Certificate number is required");
//        }
//    }
//
//    private void validatePaymentConfirmationRequest(PaymentConfirmationRequest request) {
//        if (request.getControlNumber() == null || request.getControlNumber().trim().isEmpty()) {
//            throw new RuntimeException("Control number is required");
//        }
//
//        if (request.getPaymentMethod() == null || request.getPaymentMethod().trim().isEmpty()) {
//            throw new RuntimeException("Payment method is required");
//        }
//    }
//
//    private void validatePaymentForUpdate(Payment payment) {
//        if (payment.getUsername() == null || payment.getUsername().trim().isEmpty()) {
//            throw new RuntimeException("Username is required");
//        }
//
//        if (payment.getCertificateNumber() == null || payment.getCertificateNumber().trim().isEmpty()) {
//            throw new RuntimeException("Certificate number is required");
//        }
//
//        if (payment.getAmount() == null || payment.getAmount() <= 0) {
//            throw new RuntimeException("Valid amount is required");
//        }
//
//        if (payment.getStatus() == null ||
//                (!"PAID".equalsIgnoreCase(payment.getStatus()) && !"NOT_PAID".equalsIgnoreCase(payment.getStatus()))) {
//            throw new RuntimeException("Invalid payment status");
//        }
//    }
//}