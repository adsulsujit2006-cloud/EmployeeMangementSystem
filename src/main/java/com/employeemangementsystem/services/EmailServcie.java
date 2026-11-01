package com.employeemangementsystem.services;

public interface EmailServcie {

    public void sendVerificationOtpEmail(String userEmail, String otp, String subject, String text);
}
