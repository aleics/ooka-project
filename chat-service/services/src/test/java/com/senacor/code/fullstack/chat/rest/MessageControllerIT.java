package com.senacor.code.fullstack.chat.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senacor.code.fullstack.chat.domain.ChatMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageControllerIT {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void loadMessages() throws Exception {
        String channel = "dev";
        mockMvc.perform(get("/api/v1/channels/{channel}/messages", channel)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].channelId").value("dev"))
                .andExpect(jsonPath("$[0].message").value("Goodbye"))
                .andExpect(jsonPath("$[1].channelId").value("dev"))
                .andExpect(jsonPath("$[1].message").value("World"));
    }

    @Test
    public void loadMessagesFromNotExistingChannel() throws Exception {
        String channel = "test";
        mockMvc.perform(get("/api/v1/{channel}/messages", channel)
        .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
        .andExpect(status().is4xxClientError());
    }

    @Test
    public void createMessages() throws Exception {
        String channel = "dev";
        ChatMessage testMessage = new ChatMessage("dev", "sender@test.de", "testMessage");
        mockMvc.perform(post("/api/v1/channels/{channel}/messages", channel)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testMessage))
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(header()
                        .string("location", startsWith("http://localhost/api/v1/channels/dev/messages")));
    }

}
