package com.ltc.securitydemo.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailSendRequestDto {
    private String to;
    private String from;
    private String subject;
    private String text;
}
