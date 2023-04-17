package com.jts.subscription.telegram.data.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionUserInfoRequest {
    private UUID userId;
    private String telegramId;
    private String subscriptionTitle;
}
