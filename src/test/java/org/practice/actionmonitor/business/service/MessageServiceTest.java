package org.practice.actionmonitor.business.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.practice.actionmonitor.business.model.DatabaseInsertMessage;
import org.practice.actionmonitor.dal.dto.Message;
import org.practice.actionmonitor.dal.repository.MessageRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;
    @Mock
    private MessageSenderService messageSenderService;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private Message message;

    private MessageService underTest;


    @Before
    public void setUp() {
        underTest = new MessageService(messageRepository, messageSenderService, objectMapper);
    }

    @Test
    public void shouldSendMessage() throws JsonProcessingException {
        //Given
        ArgumentCaptor<DatabaseInsertMessage> argumentCaptor = ArgumentCaptor.forClass(DatabaseInsertMessage.class);
        String valueAsString = "valueAsString";
        when(messageRepository.save(any(Message.class))).thenReturn(message);
        when(message.getId()).thenReturn(0L);
        when(message.getText()).thenReturn("text");
        when(objectMapper.writeValueAsString(any())).thenReturn(valueAsString);

        //When
        underTest.send(message);

        //Then
        verify(messageRepository).save(eq(message));
        verify(messageSenderService).send(eq(valueAsString));
        verify(objectMapper).writeValueAsString(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().getMessage()).isEqualTo("A record was inserted into the database [ id = 0, message= text]");
    }
}
