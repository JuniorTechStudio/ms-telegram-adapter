package com.jts.subscription.telegram.service;

import com.jts.subscription.telegram.data.dto.TelegramSendContentRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MessageService {

  private final TelegramLongPollingBot bot;

  public void sendMessage(String id, String text, boolean disableNotification) {
    try {
      bot.execute(
          SendMessage.builder()
              .chatId(id)
              .text(text)
              .disableNotification(disableNotification)
              .build()
      );
    } catch (TelegramApiException e) {
      log.info("{}. USER ID: {}", e.getMessage(), id);
    }
  }

  public void sendMessage(SendMessage message) {
    try {
      bot.execute(message);
    } catch (TelegramApiException e) {
      log.error("{}. USER ID: {}", e.getMessage(), message.getChatId());
    }
  }

  public void editMessage(EditMessageText msg) {
    try {
      bot.execute(msg);
    } catch (TelegramApiException e) {
      log.info("{}.", e.getMessage());
    }
  }

  public void sendContent(TelegramSendContentRequest telegramSendContentRequestList) {
    telegramSendContentRequestList.getTextToSendList().forEach(content -> {
          String id = content.getTelegramId();
          String contentString = content.getContent();
          sendMessage(id, contentString, false);
        });
  }

}
