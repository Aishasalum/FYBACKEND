package com.birthverification.birthcertsystem.repository;

import com.birthverification.birthcertsystem.model.Report;
import com.birthverification.birthcertsystem.model.VerifierOfficer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    // Sahihi kabisa kwa jina la field kwenye model
    List<Report> findByOfficer(VerifierOfficer officer);

    List<Report> findByOfficerId(Long officerId);
}
