package Network;

import Controller.AccountManager;
import Controller.ShopManager;
import Model.Account;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class InputCommandHandlerForServer extends Thread
{
    public final Object validMessageLock = new Object();
    private String message;
    private SendMessage sendMessage;
    AccountManager accountManager  =new AccountManager();
    ShopManager shopManager = new ShopManager();

    public InputCommandHandlerForServer(SendMessage sendMessage)
    {
        this.sendMessage = sendMessage;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                synchronized (validMessageLock)
                {
                    if (message == null)
                    {
                        validMessageLock.wait();
                    }
                }
                checkMassageSentByClient(getMessage());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void checkMassageSentByClient(String commandSentByClient) throws Exception {
        ClientCommand clientCommand = new Gson().fromJson(commandSentByClient, ClientCommand.class);
        switch (clientCommand.getClientCommandEnum())
        {
            case SIGN_UP:
                doingSignUpWork(clientCommand.getUserName(),clientCommand.getPassword());
                break;
            case LOGIN:
                doingLoginWork(clientCommand.getUserName(),clientCommand.getPassword());
                break;
            case LOGOUT:
                accountManager.logout();
                break;
            case MAKE_CUSTOM_SPELL:
                break;
            case MAKE_CUSTOM_MINION:
                break;
            case MAKE_CUSTOM_HERO:
                break;
            case LEADER_BOARD:
                accountManager.sortAccountsByWins();
                break;
            case SAVE_ACCOUNT_INFO:
                accountManager.saveAccountInfo(clientCommand.getAccount(),clientCommand.getUserName(),false);
                break;
            case BUY:
                shopManager.buyCard(clientCommand.getCard());
                break;
            case SELL:
                shopManager.detectIDToSell(clientCommand.getCard().getCardID());
                break;
            case IMPORT_DECK:

                break;
            case EXPORT_DECK:
                break;
            case CREATE_DECK:
                break;
            case REMOVE_CARD_FROM_DECK:

                break;
            case ADD_CARD_TO_DECK:
                break;


                case MAKE_STORY_BATTLE:
                break;
            case MAKE_CUSTOM_BATTLE:
                break;
            case MAKE_MULTI_PLAYER_BATTLE:
                break;
            case GET_ALL_OF_THE_ACCOUNTS:
                break;
            case SET_BATTLEFIELD_PANES_AND_GRIDPANE:
                break;
            case SET_NEXT_CARD_PANE:
                break;
            case GET_INSTANCE_OF_SHOW_OUTPUT:
                break;
            case SET_PLAYERS_NAME:
                break;
            case TASKS_WHEN_SURRENDER:
                break;
            case GET_PLAYER_TURN_GRAVE_YARD_CARDS:
                break;
            case CLEAR_HAND_PANES_IMAGE_VIEW_AND_END_TURN_AND_SET_HAND_ICON:
                break;
            case SET_HEROES_FIRST_PLACE:
                break;
            case SET_HERO_ICONS:
                break;
            case SET_HAND_ICONS:
                break;
            case SET_GRID_PANE:
                break;
            case GET_PLAYER_DECKS:
                break;
            case SET_MP_ICONS:
                break;
        }
        message = null;
    }
    @SuppressWarnings("Duplicates")

    private void doingLoginWork(String userName, String password)
    {
        if (userName.isEmpty() || password.isEmpty())
                {
                    ServerCommand serverCommand = new ServerCommand(ServerCommandEnum.ERROR,"you should fill the blanks ");
                    String blankson = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                    try
                    {
                        Client.getSendMessage().addMessage(blankson);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    return;
                }
                Account account = accountManager.findAccount(userName);          //FindAccount in Log in --- 1 constructor
                //loggedInAccount = accountManager.findAccount(userName);      //receive from server

                if (account == null)
                {
                    ServerCommand accountServerCommand = new ServerCommand(ServerCommandEnum.ERROR,"there was no account");
                    String accountJson = new GsonBuilder().setPrettyPrinting().create().toJson(accountServerCommand);
                    try
                    {
                        Client.getSendMessage().addMessage(accountJson);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    account = accountManager.createAccount(userName, password);
                    //loggedInAccount = accountManager.findAccount(userName);
                    try
                    {
                        accountManager.saveAccountInfo(account, userName, true);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else if (account.getPassword().equals(password))
                {
                    accountManager.login(account);                           //LogIn in accountManager --- 3 constructor
                    try
                    {
                     //   mainMenu(primaryStage);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    ServerCommand serverCommand = new ServerCommand(ServerCommandEnum.ERROR,"Password is Wrong.Try again");
                    String json = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                    try
                    {
                        Client.getSendMessage().addMessage(json);
                    }
                    catch (Exception f)
                    {
                        f.printStackTrace();
                    }
                }
    }

    /*public void saveAccountInfo(Account account,String name, boolean isNewAccount) throws IOException
    {
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
    }*/
    @SuppressWarnings("Duplicates")

    public void doingSignUpWork(String userName,String password)
    {
         if (userName.isEmpty() || password.isEmpty())
                {
                    ServerCommand serverCommand = new ServerCommand(ServerCommandEnum.ERROR,"you should fill the blanks");
                    String json = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                    try
                    {
                        Client.getSendMessage().addMessage(json);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    return;
                }
                Account account = accountManager.findAccount(userName);
                //loggedInAccount = accountManager.findAccount(userName);
                if (account == null)
                {
                    ServerCommand accountServerCommand = new ServerCommand(ServerCommandEnum.ERROR,"there was no account");
                    String accountJson = new GsonBuilder().setPrettyPrinting().create().toJson(accountServerCommand);
                    try
                    {
                        Client.getSendMessage().addMessage(accountJson);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    account = accountManager.createAccount(userName, password);
                    //loggedInAccount = accountManager.findAccount(userName);
                    try
                    {
                        accountManager.saveAccountInfo(account, userName, true);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    ServerCommand serverCommand = new ServerCommand(ServerCommandEnum.ERROR,"Account exists with this name");
                    String json = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                    try
                    {
                        Client.getSendMessage().addMessage(json);
                    }
                    catch (Exception f)
                    {
                        f.printStackTrace();
                    }
                }

    }
    public synchronized void setMessage(String message)
    {
        this.message = message;
        synchronized (validMessageLock)
        {
            validMessageLock.notify();
        }
    }

    public synchronized String getMessage()
    {
        return message;
    }

    public SendMessage getSendMessage()
    {
        return sendMessage;
    }
}