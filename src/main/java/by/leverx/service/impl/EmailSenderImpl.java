package by.leverx.service.impl;

import by.leverx.entity.User;
import by.leverx.service.EmailSender;
import by.leverx.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.UUID;

@Service
@Slf4j
public class EmailSenderImpl implements EmailSender {
    @Autowired
    private UserService service;

    @Qualifier("messageSource")
    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    public void confirmRegistration(User user, Locale locale, String appUrl) {
        log.info("IN RegistrationListener in confirmRegistration. Listener started");
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = appUrl + "/auth/confirm/:" + token;
        String message = messages.getMessage("message.regSucc", null, locale);

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
        mailSender.send(email);
        log.info("IN RegistrationListener in confirmRegistration - email send to: {} awaits email verification",email);
        log.info("Confrimation URL is: {}",confirmationUrl);
    }
}
