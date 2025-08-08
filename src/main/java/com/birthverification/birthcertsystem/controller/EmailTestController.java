package com.birthverification.birthcertsystem.controller;

import com.birthverification.birthcertsystem.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "*")
public class EmailTestController {

    @Autowired
    private EmailService emailService;

    // POST endpoint ya kutuma email
    @PostMapping("/send")
    public ResponseEntity<String> sendTestEmail(@RequestParam String toEmail,
                                                @RequestParam String subject,
                                                @RequestParam String body) {
        try {
            emailService.sendEmail(toEmail, subject, body);
            return ResponseEntity.ok("Email sent successfully to " + toEmail);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send email: " + e.getMessage());
        }
    }
}
