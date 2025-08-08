package com.birthverification.birthcertsystem.service;

import com.birthverification.birthcertsystem.model.Admin;
import com.birthverification.birthcertsystem.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Optional<Admin> login(String email, String password) {
        Optional<Admin> adminOpt = adminRepository.findByEmail(email);
        if (adminOpt.isPresent() && adminOpt.get().getPassword().equals(password)) {
            return adminOpt;
        }
        return Optional.empty();
    }
}
