package com.senacor.code.fullstack.chat.rest;

import com.senacor.code.fullstack.chat.domain.Channel;
import com.senacor.code.fullstack.chat.domain.ChatMessage;
import com.senacor.code.fullstack.chat.exceptions.ChannelNotFoundException;
import com.senacor.code.fullstack.chat.service.ChannelService;
import com.senacor.code.fullstack.chat.service.ChatMessageService;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MessageControllerTest {

    private ChatMessageService serviceMock = mock(ChatMessageService.class);
    private ChannelService channelServiceMock = mock(ChannelService.class);

    private ChatMessageController controller = new ChatMessageController(serviceMock);

    @Test
    public void loadMessages() throws ChannelNotFoundException {
        //given
        List<ChatMessage> expected = asList(
                new ChatMessage("dev", "sender@test.de", "Hello"),
                new ChatMessage("dev", "sender@test.de", "World!"));
        when(serviceMock.loadChatMessages("dev")).thenReturn(expected);
        when(channelServiceMock.existsChannel("dev")).thenReturn(true);

        //when
        List<ChatMessage> result = controller.loadMessages("dev");

        //then
        assertEquals(expected, result);
        //prüft on methode einmal aufgerufen wurde.
        verify(serviceMock).loadChatMessages("dev");
    }
}
