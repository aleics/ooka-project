package com.senacor.code.fullstack.chat.rest;

import com.senacor.code.fullstack.chat.domain.ChatMessage;
import com.senacor.code.fullstack.chat.exceptions.ChannelNotFoundException;
import com.senacor.code.fullstack.chat.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The REST controller for managing chat messages.
 */
@RestController
@RequestMapping("/api/v1/channels/{channel}/messages")
public class ChatMessageController {

    private static final String CHAT_MESSAGE_PATH = "/api/v1/channels/{channelId}/messages/{id}";
    private ChatMessageService chatMessageService;

    @Autowired
    public ChatMessageController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @GetMapping
    public List<ChatMessage> loadMessages(@PathVariable("channel") String channel) throws ChannelNotFoundException {
        return chatMessageService.loadChatMessages(channel);

    }

    @PostMapping
    public ResponseEntity<ChatMessage> saveMessages(
            @PathVariable("channel") String channel,
            @Valid @RequestBody ChatMessage chatMessage,
            UriComponentsBuilder builder) throws ChannelNotFoundException {

        ChatMessage returnMessage = chatMessageService.saveChatMessages(
                channel,
                chatMessage.getSender(),
                chatMessage.getMessage()
        );

        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("channelId", returnMessage.getChannelId());
        parameter.put("id", returnMessage.getId());

        UriComponents uriComponents = builder.path(CHAT_MESSAGE_PATH).buildAndExpand(parameter);

        return ResponseEntity.created(uriComponents.toUri()).body(returnMessage);
    }

}
