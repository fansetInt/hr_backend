package com.fanset.dms.user.emails.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Email {
    private   String toEmail;
    private  String body;
    private String subject;
    
}
