package com.jts.subscription.telegram.data.mapper;

import com.jts.subscription.telegram.data.dto.SystemUser;
import org.mapstruct.Mapper;
import org.telegram.telegrambots.meta.api.objects.User;

@Mapper(componentModel = "spring")
public interface SystemUserMapper {

  SystemUser mapToSystemUser(User user);

}
