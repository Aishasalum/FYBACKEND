package com.birthverification.birthcertsystem.servisecertificate;

import com.birthverification.birthcertsystem.model.Certificate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CertificateService {
    Certificate uploadCertificate(Long userId, String certNumber, String certName,
                                  MultipartFile file) throws IOException;

    Certificate getCertificateById(Long id);

    List<Certificate> getAllCertificates();

    void deleteCertificate(Long id);
}
