package com.jts.subscription.telegram.controller;

import com.jts.subscription.telegram.data.dto.TelegramSendContentRequest;
import com.jts.subscription.telegram.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class Controller {

  private final MessageService messageService;

  @PostMapping("/send")
  void sendContent(@RequestBody TelegramSendContentRequest telegramSendContentRequestList) {
    messageService.sendContent(telegramSendContentRequestList);
  }

}
