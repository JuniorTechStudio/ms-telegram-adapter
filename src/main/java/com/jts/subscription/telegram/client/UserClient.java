package com.jts.subscription.telegram.client;

import java.util.UUID;

import com.jts.subscription.telegram.data.dto.UserCreateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ms-users", url = "${feign.user.url}")
public interface UserClient {

  @PostMapping(value = "/users")
  void saveTelegramUser(@RequestBody UserCreateRequest telegramUser);

  @GetMapping(value = "/getID/{telegramId}")
  UUID getUserIdByTelegramId(@PathVariable String telegramId);

}
