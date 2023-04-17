package com.jts.subscription.telegram.client;

import com.jts.subscription.telegram.data.dto.SystemUser;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ms-users", url = "${feign.user.url}")
public interface UserClient {

  @PostMapping(value = "/save")
  void saveUser(@RequestBody SystemUser systemUser);

  @GetMapping(value = "/getID/{telegramId}")
  UUID getUserIdByTelegramId(@PathVariable String telegramId);

}
