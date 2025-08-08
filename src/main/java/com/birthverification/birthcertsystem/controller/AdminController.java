package com.birthverification.birthcertsystem.controller;

import com.birthverification.birthcertsystem.dto.LoginRequestDTO;
import com.birthverification.birthcertsystem.model.Admin;
import com.birthverification.birthcertsystem.model.VerifierOfficer;
import com.birthverification.birthcertsystem.repository.VerifierOfficerRepository;
import com.birthverification.birthcertsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // ruhusu frontend kupata API hii
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private VerifierOfficerRepository verifierOfficerRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        String role = request.getRole();
        String email = request.getEmail();
        String password = request.getPassword();

        if ("admin".equalsIgnoreCase(role)) {
            Optional<Admin> adminOpt = adminService.login(email, password);
            if (adminOpt.isPresent()) {
                return ResponseEntity.ok(Map.of("message", "Admin login successful"));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid admin credentials"));
            }
        } else if ("verifier".equalsIgnoreCase(role)) {
            Optional<VerifierOfficer> verifierOpt = verifierOfficerRepository.findByEmail(email);
            if (verifierOpt.isPresent() && verifierOpt.get().getPassword().equals(password)) {
                return ResponseEntity.ok(Map.of("message", "Verifier login successful"));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid verifier credentials"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Invalid role selected"));
        }
    }

}
