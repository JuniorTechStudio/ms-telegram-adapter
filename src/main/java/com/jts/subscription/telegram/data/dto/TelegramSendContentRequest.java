package com.jts.subscription.telegram.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TelegramSendContentRequest {
    private List<PreparedSubscriptionContent> preparedSubscriptionContentList;
}
