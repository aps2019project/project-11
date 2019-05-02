package Controller;

import Model.*;
import View.*;

import java.util.ArrayList;

public class AccountManager
{
    private ArrayList<Account> accounts = new ArrayList<>();

    public void checkCircumstancesToCreateAccount(String userName)
    {
        if (findAccount(userName) == null)
        {
            ShowOutput.printOutput("Enter your Password");
            Account account = new Account(userName, Request.getPassword());
            accounts.add(account);
            ShowOutput.printOutput("New Account created");
        }
        else
        {
            ShowOutput.printOutput("this Account has been existed");
        }
    }

    public void checkCircumstancesToLogin(String userName)
    {
        for (Account account : getAccounts())
        {
            if (userName.equals(account.getAccountName()))
            {
                ShowOutput.printOutput("Enter your Password");
                String inputPassword = Request.getPassword();
                if (inputPassword.equals(account.getPassword()))
                {
                    Account.login(account);
                    ShowOutput.printMainMenuCommands();
                    new CallTheAppropriateFunction().determineMainMenuCommand();
                }
                ShowOutput.printOutput("The password is not correct");
                return;
            }
        }
        ShowOutput.printOutput("This userName doesn't exist");
    }

    public void logout()
    {
        Account.logout();
        Request.getAccountCommands();
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
            ShowOutput.printOutput(counter + account.getAccountName());
            counter ++;
        }
    }

    public ArrayList<Account> getAccounts()
    {
        return accounts;
    }
}
