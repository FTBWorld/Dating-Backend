package com.ftbworld.dating.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

// TODO: SQL.
// TODO: chat with sockets.
@Document(collection = "chat_messages")
public class ChatMessage {

    private final String senderUsername;
    private final String ReceiverUsername;
    private final String message;
    private final Instant created;

    public ChatMessage(String senderUsername, String receiverUsername, String message, Instant created) {
        this.senderUsername = senderUsername;
        ReceiverUsername = receiverUsername;
        this.message = message;
        this.created = created;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public String getReceiverUsername() {
        return ReceiverUsername;
    }

    public String getMessage() {
        return message;
    }

    public Instant getCreated() {
        return created;
    }
}
