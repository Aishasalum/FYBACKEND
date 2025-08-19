//package com.birthverification.birthcertsystem.controller;
//
//import com.birthverification.birthcertsystem.dto.PaymentConfirmationRequest;
//import com.birthverification.birthcertsystem.dto.PaymentRequest;
//import com.birthverification.birthcertsystem.model.Payment;
//import com.birthverification.birthcertsystem.servisecertificate.PaymentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/payments")
//@CrossOrigin(origins = "*") // Ruhusu frontend kupata access
//public class PaymentController {
//
//    @Autowired
//    private PaymentService paymentService;
//
//    // 1. Generate control number
//    @PostMapping("/generate-control-number")
//    public ResponseEntity<Payment> generateControlNumber(
//            @RequestBody PaymentRequest request) {
//        Payment payment = paymentService.generateControlNumber(request);
//        return ResponseEntity.ok(payment);
//    }
//
//    // 2. Make payment
//    @PostMapping("/make-payment")
//    public ResponseEntity<?> makePayment(
//            @RequestBody PaymentConfirmationRequest request) {
//        try {
//            Payment payment = paymentService.makePayment(request);
//            return ResponseEntity.ok(payment);
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    // 3. Get all payments
//    @GetMapping
//    public List<Payment> getAllPayments() {
//        return paymentService.getAllPayments();
//    }
//
//    // 4. Get payment by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getPaymentById(@PathVariable Long id) {
//        Optional<Payment> paymentOpt = paymentService.getAllPayments()
//                .stream()
//                .filter(p -> p.getId().equals(id))
//                .findFirst();
//
//        return paymentOpt.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    // 5. Update full payment
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updatePayment(@PathVariable Long id, @RequestBody Payment updatedPayment) {
//        try {
//            Payment updated = paymentService.updatePayment(id, updatedPayment);
//            return ResponseEntity.ok(updated);
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    // 6. Delete payment
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deletePayment(@PathVariable Long id) {
//        try {
//            paymentService.deletePayment(id);
//            return ResponseEntity.ok("Payment deleted successfully.");
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}


package com.birthverification.birthcertsystem.controller;

import com.birthverification.birthcertsystem.dto.PaymentConfirmationRequest;
import com.birthverification.birthcertsystem.dto.PaymentRequest;
import com.birthverification.birthcertsystem.model.Payment;
import com.birthverification.birthcertsystem.servisecertificate.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // 1. Generate control number
    @PostMapping("/generate-control-number")
    public ResponseEntity<Payment> generateControlNumber(
            @RequestBody PaymentRequest request) {
        Payment payment = paymentService.generateControlNumber(request);
        return ResponseEntity.ok(payment);
    }

    // 2. Make payment
    @PostMapping("/make-payment")
    public ResponseEntity<?> makePayment(
            @RequestBody PaymentConfirmationRequest request) {
        try {
            Payment payment = paymentService.makePayment(request);
            return ResponseEntity.ok(payment);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 3. Get all active payments (not deleted)
    @GetMapping("/active")
    public ResponseEntity<List<Payment>> getAllActivePayments() {
        List<Payment> payments = paymentService.getAllActivePayments();
        return ResponseEntity.ok(payments);
    }

    // 4. Get all deleted payments (Recycle Bin)
    @GetMapping("/deleted")
    public ResponseEntity<List<Payment>> getAllDeletedPayments() {
        List<Payment> payments = paymentService.getAllDeletedPayments();
        return ResponseEntity.ok(payments);
    }

    // 5. Get payment by ID (only active payments)
    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable Long id) {
        Optional<Payment> paymentOpt = paymentService.getAllActivePayments()
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        return paymentOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 6. Update payment
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePayment(@PathVariable Long id, @RequestBody Payment updatedPayment) {
        try {
            Payment updated = paymentService.updatePayment(id, updatedPayment);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 7. Soft delete (move to Recycle Bin)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> softDeletePayment(@PathVariable Long id) {
        try {
            paymentService.softDeletePayment(id);
            return ResponseEntity.ok("Payment moved to trash (soft deleted).");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 8. Restore payment from Recycle Bin
    @PutMapping("/restore/{id}")
    public ResponseEntity<?> restorePayment(@PathVariable Long id) {
        try {
            paymentService.restorePayment(id);
            return ResponseEntity.ok("Payment restored successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 9. Permanently delete payment
    @DeleteMapping("/permanent-delete/{id}")
    public ResponseEntity<?> permanentlyDeletePayment(@PathVariable Long id) {
        try {
            paymentService.permanentlyDeletePayment(id);
            return ResponseEntity.ok("Payment permanently deleted.");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


}


//new


//package com.birthverification.birthcertsystem.controller;
//
//import com.birthverification.birthcertsystem.dto.PaymentConfirmationRequest;
//import com.birthverification.birthcertsystem.dto.PaymentRequest;
//import com.birthverification.birthcertsystem.model.Payment;
//import com.birthverification.birthcertsystem.servisecertificate.PaymentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/payments")
//@CrossOrigin(origins = "*")
//public class PaymentController {
//
//    @Autowired
//    private PaymentService paymentService;
//
//    // 1. Generate control number
//    @PostMapping("/generate-control-number")
//    public ResponseEntity<Payment> generateControlNumber(
//            @RequestBody PaymentRequest request) {
//        Payment payment = paymentService.generateControlNumber(request);
//        return ResponseEntity.ok(payment);
//    }
//
//    // 2. Make payment
//    @PostMapping("/make-payment")
//    public ResponseEntity<?> makePayment(
//            @RequestBody PaymentConfirmationRequest request) {
//        try {
//            Payment payment = paymentService.makePayment(request);
//            return ResponseEntity.ok(payment);
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    // 3. Get all active payments (not deleted)
//    @GetMapping("/active")
//    public ResponseEntity<List<Payment>> getAllActivePayments() {
//        List<Payment> payments = paymentService.getAllActivePayments();
//        return ResponseEntity.ok(payments);
//    }
//
//    // 4. Get all deleted payments (Recycle Bin)
//    @GetMapping("/deleted")
//    public ResponseEntity<List<Payment>> getAllDeletedPayments() {
//        List<Payment> payments = paymentService.getAllDeletedPayments();
//        return ResponseEntity.ok(payments);
//    }
//
//    // 5. Get payment by ID (only active payments)
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getPaymentById(@PathVariable Long id) {
//        Optional<Payment> paymentOpt = paymentService.getAllActivePayments()
//                .stream()
//                .filter(p -> p.getId().equals(id))
//                .findFirst();
//
//        return paymentOpt.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    // 6. Update payment
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updatePayment(@PathVariable Long id, @RequestBody Payment updatedPayment) {
//        try {
//            Payment updated = paymentService.updatePayment(id, updatedPayment);
//            return ResponseEntity.ok(updated);
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    // 7. Soft delete (move to Recycle Bin)
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> softDeletePayment(@PathVariable Long id) {
//        try {
//            paymentService.softDeletePayment(id);
//            return ResponseEntity.ok("Payment moved to trash (soft deleted).");
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    // 8. Restore payment from Recycle Bin
//    @PutMapping("/restore/{id}")
//    public ResponseEntity<?> restorePayment(@PathVariable Long id) {
//        try {
//            paymentService.restorePayment(id);
//            return ResponseEntity.ok("Payment restored successfully.");
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    // 9. Permanently delete payment
//    @DeleteMapping("/permanent-delete/{id}")
//    public ResponseEntity<?> permanentlyDeletePayment(@PathVariable Long id) {
//        try {
//            paymentService.permanentlyDeletePayment(id);
//            return ResponseEntity.ok("Payment permanently deleted.");
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}