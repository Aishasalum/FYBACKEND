////package com.birthverification.birthcertsystem.service;
////
////import com.birthverification.birthcertsystem.model.User;
////import com.birthverification.birthcertsystem.repository.UserRepository;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.stereotype.Service;
////
////import java.util.List;
////import java.util.Optional;
////
////@Service
////public class UserService {
////
////    @Autowired
////    private UserRepository userRepository;
////
////    @Autowired
////    private PasswordEncoder passwordEncoder;
////
////    // Register
////    public User registerUser(User user) {
////        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash password
////        return userRepository.save(user);
////    }
////
////    // Check if email exists
////    public boolean emailExists(String email) {
////        return userRepository.findByEmail(email).isPresent();
////    }
////
////    // Find by email
////    public Optional<User> findByEmail(String email) {
////        return userRepository.findByEmail(email);
////    }
////
////    // Find by ID
////    public Optional<User> findById(Long id) {
////        return userRepository.findById(id);
////    }
////
////    // Update user
////    public Optional<User> updateUser(Long id, User newUserData) {
////        return userRepository.findById(id).map(user -> {
////            user.setFullName(newUserData.getFullName());
////            user.setEmail(newUserData.getEmail());
////            user.setPhoneNumber(newUserData.getPhoneNumber());
////
////            if (newUserData.getPassword() != null && !newUserData.getPassword().isBlank()) {
////                // Always encode the new raw password
////                user.setPassword(passwordEncoder.encode(newUserData.getPassword()));
////            }
////
////            user.setRole(newUserData.getRole());
////            return userRepository.save(user);
////        });
////    }
////
////
////
////    // Delete user
////    public boolean deleteUser(Long id) {
////        if (userRepository.existsById(id)) {
////            userRepository.deleteById(id);
////            return true;
////        }
////        return false;
////    }
////
////    // Get all users
////    public List<User> getAllUsers() {
////        return userRepository.findAll();
////    }
////}
//package com.birthverification.birthcertsystem.service;
//
//import com.birthverification.birthcertsystem.model.User;
//import com.birthverification.birthcertsystem.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    // Register user
//    public User registerUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepository.save(user);
//    }
//
//    // Check if email exists
//    public boolean emailExists(String email) {
//        return userRepository.findByEmail(email).isPresent();
//    }
//
//    // Find by email (ignore deleted flag since login can be by deleted user?)
//    public Optional<User> findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
//
//    // Find active user by ID (exclude deleted)
//    public Optional<User> findActiveUserById(Long id) {
//        return userRepository.findByIdAndDeletedFalse(id);
//    }
//
//    // Find user by ID (include deleted)
//    public Optional<User> findById(Long id) {
//        return userRepository.findById(id);
//    }
//
//    // Update active user only
//    public Optional<User> updateUser(Long id, User newUserData) {
//        return userRepository.findByIdAndDeletedFalse(id).map(user -> {
//            user.setFullName(newUserData.getFullName());
//            user.setEmail(newUserData.getEmail());
//            user.setPhoneNumber(newUserData.getPhoneNumber());
//
//            if (newUserData.getPassword() != null && !newUserData.getPassword().isBlank()) {
//                user.setPassword(passwordEncoder.encode(newUserData.getPassword()));
//            }
//
//            user.setRole(newUserData.getRole());
//            return userRepository.save(user);
//        });
//    }
//
//    // Soft delete user (move to Recycle Bin)
//    public boolean deleteUser(Long id) {
//        Optional<User> optionalUser = userRepository.findByIdAndDeletedFalse(id);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            user.setDeleted(true);
//            userRepository.save(user);
//            return true;
//        }
//        return false;
//    }
//
//    // Restore user from Recycle Bin
//    public boolean restoreUser(Long id) {
//        Optional<User> optionalUser = userRepository.findById(id);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            if(user.isDeleted()) {
//                user.setDeleted(false);
//                userRepository.save(user);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    // Permanently delete user from DB
//    public boolean permanentlyDeleteUser(Long id) {
//        if (userRepository.existsById(id)) {
//            userRepository.deleteById(id);
//            return true;
//        }
//        return false;
//    }
//
//    // Get all active users
//    public List<User> getAllActiveUsers() {
//        return userRepository.findByDeletedFalse();
//    }
//
//    // Get all deleted users (Recycle Bin)
//    public List<User> getAllDeletedUsers() {
//        return userRepository.findByDeletedTrue();
//    }
//
//    // Get all users (active + deleted)
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//}




//mpyaaa na kureset password




package com.birthverification.birthcertsystem.service;

import com.birthverification.birthcertsystem.model.User;
import com.birthverification.birthcertsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final String FRONTEND_BASE_URL = "http://192.168.15.247:19006";
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService; // ✅ tumia EmailService yako

    // Register user
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Check if email exists
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    // Find by email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Find active user by ID (exclude deleted)
    public Optional<User> findActiveUserById(Long id) {
        return userRepository.findByIdAndDeletedFalse(id);
    }

    // Find user by ID (include deleted)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // Update active user only
    public Optional<User> updateUser(Long id, User newUserData) {
        return userRepository.findByIdAndDeletedFalse(id).map(user -> {
            user.setFullName(newUserData.getFullName());
            user.setEmail(newUserData.getEmail());
            user.setPhoneNumber(newUserData.getPhoneNumber());

            if (newUserData.getPassword() != null && !newUserData.getPassword().isBlank()) {
                user.setPassword(passwordEncoder.encode(newUserData.getPassword()));
            }

            user.setRole(newUserData.getRole());
            return userRepository.save(user);
        });
    }

    // Soft delete user
    public boolean deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findByIdAndDeletedFalse(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setDeleted(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    // Restore user from Recycle Bin
    public boolean restoreUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if(user.isDeleted()) {
                user.setDeleted(false);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    // Permanently delete user from DB
    public boolean permanentlyDeleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Get all active users
    public List<User> getAllActiveUsers() {
        return userRepository.findByDeletedFalse();
    }

    // Get all deleted users (Recycle Bin)
    public List<User> getAllDeletedUsers() {
        return userRepository.findByDeletedTrue();
    }

    // Get all users (active + deleted)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ================= Reset Password Functionality =================

    // Send reset password email
    public boolean sendResetPasswordEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            // Hapa tuna-reset link ya frontend
            String resetLink = FRONTEND_BASE_URL + "/set-new-password?email=" + email;


            String subject = "Birth Certificate Verification - Reset Password";
            String body = "Hello " + userOpt.get().getFullName() + ",\n\n" +
                    "Click the link below to reset your password:\n" +
                    resetLink + "\n\n" +
                    "If you didn't request a password reset, please ignore this email.";

            emailService.sendEmail(email, subject, body);
            return true;
        }
        return false;
    }

    // Update password after user clicks reset link
    public boolean updatePassword(String email, String newPassword) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
