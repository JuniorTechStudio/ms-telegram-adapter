package com.jts.subscription.telegram.client;

import com.jts.subscription.telegram.data.dto.SubscriptionUserInfoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-subscription", url = "${feign.subscription.url}")
public interface SubscriptionClient {

  @PostMapping(value = "/save")
  void saveSubscriptionUserInfo(@RequestBody SubscriptionUserInfoRequest subscriptionUserInfoRequest);
  
}
