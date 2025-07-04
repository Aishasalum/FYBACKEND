package com.birthverification.birthcertsystem.controller;

import com.birthverification.birthcertsystem.dto.CertificateVerificationRequestDTO;
import com.birthverification.birthcertsystem.exception.ResourceNotFoundException;
import com.birthverification.birthcertsystem.model.Certificate;
import com.birthverification.birthcertsystem.model.CertificateVerificationRequest;
import com.birthverification.birthcertsystem.repository.CertificateRepository;
import com.birthverification.birthcertsystem.service.CertificateVerificationRequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/verification-requests")
@CrossOrigin(origins = "*") // allow all origins for development/testing
public class CertificateVerificationRequestController {

    @Autowired
    private CertificateVerificationRequestServiceImpl requestService;

    @Autowired
    private CertificateRepository certificateRepository;

    // ✅ Create Verification Request (with uploadedCertificate)
    @PostMapping("/create")
    public ResponseEntity<CertificateVerificationRequest> createRequest(
            @RequestBody CertificateVerificationRequestDTO dto,
            @RequestParam Long uploadedCertificateId
    ) {
        Certificate uploadedCert = certificateRepository.findById(uploadedCertificateId)
                .orElseThrow(() -> new ResourceNotFoundException("Certificate not found with ID: " + uploadedCertificateId));

        CertificateVerificationRequest request = requestService.createVerificationRequest(dto);
        request.setUploadedCertificate(uploadedCert);

        return ResponseEntity.ok(requestService.saveWithCertificate(request));
    }

    // ✅ Get all verification requests
    @GetMapping("/all")
    public ResponseEntity<List<CertificateVerificationRequest>> getAll() {
        return ResponseEntity.ok(requestService.getAllRequests());
    }

    // ✅ Get specific request by ID
    @GetMapping("/{id}")
    public ResponseEntity<CertificateVerificationRequest> getById(@PathVariable Long id) {
        return requestService.getRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Delete request
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        requestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }
}
