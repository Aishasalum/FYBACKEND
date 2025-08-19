//package com.birthverification.birthcertsystem.model;
//
//import jakarta.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//public class Verification {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    // Mtumiaji aliyewasilisha
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    // Cheti kilichopakiwa
//    @OneToOne
//    @JoinColumn(name = "certificate_id")
//    private Certificate certificate;
//
//    // Kama kimematch na rekodi rasmi
//    private boolean isMatched;
//
//    // Mthibitishaji (verifier)
//    @ManyToOne
//    @JoinColumn(name = "verifier_officer_id")
//    private VerifierOfficer verifierOfficer;
//
//    // Status ya cheti
//    private String status; // Pending, Verified, Rejected
//
//    // Maoni
//    private String comment;
//
//    private LocalDateTime verifiedAt;
//
//    // Getters and Setters
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Certificate getCertificate() {
//        return certificate;
//    }
//
//    public void setCertificate(Certificate certificate) {
//        this.certificate = certificate;
//    }
//
//    public boolean isMatched() {
//        return isMatched;
//    }
//
//    public void setMatched(boolean matched) {
//        isMatched = matched;
//    }
//
//    public VerifierOfficer getVerifierOfficer() {
//        return verifierOfficer;
//    }
//
//    public void setVerifierOfficer(VerifierOfficer verifierOfficer) {
//        this.verifierOfficer = verifierOfficer;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(VerificationStatus status) {
//        this.status = String.valueOf(status);
//    }
//
//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment;
//    }
//
//    public LocalDateTime getVerifiedAt() {
//        return verifiedAt;
//    }
//
//    public void setVerifiedAt(LocalDateTime verifiedAt) {
//        this.verifiedAt = verifiedAt;
//    }
//}
