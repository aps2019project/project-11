package Network;

import Controller.AccountManager;
import Controller.ShopManager;
import Model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import javafx.scene.control.TextField;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InputCommandHandlerForServer extends Thread
{
    public final Object validMessageLock = new Object();
    private String message;
    private SendMessage sendMessage;
    private AccountManager accountManager = new AccountManager();
    private ShopManager shopManager = new ShopManager();

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
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void checkMassageSentByClient(String commandSentByClient) throws Exception
    {
        ClientCommand clientCommand = new Gson().fromJson(commandSentByClient, ClientCommand.class);
        Account account = findAccount(clientCommand.getAuthToken());
        switch (clientCommand.getClientCommandEnum())
        {
            case SIGN_UP:
                checkCircumstancesToSignUp(clientCommand.getUserName(), clientCommand.getPassword());
                break;
            case LOGIN:
                checkCircumstancesToLogin(clientCommand.getUserName(), clientCommand.getPassword());
                break;
            case LOGOUT:
                accountManager.logout(account);
                String json = new GsonBuilder().setPrettyPrinting().create().toJson(new ServerCommand(ServerCommandEnum.OK));
                getSendMessage().addMessage(json);
                break;
            case MAKE_CUSTOM_SPELL:
                workingOnSpellText(clientCommand.getTextFieldsToMakeCustom());
                break;
            case MAKE_CUSTOM_MINION:
                workingOnMinionText(clientCommand.getTextFieldsToMakeCustom());
                break;
            case MAKE_CUSTOM_HERO:
                workingOnHeroText(clientCommand.getTextFieldsToMakeCustom());
                break;
            case LEADER_BOARD:
                accountManager.sortAccountsByWins();
                break;
            case SAVE_ACCOUNT_INFO:
                accountManager.saveAccountInfo(clientCommand.getAccount(), clientCommand.getUserName(), false);
                break;
            case BUY:
                Card card = Card.findCard(clientCommand.getCard().getCardName());
                Item item = Item.findItem(clientCommand.getItem().getItemName());
                if (card != null)
                {
                    shopManager.buyCard(card);
                }
                else if (item != null)
                {
                    shopManager.buyItem(item);
                }
                break;
            case SELL:

                shopManager.detectIDToSell(clientCommand.getCard().getCardID());
                break;
            case IMPORT_DECK:
                importingToCollection(clientCommand.getDeckName());
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
            case GET_ONLINE_ACCOUNTS:
                break;

        }
        message = null;
    }

    private void checkCircumstancesToSignUp(String userName, String password) throws Exception
    {
        ServerCommand serverCommand;
        if (userName.isEmpty() || password.isEmpty())
        {
            serverCommand = new ServerCommand(ServerCommandEnum.ERROR, "you must Fill both TextFields");
        }
        else
        {
            Account account = accountManager.findAccount(userName);
            if (account == null)
            {
                account = accountManager.createAccount(userName, password);
                accountManager.saveAccountInfo(account, userName, true);
                serverCommand = new ServerCommand(ServerCommandEnum.OK);
            }
            else
            {
                serverCommand = new ServerCommand(ServerCommandEnum.ERROR, "Player exists with this name");
            }
        }
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
        getSendMessage().addMessage(json);
    }

    private void checkCircumstancesToLogin(String userName, String password) throws Exception
    {
        ServerCommand serverCommand;
        if (userName.isEmpty() || password.isEmpty())
        {
            serverCommand = new ServerCommand(ServerCommandEnum.ERROR, "you must Fill both TextFields");
        }
        else
        {
            Account account = accountManager.findAccount(userName);
            if (account == null)
            {
                serverCommand = new ServerCommand(ServerCommandEnum.ERROR, "Invalid name or password");
            }
            else if (account.getPassword().equals(password))
            {
                if (account.getAuthToken() != null)
                {
                    serverCommand = new ServerCommand(ServerCommandEnum.ERROR, "Already LoggedIn to this account");
                }
                else
                {
                    serverCommand = new ServerCommand(ServerCommandEnum.OK);
                    accountManager.login(serverCommand, account);
                }
            }
            else
            {
                serverCommand = new ServerCommand(ServerCommandEnum.ERROR, "Password is Wrong.Try again");
            }
        }
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
        getSendMessage().addMessage(json);
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

      private void importingToCollection(String deckName) throws IOException, ParseException
    {
        JsonParser jsonParser = new JsonParser();
        FileReader reader = new FileReader("SavedDecks/" + deckName + ".json");
        Object obj = jsonParser.parse(reader);
        System.out.println(obj);
        Deck deck = new Gson().fromJson(obj.toString(), Deck.class);
        deck.setDeckName("Imported " + deck.getDeckName());
        Account.loggedInAccount.getPlayerDecks().add(deck);
        addImportedDeckCardsAndItemsToCollection(deck);
        ServerCommand serverCommand = new ServerCommand(ServerCommandEnum.OK);
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
        try {
            getSendMessage().addMessage(json);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void addImportedDeckCardsAndItemsToCollection(Deck deck)
    {
        //send deck to server    //
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
        makingSpellCard(numOfOwnMinion, numOfOpponentMinion, ownHero, opponentHero, numOfOpponentBothNonSpell, numOfOwnBothNonSpell, allOwnMinion, allOpponentBothNonSpell, allOwnBothNonSpell, name, numOfTarget, kindOfMinion, nameOfBuff, buffType, effectValue, delay, last, friendOrEnemy, numOfFriendOrEnemy, isAll, mp, numOfHolyBuff, changingAP, changingHP, changingMp, cost);
    }

    @SuppressWarnings("Duplicates")

    private static void makingSpellCard(String numOfOwnMinion, String numOfOpponentMinion, String ownHero, String opponentHero, String numOfOpponentBothNonSpell, String numOfOwnBothNonSpell, String allOwnMinion, String allOpponentBothNonSpell, String allOwnBothNonSpell, String name, String numOfTarget, String kindOfMinion, String nameOfBuff, String buffType, String effectValue, String delay, String last, String friendOrEnemy, String numOfFriendOrEnemy, String isAll, String MP, String numOfHolyBuff, String changingAp, String changingHp, String changingMp, String cost)
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
        Server.addSpell(spell);
        //showOutput.printOutput("Custom card " + spell.getCardID() + " added to your collection");//todo
    }

    @SuppressWarnings("Duplicates")

    public static void workingOnMinionText(ArrayList<TextField> textFields)
    {
        String name = textFields.get(0).getText();
        String Ap = textFields.get(1).getText();
        String Hp = textFields.get(2).getText();
        String AttackType = textFields.get(3).getText();
        String Range = textFields.get(4).getText();
        String specialPowerActivation = textFields.get(5).getText();
        String cost = textFields.get(6).getText();
        String turnsToApply = textFields.get(7).getText();
        String isPositive = textFields.get(8).getText();
        String untilEnd = textFields.get(9).getText();
        String changeAp = textFields.get(10).getText();
        String changeHp = textFields.get(11).getText();
        String changeMp = textFields.get(12).getText();
        String stun = textFields.get(13).getText();
        String disarm = textFields.get(14).getText();
        String numOfHolyBuff = textFields.get(15).getText();
        String toxic = textFields.get(16).getText();
        String holyCell = textFields.get(17).getText();
        String fiery = textFields.get(18).getText();
        String combo = textFields.get(19).getText();
        String numOfOwnMinion = textFields.get(20).getText();
        String numOfOpponentMinion = textFields.get(21).getText();
        String ownHero = textFields.get(22).getText();
        String opponentHero = textFields.get(23).getText();
        String numOfOpponentBothNonSpell = textFields.get(24).getText();
        String numOfOwnBothNonSpell = textFields.get(25).getText();
        String allOwnMinion = textFields.get(26).getText();
        String allOpponentBothNonSpell = textFields.get(27).getText();
        String allOwnBothNonSpell = textFields.get(28).getText();
        makingMinionCard(numOfOwnMinion, numOfOpponentMinion, ownHero, opponentHero, numOfOpponentBothNonSpell, numOfOwnBothNonSpell, allOwnMinion, allOwnBothNonSpell, allOpponentBothNonSpell, name, Ap, Hp, AttackType, Range, specialPowerActivation, cost, turnsToApply, isPositive, untilEnd, changeAp, changeHp, changeMp, stun, disarm, numOfHolyBuff, toxic, holyCell, fiery, combo);
    }

    @SuppressWarnings("Duplicates")

    private static void makingMinionCard(String numOfOwnMinion, String numOfOpponentMinion, String ownHero, String opponentHero, String numOfOpponentBothNonSpell, String numOfOwnBothNonSpell, String allOwnMinion, String allOwnBothNonSpell, String allOpponentBothNonSpell, String name, String ap, String hp, String attackType, String range, String specialPowerActivation, String cost, String turn, String isPositive, String UntilEnd, String changeAP, String changeHP, String ChangeMP, String stun, String disarm, String numOfHolyBuff, String toxic, String holycell, String fiery, String combo)
    {
        int AP = Integer.parseInt(ap);
        int HP = Integer.parseInt(hp);
        int price = Integer.parseInt(cost);
        int rangeOfAttack = Integer.parseInt(range);
        int apChange = Integer.parseInt(changeAP);
        int hpChange = Integer.parseInt(changeHP);
        int MpChange = Integer.parseInt(ChangeMP);
        int turnToApply = Integer.parseInt(turn);
        int numberOfOwnMinion = Integer.parseInt(numOfOwnMinion);
        int numberOfOpponentMinion = Integer.parseInt(numOfOpponentMinion);
        int numberOfOpponentBothNonSpell = Integer.parseInt(numOfOpponentBothNonSpell);
        int numberOfOwnBothNonSpell = Integer.parseInt(numOfOwnBothNonSpell);
        int numberOfHolyBuff = Integer.parseInt(numOfHolyBuff);
        Minion minion = new Minion();
        minion.setCardName(name);
        minion.setDefaultAP(AP);
        minion.setDefaultHP(HP);
        minion.setRangeOfAttack(rangeOfAttack);
        SpecialPower specialPower = new SpecialPower("Custom card");
        specialPower.getSpellEffect().addSpellChange(new SpellChange());
        specialPower.getSpellEffect().addTarget(new Target());
        minion.setSpecialPower(specialPower);
        minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setNumOfHolyBuffs(numberOfHolyBuff);
        minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setChangeAP(apChange);
        minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setChangeHP(hpChange);
        minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setChangeMP(MpChange);
        minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setTurnsToApplyChange(turnToApply);
        minion.getSpecialPower().getSpellEffect().getTargets().get(0).setNumOfOpponentBothNonSpellCards(numberOfOpponentBothNonSpell);
        minion.getSpecialPower().getSpellEffect().getTargets().get(0).setNumOfOwnMinions(numberOfOwnMinion);
        minion.getSpecialPower().getSpellEffect().getTargets().get(0).setNumOfOpponentMinions(numberOfOpponentMinion);
        minion.getSpecialPower().getSpellEffect().getTargets().get(0).setNumOfOwnBothNonSpellCards(numberOfOwnBothNonSpell);
        if (ownHero.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getTargets().get(0).setOwnHero(true);
        }
        if (opponentHero.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getTargets().get(0).setOpponentHero(true);
        }
        if (allOpponentBothNonSpell.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getTargets().get(0).setAllOpponentNonSpellCards(true);
        }
        if (allOwnBothNonSpell.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getTargets().get(0).setAllOwnBothNonSpellCards(true);
        }
        if (allOwnMinion.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getTargets().get(0).setAllOwnMinions(true);
        }
        if (attackType.equalsIgnoreCase("melee"))
        {
            minion.setImpactType(ImpactType.melee);
        }
        if (attackType.equalsIgnoreCase("ranged"))
        {
            minion.setImpactType(ImpactType.ranged);
        }
        if (attackType.equalsIgnoreCase("hybrid"))
        {
            minion.setImpactType(ImpactType.hybrid);
        }
        if (isPositive.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setPositiveChange(true);
        }
        if (stun.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setStunOpponent(true);
        }
        if (fiery.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setMadeCellFiery(true);
        }
        if (combo.equalsIgnoreCase("true"))
        {
            minion.setAbleToCombo(true);
        }
        if (UntilEnd.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setApplyChangeUntilEndOfTheGame(true);
        }
        if (disarm.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setDisarmOpponent(true);
        }
        if (toxic.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setMadeCellToxic(true);
        }
        if (holycell.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setMadeCellHoly(true);
        }
        if (specialPowerActivation.equalsIgnoreCase("combo"))
        {
            minion.setAbleToCombo(true);
        }
        else if (specialPowerActivation.equalsIgnoreCase("onTurn"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setTimeToActivateSpecialPower(TimeToActivateSpecialPower.onTurn);
        }
        else if (specialPowerActivation.equalsIgnoreCase("passive"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setTimeToActivateSpecialPower(TimeToActivateSpecialPower.passive);
        }
        else if (specialPowerActivation.equalsIgnoreCase("onAttack"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setTimeToActivateSpecialPower(TimeToActivateSpecialPower.onAttack);
        }
        else if (specialPowerActivation.equalsIgnoreCase("onSpawn"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setTimeToActivateSpecialPower(TimeToActivateSpecialPower.onSpawn);
        }
        else if (specialPowerActivation.equalsIgnoreCase("onDeath"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setTimeToActivateSpecialPower(TimeToActivateSpecialPower.onDeath);
        }
        else if (specialPowerActivation.equalsIgnoreCase("onDefend"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setTimeToActivateSpecialPower(TimeToActivateSpecialPower.onDefend);
        }
        minion.setPrice(price);
        Account.loggedInAccount.getCollection().addCard(Account.loggedInAccount, minion, false);
        Shop.getInstance().addCardToShop(minion);
        Server.addMinion(minion);
        //showOutput.printOutput("Custom card " + minion.getCardID() + " added to your collection");//todo
    }

    @SuppressWarnings("Duplicates")

    public static void workingOnHeroText(ArrayList<TextField> textFields)
    {
        String name = textFields.get(0).getText();
        String Ap = textFields.get(1).getText();
        String Hp = textFields.get(2).getText();
        String AttackType = textFields.get(3).getText();
        String Range = textFields.get(4).getText();
        String coolDown = textFields.get(5).getText();
        String cost = textFields.get(6).getText();
        String turnsToApply = textFields.get(7).getText();
        String isPositive = textFields.get(8).getText();
        String untilEnd = textFields.get(9).getText();
        String changeAp = textFields.get(10).getText();
        String changeHp = textFields.get(11).getText();
        String changeMp = textFields.get(12).getText();
        String stun = textFields.get(13).getText();
        String disarm = textFields.get(14).getText();
        String numOfHolyBuff = textFields.get(15).getText();
        String toxic = textFields.get(16).getText();
        String holyCell = textFields.get(17).getText();
        String fiery = textFields.get(18).getText();
        String kill = textFields.get(19).getText();
        String numOfOwnMinion = textFields.get(20).getText();
        String numOfOpponentMinion = textFields.get(21).getText();
        String ownHero = textFields.get(22).getText();
        String opponentHero = textFields.get(23).getText();
        String numOfOpponentBothNonSpell = textFields.get(24).getText();
        String numOfOwnBothNonSpell = textFields.get(25).getText();
        String allOwnMinion = textFields.get(26).getText();
        String allOpponentBothNonSpell = textFields.get(27).getText();
        String allOwnBothNonSpell = textFields.get(28).getText();

        makingHeroCard(numOfOwnMinion, numOfOpponentMinion, ownHero, opponentHero, numOfOpponentBothNonSpell, numOfOwnBothNonSpell, allOwnMinion, allOpponentBothNonSpell, allOwnBothNonSpell, name, Ap, Hp, AttackType, Range, coolDown, cost, turnsToApply, isPositive, untilEnd, changeAp, changeHp, changeMp, stun, disarm, numOfHolyBuff, toxic, holyCell, fiery, kill);
    }

    @SuppressWarnings("Duplicates")

    private static void makingHeroCard(String numOfOwnMinion, String numOfOpponentMinion, String ownHero, String opponentHero, String numOfOpponentBothNonSpell, String numOfOwnBothNonSpell, String allOwnMinion, String allOpponentBothNonSpell, String allOwnBothNonSpell, String name, String ap, String hp, String attackType, String range, String coolDown, String cost, String turnsToApply, String isPositive, String untilEnd, String changeAp, String changeHp, String changeMP, String stun, String disarm, String numOfHolyBuff, String toxic, String holyCell, String fiery, String kill)
    {
        int AP = Integer.parseInt(ap);
        int HP = Integer.parseInt(hp);
        int price = Integer.parseInt(cost, 10);
        int rangeOfAttack = Integer.parseInt(range);
        int cooldown = Integer.parseInt(coolDown, 10);
        int turn = Integer.parseInt(turnsToApply);
        int apChange = Integer.parseInt(changeAp);
        int hpChange = Integer.parseInt(changeHp);
        int mpChange = Integer.parseInt(changeMP);
        int holyNumber = Integer.parseInt(numOfHolyBuff);
        int numberOfOwnMinion = Integer.parseInt(numOfOwnMinion);
        int numberOfOpponentMinion = Integer.parseInt(numOfOpponentMinion);
        int numberOfOpponentBothNonSpell = Integer.parseInt(numOfOpponentBothNonSpell);
        int numberOfOwnBothNonSpell = Integer.parseInt(numOfOwnBothNonSpell);
        int numberOfHolyBuff = Integer.parseInt(numOfHolyBuff);
        Hero hero = new Hero();
        hero.setCardName(name);
        hero.setDefaultAP(AP);
        hero.setDefaultHP(HP);
        SpecialPower specialPower = new SpecialPower("Custom card");
        specialPower.getSpellEffect().addTarget(new Target());
        specialPower.getSpellEffect().addSpellChange(new SpellChange());
        hero.setSpecialPower(specialPower);
        hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setTurnsToApplyChange(turn);
        hero.getSpecialPower().getSpellEffect().getTargets().get(0).setNumOfOwnBothNonSpellCards(numberOfOwnBothNonSpell);
        hero.getSpecialPower().getSpellEffect().getTargets().get(0).setNumOfOpponentMinions(numberOfOpponentMinion);
        hero.getSpecialPower().getSpellEffect().getTargets().get(0).setNumOfOwnMinions(numberOfOwnMinion);
        hero.getSpecialPower().getSpellEffect().getTargets().get(0).setNumOfOpponentBothNonSpellCards(numberOfOpponentBothNonSpell);
        hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setNumOfHolyBuffs(numberOfHolyBuff);
        if (allOpponentBothNonSpell.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getTargets().get(0).setAllOpponentNonSpellCards(true);
        }
        if (allOwnBothNonSpell.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getTargets().get(0).setAllOwnBothNonSpellCards(true);
        }
        if (allOwnMinion.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getTargets().get(0).setAllOwnMinions(true);
        }
        if (ownHero.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getTargets().get(0).setOwnHero(true);
        }
        if (opponentHero.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getTargets().get(0).setOpponentHero(true);
        }
        if (attackType.equalsIgnoreCase("melee"))
        {
            hero.setImpactType(ImpactType.melee);
        }
        if (attackType.equalsIgnoreCase("ranged"))
        {
            hero.setImpactType(ImpactType.ranged);
        }
        if (attackType.equalsIgnoreCase("hybrid"))
        {
            hero.setImpactType(ImpactType.hybrid);
        }

        if (isPositive.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setPositiveChange(true);
        }
        if (untilEnd.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setApplyChangeUntilEndOfTheGame(true);
        }
        if (stun.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setStunOpponent(true);
        }
        if (disarm.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setDisarmOpponent(true);
        }
        if (toxic.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setMadeCellToxic(true);
        }
        if (kill.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setKilling(true);
        }
        if (fiery.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setMadeCellFiery(true);
        }
        if (holyCell.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setMadeCellHoly(true);
        }
        hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setNumOfHolyBuffs(holyNumber);
        hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setChangeHP(hpChange);
        hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setChangeAP(apChange);
        hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setChangeMP(mpChange);
        hero.setPrice(price);
        hero.setCoolDown(cooldown);
        hero.setRangeOfAttack(rangeOfAttack);
        Account.loggedInAccount.getCollection().addCard(Account.loggedInAccount, hero, false);
        Shop.getInstance().addCardToShop(hero);
        Server.addHero(hero);
        //showOutput.printOutput("Custom card " + hero.getCardID() + " added to your collection"); //todo
    }

    private Account findAccount(String authToken)
    {
        for (Account account : Server.getAccounts())
        {
            if (account.getAuthToken() == null)
            {
                continue;
            }
            if (account.getAuthToken().equals(authToken))
            {
                return account;
            }
        }
        return null;
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