package Network;

import Model.*;

import java.util.ArrayList;

public class ServerCommand
{
    private ServerCommandEnum serverCommandEnum;
    private String message;
    private ArrayList<Account> accounts;
    private ArrayList<Hero> heroes = new ArrayList<>();
    private ArrayList<Minion> minions = new ArrayList<>();
    private ArrayList<Spell> spells = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private boolean login;
    private String authToken;
    private ArrayList<ChatMessage> chatMessages;

    public ServerCommand(ServerCommandEnum serverCommandEnum, String message)   //error
    {
        this.serverCommandEnum = serverCommandEnum;
        this.message = message;
    }

    public ServerCommand(ServerCommandEnum serverCommandEnum, ArrayList<Account> accounts)
    {
        this.serverCommandEnum = serverCommandEnum;
        this.accounts = accounts;
    }

    public ServerCommand(ServerCommandEnum serverCommandEnum,ArrayList<Hero> heroes,ArrayList<Minion> minions, ArrayList<Spell> spells, ArrayList<Item> items)
    {
        this.serverCommandEnum = serverCommandEnum;
        this.heroes = heroes;
        this.minions = minions;
        this.spells = spells;
        this.items = items;
    }

    public ServerCommand(String message)
    {
        this.message = message;
    }

    public ServerCommand(ServerCommandEnum serverCommandEnum)    //ok
    {
        this.serverCommandEnum = serverCommandEnum;
    }

    public ServerCommandEnum getServerCommandEnum()
    {
        return serverCommandEnum;
    }

    public String getMessage()
    {
        return message;
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

    public ArrayList<Hero> getHeroes()
    {
        return heroes;
    }

    public ArrayList<Minion> getMinions()
    {
        return minions;
    }

    public ArrayList<Spell> getSpells()
    {
        return spells;
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }
}
