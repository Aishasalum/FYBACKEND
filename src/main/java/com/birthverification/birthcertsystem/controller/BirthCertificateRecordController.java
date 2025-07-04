package com.birthverification.birthcertsystem.controller;

import com.birthverification.birthcertsystem.model.BirthCertificateRecord;
import com.birthverification.birthcertsystem.service.BirthCertificateRecordServiceImpl;
import com.birthverification.birthcertsystem.servisecertificate.BirthCertificateRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/birth-records")
@CrossOrigin(origins = "*") // Ruhusu kutoka frontend yoyote (change kwa production)
public class BirthCertificateRecordController {

    @Autowired
    private BirthCertificateRecordService recordService;

    // 1️⃣ Add new birth record
    @PostMapping
    public BirthCertificateRecord addRecord(@RequestBody BirthCertificateRecord record) {
        return recordService.addRecord(record);
    }

    // 2️⃣ Get all records
    @GetMapping
    public List<BirthCertificateRecord> getAllRecords() {
        return recordService.getAllRecords();
    }

    // 3️⃣ Get single record by ID
    @GetMapping("/{id}")
    public BirthCertificateRecord getRecordById(@PathVariable Long id) {
        return recordService.getRecordById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));
    }

    // 4️⃣ Update record by ID
    @PutMapping("/{id}")
    public BirthCertificateRecord updateRecord(@PathVariable Long id,
                                               @RequestBody BirthCertificateRecord updatedRecord) {
        return recordService.updateRecord(id, updatedRecord);
    }

    // 5️⃣ Delete record
    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id) {
        recordService.deleteRecord(id);
    }
}
