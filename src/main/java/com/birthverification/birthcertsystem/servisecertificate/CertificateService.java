//package com.birthverification.birthcertsystem.servisecertificate;
//
//import com.birthverification.birthcertsystem.model.Certificate;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//
//public interface CertificateService {
//    Certificate uploadCertificate(Long userId, String certNumber, String certName,
//                                  MultipartFile file) throws IOException;
//
//    Certificate getCertificateById(Long id);
//
//    List<Certificate> getAllCertificates();
//
//    void deleteCertificate(Long id);
//}

//
//package com.birthverification.birthcertsystem.servisecertificate;
//
//import com.birthverification.birthcertsystem.model.Certificate;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//
//public interface CertificateService {
//
//    Certificate uploadCertificate(Long userId, String certNumber, String certName,
//                                  MultipartFile file) throws IOException;
//
//    Certificate getCertificateById(Long id);
//
//    // Pata list ya certificates zote ambazo hazijafutwa (deleted = false)
//    List<Certificate> getAllActiveCertificates();
//
//    // Pata list ya certificates zote zilizofutwa (deleted = true)
//    List<Certificate> getAllDeletedCertificates();
//
//    // Soft delete certificate (mark deleted = true)
//    void softDeleteCertificate(Long id);
//
//    // Restore soft deleted certificate (mark deleted = false)
//    void restoreCertificate(Long id);
//
//    // Hard delete certificate (permanently remove from DB)
//    void hardDeleteCertificate(Long id);
//}




//mpyaaa kuongeza kutuma notification kwa email



package com.birthverification.birthcertsystem.servisecertificate;

import com.birthverification.birthcertsystem.model.Certificate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CertificateService {

    Certificate uploadCertificate(Long userId, String certNumber, String certName,
                                  MultipartFile file) throws IOException;

    Certificate getCertificateById(Long id);

    List<Certificate> getAllActiveCertificates();

    List<Certificate> getAllDeletedCertificates();

    void softDeleteCertificate(Long id);

    void restoreCertificate(Long id);

    void hardDeleteCertificate(Long id);

    // ✅ Added this method to save/update certificate
    Certificate save(Certificate certificate);


    //mpyaaa aaaa aaa

    // ✅ Ongeza hii mpya
    List<Certificate> getCertificatesByUserId(Long userId);
}

