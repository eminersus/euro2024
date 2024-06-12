package org.euro2024.service;

import org.euro2024.model.User;
import org.euro2024.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    public void registerUser(User user) {
        user.setEnabled(false);
        userRepository.save(user);
        sendVerificationEmail(user);
    }

    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public void sendVerificationEmail(User user) {
        String token = UUID.randomUUID().toString();
        // Save the token with the user (not shown in this snippet)
        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = "http://localhost:8080/api/confirm?token=" + token;
        String message = "Please confirm your registration by clicking the link below.";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " " + confirmationUrl);
        mailSender.send(email);
    }

    public boolean verifyUser(String token) {
        // Find user by token and enable the user (not shown in this snippet)
        return true;
    }


}
