package org.practice.actionmonitor.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.practice.actionmonitor.business.service.MessageService;
import org.practice.actionmonitor.dal.dto.Message;
import org.practice.actionmonitor.web.model.MessageRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessageControllerTest {

    @Mock
    private MessageRequest messageRequest;
    @Mock
    private MessageService messageService;

    private MessageController underTest;

    @Before
    public void setUp() {
        underTest = new MessageController(messageService);
    }

    @Test
    public void shouldSendMessage() throws JsonProcessingException {
        //Given
        ArgumentCaptor<Message> argumentCaptor = ArgumentCaptor.forClass(Message.class);
        String text = "text";
        when(messageRequest.getText()).thenReturn(text);

        //When
        underTest.sendMessage(messageRequest);

        //Then
        verify(messageService).send(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().getText()).isEqualTo(text);
    }
}
