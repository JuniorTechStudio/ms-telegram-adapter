package com.jts.subscription.telegram.telegram;

import com.jts.subscription.telegram.client.SubscriptionClient;
import com.jts.subscription.telegram.client.UserClient;
import com.jts.subscription.telegram.data.dto.SubscriptionUserInfoRequest;
import com.jts.subscription.telegram.service.MessageService;
import com.jts.subscription.telegram.service.UserService;

import java.util.Map;
import java.util.Objects;

import com.jts.subscription.telegram.telegram.action.Action;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdatesHandler extends TelegramLongPollingBot {

  private Map<String, Action> actions;
  private final MessageService messageService;
  private final UserClient userClient;
  private final SubscriptionClient subscriptionClient;

  @Value("${bot.name}")
  private String botUsername;

  @Value("${bot.token}")
  private String botToken;

  @Autowired
  public void setBeans(Map<String, Action> actions) {
    this.actions = actions;
  }

  @Override
  public void onUpdateReceived(Update update) {

    if (Objects.nonNull(update.getMessage()) && update.getMessage().hasText()) {

      String name = update.getMessage().getFrom().getFirstName();
      log.info("{} сказал: {}", name, update.getMessage().getText());

      String stringAction = update.getMessage().getText().replace("/", "");
      Action action = actions.get(stringAction);
      action.handle(update);

    } else if (update.hasCallbackQuery()) {
      String chatId = update.getCallbackQuery().getFrom().getId().toString();
      int messageId = update.getCallbackQuery().getMessage().getMessageId();
      String callBackData = update.getCallbackQuery().getData();
      String name = update.getCallbackQuery().getFrom().getFirstName();

      log.info("{} Выбрал тему {}", name, callBackData);
      switch (callBackData) {
        case "theme_1_button":
          {
            // код для кнопки Java
            SubscriptionUserInfoRequest subscriptionUserInfoRequest =
                SubscriptionUserInfoRequest.builder()
                    .userId(userClient.getUserIdByTelegramId(chatId))
                    .subscriptionTitle("Java")
                    .telegramId(chatId)
                    .build();
            subscriptionClient.saveSubscriptionUserInfo(subscriptionUserInfoRequest);

            EditMessageText msg = new EditMessageText();
            msg.setChatId(chatId);
            msg.setText("Вы выбрали тему 'Java'");
            msg.setMessageId(messageId);
            messageService.editMessage(msg);
            break;
          }
        case "theme_2_button":
          {
            // код для кнопки English
            SubscriptionUserInfoRequest subscriptionUserInfoRequest =
                SubscriptionUserInfoRequest.builder()
                    .userId(userClient.getUserIdByTelegramId(chatId))
                    .subscriptionTitle("English")
                    .telegramId(chatId)
                    .build();
            subscriptionClient.saveSubscriptionUserInfo(subscriptionUserInfoRequest);

            EditMessageText msg = new EditMessageText();
            msg.setChatId(chatId);
            msg.setText("Вы выбрали тему 'English'");
            msg.setMessageId(messageId);
            messageService.editMessage(msg);
            break;
          }
        case "theme_3_button":
          {
            // код для кнопки SQL
            SubscriptionUserInfoRequest subscriptionUserInfoRequest =
                SubscriptionUserInfoRequest.builder()
                    .userId(userClient.getUserIdByTelegramId(chatId))
                    .subscriptionTitle("SQL")
                    .telegramId(chatId)
                    .build();
            subscriptionClient.saveSubscriptionUserInfo(subscriptionUserInfoRequest);

            EditMessageText msg = new EditMessageText();
            msg.setChatId(chatId);
            msg.setText("Вы выбрали тему 'SQL'");
            msg.setMessageId(messageId);
            messageService.editMessage(msg);
            break;
          }
      }
    }
  }

  public String getBotUsername() {
    return botUsername;
  }

  public String getBotToken() {
    return botToken;
  }
}
