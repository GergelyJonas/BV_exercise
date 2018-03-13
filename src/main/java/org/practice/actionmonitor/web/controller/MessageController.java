package org.practice.actionmonitor.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.practice.actionmonitor.dal.dto.Message;
import org.practice.actionmonitor.web.model.MessageRequest;
import org.practice.actionmonitor.business.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(value = "/message/send", method = POST, consumes = "application/json")
    public void sendMessage(@RequestBody MessageRequest messageRequest) throws JsonProcessingException {
        messageService.send(new Message(messageRequest.getText()));
    }

}
