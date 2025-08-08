package com.birthverification.birthcertsystem.dto;

import com.birthverification.birthcertsystem.model.VerifierOfficer;

public class VerifierOfficerDTO {
    private Long id;
    private String fullName;
    private String email;
    private String username;
    private String phone;
    private String gender;
    private String role;
    private boolean active;

    // Empty constructor
    public VerifierOfficerDTO() {
    }

    // Full argument constructor
    public VerifierOfficerDTO(Long id, String fullName, String email, String username, String phone, String gender, String role, boolean active) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.phone = phone;
        this.gender = gender;
        this.role = role;
        this.active = active;
    }

    // Constructor from VerifierOfficer entity
    public VerifierOfficerDTO(VerifierOfficer officer) {
        this.id = officer.getId();
        this.fullName = officer.getFullName();
        this.email = officer.getEmail();
        this.username = officer.getUsername();
        this.phone = officer.getPhone();
        this.gender = officer.getGender();
        this.role = officer.getRole();
        this.active = officer.isActive();
    }

    // Getters
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

    // Setters
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
}
