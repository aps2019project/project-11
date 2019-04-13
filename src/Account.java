import java.util.ArrayList;
import java.util.Collections;

public class Account
{
    static ArrayList<Account> accounts = new ArrayList<>();
    private String AccountName;
    static Account loggedInAccount;
    private ArrayList<FinishedMatch> matchHistory = new ArrayList<>();
    private Collection collection = new Collection();
    private ArrayList<Deck> playerDecks = new ArrayList<>();
    private Deck mainDeck;
    private int money;
    private int  password;
    private int numOfWins = 0;
    private ArrayList<Card> hand = new ArrayList<>();
    private ArrayList<Item> collectibleItems = new ArrayList<>();
    private ArrayList<Card> graveYard = new ArrayList<>();
    private int mana;
    public Account(String userName)
    {
         int inputPassword= Main.myScanner.nextInt();
         if (findAccount(userName)==null)
         {
             Account account = new Account(userName);
             account.password=inputPassword;
         }
         else
         {
             System.out.println("this Account has been existed");
         }
    }

    public void addDeck(Deck deck)
    {
        this.playerDecks.add(deck);
    }

    public void login(String userName)
    {
        boolean flagForCheckingPassword =false;
        boolean flagForCheckingUserName=false;
        int inputPassword= Main.myScanner.nextInt();
        for (Account account:accounts)
        {
            if (inputPassword==account.password)
            {
                loggedInAccount=account;
                flagForCheckingPassword =true;
            }
        }
        if (flagForCheckingPassword==false)
        {
            System.out.println("this password is not correct");
            flagForCheckingPassword=true;
        }
        for (Account account: accounts)
        {
            if (userName.compareTo(account.AccountName)==0)
            {
                flagForCheckingUserName=true;
            }
        }
        if (flagForCheckingUserName==false)
        {
            System.out.println("this userName is not exist");
            flagForCheckingUserName=true;
        }

    }

    public void logout()
    {
        loggedInAccount=null;
    }

    public static void help()
    {
        System.out.println("create account [user name]");
        System.out.println("login [user name]");
        System.out.println("show leaderboard");
        System.out.println("save");
        System.out.println("logout");
        System.out.println("help");
    }

    public void sortAccountsByWins()
    {
        int counter;
        for (counter=0;counter<accounts.size();counter++)
        {
            if (accounts.get(counter).numOfWins<accounts.get(counter+1).numOfWins)
            {
                Collections.swap(accounts,counter+1,counter);
            }
        }
    }

    public static void showLeaderBoard()
    {
        int secondCounter;
        for (secondCounter=0;secondCounter<accounts.size();secondCounter++)
        {
            System.out.print(secondCounter);
            System.out.print("-");
            System.out.print("UserName");
            System.out.print(" ");
            System.out.print(accounts.get(secondCounter).AccountName);
            System.out.print("-");
            System.out.print("Wins");
            System.out.print(" ");
            System.out.println(accounts.get(secondCounter).numOfWins);
        }
    }

    public void save()
    {
        //todo
    }

    public static void showAllPlayers()
    {

    }

    public static Account findAccount(String userName)
    {
        for (Account account:accounts)
        {
            if (userName.compareTo(account.AccountName)==0)
            {
                return account;
            }
        }
        return null;

    }

    public static void setAIAccounts()
    {

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

    public ArrayList<Item> getCollectibleItems()
    {
        return collectibleItems;
    }

    public void setCollectibleItems(ArrayList<Item> collectibleItems) {
        this.collectibleItems = collectibleItems;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public ArrayList<Card> getGraveYard() {
        return graveYard;
    }

    public void setGraveYard(ArrayList<Card> graveYard) {
        this.graveYard = graveYard;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setMainDeck(Deck mainDeck) {
        this.mainDeck = mainDeck;
    }
}
