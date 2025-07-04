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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash password
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

    // Find by ID
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // Update user
    public Optional<User> updateUser(Long id, User newUserData) {
        return userRepository.findById(id).map(user -> {
            user.setFullName(newUserData.getFullName());
            user.setEmail(newUserData.getEmail());
            user.setPhoneNumber(newUserData.getPhoneNumber());

            if (newUserData.getPassword() != null && !newUserData.getPassword().isBlank()) {
                // Always encode the new raw password
                user.setPassword(passwordEncoder.encode(newUserData.getPassword()));
            }

            user.setRole(newUserData.getRole());
            return userRepository.save(user);
        });
    }



    // Delete user
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
