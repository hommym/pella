package com.pellanotes.pella.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    

    private final JavaMailSender mailSender;

    @Value("${EMAIL_USERNAME}")
    private String fromAdress;


    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender; 
    }


    public void sendSignUpEmail(String email,String username){
        String body="Hi "+username+"👋\n"+
                         "\n"+   
                         "Welcome to Pella Notes we are excited to have you on board!\n" + 
                        "\n" + //
                        "You're just one step away from unlocking everything our platform has to offer. To keep your account secure and ensure it is  really you, we have sent a One-Time Password (OTP) to this email address.\n" + //
                        "\n" + //
                        "🔐 Your OTP has been sent to your inbox. Please enter it in the app or website to verify your account.\n" + //
                        "\n" + //
                        "If you did not receive the OTP, please check your spam or junk folder, or click \"Resend OTP\" on the verification page.";
         SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(this.fromAdress+"@mailtrap.io");
        message.setTo(email);
        message.setSubject("Hey "+username+" welcome aboard! 🎉");
        message.setText(body);
        mailSender.send(message);
    }

    public void sendOtpEmail(String email,String username,int otp){
        String body="Hi "+username+"👋\n"+
        "\n"+   
        "To complete your account verification on PellaNotes, please use the One-Time Password (OTP) below:\n"+
         "\n"+"\n"+
         "🔐 Your OTP Code: " +otp +"\n" +
         "📌 This code is valid for the next 10 minutes.\n"+
         "\n"+
         "If you did not request this code, please ignore this email or contact our support team immediately.\n"+
         "\n"+
         "Thank you for choosing PellaNotes. Let us get started on your journey!\n"+
         "\n"+
         "Best regards,\n"+
         "\n"+
         "The PellaNotes Team";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(this.fromAdress+"@mailtrap.io");
        message.setTo(email);
        message.setSubject(" Your One-Time Password (OTP) for Account Verification");
        message.setText(body);
        mailSender.send(message);

    }


}
