package Network;

import Controller.AccountManager;
import Controller.ShopManager;
import Model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class InputCommandHandlerForServer extends Thread
{
    public final Object validMessageLock = new Object();
    private String message;
    private SendMessage sendMessage;
    AccountManager accountManager  =new AccountManager();
    ShopManager shopManager = new ShopManager();
    Spell spell = new Spell();
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
                workingOnSpellText(clientCommand.getTextFieldsToMakeCustom());
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
    @SuppressWarnings("Duplicates")
    public void workingOnSpellText(ArrayList<TextField> textFields)
    {

        String name = textFields.get(0).getText();
        String numOfTarget = textFields.get(1).getText();
        String kindOfMinion = textFields.get(2).getText();
        String nameOfBuff = textFields.get(3).getText();
        String buffType = textFields.get(4).getText();
        String effectValue = textFields.get(5).getText();
        String delay = textFields.get(6).getText();
        String last = textFields.get(7).getText();
        String friendOrEnemy = textFields.get(8).getText();
        String numOfFriendOrEnemy = textFields.get(9).getText();
        String isAll = textFields.get(10).getText();
        String mp = textFields.get(11).getText();
        String numOfHolyBuff = textFields.get(12).getText();
        String changingHP = textFields.get(13).getText();
        String changingAP = textFields.get(14).getText();
        String changingMp = textFields.get(15).getText();
        String cost = textFields.get(16).getText();
        String numOfOwnMinion = textFields.get(17).getText();
        String numOfOpponentMinion = textFields.get(18).getText();
        String ownHero = textFields.get(19).getText();
        String opponentHero = textFields.get(20).getText();
        String numOfOpponentBothNonSpell = textFields.get(21).getText();
        String numOfOwnBothNonSpell = textFields.get(22).getText();
        String allOwnMinion = textFields.get(23).getText();
        String allOpponentBothNonSpell = textFields.get(24).getText();
        String allOwnBothNonSpell = textFields.get(25).getText();
        makingSpellCard(numOfOwnMinion,numOfOpponentMinion,ownHero,opponentHero,numOfOpponentBothNonSpell,numOfOwnBothNonSpell,
                allOwnMinion,allOpponentBothNonSpell,allOwnBothNonSpell,name, numOfTarget, kindOfMinion, nameOfBuff, buffType, effectValue, delay, last, friendOrEnemy, numOfFriendOrEnemy, isAll, mp, numOfHolyBuff, changingAP, changingHP, changingMp, cost);
    }
    @SuppressWarnings("Duplicates")

    private static void makingSpellCard(String numOfOwnMinion, String numOfOpponentMinion, String ownHero, String opponentHero,
                                        String numOfOpponentBothNonSpell, String numOfOwnBothNonSpell, String allOwnMinion,
                                        String allOpponentBothNonSpell, String allOwnBothNonSpell, String name,
                                        String numOfTarget, String kindOfMinion, String nameOfBuff, String buffType,
                                        String effectValue, String delay, String last, String friendOrEnemy,
                                        String numOfFriendOrEnemy, String isAll, String MP, String numOfHolyBuff,
                                        String changingAp, String changingHp, String changingMp, String cost)
    {
        Spell spell = new Spell();
        spell.setCardName(name);
        int mp = Integer.parseInt(MP);
        spell.setRequiredMP(mp);
        int holyBuffNumber = Integer.parseInt(numOfHolyBuff);
        int apChanging = Integer.parseInt(changingAp);
        int HpChanging = Integer.parseInt(changingHp);
        int MPChanging = Integer.parseInt(changingMp);
        int lasting = Integer.parseInt(last);
        int price = Integer.parseInt(cost);
        spell.setPrice(price);
        int numberOfOwnMinion = Integer.parseInt(numOfOwnMinion);
        int numberOfOpponentMinion = Integer.parseInt(numOfOpponentMinion);
        int numberOfOpponentBothNonSpell = Integer.parseInt(numOfOpponentBothNonSpell);
        int numberOfOwnBothNonSpell = Integer.parseInt(numOfOwnBothNonSpell);
        spell.getSpellEffect().addSpellChange(new SpellChange());
        spell.getSpellEffect().addTarget(new Target());
        spell.getSpellEffect().getSpellChanges().get(0).setTurnsToApplyChange(lasting);
        spell.getSpellEffect().getSpellChanges().get(0).setChangeMP(MPChanging);
        spell.getSpellEffect().getTargets().get(0).setNumOfOwnMinions(numberOfOwnMinion);
        spell.getSpellEffect().getTargets().get(0).setNumOfOpponentMinions(numberOfOpponentMinion);
        spell.getSpellEffect().getTargets().get(0).setNumOfOpponentBothNonSpellCards(numberOfOpponentBothNonSpell);
        spell.getSpellEffect().getTargets().get(0).setNumOfOwnBothNonSpellCards(numberOfOwnBothNonSpell);
        if (ownHero.equalsIgnoreCase("true"))
        {
            spell.getSpellEffect().getTargets().get(0).setOwnHero(true);
        }
        if (opponentHero.equalsIgnoreCase("true"))
        {
            spell.getSpellEffect().getTargets().get(0).setOpponentHero(true);
        }
        if (allOpponentBothNonSpell.equalsIgnoreCase("true"))
        {

            spell.getSpellEffect().getTargets().get(0).setAllOpponentNonSpellCards(true);
        }
        if (allOwnBothNonSpell.equalsIgnoreCase("true"))
        {
            spell.getSpellEffect().getTargets().get(0).setAllOwnBothNonSpellCards(true);
        }
        if (allOwnMinion.equalsIgnoreCase("true"))
        {
            spell.getSpellEffect().getTargets().get(0).setAllOwnMinions(true);
        }
        if (buffType.equalsIgnoreCase("holy"))
        {
            spell.getSpellEffect().getSpellChanges().get(0).setActivateHolyBuff(true);
            spell.getSpellEffect().getSpellChanges().get(0).setNumOfHolyBuffs(holyBuffNumber);
        }
        if (buffType.equalsIgnoreCase("stun"))
        {
            spell.getSpellEffect().getSpellChanges().get(0).setStunOpponent(true);
        }
        if (buffType.equalsIgnoreCase("disarm"))
        {
            spell.getSpellEffect().getSpellChanges().get(0).setDisarmOpponent(true);
        }
        if (buffType.equalsIgnoreCase("power"))
        {
            spell.getSpellEffect().getSpellChanges().get(0).setChangeAP(apChanging);
            spell.getSpellEffect().getSpellChanges().get(0).setChangeHP(HpChanging);
        }
        if (buffType.equalsIgnoreCase("weakness"))
        {
            spell.getSpellEffect().getSpellChanges().get(0).setChangeHP(HpChanging);
            spell.getSpellEffect().getSpellChanges().get(0).setChangeAP(apChanging);
        }
        if (friendOrEnemy.equalsIgnoreCase("friend"))
        {
            spell.getSpellEffect().getTargets().get(0).setNumOfOwnMinions(Integer.parseInt(numOfFriendOrEnemy));
            if (isAll.equalsIgnoreCase("true"))
            {
                spell.getSpellEffect().getTargets().get(0).setAllOwnBothNonSpellCards(true);
            }
        }
        else if (friendOrEnemy.equalsIgnoreCase("enemy"))
        {
            spell.getSpellEffect().getTargets().get(0).setNumOfOpponentBothNonSpellCards(Integer.parseInt(numOfFriendOrEnemy));
            if (isAll.equalsIgnoreCase("true"))
            {
                spell.getSpellEffect().getTargets().get(0).setAllOpponentNonSpellCards(true);
            }
        }
        Account.loggedInAccount.getCollection().addCard(Account.loggedInAccount, spell, false);
        Shop.getInstance().addCardToShop(spell);
        Spell.getSpells().add(spell);
        //showOutput.printOutput("Custom card " + spell.getCardID() + " added to your collection");//todo
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