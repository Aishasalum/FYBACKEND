package com.birthverification.birthcertsystem.repository;

import com.birthverification.birthcertsystem.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Tafuta payment kwa control number
    Optional<Payment> findByControlNumber(String controlNumber);

    // Tafuta payment kwa certificate number
    Optional<Payment> findByCertificateNumber(String certificateNumber);
}
