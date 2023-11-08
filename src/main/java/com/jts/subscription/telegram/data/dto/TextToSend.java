package com.jts.subscription.telegram.data.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TextToSend {
    private String telegramId;
    private String content;
}
