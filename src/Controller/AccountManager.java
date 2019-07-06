package Controller;

import Model.*;
import Network.Server;
import Network.ServerCommand;
import View.*;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class AccountManager
{
    private ShowOutput showOutput = ShowOutput.getInstance();

    public Account createAccount(String userName, String password)
    {
        Account account = new Account(userName, password);
        account.addDefaultCardsToCollection(account);
        Server.addAccount(account);
        return account;
    }

    public void login(ServerCommand serverCommand, Account account)
    {
        serverCommand.setLogin(true);
        serverCommand.setAuthToken("Account_" + Server.getNumberOfAccount());
        account.setAuthToken("Account_" + Server.getNumberOfAccount());
        Server.setNumberOfAccount(Server.getNumberOfAccount() + 1);
        System.out.println("\n" + account.getAuthToken());
    }

    public void logout(Account account)
    {
        account.setAuthToken(null);
    }

    public void saveAccountInfo(Account account, boolean isNewAccount) throws IOException
    {
        String name = account.getAccountName();
        FileWriter SavedAccountPath = new FileWriter("SavedAccounts/SavedAccountPath.txt" ,true);
        if (isNewAccount)
        {
            SavedAccountPath.write(name + "\n");
            SavedAccountPath.close();
        }
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(account);
        System.out.println(json);
        try
        {

            FileWriter saveAccountInfo = new FileWriter("SavedAccounts/" + name + ".json", false);
            saveAccountInfo.write(json);
            saveAccountInfo.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void sortAccountsByWins()
    {
        Server.getAccounts().sort((o1, o2) -> {
            if (o1.getNumOfWins() < o2.getNumOfWins())
            {
                return 1;
            }
            else if (o1.getNumOfWins() > o2.getNumOfWins())
            {
                return -1;
            }
            return 0;
        });
    }

    public Account findAccount(String userName)
    {
        for (Account account : Server.getAccounts())
        {
            if (userName.equals(account.getAccountName()))
            {
                return account;
            }
        }
        return null;
    }

    public void showAllPlayers()
    {
        int counter = 1;
        for (Account account : Server.getAccounts())
        {
            showOutput.printOutput(counter + "- " + account.getAccountName());
            counter ++;
        }
    }

    public Player makeStoryPlayer(int selectedNumber)
    {
        switch (selectedNumber)
        {
            case 1:
                Account account1 = new Account("StoryPlayerOne");
                Deck deck1 = Deck.createMainDeckForStoryAccount(account1, 1);
                account1.addDeck(deck1);
                account1.setMainDeck(deck1);
                return new Player(account1, true);
            case 2:
                Account account2 = new Account("StoryPlayerTwo");
                Deck deck2 = Deck.createMainDeckForStoryAccount(account2, 2);
                account2.addDeck(deck2);
                account2.setMainDeck(deck2);
                return new Player(account2, true);
            case 3:
                Account account3 = new Account("StoryPlayerThree");
                Deck deck3 = Deck.createMainDeckForStoryAccount(account3, 3);
                account3.addDeck(deck3);
                account3.setMainDeck(deck3);
                return new Player(account3, true);
        }
        return null;
    }

    public Player makeCustomGamePlayer(String deckNameForCustomGame, Account account)
    {
        Deck deck = DeckManager.findDeck(deckNameForCustomGame, account);
        if (new DeckManager().checkDeckValidity(deck))
        {
            Account customPlayer = new Account("CustomPlayer");
            customPlayer.addDeck(deck);
            customPlayer.setMainDeck(deck);
            return new Player(customPlayer, true);
        }
        else
        {
            return null;
        }
    }
}
