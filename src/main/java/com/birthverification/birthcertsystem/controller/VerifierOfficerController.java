package com.birthverification.birthcertsystem.controller;

import com.birthverification.birthcertsystem.model.VerifierOfficer;
import com.birthverification.birthcertsystem.service.VerifierOfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/verifier-officer")
public class VerifierOfficerController {

    @Autowired
    private VerifierOfficerService service;

    // ✅ Create
    @PostMapping("/add")
    public VerifierOfficer addVerifier(@RequestBody VerifierOfficer verifier) {
        return service.addVerifier(verifier);
    }

    // ✅ Get All
    @GetMapping("/all")
    public List<VerifierOfficer> getAll() {
        return service.getAll();
    }

    // ✅ Get by ID
    @GetMapping("/{id}")
    public Optional<VerifierOfficer> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // ✅ Update
    @PutMapping("/update/{id}")
    public VerifierOfficer updateVerifier(@PathVariable Long id, @RequestBody VerifierOfficer updatedVerifier) {
        return service.updateVerifier(id, updatedVerifier);
    }

    // ✅ Delete
    @DeleteMapping("/delete/{id}")
    public String deleteVerifier(@PathVariable Long id) {
        return service.deleteVerifier(id);
    }

    // ✅ Login
    @PostMapping("/login")
    public String login(@RequestBody VerifierOfficer loginReq) {
        Optional<VerifierOfficer> optional = service.findByUsername(loginReq.getUsername());
        if (optional.isPresent()) {
            VerifierOfficer officer = optional.get();
            if (officer.getPassword().equals(loginReq.getPassword())) {
                return "Login successful";
            }
        }
        return "Login failed";
    }
}
