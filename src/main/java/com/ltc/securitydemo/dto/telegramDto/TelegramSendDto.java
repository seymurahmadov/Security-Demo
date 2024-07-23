package com.ltc.securitydemo.dto.telegramDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelegramSendDto {
    private Integer chat_id;
    private String text;
}
