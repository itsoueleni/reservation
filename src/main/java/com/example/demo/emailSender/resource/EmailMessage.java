package com.example.demo.emailSender.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmailMessage {
    private String to;
    private String subject;
    private String message;




}
