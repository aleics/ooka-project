package com.senacor.code.fullstack.chat.repository;

import com.senacor.code.fullstack.chat.ChatApplication;
import com.senacor.code.fullstack.chat.domain.ChatMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ChatApplication.class)
public class ChatMessageRepositoryIT {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Before
    public void setup() {
        // ensure we are staring without any chat messages
        chatMessageRepository.deleteAll();
    }

    @After
    public void cleanup() {
        chatMessageRepository.deleteAll();
    }

    @Test
    public void saveAndLoadChatMessages() {
        assertTrue(chatMessageRepository.findAll().isEmpty());

        ChatMessage chatMessageOne = chatMessageRepository.save(
                new ChatMessage("dev","sender@test.de","Hello"));
        assertEquals(1, chatMessageRepository.findAll().size());

        ChatMessage chatMessageTwo = chatMessageRepository.save(
                new ChatMessage("general", "sender@test.de", "World!"));

        assertEquals(asList(chatMessageOne, chatMessageTwo), chatMessageRepository.findAll());
    }

    @Test
    public void saveAndLoadMessagesById(){
        assertTrue(chatMessageRepository.findAll().isEmpty());

        ChatMessage chatMessageOne = chatMessageRepository.save(
                new ChatMessage("dev","sender@test.de","Hello"));
        ChatMessage chatMessageTwo = chatMessageRepository.save(
                new ChatMessage("dev", "sender@test.de", "World!"));
        ChatMessage chatMessageThree = chatMessageRepository.save(
                new ChatMessage("general", "sender@test.de", "World!"));
        ChatMessage chatMessageFour = chatMessageRepository.save(
                new ChatMessage("general", "sender@test.de", "World!"));

        assertEquals(
                asList(chatMessageOne, chatMessageTwo),
                chatMessageRepository.findByChannelIdOrderByCreationTimestampDesc("dev"));
    }

}
