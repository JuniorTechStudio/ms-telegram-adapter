package com.jts.subscription.telegram.telegram.action;

import com.jts.subscription.telegram.service.MessageService;
import com.jts.subscription.telegram.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component("start")
@RequiredArgsConstructor
public class StartMessage implements Action {

  private final MessageService messageService;
  private final UserService userService;

  @Override
  public void handle(Update update) {
    User user = update.getMessage().getFrom();
    userService.createUser(user);

    String chatId = update.getMessage().getChatId().toString();
    String startMsg = "Привет! Выбери тему которую хочешь изучать! А я буду присылать тебе контент!";

    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
    List<InlineKeyboardButton> buttons = new ArrayList<>();

    var button1 = new InlineKeyboardButton();
    button1.setText("Тема 1 ?");
    button1.setCallbackData("theme_1_button");

    var button2 = new InlineKeyboardButton();
    button2.setText("Тема 2 ?");
    button2.setCallbackData("theme_2_button");

    var button3 = new InlineKeyboardButton();
    button3.setText("Тема 3 ?");
    button3.setCallbackData("theme_3_button");

    buttons.add(button1);
    buttons.add(button2);
    buttons.add(button3);

    keyboard.add(buttons);
    inlineKeyboardMarkup.setKeyboard(keyboard);

    SendMessage msg = SendMessage.builder()
            .chatId(chatId)
            .text(startMsg)
            .disableNotification(false)
            .build();
    msg.setReplyMarkup(inlineKeyboardMarkup);

    messageService.sendMessage(msg);
    log.info("бот сказал: {}", startMsg);
  }

}
