package com.jts.subscription.telegram.service;


import com.jts.subscription.telegram.client.SubscriptionClient;
import com.jts.subscription.telegram.data.dto.SubscriptionValue;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    
    private final SubscriptionClient subscriptionClient;
    
    public List<String> getAllSubscriptionsTitles() {
        return subscriptionClient.getAllSubscriptions().subscriptionsList().stream()
                .map(SubscriptionValue::title)
                .toList();
    }
    
}
