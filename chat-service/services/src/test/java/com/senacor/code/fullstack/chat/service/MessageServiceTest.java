package com.senacor.code.fullstack.chat.service;

import com.senacor.code.fullstack.chat.domain.ChatMessage;
import com.senacor.code.fullstack.chat.exceptions.ChannelNotFoundException;
import com.senacor.code.fullstack.chat.repository.ChatMessageRepository;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Nikolas Glaser on 26.01.18.
 */
public class MessageServiceTest {
    private ChatMessageService service;
    private ChannelService channelService = mock(ChannelService.class);
    private ChatMessageRepository chatMessageRepository = mock(ChatMessageRepository.class);
    private String channel = "dev";

    @Before
    public void setup() {

        when(channelService.existsChannel(channel)).thenReturn(true);
        when(chatMessageRepository.findByChannelIdOrderByCreationTimestampDesc(channel))
                .thenReturn(Lists.newArrayList(
                        new ChatMessage("dev", "sender@test.de", "Hello"),
                        new ChatMessage("dev", "sender@test.de", "World!")
                        )
                );
        service = new ChatMessageService(channelService, chatMessageRepository);
    }

    @Test
    public void testloadMessages() {
        List<ChatMessage> result = service.loadChatMessages("dev");

        Assert.assertEquals(2, result.size());
        Assert.assertEquals("Hello", result.get(0).getMessage());
        Assert.assertEquals("World!", result.get(1).getMessage());
    }

    @Test(expected = ChannelNotFoundException.class)
    public void loadChatMessageFromNotExistingChannel() {
        when(channelService.existsChannel("test")).thenReturn(false);
        service.loadChatMessages("test");
    }
}
