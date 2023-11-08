package com.jts.subscription.telegram.telegram;

import com.jts.subscription.telegram.client.SubscriptionClient;
import com.jts.subscription.telegram.client.UserClient;
import com.jts.subscription.telegram.data.dto.AddUserToSubscriptionRequest;
import com.jts.subscription.telegram.data.dto.SubscriptionUserInfoRequest;
import com.jts.subscription.telegram.service.MessageService;
import com.jts.subscription.telegram.telegram.action.Action;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import static java.lang.String.format;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdatesHandler extends TelegramLongPollingBot {

  private Map<String, Action> actions;
  private final MessageService messageService;
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
      String stringAction = update.getMessage().getText().replace("/", "");
      Action action = actions.get(stringAction);
      action.handle(update);
    } else if (update.hasCallbackQuery()) {
      String chatId = update.getCallbackQuery().getFrom().getId().toString();
      int messageId = update.getCallbackQuery().getMessage().getMessageId();
      String callBackData = update.getCallbackQuery().getData();
      String[] split = callBackData.split("|");
      String title = split[0];
      UUID subscriptionId = UUID.fromString(split[1]);

      subscriptionClient.subscribe(subscriptionId, new AddUserToSubscriptionRequest(chatId));

      EditMessageText message = new EditMessageText();
      message.setChatId(chatId);
      message.setText(format("You was subscribed to (%s).", title));
      message.setMessageId(messageId);
      messageService.editMessage(message);
    }
  }

  public String getBotUsername() {
    return botUsername;
  }

  public String getBotToken() {
    return botToken;
  }

}
