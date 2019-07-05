package Network;

import Model.Account;

import java.util.ArrayList;

public class ServerCommand
{
    private ServerCommandEnum serverCommandEnum;
    private String errorMessage;
    private ArrayList<Account> accounts;
    private boolean login;
    private String authToken;
    private ArrayList<ChatMessage> chatMessages;

    public ServerCommand(ServerCommandEnum serverCommandEnum, String errorMessage)   //error
    {
        this.serverCommandEnum = serverCommandEnum;
        this.errorMessage = errorMessage;
    }

    public ServerCommand(ServerCommandEnum serverCommandEnum, ArrayList<Account> accounts)
    {
        this.serverCommandEnum = serverCommandEnum;
        this.accounts = accounts;
    }

    public ServerCommand(ServerCommandEnum serverCommandEnum)    //ok
    {
        this.serverCommandEnum = serverCommandEnum;
    }

    public ServerCommandEnum getServerCommandEnum()
    {
        return serverCommandEnum;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setAuthToken(String authToken)
    {
        this.authToken = authToken;
    }

    public String getAuthToken()
    {
        return authToken;
    }

    public boolean isLogin()
    {
        return login;
    }

    public void setLogin(boolean login)
    {
        this.login = login;
    }

    public ArrayList<Account> getAccounts()
    {
        return accounts;
    }

    public ArrayList<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(ArrayList<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }
}
