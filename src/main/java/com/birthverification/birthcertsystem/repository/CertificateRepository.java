package com.birthverification.birthcertsystem.repository;

import com.birthverification.birthcertsystem.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
}
