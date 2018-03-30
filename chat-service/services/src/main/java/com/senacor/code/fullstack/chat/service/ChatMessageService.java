package com.senacor.code.fullstack.chat.service;


import com.senacor.code.fullstack.chat.domain.ChatMessage;
import com.senacor.code.fullstack.chat.exceptions.ChannelNotFoundException;
import com.senacor.code.fullstack.chat.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nikolas Glaser on 26.01.18.
 */
@Service
public class ChatMessageService {

    private ChannelService channelService;
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    public ChatMessageService (ChannelService channelService, ChatMessageRepository chatMessageRepository) {
        this.channelService = channelService;
        this.chatMessageRepository = chatMessageRepository;
    }

    public List<ChatMessage> loadChatMessages (String channelId){
        if(!channelService.existsChannel(channelId)){
            throw new ChannelNotFoundException();
        }
        return chatMessageRepository.findByChannelIdOrderByCreationTimestampAsc(channelId);

    }

    public ChatMessage saveChatMessages (String channelId, String sender, String message){
        if(!channelService.existsChannel(channelId)){
            throw new ChannelNotFoundException();
        }
        return chatMessageRepository.save(new ChatMessage(channelId, sender, message));
    }
}
