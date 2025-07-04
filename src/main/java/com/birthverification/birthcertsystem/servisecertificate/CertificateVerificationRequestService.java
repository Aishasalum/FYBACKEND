package com.birthverification.birthcertsystem.servisecertificate;

import com.birthverification.birthcertsystem.dto.CertificateVerificationRequestDTO;
import com.birthverification.birthcertsystem.model.CertificateVerificationRequest;

import java.util.List;
import java.util.Optional;

public interface CertificateVerificationRequestService {

    /**
     * Tengeneza object ya verification bila kuihifadhi.
     */
    CertificateVerificationRequest createVerificationRequest(CertificateVerificationRequestDTO dto);

    /**
     * Hifadhi verification request yenye certificate iliyowekwa.
     */
    CertificateVerificationRequest saveWithCertificate(CertificateVerificationRequest request);

    /**
     * Pata list ya maombi yote ya verification.
     */
    List<CertificateVerificationRequest> getAllRequests();

    /**
     * Pata maombi ya verification kwa ID.
     */
    Optional<CertificateVerificationRequest> getRequestById(Long id);

    /**
     * Update taarifa za verification request.
     */
    CertificateVerificationRequest updateVerificationRequest(Long id, CertificateVerificationRequestDTO dto);

    /**
     * Futa verification request kwa ID.
     */
    void deleteRequest(Long id);
}
