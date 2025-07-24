//package com.birthverification.birthcertsystem.repository;
//
//import com.birthverification.birthcertsystem.model.Certificate;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface CertificateRepository extends JpaRepository<Certificate, Long> {
//}



package com.birthverification.birthcertsystem.repository;

import com.birthverification.birthcertsystem.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

    // Pata certificates zote ambazo **hazijafutwa** (deleted = false)
    List<Certificate> findByDeletedFalse();

    // Pata certificates zote ambazo ziko kwenye trash (deleted = true)
    List<Certificate> findByDeletedTrue();

}
