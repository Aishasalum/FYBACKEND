package com.birthverification.birthcertsystem.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "birth_certificates_records")
public class BirthCertificateRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String certificateNumber;

    @Column(nullable = false)
    private String childName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String placeOfBirth;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String fatherName;

    @Column(nullable = false)
    private String motherName;

    @Column(nullable = false)
    private String residentOfParents;

    @Column(nullable = false)
    private String informantName;

    @Column(nullable = false)
    private LocalDate dateOfIssue;

    // ----------- Constructors -----------

    public BirthCertificateRecord() {}

    public BirthCertificateRecord(Long id, String certificateNumber, String childName, LocalDate dateOfBirth,
                                  String placeOfBirth, String gender, String fatherName, String motherName,
                                  String residentOfParents, String informantName, LocalDate dateOfIssue) {
        this.id = id;
        this.certificateNumber = certificateNumber;
        this.childName = childName;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.gender = gender;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.residentOfParents = residentOfParents;
        this.informantName = informantName;
        this.dateOfIssue = dateOfIssue;
    }

    // ----------- Getters and Setters -----------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
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

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getResidentOfParents() {
        return residentOfParents;
    }

    public void setResidentOfParents(String residentOfParents) {
        this.residentOfParents = residentOfParents;
    }

    public String getInformantName() {
        return informantName;
    }

    public void setInformantName(String informantName) {
        this.informantName = informantName;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }
}
