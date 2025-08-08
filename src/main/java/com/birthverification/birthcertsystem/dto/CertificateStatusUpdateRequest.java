package com.birthverification.birthcertsystem.dto;

public class CertificateStatusUpdateRequest {
    private String status;      // e.g., VERIFIED or REJECTED
    private String notifyVia;   // e.g., EMAIL or BOTH
    private String message;     // ujumbe wa kutuma

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotifyVia() {
        return notifyVia;
    }

    public void setNotifyVia(String notifyVia) {
        this.notifyVia = notifyVia;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
