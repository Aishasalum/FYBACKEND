//package com.birthverification.birthcertsystem.service;
//
//import com.birthverification.birthcertsystem.model.Admin;
//import com.birthverification.birthcertsystem.repository.AdminRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class AdminService {
//
//    @Autowired
//    private AdminRepository adminRepository;
//
//    public Optional<Admin> login(String email, String password) {
//        Optional<Admin> adminOpt = adminRepository.findByEmail(email);
//        if (adminOpt.isPresent() && adminOpt.get().getPassword().equals(password)) {
//            return adminOpt;
//        }
//        return Optional.empty();
//    }
//}




//mpyaaa hashed password


package com.birthverification.birthcertsystem.service;

import com.birthverification.birthcertsystem.model.Admin;
import com.birthverification.birthcertsystem.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Add new Admin with hashed password
    public Admin addAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    // Login admin
    public Optional<Admin> login(String email, String password) {
        Optional<Admin> adminOpt = adminRepository.findByEmail(email);
        if (adminOpt.isPresent() && passwordEncoder.matches(password, adminOpt.get().getPassword())) {
            return adminOpt;
        }
        return Optional.empty();
    }
}


