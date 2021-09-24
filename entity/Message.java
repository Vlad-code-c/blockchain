package entity;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private String message;
    private final long timestamp;

    public Message(String message) {
        this.message = message;
        this.timestamp = new Date().getTime();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
