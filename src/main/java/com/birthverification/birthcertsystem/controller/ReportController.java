package com.birthverification.birthcertsystem.controller;

import com.birthverification.birthcertsystem.model.Report;
import com.birthverification.birthcertsystem.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Submit a new report
    @PostMapping("/submit")
    public ResponseEntity<Report> submitReport(
            @RequestParam Long officerId,
            @RequestParam String reportTitle,
            @RequestParam String fromDate,
            @RequestParam String toDate,
            @RequestParam int verifiedCount,
            @RequestParam int rejectedCount,
            @RequestParam String comments,
            @RequestParam String sendMethod,
            @RequestParam String adminEmail
    ) {
        try {
            LocalDate from = LocalDate.parse(fromDate);
            LocalDate to = LocalDate.parse(toDate);

            Report savedReport = reportService.submitReport(
                    officerId, reportTitle, from, to,
                    verifiedCount, rejectedCount,
                    comments, sendMethod, adminEmail
            );

            return ResponseEntity.ok(savedReport);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Get all reports
    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    // Get reports submitted by a specific officer
    @GetMapping("/officer/{officerId}")
    public ResponseEntity<List<Report>> getReportsByOfficer(@PathVariable Long officerId) {
        List<Report> reports = reportService.getReportsByOfficer(officerId);
        return ResponseEntity.ok(reports);
    }
}
