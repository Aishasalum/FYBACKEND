//package com.birthverification.birthcertsystem.model;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "verifier_officers")
//public class VerifierOfficer {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String fullName;
//    private String email;
//
//    @Column(unique = true)
//    private String username;
//
//    private String password;
//    private String phone;
//    private String gender;
//    private String role;
//
//    private boolean active = true;
//
//    public VerifierOfficer() {}
//
//    public VerifierOfficer(String fullName, String email, String username, String password, String phone, String gender, String role) {
//        this.fullName = fullName;
//        this.email = email;
//        this.username = username;
//        this.password = password;
//        this.phone = phone;
//        this.gender = gender;
//        this.role = role;
//        this.active = true;
//    }
//
//    // ✅ Getters
//    public Long getId() {
//        return id;
//    }
//
//    public String getFullName() {
//        return fullName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public boolean isActive() {
//        return active;
//    }
//
//    // ✅ Setters (optional but useful for updates)
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    public void setActive(boolean active) {
//        this.active = active;
//    }
//}



package com.birthverification.birthcertsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "verifier_officers")
public class VerifierOfficer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;

    @Column(unique = true)
    private String username;

    private String password;
    private String phone;
    private String gender;
    private String role;

    private boolean active = true;

    // ✅ Add OneToMany relationship to Report
    @OneToMany(mappedBy = "officer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Report> reports;

    public VerifierOfficer() {}

    public VerifierOfficer(String fullName, String email, String username, String password, String phone, String gender, String role) {
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.role = role;
        this.active = true;
    }

    // ✅ Getters
    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getRole() {
        return role;
    }

    public boolean isActive() {
        return active;
    }

    public List<Report> getReports() {
        return reports;
    }

    // ✅ Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}
