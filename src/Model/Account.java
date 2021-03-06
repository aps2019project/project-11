package Model;

import java.util.ArrayList;

public class Account
{
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
    private String authToken;

    public Account(String userName, String password)
    {
        this.password = password;
        this.accountName = userName;
    }

    public Account(String AIAccountName)
    {
        accountName = AIAccountName;
    }

    public void addDefaultCardsToCollection(Account account)
    {
        try
        {
            this.getCollection().addCard(account, (Hero) Hero.findHero("Rostam").clone(), false);
            this.getCollection().addItem(account, (Item) Item.findItem("CrownOfWisdom").clone(), false);
            this.getCollection().addCard(account, (Minion) Card.findCard("kamandarFars").clone(), false);
            this.getCollection().addCard(account, (Minion) Card.findCard("kamandarFars").clone(), false);
            this.getCollection().addCard(account, (Minion) Card.findCard("neizedarFars").clone(), false);
            this.getCollection().addCard(account, (Minion) Card.findCard("neizedarFars").clone(), false);
            this.getCollection().addCard(account, (Minion) Card.findCard("asbsavarFars").clone(), false);
            this.getCollection().addCard(account, (Minion) Card.findCard("kamandarTorani").clone(), false);
            this.getCollection().addCard(account, (Minion) Card.findCard("kamandarTorani").clone(), false);
            this.getCollection().addCard(account, (Minion) Card.findCard("GorzdarTorani").clone(), false);
            this.getCollection().addCard(account, (Minion) Card.findCard("gholabsangdarTorani").clone(), false);
            this.getCollection().addCard(account, (Minion) Card.findCard("DivGorazSavar").clone(), false);
            this.getCollection().addCard(account, (Minion) Card.findCard("Iraj").clone(), false);
            this.getCollection().addCard(account, (Minion) Card.findCard("EzhdehayeAtashAndaz").clone(), false);
            this.getCollection().addCard(account, (Minion) Card.findCard("GhoulBozorg").clone(), false);
            this.getCollection().addCard(account, (Minion) Card.findCard("neizedarTorani").clone(), false);
            this.getCollection().addCard(account, (Spell) Card.findCard("totalDisarm").clone(), false);
            this.getCollection().addCard(account, (Spell) Card.findCard("totalDisarm").clone(), false);
            this.getCollection().addCard(account, (Spell) Card.findCard("areaDispel").clone(), false);
            this.getCollection().addCard(account, (Spell) Card.findCard("fireball").clone(), false);
            this.getCollection().addCard(account, (Spell) Card.findCard("shock").clone(), false);
            this.getCollection().addCard(account, (Spell) Card.findCard("godStrength").clone(), false);
            addDefaultDeck(account);
        }
        catch (CloneNotSupportedException ignored)
        {

        }
    }

    public void addDefaultDeck(Account account)
    {
        Deck deck = new Deck("defaultDeck");
        for (Hero hero : getCollection().getHeroes())
        {
            deck.addCardToDeck(hero, account, false);
        }
        for (Minion minion : getCollection().getMinions())
        {
            deck.addCardToDeck(minion, account, false);
        }
        for (Spell spell : getCollection().getSpells())
        {
            deck.addCardToDeck(spell, account, false);
        }
        deck.addItemToDeck(this.getCollection().getItems().get(0), account, false);
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
        numOfWins ++;
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

    public void addImportedDeckCardsAndItemsToCollection(Deck deck)
    {
        for (Hero hero : deck.getHero())
        {
            getCollection().addCard(this, hero, true);
        }
        for (Minion minion : deck.getMinions())
        {
            getCollection().addCard(this, minion, true);
        }
        for (Spell spell : deck.getSpells())
        {
            getCollection().addCard(this, spell, true);
        }
        for (Item item : deck.getItem())
        {
            getCollection().addItem(this, item, true);
        }
    }

    public String getAuthToken()
    {
        return authToken;
    }

    public void setAuthToken(String authToken)
    {
        this.authToken = authToken;
    }
}

