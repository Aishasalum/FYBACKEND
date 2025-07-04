package com.birthverification.birthcertsystem.service;

import com.birthverification.birthcertsystem.dto.PaymentConfirmationRequest;
import com.birthverification.birthcertsystem.dto.PaymentRequest;
import com.birthverification.birthcertsystem.model.Payment;
import com.birthverification.birthcertsystem.repository.PaymentRepository;
import com.birthverification.birthcertsystem.servisecertificate.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PaymentServiceImplementation implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    private static final int AMOUNT = 20000;

    @Override
    public Payment generateControlNumber(PaymentRequest request) {
        String controlNumber = generateUniqueControlNumber();

        Payment payment = new Payment();
        payment.setUsername(request.getUsername());
        payment.setCertificateNumber(request.getCertificateNumber());
        payment.setControlNumber(controlNumber);
        payment.setAmount(AMOUNT);
        payment.setStatus("NOT_PAID");
        payment.setPaymentDate(null); // Not yet paid

        return paymentRepository.save(payment);
    }

    @Override
    public Payment makePayment(PaymentConfirmationRequest request) {
        Optional<Payment> paymentOpt = paymentRepository.findByControlNumber(request.getControlNumber());

        if (paymentOpt.isEmpty()) {
            throw new RuntimeException("Control number not found.");
        }

        Payment payment = paymentOpt.get();

        if ("PAID".equalsIgnoreCase(payment.getStatus())) {
            throw new RuntimeException("This payment is already completed.");
        }

        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setStatus("PAID");
        payment.setPaymentDate(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Optional<Payment> getPaymentByControlNumber(String controlNumber) {
        return paymentRepository.findByControlNumber(controlNumber);
    }

    @Override
    public Optional<Payment> getPaymentByCertificateNumber(String certificateNumber) {
        return paymentRepository.findByCertificateNumber(certificateNumber);
    }

    @Override
    public Payment updatePayment(Long id, Payment updatedPayment) {
        return paymentRepository.findById(id).map(existing -> {
            existing.setUsername(updatedPayment.getUsername());
            existing.setCertificateNumber(updatedPayment.getCertificateNumber());
            existing.setControlNumber(updatedPayment.getControlNumber());
            existing.setAmount(updatedPayment.getAmount());
            existing.setPaymentMethod(updatedPayment.getPaymentMethod());
            existing.setStatus(updatedPayment.getStatus());
            existing.setPaymentDate(updatedPayment.getPaymentDate());
            return paymentRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
    }

    @Override
    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new RuntimeException("Payment not found for deletion.");
        }
        paymentRepository.deleteById(id);
    }

    // Helper method to generate unique control number
    private String generateUniqueControlNumber() {
        String controlNumber;
        Random random = new Random();
        do {
            controlNumber = "CN" + (100000 + random.nextInt(900000));
        } while (paymentRepository.findByControlNumber(controlNumber).isPresent());

        return controlNumber;
    }
}
