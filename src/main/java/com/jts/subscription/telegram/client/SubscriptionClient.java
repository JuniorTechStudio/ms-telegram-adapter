package com.jts.subscription.telegram.client;

import com.jts.subscription.telegram.data.dto.SubscriptionUserInfoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ms-subscription", url = "localhost:8085/subscription")
public interface SubscriptionClient {

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    void saveSubscriptionUserInfo(@RequestBody SubscriptionUserInfoRequest subscriptionUserInfoRequest);
}
