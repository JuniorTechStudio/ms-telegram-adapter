package com.jts.subscription.telegram.telegram.action;

import com.jts.subscription.telegram.client.SubscriptionClient;
import com.jts.subscription.telegram.service.MessageService;
import com.jts.subscription.telegram.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Slf4j
@Component("start")
@RequiredArgsConstructor
public class StartMessage implements Action {

  private final MessageService messageService;
  private final SubscriptionClient subscriptionClient;
  private final UserService userService;

  @Override
  public void handle(Update update) {
    User user = update.getMessage().getFrom();
    userService.createUser(user);
    String chatId = update.getMessage().getChatId().toString();
    SendMessage message = new SendMessage();
    message.setChatId(chatId);
    message.setText("Hi there! Choose You theme for subscribe.");
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    List<List<InlineKeyboardButton>> keyboard = getKeyboardList();
    inlineKeyboardMarkup.setKeyboard(keyboard);
    message.setReplyMarkup(inlineKeyboardMarkup);
    messageService.sendMessage(message);
  }

  private List<List<InlineKeyboardButton>> getKeyboardList() {
    List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
    subscriptionClient.getAllSubscriptions().subscriptionsList().forEach(subscription -> {
      List<InlineKeyboardButton> buttons = new ArrayList<>();
      var button = new InlineKeyboardButton();
      String title = subscription.title();
      UUID subscriptionId = subscription.id();
      button.setText(title);
      button.setCallbackData(title + ";" + subscriptionId);
      buttons.add(button);
      keyboard.add(buttons);
    });
    return keyboard;
  }

}
