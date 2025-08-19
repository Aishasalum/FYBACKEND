package com.birthverification.birthcertsystem.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "certificate_applications")
public class CertificateApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Applicant Information
    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private Date dateOfBirth;

    private String placeOfBirth;

    @Column(nullable = false)
    private String gender;

    private String nationality;
    private String nidNumber;

    // Parents Information
    private String fatherName;
    private String fatherNationality;
    private String motherName;
    private String motherNationality;

    // Contact Information
    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    private String address;

    // Reason
    @Column(columnDefinition = "TEXT")
    private String reason;

    @Column(nullable = false)
    private Date applicationDate = new Date();

    @Column(nullable = false)
    private String status = "PENDING"; // PENDING, APPROVED, REJECTED

    // Field ya Recycle Bin
    private boolean deleted = false;

    // Constructors
    public CertificateApplication() {
    }

    public CertificateApplication(String fullName, Date dateOfBirth, String gender, String phone, String email) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNidNumber() {
        return nidNumber;
    }

    public void setNidNumber(String nidNumber) {
        this.nidNumber = nidNumber;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherNationality() {
        return fatherNationality;
    }

    public void setFatherNationality(String fatherNationality) {
        this.fatherNationality = fatherNationality;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherNationality() {
        return motherNationality;
    }

    public void setMotherNationality(String motherNationality) {
        this.motherNationality = motherNationality;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    // Helper method kwa soft delete
    public void moveToRecycleBin() {
        this.deleted = true;
    }

    // Helper method kwa restore
    public void restoreFromRecycleBin() {
        this.deleted = false;
    }
}