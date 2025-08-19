//package com.birthverification.birthcertsystem.service;
//
//import com.birthverification.birthcertsystem.dto.VerifierOfficerDTO;
//import com.birthverification.birthcertsystem.model.VerifierOfficer;
//import com.birthverification.birthcertsystem.repository.VerifierOfficerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class VerifierOfficerService {
//
//    @Autowired
//    private VerifierOfficerRepository repository;
//
//    // Helper method: convert entity to DTO
//    private VerifierOfficerDTO convertToDTO(VerifierOfficer officer) {
//        if (officer == null) return null;
//        return new VerifierOfficerDTO(
//                officer.getId(),
//                officer.getFullName(),
//                officer.getEmail(),
//                officer.getUsername(),
//                officer.getPhone(),
//                officer.getGender(),
//                officer.getRole(),
//                officer.isActive()
//        );
//    }
//
//    // Add Verifier (returns DTO)
//    public VerifierOfficerDTO addVerifier(VerifierOfficer verifier) {
//        VerifierOfficer saved = repository.save(verifier);
//        return convertToDTO(saved);
//    }
//
//    // Get all Verifiers as DTOs
//    public List<VerifierOfficerDTO> getAll() {
//        return repository.findAll()
//                .stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    // Get by Username as DTO (for general use)
//    public Optional<VerifierOfficerDTO> findByUsername(String username) {
//        return repository.findByUsername(username).map(this::convertToDTO);
//    }
//
//    // NEW: Get VerifierOfficer entity by username (for login password check)
//    public Optional<VerifierOfficer> findEntityByUsername(String username) {
//        return repository.findByUsername(username);
//    }
//
//    // Get by ID as DTO
//    public Optional<VerifierOfficerDTO> getById(Long id) {
//        return repository.findById(id).map(this::convertToDTO);
//    }
//
//    // Update Verifier (returns DTO)
//    public VerifierOfficerDTO updateVerifier(Long id, VerifierOfficer updatedVerifier) {
//        Optional<VerifierOfficer> optional = repository.findById(id);
//        if (optional.isPresent()) {
//            VerifierOfficer existing = optional.get();
//            existing.setFullName(updatedVerifier.getFullName());
//            existing.setEmail(updatedVerifier.getEmail());
//            existing.setUsername(updatedVerifier.getUsername());
//            existing.setPassword(updatedVerifier.getPassword());
//            existing.setPhone(updatedVerifier.getPhone());
//            existing.setGender(updatedVerifier.getGender());
//            existing.setRole(updatedVerifier.getRole());
//            existing.setActive(updatedVerifier.isActive());
//            VerifierOfficer saved = repository.save(existing);
//            return convertToDTO(saved);
//        }
//        return null;
//    }
//
//    // Delete Verifier
//    public String deleteVerifier(Long id) {
//        if (repository.existsById(id)) {
//            repository.deleteById(id);
//            return "Verifier officer with ID " + id + " has been deleted.";
//        }
//        return "Verifier officer not found.";
//    }
//}




//mpyaaa with hashed password


package com.birthverification.birthcertsystem.service;

import com.birthverification.birthcertsystem.dto.VerifierOfficerDTO;
import com.birthverification.birthcertsystem.model.VerifierOfficer;
import com.birthverification.birthcertsystem.repository.VerifierOfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VerifierOfficerService {

    @Autowired
    private VerifierOfficerRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private VerifierOfficerDTO convertToDTO(VerifierOfficer officer) {
        if (officer == null) return null;
        return new VerifierOfficerDTO(
                officer.getId(),
                officer.getFullName(),
                officer.getEmail(),
                officer.getUsername(),
                officer.getPhone(),
                officer.getGender(),
                officer.getRole(),
                officer.isActive()
        );
    }

    public VerifierOfficerDTO addVerifier(VerifierOfficer verifier) {
        verifier.setPassword(passwordEncoder.encode(verifier.getPassword()));
        VerifierOfficer saved = repository.save(verifier);
        return convertToDTO(saved);
    }

    public List<VerifierOfficerDTO> getAll() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<VerifierOfficer> findByEmailOrUsername(String identifier) {
        Optional<VerifierOfficer> byEmail = repository.findByEmail(identifier);
        return byEmail.isPresent() ? byEmail : repository.findByUsername(identifier);
    }

    public Optional<VerifierOfficerDTO> getById(Long id) {
        return repository.findById(id).map(this::convertToDTO);
    }

    public VerifierOfficerDTO updateVerifier(Long id, VerifierOfficer updatedVerifier) {
        Optional<VerifierOfficer> optional = repository.findById(id);
        if (optional.isPresent()) {
            VerifierOfficer existing = optional.get();
            existing.setFullName(updatedVerifier.getFullName());
            existing.setEmail(updatedVerifier.getEmail());
            existing.setUsername(updatedVerifier.getUsername());
            if (updatedVerifier.getPassword() != null && !updatedVerifier.getPassword().isBlank()) {
                existing.setPassword(passwordEncoder.encode(updatedVerifier.getPassword()));
            }
            existing.setPhone(updatedVerifier.getPhone());
            existing.setGender(updatedVerifier.getGender());
            existing.setRole(updatedVerifier.getRole());
            existing.setActive(updatedVerifier.isActive());
            return convertToDTO(repository.save(existing));
        }
        return null;
    }

    public String deleteVerifier(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Verifier officer with ID " + id + " has been deleted.";
        }
        return "Verifier officer not found.";
    }
}

