package Model;

import java.util.ArrayList;

public class Account
{
    public transient static Account loggedInAccount;
    private String accountName;
    private ArrayList<FinishedMatch> matchHistory = new ArrayList<>();
    private Collection collection = new Collection();
    private ArrayList<Deck> playerDecks = new ArrayList<>();
    private Deck mainDeck;
    private int money = 15000;
    private String password;
    private int numOfWins = 0;
    private int defaultID = 1;
    private int AIAccountDefaultID = 10000;

    public Account(String userName, String password)
    {
        this.password = password;
        this.accountName = userName;
    }

    public Account()
    {

    }

    public void addDefaultCardsToCollection(Account account)
    {
        try
        {
            this.getCollection().addCard(account, (Hero) Hero.findHero("Rostam").clone());
            this.getCollection().addItem(account, (Item) Item.findItem("CrownOfWisdom").clone());
            this.getCollection().addCard(account, (Minion) Card.findCard("kamandarFars").clone());
            this.getCollection().addCard(account, (Minion) Card.findCard("kamandarFars").clone());
            this.getCollection().addCard(account, (Minion) Card.findCard("neizedarFars").clone());
            this.getCollection().addCard(account, (Minion) Card.findCard("neizedarFars").clone());
            this.getCollection().addCard(account, (Minion) Card.findCard("asbsavarFars").clone());
            this.getCollection().addCard(account, (Minion) Card.findCard("kamandarTorani").clone());
            this.getCollection().addCard(account, (Minion) Card.findCard("kamandarTorani").clone());
            this.getCollection().addCard(account, (Minion) Card.findCard("GorzdarTorani").clone());
            this.getCollection().addCard(account, (Minion) Card.findCard("gholabsangdarTorani").clone());
            this.getCollection().addCard(account, (Minion) Card.findCard("DivGorazSavar").clone());
            this.getCollection().addCard(account, (Minion) Card.findCard("Iraj").clone());
            this.getCollection().addCard(account, (Minion) Card.findCard("EzhdehayeAtashAndaz").clone());
            this.getCollection().addCard(account, (Minion) Card.findCard("GhoulBozorg").clone());
            this.getCollection().addCard(account, (Minion) Card.findCard("neizedarTorani").clone());
            this.getCollection().addCard(account, (Spell) Card.findCard("totalDisarm").clone());
            this.getCollection().addCard(account, (Spell) Card.findCard("totalDisarm").clone());
            this.getCollection().addCard(account, (Spell) Card.findCard("areaDispel").clone());
            this.getCollection().addCard(account, (Spell) Card.findCard("fireball").clone());
            this.getCollection().addCard(account, (Spell) Card.findCard("shock").clone());
            this.getCollection().addCard(account, (Spell) Card.findCard("godStrength").clone());
            addDefaultDeck();
        }
        catch (CloneNotSupportedException ignored)
        {

        }
    }

    public void addDefaultDeck()
    {
        Deck deck = new Deck("defaultDeck");
        for (Hero hero : getCollection().getHeroes())
        {
            deck.addCardToDeck(hero, false);
        }
        for (Minion minion : getCollection().getMinions())
        {
            deck.addCardToDeck(minion, false);
        }
        for (Spell spell : getCollection().getSpells())
        {
            deck.addCardToDeck(spell, false);
        }
        deck.addItemToDeck(this.getCollection().getItems().get(0), false);
        addDeck(deck);
        setMainDeck(deck);
    }

    public void addDeck(Deck deck)
    {
        getPlayerDecks().add(deck);
    }

    public void deleteDeck(Deck deck)
    {
        getPlayerDecks().remove(deck);
        if (getMainDeck().equals(deck))
        {
            setMainDeck(null);
        }
    }

    public static void login(Account account)
    {
        loggedInAccount = account;
    }

    public static void logout()
    {
        loggedInAccount = null;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Account)
        {
            return ((Account) obj).getAccountName().equals(this.getAccountName());
        }
        return false;
    }

    public Collection getCollection()
    {
        return collection;
    }

    public int getMoney()
    {
        return money;
    }

    public void addMoney(int money)
    {
        this.money = this.getMoney() + money;
    }

    public void decreaseMoney(int money)
    {
        this.money = this.getMoney() - money;
    }

    public ArrayList<FinishedMatch> getMatchHistory()
    {
        return matchHistory;
    }

    public ArrayList<Deck> getPlayerDecks()
    {
        return playerDecks;
    }

    public Deck getMainDeck()
    {
        return mainDeck;
    }

    public int getNumOfWins()
    {
        return numOfWins;
    }

    public void increaseNumberOfWins()
    {
        numOfWins++;
    }

    public void setMainDeck(Deck mainDeck)
    {
        this.mainDeck = mainDeck;
    }

    public String getAccountName()
    {
        return accountName;
    }

    public String getPassword()
    {
        return password;
    }

    public int getDefaultID()
    {
        return defaultID;
    }

    public void increaseDefaultID()
    {
        this.defaultID++;
    }

    public int getAIAccountDefaultID()
    {
        return AIAccountDefaultID;
    }

    public void increaseAIAccountDefaultID()
    {
        this.AIAccountDefaultID++;
    }
}

