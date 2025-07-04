package com.birthverification.birthcertsystem.repository;

import com.birthverification.birthcertsystem.enums.MatchResult;
import com.birthverification.birthcertsystem.enums.VerificationStatus;
import com.birthverification.birthcertsystem.model.CertificateVerificationRequest;
import com.birthverification.birthcertsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateVerificationRequestRepository extends JpaRepository<CertificateVerificationRequest, Long> {

    // Get all requests by a specific user
    List<CertificateVerificationRequest> findByUser(User user);

    // Get all requests by verification status
    List<CertificateVerificationRequest> findByStatus(VerificationStatus status);

    // Get all requests by match result
    List<CertificateVerificationRequest> findByMatchResult(MatchResult matchResult);

    // Get all sorted by creation date descending
    List<CertificateVerificationRequest> findAllByOrderByCreatedAtDesc();
}
