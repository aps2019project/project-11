package Network;

import Model.Account;

import java.util.Date;

public class ChatMessage {
    private Account sender;
    private Account receiver;
    private String message;
    private Date date;

    public ChatMessage(Account sender, Account receiver, String message, Date date) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.date = date;
        GlobalChat.getChatMessages().add(this);
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
