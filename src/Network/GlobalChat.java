package Network;

import java.util.ArrayList;

public class GlobalChat
{
    private static GlobalChat globalChat = new GlobalChat();
    private static ArrayList<ChatMessage> chatMessages;

    private GlobalChat()
    {

    }

    public static GlobalChat getGlobalChat()
    {
        return globalChat;
    }

    public static ArrayList<ChatMessage> getChatMessages()
    {
        return chatMessages;
    }

    public static void addChatMessages(ChatMessage chatMessage)
    {
        GlobalChat.chatMessages.add(chatMessage);
    }
}
