package org.practice.actionmonitor.business.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MessageSenderServiceTest {

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    private MessageSenderService underTest;

    @Before
    public void setUp() {
        underTest = new MessageSenderService(messagingTemplate);
    }

    @Test
    public void shouldSendMessage() {
        //Given
        Object message = mock(Object.class);

        //When
        underTest.send(message);

        //Then
        verify(messagingTemplate).convertAndSend(eq("/topic/messages"), eq(message));
    }

}
