package com.example.demo.emailSender.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface EmailService {

    void sendEmail(String to, String subject, String message);

}