package Network;

import Model.Account;

public class ChatMessage
{
    private Account sender;
    private String message;

    public ChatMessage(Account sender, String message)
    {
        this.sender = sender;
        this.message = message;
        GlobalChat.getChatMessages().add(this);
    }

    public Account getSender()
    {
        return sender;
    }

    public String getMessage()
    {
        return message;
    }
}
