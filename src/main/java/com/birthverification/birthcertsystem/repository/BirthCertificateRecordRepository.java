package com.birthverification.birthcertsystem.repository;

import com.birthverification.birthcertsystem.model.BirthCertificateRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BirthCertificateRecordRepository extends JpaRepository<BirthCertificateRecord, Long> {

    // Custom method to search by multiple fields (kwa verification)
    @Query("SELECT b FROM BirthCertificateRecord b WHERE " +
            "b.certificateNumber = :certificateNumber AND " +
            "b.childName = :childName AND " +
            "b.dateOfBirth = :dateOfBirth AND " +
            "b.placeOfBirth = :placeOfBirth AND " +
            "b.gender = :gender AND " +
            "b.fatherName = :fatherName AND " +
            "b.motherName = :motherName AND " +
            "b.residentOfParents = :residentOfParents AND " +
            "b.informantName = :informantName AND " +
            "b.dateOfIssue = :dateOfIssue")
    Optional<BirthCertificateRecord> findByMultipleFields(
            String certificateNumber,
            String childName,
            LocalDate dateOfBirth,
            String placeOfBirth,
            String gender,
            String fatherName,
            String motherName,
            String residentOfParents,
            String informantName,
            LocalDate dateOfIssue
    );

    Optional<BirthCertificateRecord> findByCertificateNumber(String certificateNumber);

}
