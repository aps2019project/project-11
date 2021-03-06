package Network;

import Model.*;

import java.util.ArrayList;

public class ServerCommand
{
    private ServerCommandEnum serverCommandEnum;
    private String message;
    private Account account;
    private ArrayList<Account> accounts;
    private ArrayList<Hero> heroes = new ArrayList<>();
    private ArrayList<Minion> minions = new ArrayList<>();
    private ArrayList<Spell> spells = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Deck> decks = new ArrayList<>();
    private Hero bidHero;
    private Minion bidMinion;
    private Spell bidSpell;
    private Item bidItem;
    private boolean login;
    private String authToken;
    private ArrayList<ChatMessage> chatMessages;
    private Player player;
    private Battle battle;

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

    public ServerCommand(ArrayList<Deck> decks, ServerCommandEnum serverCommandEnum)
    {
        this.serverCommandEnum = serverCommandEnum;
        this.decks = decks;
    }

    public ServerCommand(ServerCommandEnum serverCommandEnum, Account account)
    {
        this.serverCommandEnum = serverCommandEnum;
        this.account = account;
    }

    public ServerCommand(ServerCommandEnum serverCommandEnum, ArrayList<Hero> heroes, ArrayList<Minion> minions, ArrayList<Spell> spells, ArrayList<Item> items)
    {
        this.serverCommandEnum = serverCommandEnum;
        this.heroes = heroes;
        this.minions = minions;
        this.spells = spells;
        this.items = items;
    }

    public ServerCommand(ServerCommandEnum serverCommandEnum, Hero hero, Minion minion, Spell spell, Item item)
    {
        this.serverCommandEnum = serverCommandEnum;
        this.bidHero = hero;
        this.bidMinion = minion;
        this.bidSpell = spell;
        this.bidItem = item;
    }

    public ServerCommand(ServerCommandEnum serverCommandEnum, ArrayList<Hero> heroes, ArrayList<Minion> minions, ArrayList<Spell> spells, ArrayList<Item> items, ArrayList<Deck> decks)
    {
        this.serverCommandEnum = serverCommandEnum;
        this.heroes = heroes;
        this.minions = minions;
        this.spells = spells;
        this.items = items;
        this.decks = decks;
    }

    public ServerCommand(String message)
    {
        this.message = message;
    }

    public ServerCommand(ServerCommandEnum serverCommandEnum)    //ok
    {
        this.serverCommandEnum = serverCommandEnum;
    }

    public ServerCommand(ServerCommandEnum serverCommandEnum, Battle battle)
    {
        this.serverCommandEnum = serverCommandEnum;
        this.battle = battle;
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

    public ArrayList<ChatMessage> getChatMessages()
    {
        return chatMessages;
    }

    public void setChatMessages(ArrayList<ChatMessage> chatMessages)
    {
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

    public Account getAccount()
    {
        return account;
    }

    public ArrayList<Deck> getDecks()
    {
        return decks;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Player getPlayer()
    {
        return player;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public Hero getBidHero()
    {
        return bidHero;
    }

    public Minion getBidMinion()
    {
        return bidMinion;
    }

    public Spell getBidSpell()
    {
        return bidSpell;
    }

    public Item getBidItem()
    {
        return bidItem;
    }

    public Battle getBattle()
    {
        return battle;
    }
}
