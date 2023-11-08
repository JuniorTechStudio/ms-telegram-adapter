package com.jts.subscription.telegram.data.mapper;

import com.jts.subscription.telegram.data.dto.UserCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.telegram.telegrambots.meta.api.objects.User;

@Mapper(componentModel = "spring")
public interface SystemUserMapper {

  @Mapping(target = "telegramId", source = "id")
  UserCreateRequest mapToSystemUser(User user);

}
