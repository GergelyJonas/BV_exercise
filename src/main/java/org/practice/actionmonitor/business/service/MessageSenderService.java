package org.practice.actionmonitor.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderService {

    public static final String TOPIC_PATH = "/topic/messages";
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public MessageSenderService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void send(Object message) {
        messagingTemplate.convertAndSend(TOPIC_PATH, message);
    }
}
