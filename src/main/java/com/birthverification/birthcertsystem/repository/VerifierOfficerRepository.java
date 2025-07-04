package com.birthverification.birthcertsystem.repository;

import com.birthverification.birthcertsystem.model.VerifierOfficer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerifierOfficerRepository extends JpaRepository<VerifierOfficer, Long> {
    Optional<VerifierOfficer> findByUsername(String username);
}
