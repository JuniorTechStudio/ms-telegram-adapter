package com.jts.subscription.telegram.data.dto;

public record UserCreateRequest(String firstName, String lastName, String userName, String telegramId) {
}