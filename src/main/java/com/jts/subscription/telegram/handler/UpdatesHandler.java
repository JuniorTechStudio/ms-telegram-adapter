package com.jts.subscription.telegram.handler;

import com.jts.subscription.telegram.client.UserClient;
import com.jts.subscription.telegram.data.dto.SystemUser;
import com.jts.subscription.telegram.data.mapper.SystemUserMapper;
import com.jts.subscription.telegram.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

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
        if (Objects.nonNull(update.getMessage()) && update.getMessage().hasText()) {

            String userId = update.getMessage().getFrom().getId().toString();

            switch(update.getMessage().getText()) {
                case "/start":
                    String startMessage = String.format("Привет, %s!", update.getMessage().getFrom().getFirstName());
                    userClient.saveUser(systemUserMapper.mapToSystemUser(update.getMessage().getFrom()));
                    messageService.sendMessage(userId, startMessage, false);
                    break;
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
