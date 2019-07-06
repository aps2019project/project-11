package Network;

import Model.Account;

import java.util.Date;

public class ChatMessage {
    private Account sender;
    private String message;

    public ChatMessage(Account sender, String message) {
        this.sender = sender;
        this.message = message;
        GlobalChat.getChatMessages().add(this);
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
