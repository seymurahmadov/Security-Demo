package com.ltc.securitydemo.controller;

import com.ltc.securitydemo.dto.telegramDto.TelegramRoot;
import com.ltc.securitydemo.dto.telegramDto.TelegramSendDto;
import com.ltc.securitydemo.service.TelegramService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/telegram")
public class TelegramController {

    private final TelegramService telegramService;

    public TelegramController(TelegramService telegramService) {
        this.telegramService = telegramService;
    }


    @GetMapping("/son-mesajlar")
    public TelegramRoot getMesaj(){
       return telegramService.getMessageService();
    }

    @PostMapping("/mesaj-gonder")
    public void sendMesaj(@RequestBody TelegramSendDto telegramSendDto){
        telegramService.sendMessageService(telegramSendDto);
    }
}
