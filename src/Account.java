import java.util.ArrayList;

public class Account
{
    static ArrayList<Account> accounts = new ArrayList<>();
    static Account loggedInAccount;
    private ArrayList<FinishedMatch> matchHistory = new ArrayList<>();
    private Collection collection;
    private ArrayList<Deck> playerDecks = new ArrayList<>();
    private Deck mainDeck;
    private int money;
    private String password;
    private int numOfWins = 0;

    public Account(String userName)
    {

    }

    public void login(String userName)
    {

    }

    public void logout()
    {

    }

    public static void help()
    {

    }

    public static void showLeaderBoard()
    {

    }

    public void sortAccountsByWins()
    {

    }

    public void save()
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
}
