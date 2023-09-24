package com.example.demo.emailSender.controller;

import com.example.demo.emailSender.resource.EmailMessage;
import com.example.demo.emailSender.service.EmailService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;
@Data

@RestController
public class EmailController {

    private final EmailService emailSenderService;

    public EmailController(EmailService emailService) {
        this.emailSenderService = emailService;
    }

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailMessage emailMessage) {
        this.emailSenderService.sendEmail(emailMessage.getTo(),emailMessage.getSubject(),emailMessage.getMessage());
        return ResponseEntity.ok("Success");
    }
}
