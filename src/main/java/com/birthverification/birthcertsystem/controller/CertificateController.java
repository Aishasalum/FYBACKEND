//package com.birthverification.birthcertsystem.controller;
//
//import com.birthverification.birthcertsystem.model.Certificate;
//import com.birthverification.birthcertsystem.service.CertificateServiceImplementation;
//import com.birthverification.birthcertsystem.servisecertificate.CertificateService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/certificates")
//@CrossOrigin(origins = "*") // Allow all origins — good for testing
//public class CertificateController {
//
//    @Autowired
//    private CertificateService certificateService;
//
//    @PostMapping("/upload")
//    public ResponseEntity<Certificate> uploadCertificate(
//            @RequestParam Long userId,
//            @RequestParam String certificateNumber,
//            @RequestParam String certificateName,
//            @RequestParam("file") MultipartFile file
//    ) throws IOException {
//        // ✅ Debugging logs
//        System.out.println("🔵 Uploading Certificate:");
//        System.out.println("➡️  User ID: " + userId);
//        System.out.println("➡️  Certificate Number: " + certificateNumber);
//        System.out.println("➡️  Certificate Name: " + certificateName);
//        System.out.println("➡️  File Name: " + file.getOriginalFilename());
//        System.out.println("➡️  File Size: " + file.getSize() + " bytes");
//
//        Certificate saved = certificateService.uploadCertificate(userId, certificateNumber, certificateName, file);
//
//        System.out.println("✅ Certificate uploaded successfully with ID: " + saved.getId());
//
//        return ResponseEntity.ok(saved);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Certificate> getCertificateById(@PathVariable Long id) {
//        Certificate cert = certificateService.getCertificateById(id);
//        if (cert == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(cert);
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<Certificate>> getAllCertificates() {
//        List<Certificate> all = certificateService.getAllCertificates();
//        return ResponseEntity.ok(all);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCertificate(@PathVariable Long id) {
//        certificateService.deleteCertificate(id);
//        return ResponseEntity.noContent().build();
//    }
//}

//
//package com.birthverification.birthcertsystem.controller;
//
//import com.birthverification.birthcertsystem.model.Certificate;
//import com.birthverification.birthcertsystem.service.CertificateServiceImplementation;
//import com.birthverification.birthcertsystem.servisecertificate.CertificateService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/certificates")
//@CrossOrigin(origins = "*")
//public class CertificateController {
//
//    @Autowired
//    private CertificateService certificateService;
//
//    @Value("${file.upload-dir}")
//    private String uploadDir;
//
//    @PostMapping("/upload")
//    public ResponseEntity<Certificate> uploadCertificate(
//            @RequestParam Long userId,
//            @RequestParam String certificateNumber,
//            @RequestParam String certificateName,
//            @RequestParam("file") MultipartFile file) throws IOException {
//
//        System.out.println("🔵 Uploading Certificate:");
//        System.out.println("➡️ User ID: " + userId);
//        System.out.println("➡️ Certificate Number: " + certificateNumber);
//        System.out.println("➡️ Certificate Name: " + certificateName);
//        System.out.println("➡️ File Name: " + file.getOriginalFilename());
//        System.out.println("➡️ File Size: " + file.getSize() + " bytes");
//
//        Certificate saved = certificateService.uploadCertificate(userId, certificateNumber, certificateName, file);
//
//        System.out.println("✅ Certificate uploaded successfully with ID: " + saved.getId());
//
//        return ResponseEntity.ok(saved);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Certificate> getCertificateById(@PathVariable Long id) {
//        Certificate cert = certificateService.getCertificateById(id);
//        if (cert == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(cert);
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<Certificate>> getAllCertificates() {
//        List<Certificate> all = certificateService.getAllCertificates();
//        return ResponseEntity.ok(all);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCertificate(@PathVariable Long id) {
//        certificateService.deleteCertificate(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping("/preview/{filename:.+}")
//    public ResponseEntity<Resource> previewFile(@PathVariable String filename) throws IOException {
//        try {
//            Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
//            Resource resource = new UrlResource(filePath.toUri());
//
//            if (!resource.exists() || !resource.isReadable()) {
//                throw new RuntimeException("Could not read file: " + filename);
//            }
//
//            // Determine content type dynamically
//            String contentType = Files.probeContentType(filePath);
//            if (contentType == null) {
//                contentType = "application/octet-stream";
//            }
//
//            return ResponseEntity.ok()
//                    .contentType(MediaType.parseMediaType(contentType))
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
//                    .body(resource);
//        } catch (Exception e) {
//            throw new RuntimeException("Error: " + e.getMessage());
//        }
//    }
//
//    @GetMapping("/download/{filename:.+}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws IOException {
//        try {
//            Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
//            Resource resource = new UrlResource(filePath.toUri());
//
//            if (!resource.exists() || !resource.isReadable()) {
//                throw new RuntimeException("Could not read file: " + filename);
//            }
//
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                    .body(resource);
//        } catch (Exception e) {
//            throw new RuntimeException("Error: " + e.getMessage());
//        }
//    }
//}
//



package com.birthverification.birthcertsystem.controller;

import com.birthverification.birthcertsystem.model.Certificate;
import com.birthverification.birthcertsystem.servisecertificate.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/certificates")
@CrossOrigin(origins = "*")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    // Upload new certificate
    @PostMapping("/upload")
    public ResponseEntity<Certificate> uploadCertificate(
            @RequestParam Long userId,
            @RequestParam String certificateNumber,
            @RequestParam String certificateName,
            @RequestParam("file") MultipartFile file) throws IOException {

        Certificate saved = certificateService.uploadCertificate(userId, certificateNumber, certificateName, file);
        return ResponseEntity.ok(saved);
    }

    // Get certificate by ID
    @GetMapping("/{id}")
    public ResponseEntity<Certificate> getCertificateById(@PathVariable Long id) {
        Certificate cert = certificateService.getCertificateById(id);
        if (cert == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cert);
    }

    // Get all certificates NOT deleted (active)
    @GetMapping("/all")
    public ResponseEntity<List<Certificate>> getAllActiveCertificates() {
        List<Certificate> activeCerts = certificateService.getAllActiveCertificates();
        return ResponseEntity.ok(activeCerts);
    }

    // Get all certificates in trash (deleted = true)
    @GetMapping("/trash")
    public ResponseEntity<List<Certificate>> getAllDeletedCertificates() {
        List<Certificate> deletedCerts = certificateService.getAllDeletedCertificates();
        return ResponseEntity.ok(deletedCerts);
    }

    // Soft delete (move to trash)
    @PutMapping("/soft-delete/{id}")
    public ResponseEntity<Void> softDeleteCertificate(@PathVariable Long id) {
        try {
            certificateService.softDeleteCertificate(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Restore soft deleted certificate
    @PutMapping("/restore/{id}")
    public ResponseEntity<Void> restoreCertificate(@PathVariable Long id) {
        try {
            certificateService.restoreCertificate(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Hard delete (permanent)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> hardDeleteCertificate(@PathVariable Long id) {
        try {
            certificateService.hardDeleteCertificate(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Preview file inline
    @GetMapping("/preview/{filename:.+}")
    public ResponseEntity<Resource> previewFile(@PathVariable String filename) throws IOException {
        Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            return ResponseEntity.notFound().build();
        }

        String contentType = Files.probeContentType(filePath);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    // Download file as attachment
    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws IOException {
        Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
