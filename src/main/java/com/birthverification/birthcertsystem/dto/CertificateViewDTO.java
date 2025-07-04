package com.birthverification.birthcertsystem.dto;

public class CertificateViewDTO {
    private String userName;
    private String email;
    private String phoneNumber;
    private String certificateName;
    private String fileUrl;
    private String childName;

    public CertificateViewDTO() {
    }

    public CertificateViewDTO(String userName, String email, String phoneNumber,
                              String certificateName, String fileUrl, String childName) {
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.certificateName = certificateName;
        this.fileUrl = fileUrl;
        this.childName = childName;
    }

    // Getters & Setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }
}
