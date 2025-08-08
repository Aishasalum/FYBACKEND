package com.birthverification.birthcertsystem.service;

import com.birthverification.birthcertsystem.model.Report;
import com.birthverification.birthcertsystem.model.VerifierOfficer;
import com.birthverification.birthcertsystem.repository.ReportRepository;
import com.birthverification.birthcertsystem.repository.VerifierOfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private VerifierOfficerRepository officerRepository;

    // Save report data to database
    public Report submitReport(
            Long officerId,
            String reportTitle,
            LocalDate fromDate,
            LocalDate toDate,
            int verifiedCount,
            int rejectedCount,
            String comments,
            String sendMethod,
            String adminEmail
    ) {
        VerifierOfficer officer = officerRepository.findById(officerId)
                .orElseThrow(() -> new IllegalArgumentException("Verifier Officer not found"));

        Report report = new Report();
        report.setOfficer(officer);
        report.setReportTitle(reportTitle);
        report.setFromDate(fromDate);
        report.setToDate(toDate);
        report.setVerifiedCount(verifiedCount);
        report.setRejectedCount(rejectedCount);
        report.setComments(comments);
        report.setSendMethod(sendMethod);
        report.setAdminEmail(adminEmail);

        return reportRepository.save(report);
    }

    // Return all reports (used in dashboard view or admin)
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    // Return reports specific to a verifier officer
    public List<Report> getReportsByOfficer(Long officerId) {
        return reportRepository.findByOfficerId(officerId);
    }
}
