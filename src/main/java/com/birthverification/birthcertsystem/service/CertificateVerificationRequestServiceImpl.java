//package com.birthverification.birthcertsystem.service;
//
//import com.birthverification.birthcertsystem.dto.CertificateVerificationRequestDTO;
//import com.birthverification.birthcertsystem.enums.MatchResult;
//import com.birthverification.birthcertsystem.enums.VerificationStatus;
//import com.birthverification.birthcertsystem.exception.ResourceNotFoundException;
//import com.birthverification.birthcertsystem.model.BirthCertificateRecord;
//import com.birthverification.birthcertsystem.model.CertificateVerificationRequest;
//import com.birthverification.birthcertsystem.model.User;
//import com.birthverification.birthcertsystem.repository.BirthCertificateRecordRepository;
//import com.birthverification.birthcertsystem.repository.CertificateVerificationRequestRepository;
//import com.birthverification.birthcertsystem.repository.UserRepository;
//import com.birthverification.birthcertsystem.servisecertificate.CertificateVerificationRequestService;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class CertificateVerificationRequestServiceImpl implements CertificateVerificationRequestService {
//
//    private final CertificateVerificationRequestRepository requestRepository;
//    private final BirthCertificateRecordRepository birthCertificateRecordRepository;
//    private final UserRepository userRepository;
//
//    public CertificateVerificationRequestServiceImpl(
//            CertificateVerificationRequestRepository requestRepository,
//            BirthCertificateRecordRepository birthCertificateRecordRepository,
//            UserRepository userRepository
//    ) {
//        this.requestRepository = requestRepository;
//        this.birthCertificateRecordRepository = birthCertificateRecordRepository;
//        this.userRepository = userRepository;
//    }
//
//    /**
//     * Tengeneza verification request bila ku-save.
//     */
//    @Override
//    public CertificateVerificationRequest createVerificationRequest(CertificateVerificationRequestDTO dto) {
//        // 1. Tafuta user
//        User user = userRepository.findById(dto.getUserId())
//                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + dto.getUserId()));
//
//        // 2. Tengeneza request mpya
//        CertificateVerificationRequest request = new CertificateVerificationRequest();
//        request.setCertificateNumber(dto.getCertificateNumber());
//        request.setChildName(dto.getChildName());
//        request.setDateOfBirth(dto.getDateOfBirth());
//        request.setPlaceOfBirth(dto.getPlaceOfBirth());
//        request.setGender(dto.getGender());
//        request.setFatherName(dto.getFatherName());
//        request.setMotherName(dto.getMotherName());
//        request.setResidentOfParents(dto.getResidentOfParents());
//        request.setInformantName(dto.getInformantName());
//        request.setDateOfIssue(dto.getDateOfIssue());
//        request.setStatus(VerificationStatus.PENDING);
//        request.setUser(user);
//
//        // 3. Matching
//        Optional<BirthCertificateRecord> matchedRecord = birthCertificateRecordRepository.findByMultipleFields(
//                dto.getCertificateNumber(),
//                dto.getChildName(),
//                dto.getDateOfBirth(),
//                dto.getPlaceOfBirth(),
//                dto.getGender(),
//                dto.getFatherName(),
//                dto.getMotherName(),
//                dto.getResidentOfParents(),
//                dto.getInformantName(),
//                dto.getDateOfIssue()
//        );
//
//        if (matchedRecord.isPresent()) {
//            request.setMatchResult(MatchResult.MATCHED);
//            request.setMatchedRecord(matchedRecord.get());
//        } else {
//            request.setMatchResult(MatchResult.NOT_MATCHED);
//        }
//
//        return request; // ❌ No save here
//    }
//
//    /**
//     * Save request that has full data (including uploaded certificate).
//     */
//    @Override
//    public CertificateVerificationRequest saveWithCertificate(CertificateVerificationRequest request) {
//        return requestRepository.save(request);
//    }
//
//    @Override
//    public List<CertificateVerificationRequest> getAllRequests() {
//        return requestRepository.findAll();
//    }
//
//    @Override
//    public Optional<CertificateVerificationRequest> getRequestById(Long id) {
//        return requestRepository.findById(id);
//    }
//
//    @Override
//    public CertificateVerificationRequest updateVerificationRequest(Long id, CertificateVerificationRequestDTO dto) {
//        CertificateVerificationRequest request = requestRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Verification request not found with ID: " + id));
//
//        request.setCertificateNumber(dto.getCertificateNumber());
//        request.setChildName(dto.getChildName());
//        request.setDateOfBirth(dto.getDateOfBirth());
//        request.setPlaceOfBirth(dto.getPlaceOfBirth());
//        request.setGender(dto.getGender());
//        request.setFatherName(dto.getFatherName());
//        request.setMotherName(dto.getMotherName());
//        request.setResidentOfParents(dto.getResidentOfParents());
//        request.setInformantName(dto.getInformantName());
//        request.setDateOfIssue(dto.getDateOfIssue());
//
//        return requestRepository.save(request);
//    }
//
//    @Override
//    public void deleteRequest(Long id) {
//        requestRepository.deleteById(id);
//    }
//}



package com.birthverification.birthcertsystem.service;

import com.birthverification.birthcertsystem.dto.CertificateVerificationRequestDTO;
import com.birthverification.birthcertsystem.enums.MatchResult;
import com.birthverification.birthcertsystem.enums.VerificationStatus;
import com.birthverification.birthcertsystem.exception.ResourceNotFoundException;
import com.birthverification.birthcertsystem.model.BirthCertificateRecord;
import com.birthverification.birthcertsystem.model.CertificateVerificationRequest;
import com.birthverification.birthcertsystem.model.User;
import com.birthverification.birthcertsystem.repository.BirthCertificateRecordRepository;
import com.birthverification.birthcertsystem.repository.CertificateVerificationRequestRepository;
import com.birthverification.birthcertsystem.repository.UserRepository;
import com.birthverification.birthcertsystem.servisecertificate.CertificateVerificationRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CertificateVerificationRequestServiceImpl implements CertificateVerificationRequestService {

    private final CertificateVerificationRequestRepository requestRepository;
    private final BirthCertificateRecordRepository birthCertificateRecordRepository;
    private final UserRepository userRepository;

    public CertificateVerificationRequestServiceImpl(
            CertificateVerificationRequestRepository requestRepository,
            BirthCertificateRecordRepository birthCertificateRecordRepository,
            UserRepository userRepository
    ) {
        this.requestRepository = requestRepository;
        this.birthCertificateRecordRepository = birthCertificateRecordRepository;
        this.userRepository = userRepository;
    }

    /**
     * Tengeneza verification request bila ku-save.
     */
    @Override
    public CertificateVerificationRequest createVerificationRequest(CertificateVerificationRequestDTO dto) {
        // 1. Tafuta user
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + dto.getUserId()));

        // 2. Tengeneza request mpya
        CertificateVerificationRequest request = new CertificateVerificationRequest();
        request.setCertificateNumber(dto.getCertificateNumber());
        request.setChildName(dto.getChildName());
        request.setDateOfBirth(dto.getDateOfBirth());
        request.setPlaceOfBirth(dto.getPlaceOfBirth());
        request.setGender(dto.getGender());
        request.setFatherName(dto.getFatherName());
        request.setMotherName(dto.getMotherName());
        request.setResidentOfParents(dto.getResidentOfParents());
        request.setInformantName(dto.getInformantName());
        request.setDateOfIssue(dto.getDateOfIssue());
        request.setStatus(VerificationStatus.PENDING);
        request.setUser(user);

        // 3. Matching using flexible Java logic (no strict SQL match)
        List<BirthCertificateRecord> allRecords = birthCertificateRecordRepository.findAll();

        Optional<BirthCertificateRecord> matchedRecord = allRecords.stream().filter(record ->
                safeEquals(record.getCertificateNumber(), dto.getCertificateNumber()) &&
                        safeEquals(record.getChildName(), dto.getChildName()) &&
                        safeEqualsDate(record.getDateOfBirth(), dto.getDateOfBirth()) &&
                        safeEquals(record.getPlaceOfBirth(), dto.getPlaceOfBirth()) &&
                        safeEquals(record.getGender(), dto.getGender()) &&
                        safeEquals(record.getFatherName(), dto.getFatherName()) &&
                        safeEquals(record.getMotherName(), dto.getMotherName()) &&
                        safeEquals(record.getResidentOfParents(), dto.getResidentOfParents()) &&
                        safeEquals(record.getInformantName(), dto.getInformantName()) &&
                        safeEqualsDate(record.getDateOfIssue(), dto.getDateOfIssue())
        ).findFirst();

        if (matchedRecord.isPresent()) {
            request.setMatchResult(MatchResult.MATCHED);
            request.setMatchedRecord(matchedRecord.get());
        } else {
            request.setMatchResult(MatchResult.NOT_MATCHED);
        }

        return request; // still no save here
    }

    /**
     * Save request that has full data (including uploaded certificate).
     */
    @Override
    public CertificateVerificationRequest saveWithCertificate(CertificateVerificationRequest request) {
        return requestRepository.save(request);
    }

    @Override
    public List<CertificateVerificationRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    public Optional<CertificateVerificationRequest> getRequestById(Long id) {
        return requestRepository.findById(id);
    }

    @Override
    public CertificateVerificationRequest updateVerificationRequest(Long id, CertificateVerificationRequestDTO dto) {
        CertificateVerificationRequest request = requestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Verification request not found with ID: " + id));

        request.setCertificateNumber(dto.getCertificateNumber());
        request.setChildName(dto.getChildName());
        request.setDateOfBirth(dto.getDateOfBirth());
        request.setPlaceOfBirth(dto.getPlaceOfBirth());
        request.setGender(dto.getGender());
        request.setFatherName(dto.getFatherName());
        request.setMotherName(dto.getMotherName());
        request.setResidentOfParents(dto.getResidentOfParents());
        request.setInformantName(dto.getInformantName());
        request.setDateOfIssue(dto.getDateOfIssue());

        return requestRepository.save(request);
    }

    @Override
    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }

    // ========== FLEXIBLE MATCHING HELPERS ==========

    private boolean safeEquals(String a, String b) {
        if (a == null || b == null) return false;
        return a.trim().equalsIgnoreCase(b.trim());
    }

    private boolean safeEqualsDate(LocalDate a, LocalDate b) {
        return a != null && b != null && a.equals(b);
    }
}
