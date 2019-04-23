package Controller;

import Model.*;
import View.*;

import java.util.ArrayList;

public class AccountManager
{
    private static ArrayList<Account> accounts = new ArrayList<>();

    public static void checkCircumstancesToCreateAccount(String userName)
    {
        if (findAccount(userName) == null)
        {
            CallTheAppropriateFunction.printOutput("Enter your Password");
            Account account = new Account(userName, CallTheAppropriateFunction.getPassword());
            accounts.add(account);
            CallTheAppropriateFunction.printOutput("New Account created");
        }
        else
        {
            CallTheAppropriateFunction.printOutput("this Account has been existed");
        }
    }

    public static void checkCircumstancesToLogin(String userName)
    {
        for (Account account : getAccounts())
        {
            if (userName.equals(account.getAccountName()))
            {
                CallTheAppropriateFunction.printOutput("Enter your Password");
                String inputPassword = CallTheAppropriateFunction.getPassword();
                if (inputPassword.equals(account.getPassword()))
                {
                    Account.login(account);
                    ShowOutput.printMainMenuCommands();
                    CallTheAppropriateFunction.determineMainMenuCommand();
                }
                CallTheAppropriateFunction.printOutput("The password is not correct");
                return;
            }
        }
        CallTheAppropriateFunction.printOutput("This userName doesn't exist");
    }

    public static void logout()
    {
        Account.logout();
        Request.getAccountCommands();
    }

    public static void sortAccountsByWins()
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

    public static Account findAccount(String userName)
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

    public static ArrayList<Account> getAccounts()
    {
        return accounts;
    }
}
