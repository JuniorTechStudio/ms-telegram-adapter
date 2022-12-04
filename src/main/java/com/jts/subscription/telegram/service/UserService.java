package com.jts.subscription.telegram.service;

import com.jts.subscription.telegram.client.UserClient;
import com.jts.subscription.telegram.data.dto.SystemUser;
import com.jts.subscription.telegram.data.mapper.SystemUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;
    private final SystemUserMapper systemUserMapper;

    public void createUser(User user) {
        SystemUser newUser = systemUserMapper.mapToSystemUser(user);
        userClient.saveUser(newUser);
    }

}
