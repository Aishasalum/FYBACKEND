//package com.birthverification.birthcertsystem.controller;
//
//import com.birthverification.birthcertsystem.dto.CertificateVerificationRequestDTO;
//import com.birthverification.birthcertsystem.exception.ResourceNotFoundException;
//import com.birthverification.birthcertsystem.model.Certificate;
//import com.birthverification.birthcertsystem.model.CertificateVerificationRequest;
//import com.birthverification.birthcertsystem.repository.CertificateRepository;
//import com.birthverification.birthcertsystem.service.CertificateVerificationRequestServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/verification-requests")
//@CrossOrigin(origins = "*") // allow all origins for development/testing
//public class CertificateVerificationRequestController {
//
//    @Autowired
//    private CertificateVerificationRequestServiceImpl requestService;
//
//    @Autowired
//    private CertificateRepository certificateRepository;
//
//    // ✅ Create Verification Request (with uploadedCertificate)
//    @PostMapping("/create")
//    public ResponseEntity<CertificateVerificationRequest> createRequest(
//            @RequestBody CertificateVerificationRequestDTO dto,
//            @RequestParam Long uploadedCertificateId
//    ) {
//        Certificate uploadedCert = certificateRepository.findById(uploadedCertificateId)
//                .orElseThrow(() -> new ResourceNotFoundException("Certificate not found with ID: " + uploadedCertificateId));
//
//        CertificateVerificationRequest request = requestService.createVerificationRequest(dto);
//        request.setUploadedCertificate(uploadedCert);
//
//        return ResponseEntity.ok(requestService.saveWithCertificate(request));
//    }
//
//    // ✅ Get all verification requests
//    @GetMapping("/all")
//    public ResponseEntity<List<CertificateVerificationRequest>> getAll() {
//        return ResponseEntity.ok(requestService.getAllRequests());
//    }
//
//    // ✅ Get specific request by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<CertificateVerificationRequest> getById(@PathVariable Long id) {
//        return requestService.getRequestById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    // ✅ Delete request
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        requestService.deleteRequest(id);
//        return ResponseEntity.noContent().build();
//    }
//}



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

    // ✅ Create Verification Request with certificate
    @PostMapping("/create")
    public ResponseEntity<CertificateVerificationRequest> createRequest(
            @RequestBody CertificateVerificationRequestDTO dto,
            @RequestParam Long uploadedCertificateId
    ) {
        try {
            // Check certificate first
            Certificate uploadedCert = certificateRepository.findById(uploadedCertificateId)
                    .orElseThrow(() -> new ResourceNotFoundException("Certificate not found with ID: " + uploadedCertificateId));

            // Then create request
            CertificateVerificationRequest request = requestService.createVerificationRequest(dto);
            request.setUploadedCertificate(uploadedCert);

            return ResponseEntity.ok(requestService.saveWithCertificate(request));
        } catch (Exception e) {
            e.printStackTrace(); // Log the actual error
            return ResponseEntity.internalServerError().build();
        }
    }
    // ✅ Get all (active + deleted) requests — optional
    @GetMapping("/all")
    public ResponseEntity<List<CertificateVerificationRequest>> getAll() {
        return ResponseEntity.ok(requestService.getAllRequests());
    }

    // ✅ Get only active requests
    @GetMapping("/active")
    public ResponseEntity<List<CertificateVerificationRequest>> getActiveRequests() {
        return ResponseEntity.ok(requestService.getAllActiveRequests());
    }

    // ✅ Get only deleted requests (in trash)
    @GetMapping("/deleted")
    public ResponseEntity<List<CertificateVerificationRequest>> getDeletedRequests() {
        return ResponseEntity.ok(requestService.getAllDeletedRequests());
    }

    // ✅ Get request by ID
    @GetMapping("/{id}")
    public ResponseEntity<CertificateVerificationRequest> getById(@PathVariable Long id) {
        return requestService.getRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Update request by ID
    @PutMapping("/{id}")
    public ResponseEntity<CertificateVerificationRequest> updateRequest(
            @PathVariable Long id,
            @RequestBody CertificateVerificationRequestDTO dto
    ) {
        CertificateVerificationRequest updated = requestService.updateVerificationRequest(id, dto);
        return ResponseEntity.ok(updated);
    }

    // ✅ Soft delete (move to trash)
    @DeleteMapping("/soft-delete/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable Long id) {
        requestService.softDeleteRequest(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Restore soft deleted request
    @PutMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        requestService.restoreRequest(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Hard delete (permanent)
    @DeleteMapping("/hard-delete/{id}")
    public ResponseEntity<Void> hardDelete(@PathVariable Long id) {
        requestService.hardDeleteRequest(id);
        return ResponseEntity.noContent().build();
    }
}
