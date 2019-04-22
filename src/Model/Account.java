package Model;

import Controller.CallTheAppropriateFunction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Account
{
    private static ArrayList<Account> accounts = new ArrayList<>();
    private String accountName;
    public static Account loggedInAccount;
    private ArrayList<FinishedMatch> matchHistory = new ArrayList<>();
    private Collection collection = new Collection();
    private ArrayList<Deck> playerDecks = new ArrayList<>();
    private Deck mainDeck;
    private int money;
    private String password;
    private int numOfWins = 0;

    public Account(String userName)
    {
        
        if (findAccount(userName) == null)
        {
            CallTheAppropriateFunction.printOutput("Enter your Password");
            this.password = CallTheAppropriateFunction.getPassword();
            this.accountName = userName;
            accounts.add(this);
            CallTheAppropriateFunction.printOutput("New Account created");
        }
        else
        {
            CallTheAppropriateFunction.printOutput("this Account has been existed");
        }
    }

    public void addDeck(Deck deck)
    {
        this.playerDecks.add(deck);
    }

    public static void login(String userName)
    {
        for (Account account : accounts)
        {
            if (userName.equals(account.accountName))
            {
                CallTheAppropriateFunction.printOutput("Enter your Password");
                String inputPassword = CallTheAppropriateFunction.getPassword();
                if (inputPassword.equals(account.password))
                {
                    loggedInAccount = account;
                    return;
                }
                CallTheAppropriateFunction.printOutput("The password is not correct");
                return;
            }
        }
        CallTheAppropriateFunction.printOutput("this userName is not exist");
    }

    public static void logout()
    {
        loggedInAccount = null;
    }

    public static void sortAccountsByWins()
    {
        Collections.sort(accounts, new Comparator<Account>()
        {
            @Override
            public int compare(Account o1, Account o2)
            {
                if (o1.getNumOfWins() < o2.getNumOfWins())
                {
                    return 1;
                }
                else if (o1.getNumOfWins() > o2.getNumOfWins())
                {
                    return -1;
                }
                return 0;
            }
        });
    }

    public void save()
    {
        //todo
    }

    public static void showAllPlayers()
    {
        //todo
    }

    public static Account findAccount(String userName)
    {
        for (Account account : accounts)
        {
            if (userName.equals(account.accountName))
            {
                return account;
            }
        }
        return null;
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

    public static ArrayList<Account> getAccounts() {
        return accounts;
    }

    public String getAccountName() {
        return accountName;
    }
}

