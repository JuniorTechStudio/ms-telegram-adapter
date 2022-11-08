package com.jts.subscription.telegram.handler;

import com.jts.subscription.telegram.client.UserClient;
import com.jts.subscription.telegram.data.mapper.SystemUserMapper;
import com.jts.subscription.telegram.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdatesHandler extends TelegramLongPollingBot {

    private final UserClient userClient;
    private final SystemUserMapper systemUserMapper;
    private final MessageService messageService;

    @Value("${bot.name}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;


    @Override
    public void onUpdateReceived(Update update) {

        if(Objects.nonNull(update.getMessage()) && update.getMessage().hasText()) {

            String chatId = update.getMessage().getChatId().toString();
            String name = update.getMessage().getFrom().getFirstName();
            User user = update.getMessage().getFrom();

            log.info("{} сказал: {}", name, update.getMessage().getText());

            switch(update.getMessage().getText()) {
                case "/start":
                {
                    //код для /start
                    //SystemUser newUser = systemUserMapper.mapToSystemUser(user);
                    //userClient.saveUser(newUser);
                    messageService.sendStartMessage(chatId, false);
                    break;
                }
                case "/stop":
                {
                    //код для /stop
                    messageService.sendMessage(chatId,"stop", false);
                    break;
                }
                case "/go": {
                    // код для /go
                    messageService.sendMessage(chatId,"go", false);
                    break;
                }
                case "/profile": {
                    //код для /profile
                    messageService.sendMessage(chatId,"profile", false);
                    break;
                }
            }
        }

        else if (update.hasCallbackQuery()) {

            String chatId = update.getCallbackQuery().getFrom().getId().toString();
            int messageId = update.getCallbackQuery().getMessage().getMessageId();
            String callBackData = update.getCallbackQuery().getData();
            String name = update.getCallbackQuery().getFrom().getFirstName();

            log.info("{} Выбрал тему {}", name, callBackData);
            switch (callBackData) {
                case "theme_1_button" : {
                    //код для кнопки
                    EditMessageText msg = new EditMessageText();
                    msg.setChatId(chatId);
                    msg.setText("Вы выбрали тему 'Java'");
                    msg.setMessageId(messageId);
                    messageService.editMessage(msg);
                    break;
                }
                case "theme_2_button" : {
                    //код для кнопки
                    EditMessageText msg = new EditMessageText();
                    msg.setChatId(chatId);
                    msg.setText("Вы выбрали тему 'English'");
                    msg.setMessageId(messageId);
                    messageService.editMessage(msg);
                    break;
                }
                case "theme_3_button" : {
                    //код для кнопки
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
