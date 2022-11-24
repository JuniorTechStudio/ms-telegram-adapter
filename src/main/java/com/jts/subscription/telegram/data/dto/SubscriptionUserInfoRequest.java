package com.jts.subscription.telegram.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionUserInfoRequest {
    private UUID userId;
    private String telegramId;
    private String subscriptionTitle;
}
