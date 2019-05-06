package Model;

import java.util.ArrayList;

public class Account
{
    private String accountName;
    public static Account loggedInAccount;
    private ArrayList<FinishedMatch> matchHistory = new ArrayList<>();
    private Collection collection = new Collection();
    private ArrayList<Deck> playerDecks = new ArrayList<>();
    private Deck mainDeck;
    private int money = 15000;
    private String password;
    private int numOfWins = 0;
    private int defaultID = 1;
    private int AIAccountDefaultID = 10000;

    public Account(String userName,String password)
    {
        this.password = password;
        this.accountName = userName;
        this.addDefaultCardsToCollection();
    }

    public Account()
    {

    }

    private void addDefaultCardsToCollection()
    {
        this.getCollection().addCard(Hero.findHero("Rostam"));
        this.getCollection().addItem(Item.findItem("CrownOfWisdom"));
        this.getCollection().addCard(Card.findCard("kamandarFars"));
        this.getCollection().addCard(Card.findCard("kamandarFars"));
        this.getCollection().addCard(Card.findCard("neizedarFars"));
        this.getCollection().addCard(Card.findCard("neizedarFars"));
        this.getCollection().addCard(Card.findCard("asbsavarFars"));
        this.getCollection().addCard(Card.findCard("kamandarTorani"));
        this.getCollection().addCard(Card.findCard("kamandarTorani"));
        this.getCollection().addCard(Card.findCard("GorzdarTorani"));
        this.getCollection().addCard(Card.findCard("gholabsangdarTorani"));
        this.getCollection().addCard(Card.findCard("DivGorazSavar"));
        this.getCollection().addCard(Card.findCard("Iraj"));
        this.getCollection().addCard(Card.findCard("EzhdehayeAtashAndaz"));
        this.getCollection().addCard(Card.findCard("GhoulBozorg"));
        this.getCollection().addCard(Card.findCard("neizedarTorani"));
        this.getCollection().addCard(Card.findCard("totalDisarm"));
        this.getCollection().addCard(Card.findCard("totalDisarm"));
        this.getCollection().addCard(Card.findCard("areaDispel"));
        this.getCollection().addCard(Card.findCard("fireball"));
        this.getCollection().addCard(Card.findCard("shock"));
        this.getCollection().addCard(Card.findCard("godStrength"));
        addDefaultDeck();
    }

    public void addDefaultDeck()
    {
        Deck deck = new Deck("defaultDeck");
        for (Card card : this.getCollection().getCards())
        {
            if (card instanceof Hero)
            {
                deck.addHeroToDeck((Hero) card, false);
            }
            else
            {
                deck.addNonHeroCardToDeck(card, false);
            }
        }
        deck.addItemToDeck(this.getCollection().getItems().get(0), false);
        this.addDeck(deck);
        this.setMainDeck(deck);
    }

    public void addDeck(Deck deck)
    {
        this.playerDecks.add(deck);
    }

    public void deleteDeck(Deck deck)
    {
        Account.loggedInAccount.getPlayerDecks().remove(deck);
    }

    public static void login(Account account)
    {
        loggedInAccount = account;
    }

    public static void logout()
    {
        loggedInAccount = null;
    }

    public void save()
    {
        //todo
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

    public void setMainDeck(Deck mainDeck) {
        this.mainDeck = mainDeck;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getPassword() {
        return password;
    }

    public int getDefaultID()
    {
        return defaultID;
    }

    public void increaseDefaultID()
    {
        this.defaultID ++;
    }

    public int getAIAccountDefaultID()
    {
        return AIAccountDefaultID;
    }

    public void increaseAIAccountDefaultID()
    {
        this.AIAccountDefaultID ++;
    }
}

