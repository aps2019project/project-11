package Controller;

import Model.*;
import View.*;
import com.google.gson.GsonBuilder;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AccountManager
{
    private static ArrayList<Account> accounts = new ArrayList<>();
    private ShowOutput showOutput = ShowOutput.getInstance();

    public void createAccount(String userName, String password)
    {
        Account account = new Account(userName, password);
        account.addDefaultCardsToCollection(account);
        AccountManager.getAccounts().add(account);
    }

    public void login(Account account)
    {
        Account.login(account);
    }

    public void logout()
    {
        Account.logout();
    }



    public void sortAccountsByWins()
    {
        accounts.sort((o1, o2) -> {
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
        for (Account account : accounts)
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
        for (Account account : getAccounts())
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
                Account account1 = new Account();
                Deck deck1 = Deck.createMainDeckForStoryAccount(1);
                account1.addDeck(deck1);
                account1.setMainDeck(deck1);
                return new Player(account1, true);
            case 2:
                Account account2 = new Account();
                Deck deck2 = Deck.createMainDeckForStoryAccount(2);
                account2.addDeck(deck2);
                account2.setMainDeck(deck2);
                return new Player(account2, true);
            case 3:
                Account account3 = new Account();
                Deck deck3 = Deck.createMainDeckForStoryAccount(3);
                account3.addDeck(deck3);
                account3.setMainDeck(deck3);
                return new Player(account3, true);
        }
        return null;
    }

    public Player makeCustomGamePlayer(String deckNameForCustomGame)
    {
        Account account = new Account();
        account.addDeck(DeckManager.findDeck(deckNameForCustomGame));
        account.setMainDeck(DeckManager.findDeck(deckNameForCustomGame));
        return new Player(account, true);
    }

    public static ArrayList<Account> getAccounts()
    {
        return accounts;
    }
}
