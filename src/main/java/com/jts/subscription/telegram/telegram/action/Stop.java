package com.jts.subscription.telegram.telegram.action;

import com.jts.subscription.telegram.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component("stop")
@RequiredArgsConstructor
public class Stop implements Action {

  private final MessageService messageService;

  @Override
  public void handle(Update update) {
    String chatId = update.getMessage().getChatId().toString();
    messageService.sendMessage(chatId, "stop", false);
  }

}
