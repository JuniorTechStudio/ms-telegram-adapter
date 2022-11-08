package com.jts.subscription.telegram.controller;

import com.jts.subscription.telegram.data.dto.TelegramSendContentRequest;
import com.jts.subscription.telegram.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/JTSBot")
@RequiredArgsConstructor
public class Controller {

    private final MessageService messageService;

    @PostMapping("/subscription")
    public void sendContent(@RequestBody TelegramSendContentRequest telegramSendContentRequest) {
        messageService.sendContent(telegramSendContentRequest);
    }
}
