package com.ltc.securitydemo.service;

import com.ltc.securitydemo.dto.request.EmailSendRequestDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void sendSimpleMail(EmailSendRequestDto dto) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(dto.getTo());
        message.setFrom("s.axmedov99@gmail.com");
        message.setSubject(dto.getSubject());
        message.setText(dto.getText());

        javaMailSender.send(message);
    }

    public void sendMailWithFile(EmailSendRequestDto requestDto, List<MultipartFile> multipartFiles) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

        messageHelper.setFrom(requestDto.getFrom());
        messageHelper.setTo(requestDto.getTo());
        messageHelper.setSubject(requestDto.getSubject());
        messageHelper.setText(requestDto.getText());


        for (MultipartFile multipartFile : multipartFiles) {



            String originalFilename = multipartFile.getOriginalFilename();
            messageHelper.addAttachment(originalFilename, multipartFile);
        }

        javaMailSender.send(message);
    }
}


