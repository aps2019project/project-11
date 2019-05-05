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
    }

    public Account()
    {

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

    public static void setAIAccounts()
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

