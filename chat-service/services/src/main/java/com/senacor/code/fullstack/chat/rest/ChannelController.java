package com.senacor.code.fullstack.chat.rest;

import com.senacor.code.fullstack.chat.domain.Channel;
import com.senacor.code.fullstack.chat.domain.ChatMessage;
import com.senacor.code.fullstack.chat.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * The REST controller for managing communication channels.
 */
@RestController
@RequestMapping("/api/v1/channels")
public class ChannelController {

    private ChannelService channelService;

    @Autowired
    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Channel> loadChannels() {
        return channelService.loadChannels();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Channel createChannel(@Valid @RequestBody Channel channel) {
        return channelService.createChannel(channel);
    }

}
