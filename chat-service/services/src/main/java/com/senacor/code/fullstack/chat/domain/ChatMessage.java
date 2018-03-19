package com.senacor.code.fullstack.chat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

/**
 * Created by Nikolas Glaser on 26.01.18.
 */

public class ChatMessage {


    private String id;

    private String channelId;

    @NotEmpty
    @Email
    private String sender;

    @NotEmpty
    @Size(min = 1, max = 140)
    private String message;
    private Instant creationTimestamp;

    public ChatMessage() {

    }

    public ChatMessage(String channelId, String sender, String message) {
        this.channelId = channelId;
        this.sender = sender;
        this.message = message;
        this.creationTimestamp = Instant.now();
    }

    public String getId() {
        return id;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatMessage that = (ChatMessage) o;

        if (!id.equals(that.id)) return false;
        if (channelId != null ? !channelId.equals(that.channelId) : that.channelId != null) return false;
        if (sender != null ? !sender.equals(that.sender) : that.sender != null) return false;
        return message != null ? message.equals(that.message) : that.message == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (channelId != null ? channelId.hashCode() : 0);
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id='" + id + '\'' +
                ", channelId='" + channelId + '\'' +
                ", sender='" + sender + '\'' +
                ", message='" + message + '\'' +
                ", creationTimestamp=" + creationTimestamp +
                '}';
    }
}
