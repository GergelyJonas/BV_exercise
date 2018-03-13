package org.practice.actionmonitor.business.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.practice.actionmonitor.business.model.DatabaseInsertMessage;
import org.practice.actionmonitor.dal.dto.Message;
import org.practice.actionmonitor.dal.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);
    private static final String MESSAGE_TEMPLATE = "A record was inserted into the database [ id = %d, message= %s]";
    private static final String LOG_MESSAGE = "Message \"{}\" has been stored in the database..";

    private final MessageRepository messageRepository;
    private final MessageSenderService messageSenderService;
    private final ObjectMapper objectMapper;

    @Autowired
    public MessageService(MessageRepository messageRepository, MessageSenderService messageSenderService, ObjectMapper objectMapper) {
        this.messageRepository = messageRepository;
        this.messageSenderService = messageSenderService;
        this.objectMapper = objectMapper;
    }

    public void send(Message message) throws JsonProcessingException {
        Message persistedMessage = messageRepository.save(message);
        LOGGER.info(LOG_MESSAGE, persistedMessage.getText());
        messageSenderService.send(createJsonMessage(persistedMessage));
    }

    private String createJsonMessage(Message message) throws JsonProcessingException {
        return objectMapper.writeValueAsString(new DatabaseInsertMessage(String.format(MESSAGE_TEMPLATE, message.getId(), message.getText())));
    }

}
