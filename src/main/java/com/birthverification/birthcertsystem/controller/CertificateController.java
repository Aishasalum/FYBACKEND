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

//
//
//package com.birthverification.birthcertsystem.controller;
//
//import com.birthverification.birthcertsystem.model.Certificate;
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
//    // Upload new certificate
//    @PostMapping("/upload")
//    public ResponseEntity<Certificate> uploadCertificate(
//            @RequestParam Long userId,
//            @RequestParam String certificateNumber,
//            @RequestParam String certificateName,
//            @RequestParam("file") MultipartFile file) throws IOException {
//
//        Certificate saved = certificateService.uploadCertificate(userId, certificateNumber, certificateName, file);
//        return ResponseEntity.ok(saved);
//    }
//
//    // Get certificate by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Certificate> getCertificateById(@PathVariable Long id) {
//        Certificate cert = certificateService.getCertificateById(id);
//        if (cert == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(cert);
//    }
//
//    // Get all certificates NOT deleted (active)
//    @GetMapping("/all")
//    public ResponseEntity<List<Certificate>> getAllActiveCertificates() {
//        List<Certificate> activeCerts = certificateService.getAllActiveCertificates();
//        return ResponseEntity.ok(activeCerts);
//    }
//
//    // Get all certificates in trash (deleted = true)
//    @GetMapping("/trash")
//    public ResponseEntity<List<Certificate>> getAllDeletedCertificates() {
//        List<Certificate> deletedCerts = certificateService.getAllDeletedCertificates();
//        return ResponseEntity.ok(deletedCerts);
//    }
//
//    // Soft delete (move to trash)
//    @PutMapping("/soft-delete/{id}")
//    public ResponseEntity<Void> softDeleteCertificate(@PathVariable Long id) {
//        try {
//            certificateService.softDeleteCertificate(id);
//            return ResponseEntity.noContent().build();
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    // Restore soft deleted certificate
//    @PutMapping("/restore/{id}")
//    public ResponseEntity<Void> restoreCertificate(@PathVariable Long id) {
//        try {
//            certificateService.restoreCertificate(id);
//            return ResponseEntity.noContent().build();
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    // Hard delete (permanent)
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> hardDeleteCertificate(@PathVariable Long id) {
//        try {
//            certificateService.hardDeleteCertificate(id);
//            return ResponseEntity.noContent().build();
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    // Preview file inline
//    @GetMapping("/preview/{filename:.+}")
//    public ResponseEntity<Resource> previewFile(@PathVariable String filename) throws IOException {
//        Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
//        Resource resource = new UrlResource(filePath.toUri());
//
//        if (!resource.exists() || !resource.isReadable()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        String contentType = Files.probeContentType(filePath);
//        if (contentType == null) {
//            contentType = "application/octet-stream";
//        }
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//    }
//
//    // Download file as attachment
//    @GetMapping("/download/{filename:.+}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws IOException {
//        Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
//        Resource resource = new UrlResource(filePath.toUri());
//
//        if (!resource.exists() || !resource.isReadable()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//    }
//}

//
//package com.birthverification.birthcertsystem.controller;
//
//import com.birthverification.birthcertsystem.model.Certificate;
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
//import java.net.URLDecoder;
//import java.nio.charset.StandardCharsets;
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
//    // Upload new certificate
//    @PostMapping("/upload")
//    public ResponseEntity<Certificate> uploadCertificate(
//            @RequestParam Long userId,
//            @RequestParam String certificateNumber,
//            @RequestParam String certificateName,
//            @RequestParam("file") MultipartFile file) throws IOException {
//
//        Certificate saved = certificateService.uploadCertificate(userId, certificateNumber, certificateName, file);
//        return ResponseEntity.ok(saved);
//    }
//
//    // Get certificate by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Certificate> getCertificateById(@PathVariable Long id) {
//        Certificate cert = certificateService.getCertificateById(id);
//        if (cert == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(cert);
//    }
//
//    // Get all certificates NOT deleted (active)
//    @GetMapping("/all")
//    public ResponseEntity<List<Certificate>> getAllActiveCertificates() {
//        List<Certificate> activeCerts = certificateService.getAllActiveCertificates();
//        return ResponseEntity.ok(activeCerts);
//    }
//
//    // Get all certificates in trash (deleted = true)
//    @GetMapping("/trash")
//    public ResponseEntity<List<Certificate>> getAllDeletedCertificates() {
//        List<Certificate> deletedCerts = certificateService.getAllDeletedCertificates();
//        return ResponseEntity.ok(deletedCerts);
//    }
//
//    // Soft delete (move to trash)
//    @PutMapping("/soft-delete/{id}")
//    public ResponseEntity<Void> softDeleteCertificate(@PathVariable Long id) {
//        try {
//            certificateService.softDeleteCertificate(id);
//            return ResponseEntity.noContent().build();
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    // Restore soft deleted certificate
//    @PutMapping("/restore/{id}")
//    public ResponseEntity<Void> restoreCertificate(@PathVariable Long id) {
//        try {
//            certificateService.restoreCertificate(id);
//            return ResponseEntity.noContent().build();
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    // Hard delete (permanent)
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> hardDeleteCertificate(@PathVariable Long id) {
//        try {
//            certificateService.hardDeleteCertificate(id);
//            return ResponseEntity.noContent().build();
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    // ✅ Preview file (auto-decodes URL-encoded filename)
//    @GetMapping("/preview/{filename:.+}")
//    public ResponseEntity<Resource> previewFile(@PathVariable String filename) throws IOException {
//        String decodedFilename = URLDecoder.decode(filename, StandardCharsets.UTF_8);
//        Path filePath = Paths.get(uploadDir).resolve(decodedFilename).normalize();
//        Resource resource = new UrlResource(filePath.toUri());
//
//        if (!resource.exists() || !resource.isReadable()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        String contentType = Files.probeContentType(filePath);
//        if (contentType == null) {
//            contentType = "application/octet-stream";
//        }
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//    }
//
//    // ✅ Download file (auto-decodes URL-encoded filename)
//    @GetMapping("/download/{filename:.+}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws IOException {
//        String decodedFilename = URLDecoder.decode(filename, StandardCharsets.UTF_8);
//        Path filePath = Paths.get(uploadDir).resolve(decodedFilename).normalize();
//        Resource resource = new UrlResource(filePath.toUri());
//
//        if (!resource.exists() || !resource.isReadable()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//    }
//}



//
//package com.birthverification.birthcertsystem.controller;
//
//import com.birthverification.birthcertsystem.model.Certificate;
//import com.birthverification.birthcertsystem.servisecertificate.CertificateService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.URLDecoder;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//import java.util.logging.Logger;
//
//@RestController
//@RequestMapping("/api/certificates")
//@CrossOrigin(origins = "*")
//public class CertificateController {
//    private static final Logger logger = Logger.getLogger(CertificateController.class.getName());
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
//        Certificate saved = certificateService.uploadCertificate(userId, certificateNumber, certificateName, file);
//        return ResponseEntity.ok(saved);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Certificate> getCertificateById(@PathVariable Long id) {
//        Certificate cert = certificateService.getCertificateById(id);
//        return cert != null ? ResponseEntity.ok(cert) : ResponseEntity.notFound().build();
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<Certificate>> getAllActiveCertificates() {
//        return ResponseEntity.ok(certificateService.getAllActiveCertificates());
//    }
//
//    @GetMapping("/trash")
//    public ResponseEntity<List<Certificate>> getAllDeletedCertificates() {
//        return ResponseEntity.ok(certificateService.getAllDeletedCertificates());
//    }
//
//    @PutMapping("/soft-delete/{id}")
//    public ResponseEntity<Void> softDeleteCertificate(@PathVariable Long id) {
//        try {
//            certificateService.softDeleteCertificate(id);
//            return ResponseEntity.noContent().build();
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PutMapping("/restore/{id}")
//    public ResponseEntity<Void> restoreCertificate(@PathVariable Long id) {
//        try {
//            certificateService.restoreCertificate(id);
//            return ResponseEntity.noContent().build();
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> hardDeleteCertificate(@PathVariable Long id) {
//        try {
//            certificateService.hardDeleteCertificate(id);
//            return ResponseEntity.noContent().build();
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping("/preview/{filename:.+}")
//    public ResponseEntity<?> previewFile(@PathVariable String filename) {
//        try {
//            // Handle double encoding if present
//            String decodedFilename = URLDecoder.decode(filename, StandardCharsets.UTF_8);
//            if (decodedFilename.contains("%")) {
//                decodedFilename = URLDecoder.decode(decodedFilename, StandardCharsets.UTF_8);
//            }
//
//            logger.info("Attempting to preview file: " + decodedFilename);
//
//            Path filePath = Paths.get(uploadDir).resolve(decodedFilename).normalize();
//            Resource resource = new UrlResource(filePath.toUri());
//
//            if (!resource.exists()) {
//                logger.warning("File not found: " + filePath);
//                return ResponseEntity.notFound().build();
//            }
//
//            if (!resource.isReadable()) {
//                logger.warning("File not readable: " + filePath);
//                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//            }
//
//            String contentType = determineContentType(decodedFilename);
//            logger.info("Detected content type: " + contentType);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.parseMediaType(contentType));
//            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"");
//
//            return ResponseEntity.ok()
//                    .headers(headers)
//                    .body(resource);
//        } catch (Exception e) {
//            logger.severe("Error previewing file: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error previewing file: " + e.getMessage());
//        }
//    }
//
//    @GetMapping("/download/{filename:.+}")
//    public ResponseEntity<?> downloadFile(@PathVariable String filename) {
//        try {
//            // Fix double encoding issue
//            String decodedFilename = URLDecoder.decode(filename, StandardCharsets.UTF_8);
//
//            // If still contains encoded characters, decode again
//            while (decodedFilename.contains("%")) {
//                decodedFilename = URLDecoder.decode(decodedFilename, StandardCharsets.UTF_8);
//            }
//
//            logger.info("Final decoded filename: " + decodedFilename);
//
//            Path filePath = Paths.get(uploadDir).resolve(decodedFilename).normalize();
//            Resource resource = new UrlResource(filePath.toUri());
//
//            if (!resource.exists()) {
//                logger.warning("File not found: " + filePath);
//                return ResponseEntity.notFound().build();
//            }
//
//            if (!resource.isReadable()) {
//                logger.warning("File not readable: " + filePath);
//                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//            }
//
//            // Force PDF content type if extension is .pdf
//            String contentType = decodedFilename.toLowerCase().endsWith(".pdf")
//                    ? "application/pdf"
//                    : Files.probeContentType(filePath);
//
//            return ResponseEntity.ok()
//                    .contentType(MediaType.parseMediaType(contentType))
//                    .header(HttpHeaders.CONTENT_DISPOSITION,
//                            "attachment; filename=\"" + resource.getFilename() + "\"")
//                    .body(resource);
//        } catch (Exception e) {
//            logger.severe("Download error: " + e.getMessage());
//            return ResponseEntity.badRequest()
//                    .body("Error: " + e.getMessage());
//        }
//    }
//
//    private String determineContentType(String filename) throws IOException {
//        String lowerCaseFilename = filename.toLowerCase();
//
//        // First check by extension
//        if (lowerCaseFilename.endsWith(".pdf")) {
//            return "application/pdf";
//        } else if (lowerCaseFilename.endsWith(".jpg") || lowerCaseFilename.endsWith(".jpeg")) {
//            return "image/jpeg";
//        } else if (lowerCaseFilename.endsWith(".png")) {
//            return "image/png";
//        } else if (lowerCaseFilename.endsWith(".gif")) {
//            return "image/gif";
//        }
//
//        // Fall back to system probe
//        Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
//        String contentType = Files.probeContentType(filePath);
//
//        return contentType != null ? contentType : "application/octet-stream";
//    }
//}








//mpya sasa   ya kutuma notification via email


package com.birthverification.birthcertsystem.controller;

import com.birthverification.birthcertsystem.dto.CertificateStatusUpdateRequest;
import com.birthverification.birthcertsystem.enums.VerificationStatus;
import com.birthverification.birthcertsystem.model.Certificate;
import com.birthverification.birthcertsystem.model.User;
import com.birthverification.birthcertsystem.servisecertificate.CertificateService;
import com.birthverification.birthcertsystem.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/certificates")
@CrossOrigin(origins = "*")
public class CertificateController {

    private static final Logger logger = Logger.getLogger(CertificateController.class.getName());

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private EmailService emailService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public ResponseEntity<Certificate> uploadCertificate(
            @RequestParam Long userId,
            @RequestParam String certificateNumber,
            @RequestParam String certificateName,
            @RequestParam("file") MultipartFile file) throws IOException {
        Certificate saved = certificateService.uploadCertificate(userId, certificateNumber, certificateName, file);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificate> getCertificateById(@PathVariable Long id) {
        Certificate cert = certificateService.getCertificateById(id);
        return cert != null ? ResponseEntity.ok(cert) : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Certificate>> getAllActiveCertificates() {
        return ResponseEntity.ok(certificateService.getAllActiveCertificates());
    }

    @GetMapping("/trash")
    public ResponseEntity<List<Certificate>> getAllDeletedCertificates() {
        return ResponseEntity.ok(certificateService.getAllDeletedCertificates());
    }

//    @PutMapping("/soft-delete/{id}")
//    public ResponseEntity<Void> softDeleteCertificate(@PathVariable Long id) {
//        try {
//            certificateService.softDeleteCertificate(id);
//            return ResponseEntity.noContent().build();
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @PutMapping("/soft-delete/{id}")
    public ResponseEntity<?> softDeleteCertificate(@PathVariable Long id) {
        try {
            certificateService.softDeleteCertificate(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "error", "Operation failed",
                            "message", e.getMessage()
                    ));
        }
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<Void> restoreCertificate(@PathVariable Long id) {
        try {
            certificateService.restoreCertificate(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> hardDeleteCertificate(@PathVariable Long id) {
        try {
            certificateService.hardDeleteCertificate(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/preview/{filename:.+}")
    public ResponseEntity<?> previewFile(@PathVariable String filename) {
        try {
            String decodedFilename = URLDecoder.decode(filename, StandardCharsets.UTF_8);
            if (decodedFilename.contains("%")) {
                decodedFilename = URLDecoder.decode(decodedFilename, StandardCharsets.UTF_8);
            }

            logger.info("Attempting to preview file: " + decodedFilename);

            Path filePath = Paths.get(uploadDir).resolve(decodedFilename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                logger.warning("File not found: " + filePath);
                return ResponseEntity.notFound().build();
            }

            if (!resource.isReadable()) {
                logger.warning("File not readable: " + filePath);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            String contentType = determineContentType(decodedFilename);
            logger.info("Detected content type: " + contentType);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(contentType));
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (Exception e) {
            logger.severe("Error previewing file: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error previewing file: " + e.getMessage());
        }
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<?> downloadFile(@PathVariable String filename) {
        try {
            String decodedFilename = URLDecoder.decode(filename, StandardCharsets.UTF_8);
            while (decodedFilename.contains("%")) {
                decodedFilename = URLDecoder.decode(decodedFilename, StandardCharsets.UTF_8);
            }

            logger.info("Final decoded filename: " + decodedFilename);

            Path filePath = Paths.get(uploadDir).resolve(decodedFilename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                logger.warning("File not found: " + filePath);
                return ResponseEntity.notFound().build();
            }

            if (!resource.isReadable()) {
                logger.warning("File not readable: " + filePath);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            String contentType = decodedFilename.toLowerCase().endsWith(".pdf")
                    ? "application/pdf"
                    : Files.probeContentType(filePath);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            logger.severe("Download error: " + e.getMessage());
            return ResponseEntity.badRequest()
                    .body("Error: " + e.getMessage());
        }
    }

    private String determineContentType(String filename) throws IOException {
        String lowerCaseFilename = filename.toLowerCase();

        if (lowerCaseFilename.endsWith(".pdf")) {
            return "application/pdf";
        } else if (lowerCaseFilename.endsWith(".jpg") || lowerCaseFilename.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (lowerCaseFilename.endsWith(".png")) {
            return "image/png";
        } else if (lowerCaseFilename.endsWith(".gif")) {
            return "image/gif";
        }

        Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
        String contentType = Files.probeContentType(filePath);

        return contentType != null ? contentType : "application/octet-stream";
    }

    // 🔥 NEW: Update certificate status and send email
    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateCertificateStatus(
            @PathVariable Long id,
            @RequestBody CertificateStatusUpdateRequest request
    ) {
        Certificate cert = certificateService.getCertificateById(id);
        if (cert == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Certificate not found");
        }

        cert.setVerificationStatus(VerificationStatus.valueOf(request.getStatus()));

        certificateService.save(cert);

        if ("EMAIL".equalsIgnoreCase(request.getNotifyVia()) || "BOTH".equalsIgnoreCase(request.getNotifyVia())) {
            User user = cert.getUser();
            if (user != null && user.getEmail() != null) {
                try {
                    emailService.sendEmail(
                            user.getEmail(),
                            "Certificate " + request.getStatus(),
                            request.getMessage()
                    );
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Status updated but failed to send email: " + e.getMessage());
                }
            }
        }

        return ResponseEntity.ok("Certificate status updated successfully");
    }
}
