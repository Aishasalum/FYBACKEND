package com.birthverification.birthcertsystem.controller;

import com.birthverification.birthcertsystem.dto.VerifierOfficerDTO;
import com.birthverification.birthcertsystem.model.VerifierOfficer;
import com.birthverification.birthcertsystem.service.VerifierOfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/verifier-officer")
@CrossOrigin(origins = "*")
public class VerifierOfficerController {

    @Autowired
    private VerifierOfficerService service;

    // ✅ Create - returns DTO
    @PostMapping("/add")
    public ResponseEntity<VerifierOfficerDTO> addVerifier(@RequestBody VerifierOfficer verifier) {
        VerifierOfficerDTO dto = service.addVerifier(verifier);
        return ResponseEntity.ok(dto);
    }

    // ✅ Get All - returns list of DTOs
    @GetMapping("/all")
    public ResponseEntity<List<VerifierOfficerDTO>> getAll() {
        List<VerifierOfficerDTO> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    // ✅ Get by ID - returns DTO inside Optional
    @GetMapping("/{id}")
    public ResponseEntity<VerifierOfficerDTO> getById(@PathVariable Long id) {
        Optional<VerifierOfficerDTO> optional = service.getById(id);
        return optional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Update - returns DTO
    @PutMapping("/update/{id}")
    public ResponseEntity<VerifierOfficerDTO> updateVerifier(@PathVariable Long id, @RequestBody VerifierOfficer updatedVerifier) {
        VerifierOfficerDTO updatedDTO = service.updateVerifier(id, updatedVerifier);
        if (updatedDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedDTO);
    }

    // ✅ Delete - returns string message
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVerifier(@PathVariable Long id) {
        String message = service.deleteVerifier(id);
        if (message.contains("not found")) {
            return ResponseEntity.status(404).body(message);
        }
        return ResponseEntity.ok(message);
    }

    // ✅ Login - returns message or DTO
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody VerifierOfficer loginReq) {
        Optional<VerifierOfficer> optional = service.findEntityByUsername(loginReq.getUsername());

        if (optional.isPresent()) {
            VerifierOfficer officer = optional.get();
            // TODO: Use hashed password check in production, this is plain text check for demo only
            if (officer.getPassword().equals(loginReq.getPassword())) {
                // Convert to DTO before sending back
                VerifierOfficerDTO dto = new VerifierOfficerDTO(officer);
                return ResponseEntity.ok(dto);
            } else {
                return ResponseEntity.status(401).body("Invalid password");
            }
        }
        return ResponseEntity.status(404).body("User not found");
    }
}
