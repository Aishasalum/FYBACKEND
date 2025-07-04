package com.birthverification.birthcertsystem.service;

import com.birthverification.birthcertsystem.model.VerifierOfficer;
import com.birthverification.birthcertsystem.repository.VerifierOfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VerifierOfficerService {

    @Autowired
    private VerifierOfficerRepository repository;

    // Add Verifier
    public VerifierOfficer addVerifier(VerifierOfficer verifier) {
        return repository.save(verifier);
    }

    // Get all Verifiers
    public List<VerifierOfficer> getAll() {
        return repository.findAll();
    }

    // Get by Username (for login)
    public Optional<VerifierOfficer> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    // ✅ Get by ID
    public Optional<VerifierOfficer> getById(Long id) {
        return repository.findById(id);
    }

    // ✅ Update Verifier
    public VerifierOfficer updateVerifier(Long id, VerifierOfficer updatedVerifier) {
        Optional<VerifierOfficer> optional = repository.findById(id);
        if (optional.isPresent()) {
            VerifierOfficer existing = optional.get();
            existing.setFullName(updatedVerifier.getFullName());
            existing.setEmail(updatedVerifier.getEmail());
            existing.setUsername(updatedVerifier.getUsername());
            existing.setPassword(updatedVerifier.getPassword());
            existing.setPhone(updatedVerifier.getPhone());
            existing.setGender(updatedVerifier.getGender());
            existing.setRole(updatedVerifier.getRole());
            existing.setActive(updatedVerifier.isActive());
            return repository.save(existing);
        }
        return null;
    }

    // ✅ Delete Verifier
    public String deleteVerifier(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Verifier officer with ID " + id + " has been deleted.";
        }
        return "Verifier officer not found.";
    }
}
