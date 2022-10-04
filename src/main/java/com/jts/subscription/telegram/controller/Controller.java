package com.jts.subscription.telegram.controller;

import com.jts.subscription.telegram.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/JTSBot")
@RequiredArgsConstructor
public class Controller {

    private final MessageService messageService;

    @PostMapping("/{text}")
    public void sendMessage(@PathVariable String text){
        messageService.sendMessage("1037426316", text, false);
    }
}
