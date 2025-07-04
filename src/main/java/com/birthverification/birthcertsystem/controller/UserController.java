package com.birthverification.birthcertsystem.controller;

import com.birthverification.birthcertsystem.model.User;
import com.birthverification.birthcertsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register new user
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userService.emailExists(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists.");
        }

        // DO NOT hash password here (handled inside service)
        User registeredUser = userService.registerUser(user);

        // Hide password before returning
        registeredUser.setPassword(null);

        return ResponseEntity.ok(registeredUser);
    }

    // Login user
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        Optional<User> existingUser = userService.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            // Compare raw password with encoded password from DB
            if (passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
                User safeUser = existingUser.get();
                safeUser.setPassword(null); // Hide password before sending response
                return ResponseEntity.ok(safeUser);
            } else {
                return ResponseEntity.status(401).body("Invalid password.");
            }
        } else {
            return ResponseEntity.status(404).body("User not found.");
        }
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            User safeUser = user.get();
            safeUser.setPassword(null);
            return ResponseEntity.ok(safeUser);
        } else {
            return ResponseEntity.status(404).body("User not found.");
        }
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        users.forEach(u -> u.setPassword(null)); // Remove password before returning list
        return ResponseEntity.ok(users);
    }

    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        // DO NOT encode password here. The service will handle encoding if necessary.
        Optional<User> updatedUser = userService.updateUser(id, user);
        if (updatedUser.isPresent()) {
            User safeUser = updatedUser.get();
            safeUser.setPassword(null);
            return ResponseEntity.ok(safeUser);
        } else {
            return ResponseEntity.status(404).body("User not found.");
        }
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.ok("User deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("User not found.");
        }
    }
}
