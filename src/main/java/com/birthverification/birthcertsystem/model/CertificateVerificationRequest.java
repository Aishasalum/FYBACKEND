//package com.birthverification.birthcertsystem.model;
//
//import com.birthverification.birthcertsystem.enums.MatchResult;
//import com.birthverification.birthcertsystem.enums.VerificationStatus;
//import jakarta.persistence.*;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "certificate_verification_requests")
//public class CertificateVerificationRequest {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String certificateNumber;
//
//    @Column(nullable = false)
//    private String childName;
//
//    @Column(nullable = false)
//    private LocalDate dateOfBirth;
//
//    @Column(nullable = false)
//    private LocalDate dateOfIssue;
//
//    @Column(nullable = false)
//    private String fatherName;
//
//    @Column(nullable = false)
//    private String motherName;
//
//    @Column(nullable = false)
//    private String gender;
//
//    @Column(nullable = false)
//    private String informantName;
//
//    @Column(nullable = false)
//    private String placeOfBirth;
//
//    @Column(nullable = false)
//    private String residentOfParents;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private VerificationStatus status = VerificationStatus.PENDING;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private MatchResult matchResult = MatchResult.NOT_MATCHED;
//
//    @Column(nullable = false)
//    private LocalDateTime createdAt;
//
//    @Column
//    private LocalDateTime updatedAt;
//
//    // ===== Relations =====
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "uploaded_certificate_id", nullable = true)
//    private Certificate uploadedCertificate;
//
//    @ManyToOne
//    @JoinColumn(name = "matched_record_id", nullable = true)
//    private BirthCertificateRecord matchedRecord;
//
//    // ===== Lifecycle Hooks =====
//
//    @PrePersist
//    protected void onCreate() {
//        createdAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        updatedAt = LocalDateTime.now();
//    }
//
//    // ===== Getters =====
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getCertificateNumber() {
//        return certificateNumber;
//    }
//
//    public String getChildName() {
//        return childName;
//    }
//
//    public LocalDate getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public LocalDate getDateOfIssue() {
//        return dateOfIssue;
//    }
//
//    public String getFatherName() {
//        return fatherName;
//    }
//
//    public String getMotherName() {
//        return motherName;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public String getInformantName() {
//        return informantName;
//    }
//
//    public String getPlaceOfBirth() {
//        return placeOfBirth;
//    }
//
//    public String getResidentOfParents() {
//        return residentOfParents;
//    }
//
//    public VerificationStatus getStatus() {
//        return status;
//    }
//
//    public MatchResult getMatchResult() {
//        return matchResult;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public Certificate getUploadedCertificate() {
//        return uploadedCertificate;
//    }
//
//    public BirthCertificateRecord getMatchedRecord() {
//        return matchedRecord;
//    }
//
//    // ===== Setters =====
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setCertificateNumber(String certificateNumber) {
//        this.certificateNumber = certificateNumber;
//    }
//
//    public void setChildName(String childName) {
//        this.childName = childName;
//    }
//
//    public void setDateOfBirth(LocalDate dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }
//
//    public void setDateOfIssue(LocalDate dateOfIssue) {
//        this.dateOfIssue = dateOfIssue;
//    }
//
//    public void setFatherName(String fatherName) {
//        this.fatherName = fatherName;
//    }
//
//    public void setMotherName(String motherName) {
//        this.motherName = motherName;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public void setInformantName(String informantName) {
//        this.informantName = informantName;
//    }
//
//    public void setPlaceOfBirth(String placeOfBirth) {
//        this.placeOfBirth = placeOfBirth;
//    }
//
//    public void setResidentOfParents(String residentOfParents) {
//        this.residentOfParents = residentOfParents;
//    }
//
//    public void setStatus(VerificationStatus status) {
//        this.status = status;
//    }
//
//    public void setMatchResult(MatchResult matchResult) {
//        this.matchResult = matchResult;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public void setUpdatedAt(LocalDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public void setUploadedCertificate(Certificate uploadedCertificate) {
//        this.uploadedCertificate = uploadedCertificate;
//    }
//
//    public void setMatchedRecord(BirthCertificateRecord matchedRecord) {
//        this.matchedRecord = matchedRecord;
//    }
//}


package com.birthverification.birthcertsystem.model;

import com.birthverification.birthcertsystem.enums.MatchResult;
import com.birthverification.birthcertsystem.enums.VerificationStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "certificate_verification_requests")
public class CertificateVerificationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String certificateNumber;

    @Column(nullable = false)
    private String childName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private LocalDate dateOfIssue;

    @Column(nullable = false)
    private String fatherName;

    @Column(nullable = false)
    private String motherName;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String informantName;

    @Column(nullable = false)
    private String placeOfBirth;

    @Column(nullable = false)
    private String residentOfParents;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VerificationStatus status = VerificationStatus.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MatchResult matchResult = MatchResult.NOT_MATCHED;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private boolean deleted = false;  // << Soft delete flag

    // ===== Relations =====

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "uploaded_certificate_id", nullable = true)
    private Certificate uploadedCertificate;

    @ManyToOne
    @JoinColumn(name = "matched_record_id", nullable = true)
    private BirthCertificateRecord matchedRecord;

    // ===== Lifecycle Hooks =====

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // ===== Getters =====

    public Long getId() {
        return id;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public String getChildName() {
        return childName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getGender() {
        return gender;
    }

    public String getInformantName() {
        return informantName;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public String getResidentOfParents() {
        return residentOfParents;
    }

    public VerificationStatus getStatus() {
        return status;
    }

    public MatchResult getMatchResult() {
        return matchResult;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public User getUser() {
        return user;
    }

    public Certificate getUploadedCertificate() {
        return uploadedCertificate;
    }

    public BirthCertificateRecord getMatchedRecord() {
        return matchedRecord;
    }

    // ===== Setters =====

    public void setId(Long id) {
        this.id = id;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setInformantName(String informantName) {
        this.informantName = informantName;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public void setResidentOfParents(String residentOfParents) {
        this.residentOfParents = residentOfParents;
    }

    public void setStatus(VerificationStatus status) {
        this.status = status;
    }

    public void setMatchResult(MatchResult matchResult) {
        this.matchResult = matchResult;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUploadedCertificate(Certificate uploadedCertificate) {
        this.uploadedCertificate = uploadedCertificate;
    }

    public void setMatchedRecord(BirthCertificateRecord matchedRecord) {
        this.matchedRecord = matchedRecord;
    }
}
