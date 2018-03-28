package com.senacor.code.fullstack.chat.repository;

import com.senacor.code.fullstack.chat.domain.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Nikolas Glaser on 26.01.18.
 */
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    public List<ChatMessage> findByChannelId(String channel);

    public List<ChatMessage> findByChannelIdOrderByCreationTimestampAsc(String channel);
}
