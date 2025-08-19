package com.birthverification.birthcertsystem.repository;

import com.birthverification.birthcertsystem.model.CertificateApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CertificateApplicationRepository extends JpaRepository<CertificateApplication, Long> {

    // Basic CRUD operations are inherited automatically

    // Custom finders without @Query
    List<CertificateApplication> findByFullNameContainingIgnoreCase(String name);

    List<CertificateApplication> findByStatus(String status);

    List<CertificateApplication> findByDeleted(boolean isDeleted);

    List<CertificateApplication> findByGender(String gender);

    Optional<CertificateApplication> findByNidNumber(String nidNumber);

    List<CertificateApplication> findByEmail(String email);

    List<CertificateApplication> findByPhone(String phone);

    List<CertificateApplication> findByDateOfBirth(Date dateOfBirth);

    List<CertificateApplication> findByApplicationDate(Date applicationDate);

    // Parent related finders
    List<CertificateApplication> findByFatherNameContainingIgnoreCase(String fatherName);

    List<CertificateApplication> findByMotherNameContainingIgnoreCase(String motherName);

    // Combined finders
    List<CertificateApplication> findByStatusAndDeleted(String status, boolean deleted);

    List<CertificateApplication> findByGenderAndDeleted(String gender, boolean deleted);

    List<CertificateApplication> findBynidNumber(String nidNumber);

}