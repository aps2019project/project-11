package Controller;

import Model.*;
import View.*;

import java.util.ArrayList;

public class AccountManager
{
    private static ArrayList<Account> accounts = new ArrayList<>();
    private Request request = new Request();
    private ShowOutput showOutput = new ShowOutput();

    public void checkCircumstancesToCreateAccount(String userName)
    {
        if (findAccount(userName) == null)
        {
            showOutput.printOutput("Enter your Password");
            Account account = new Account(userName, request.getPassword());
            account.addDefaultCardsToCollection(account);
            accounts.add(account);
            showOutput.printOutput("New Account created");
        }
        else
        {
            showOutput.printOutput("this Account has been existed");
        }
    }

    public void checkCircumstancesToLogin(String userName)
    {
        for (Account account : getAccounts())
        {
            if (userName.equals(account.getAccountName()))
            {
                showOutput.printOutput("Enter your Password");
                String inputPassword = request.getPassword();
                if (inputPassword.equals(account.getPassword()))
                {
                    Account.login(account);
                    new CallTheAppropriateFunction().determineMainMenuCommand();
                }
                showOutput.printOutput("The password is not correct");
                return;
            }
        }
        showOutput.printOutput("This userName doesn't exist");
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

    public static ArrayList<Account> getAccounts()
    {
        return accounts;
    }
}
