package com.jts.subscription.telegram.service;

import com.jts.subscription.telegram.data.dto.PreparedSubscriptionContent;
import com.jts.subscription.telegram.data.dto.TelegramSendContentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MessageService {

    private final TelegramLongPollingBot bot;

    public void sendMessage(String id, String text, boolean disableNotification) {
        try {
            bot.execute(SendMessage.builder()
                    .chatId(id)
                    .text(text)
                    .disableNotification(disableNotification)
                    .build()
            );
        } catch (TelegramApiException e) {
            log.info("{}. USER ID: {}", e.getMessage(), id);
        }
    }

    public void sendStartMessage(String id, boolean disableNotification) {
        try {
            String startMsg = "Привет! я бот прикол :) выбери тему которую хочешь изучать! А я буду присылать тебе контент!";

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
                    .chatId(id)
                    .text(startMsg)
                    .disableNotification(disableNotification)
                    .build();
            msg.setReplyMarkup(inlineKeyboardMarkup);
            bot.execute(msg);
            log.info("бот сказал: {}", startMsg);
        } catch (TelegramApiException e) {
            log.info("{}. USER ID: {}", e.getMessage(), id);
        }
    }

    public void sendResetMessage(String id, boolean disableNotification) {
        try {
            String startMsg = "Вы изучили всю тему! Хотите изучить другую или повторить завершенную?";

            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
            List<InlineKeyboardButton> buttons = new ArrayList<>();

            var button1 = new InlineKeyboardButton();
            button1.setText("Тема 1");
            button1.setCallbackData("theme_1_button");

            var button2 = new InlineKeyboardButton();
            button2.setText("Тема 2");
            button2.setCallbackData("theme_2_button");

            var button3 = new InlineKeyboardButton();
            button3.setText("Тема 3");
            button3.setCallbackData("theme_3_button");

            buttons.add(button1);
            buttons.add(button2);
            buttons.add(button3);

            keyboard.add(buttons);
            inlineKeyboardMarkup.setKeyboard(keyboard);

            SendMessage msg = SendMessage.builder()
                    .chatId(id)
                    .text(startMsg)
                    .disableNotification(disableNotification)
                    .build();
            msg.setReplyMarkup(inlineKeyboardMarkup);
            bot.execute(msg);
            log.info("бот сказал: {}", startMsg);
        } catch (TelegramApiException e) {
            log.info("{}. USER ID: {}", e.getMessage(), id);
        }
    }

    public void editMessage(EditMessageText msg) {
        try {
            bot.execute(msg);
        } catch (TelegramApiException e) {
            log.info("{}.", e.getMessage());
        }
    }

    public void sendContent(TelegramSendContentRequest telegramSendContentRequest) {
        List<PreparedSubscriptionContent> preparedSubscriptionContentList = telegramSendContentRequest.getPreparedSubscriptionContentList();
        for(PreparedSubscriptionContent content : preparedSubscriptionContentList) {
            String id = content.getTelegramId();
            String contentString = content.getContent();
            sendMessage(id, contentString, false);
        }
    }
}
