package persistence;

import entity.Message;
import log.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MessageStorage implements Serializable {
    private static final List<Message> messages = new ArrayList<>();
    private static MessageStorage instance;

    private MessageStorage() {}

    public static MessageStorage getInstance() {
        if (instance == null) instance = new MessageStorage();
        return instance;
    }

    public static List<Message> getMessages(long timestamp) {
        messages.removeIf(message -> message.getTimestamp() < timestamp);
        return messages;
    }

    public static void addMessage(Message message) {
        messages.add(message);
    }

//    public ArrayList<Message> getMessages() {
//        return messages;
//    }

    public String toString() {
        return messages.isEmpty() ? " no messages" : "\n" +
                messages.stream().map(message -> message.getMessage() + "\n")
                        .collect(Collectors.joining());
    }
}
