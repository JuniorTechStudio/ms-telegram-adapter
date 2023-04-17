package com.jts.subscription.telegram.telegram.action;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Action {

  void handle(Update update);

}
