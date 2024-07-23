package com.ltc.securitydemo.client;


import com.ltc.securitydemo.dto.telegramDto.TelegramRoot;
import com.ltc.securitydemo.dto.telegramDto.TelegramSendDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "telegram-service", url = "https://api.telegram.org/bot7379146267:AAGeHWZ0tyPe2KsrcVB5OuM4nFXDmTRzbZQ")
public interface TelegramClient {

    @GetMapping("/getUpdates?offset={value}")
    TelegramRoot getMessage(@PathVariable int value);

    @PostMapping("/sendMessage")
    void sendMessage(TelegramSendDto dto);




}
