package com.birthverification.birthcertsystem.servisecertificate;

import com.birthverification.birthcertsystem.model.BirthCertificateRecord;

import java.util.List;
import java.util.Optional;

public interface BirthCertificateRecordService {
    BirthCertificateRecord addRecord(BirthCertificateRecord record);
    List<BirthCertificateRecord> getAllRecords();
    Optional<BirthCertificateRecord> getRecordById(Long id);
    BirthCertificateRecord updateRecord(Long id, BirthCertificateRecord updatedRecord);
    void deleteRecord(Long id);
}
