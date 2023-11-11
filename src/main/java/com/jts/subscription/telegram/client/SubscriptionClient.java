package com.jts.subscription.telegram.client;

import com.jts.subscription.telegram.data.dto.AddUserToSubscriptionRequest;
import com.jts.subscription.telegram.data.dto.SubscriptionsList;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-subscription", url = "${feign.subscription.url}")
public interface SubscriptionClient {

  @GetMapping(value = "/subscriptions")
  SubscriptionsList getAllSubscriptions();

  @PostMapping("/subscriptions/{subscriptionId}/users")
  void subscribe(@PathVariable UUID subscriptionId, @RequestBody AddUserToSubscriptionRequest request);
  
}
