//package com.birthverification.birthcertsystem.controller;
//
//import com.birthverification.birthcertsystem.model.Verification;
//import com.birthverification.birthcertsystem.service.VerificationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/verifications")
//@CrossOrigin(origins = "*")
//public class VerificationController {
//
//    @Autowired
//    private VerificationService verificationService;
//
//    @PostMapping
//    public Verification createVerification(@RequestBody Verification verification) {
//        return verificationService.saveVerification(verification);
//    }
//
//    @GetMapping
//    public List<Verification> getAllVerifications() {
//        return verificationService.getAllVerifications();
//    }
//
//    @GetMapping("/{id}")
//    public Verification getVerificationById(@PathVariable Long id) {
//        return verificationService.getVerificationById(id);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteVerification(@PathVariable Long id) {
//        verificationService.deleteVerification(id);
//    }
//
//    // ✅ NEW: Approve verification + send notification
//    @PostMapping("/approve/{id}")
//    public Verification approveVerification(@PathVariable Long id) {
//        return verificationService.approveVerification(id);
//    }
//}
