package com.jts.subscription.telegram.client;

import com.jts.subscription.telegram.data.dto.SystemUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ms-users", url = "http://192.168.88.13:8083/users")
public interface UserClient {

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    void saveUser(@RequestBody SystemUser systemUser);
}
