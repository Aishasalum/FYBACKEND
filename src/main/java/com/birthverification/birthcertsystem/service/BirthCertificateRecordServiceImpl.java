package com.birthverification.birthcertsystem.service;

import com.birthverification.birthcertsystem.model.BirthCertificateRecord;
import com.birthverification.birthcertsystem.repository.BirthCertificateRecordRepository;
import com.birthverification.birthcertsystem.servisecertificate.BirthCertificateRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BirthCertificateRecordServiceImpl implements BirthCertificateRecordService {

    @Autowired
    private BirthCertificateRecordRepository repository;

    @Override
    public BirthCertificateRecord addRecord(BirthCertificateRecord record) {
        return repository.save(record);
    }

    @Override
    public List<BirthCertificateRecord> getAllRecords() {
        return repository.findAll();
    }

    @Override
    public Optional<BirthCertificateRecord> getRecordById(Long id) {
        return repository.findById(id);
    }

    @Override
    public BirthCertificateRecord updateRecord(Long id, BirthCertificateRecord updatedRecord) {
        Optional<BirthCertificateRecord> optionalRecord = repository.findById(id);
        if (optionalRecord.isPresent()) {
            BirthCertificateRecord existing = optionalRecord.get();

            existing.setCertificateNumber(updatedRecord.getCertificateNumber());
            existing.setChildName(updatedRecord.getChildName());
            existing.setDateOfBirth(updatedRecord.getDateOfBirth());
            existing.setPlaceOfBirth(updatedRecord.getPlaceOfBirth());
            existing.setGender(updatedRecord.getGender());
            existing.setFatherName(updatedRecord.getFatherName());
            existing.setMotherName(updatedRecord.getMotherName());
            existing.setResidentOfParents(updatedRecord.getResidentOfParents());
            existing.setInformantName(updatedRecord.getInformantName());
            existing.setDateOfIssue(updatedRecord.getDateOfIssue());

            return repository.save(existing);
        } else {
            throw new RuntimeException("Birth record not found");
        }
    }

    @Override
    public void deleteRecord(Long id) {
        repository.deleteById(id);
    }
}
