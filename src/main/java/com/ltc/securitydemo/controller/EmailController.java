package com.ltc.securitydemo.controller;


import com.ltc.securitydemo.dto.request.EmailSendRequestDto;
import com.ltc.securitydemo.service.EmailService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.mail.MessagingException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/mail")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }


    @PostMapping("/send/simple")
    public void sendMail(@RequestBody EmailSendRequestDto dto){
        emailService.sendSimpleMail(dto);
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void sendMailWithAttachment(EmailSendRequestDto requestDto , @Parameter(description = "File to upload")
    @RequestPart(value = "files",required = true)
    List<MultipartFile> file    ) throws MessagingException {
        emailService.sendMailWithFile(requestDto, file);
    }
}
