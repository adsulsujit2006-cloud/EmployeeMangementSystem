package com.employeemangementsystem.services.impl;

import com.employeemangementsystem.services.EmailServcie;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

@Service
@RequiredArgsConstructor
public class EmailServcieImpl implements EmailServcie {


    @Autowired
    private  JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void sendVerificationOtpEmail(String userEmail, String otp, String subject, String text) {

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");

            helper.setFrom(fromEmail, "Employee Management System");
            helper.setTo(userEmail);
            helper.setSubject(subject);
            helper.setText(buildOtpHtml(text, otp), true);   // true = HTML

            javaMailSender.send(mimeMessage);

        } catch (MessagingException | java.io.UnsupportedEncodingException e) {
            throw new MailSendException("Failed to send email to " + userEmail, e);
        }
    }

    private String buildOtpHtml(String text, String otp) {
        String safeText = HtmlUtils.htmlEscape(text);
        String safeOtp = HtmlUtils.htmlEscape(otp);

        return """
            <!DOCTYPE html>
            <html>
            <head>
              <meta charset="UTF-8">
              <meta name="viewport" content="width=device-width, initial-scale=1.0">
            </head>
            <body style="margin:0; padding:0; background-color:#f2f4f8; font-family:Arial, Helvetica, sans-serif;">
              <table role="presentation" width="100%%" cellspacing="0" cellpadding="0" style="background-color:#f2f4f8; padding:30px 0;">
                <tr>
                  <td align="center">
                    <table role="presentation" width="520" cellspacing="0" cellpadding="0"
                           style="max-width:520px; width:100%%; background:#ffffff; border-radius:10px; overflow:hidden; box-shadow:0 2px 8px rgba(0,0,0,0.08);">

                      <!-- Header -->
                      <tr>
                        <td align="center" style="background-color:#2563eb; padding:28px 20px;">
                          <h1 style="margin:0; color:#ffffff; font-size:22px; letter-spacing:0.5px;">
                            Employee Management System
                          </h1>
                        </td>
                      </tr>

                      <!-- Body -->
                      <tr>
                        <td style="padding:32px 36px; color:#333333;">
                          <h2 style="margin:0 0 12px 0; font-size:20px; color:#111827;">Verify your email</h2>
                          <p style="margin:0 0 24px 0; font-size:15px; line-height:1.6; color:#4b5563;">
                            %s
                          </p>

                          <!-- OTP box -->
                          <table role="presentation" width="100%%" cellspacing="0" cellpadding="0">
                            <tr>
                              <td align="center" style="background-color:#eff6ff; border:2px dashed #2563eb; border-radius:8px; padding:20px;">
                                <span style="font-size:34px; font-weight:bold; letter-spacing:10px; color:#1d4ed8;">
                                  %s
                                </span>
                              </td>
                            </tr>
                          </table>

                          <p style="margin:24px 0 0 0; font-size:14px; line-height:1.6; color:#6b7280;">
                            This OTP is valid for <strong>10 minutes</strong>.
                            Do not share it with anyone.
                          </p>
                          <p style="margin:12px 0 0 0; font-size:14px; line-height:1.6; color:#6b7280;">
                            If you did not request this, you can safely ignore this email.
                          </p>
                        </td>
                      </tr>

                      <!-- Footer -->
                      <tr>
                        <td align="center" style="background-color:#f9fafb; padding:18px; border-top:1px solid #e5e7eb;">
                          <p style="margin:0; font-size:12px; color:#9ca3af;">
                            &copy; 2026 Employee Management System. All rights reserved.<br>
                            This is an automated message, please do not reply.
                          </p>
                        </td>
                      </tr>

                    </table>
                  </td>
                </tr>
              </table>
            </body>
            </html>
            """.formatted(safeText, safeOtp);
    }
}