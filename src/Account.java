import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Account
{
    private static ArrayList<Account> accounts = new ArrayList<>();
    private String accountName;
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
    private int MP;

    public Account(String userName)
    {
         int inputPassword= Main.myScanner.nextInt();
         if (findAccount(userName) == null)
         {
             this.password = inputPassword;
             this.accountName = userName;
             accounts.add(this);
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

    public static void login(String userName)
    {
        int inputPassword = Main.myScanner.nextInt();
        for (Account account : accounts)
        {
            if (userName.equals(account.accountName))
            {
                if (inputPassword == account.password)
                {
                    loggedInAccount = account;
                    return;
                }
                System.out.println("The password is not correct");
                return;
            }
        }
        System.out.println("this userName is not exist");
    }

    public static void logout()
    {
        loggedInAccount = null;
    }

    public static void help()
    {
        System.out.println("create account [user name]");
        System.out.println("login [user name]");
        System.out.println("show leaderBoard");
        System.out.println("save");
        System.out.println("logout");
        System.out.println("help");
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

    public static void showLeaderBoard()
    {
        int counter = 1;
        for (Account account : accounts)
        {
            System.out.println(counter + "- UserName : " + account.accountName + "- Wins : " + account.numOfWins);
            counter ++;
        }
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

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public void setMainDeck(Deck mainDeck) {
        this.mainDeck = mainDeck;
    }
}
