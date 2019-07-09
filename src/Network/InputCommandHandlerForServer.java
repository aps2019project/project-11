package Network;

import Controller.AccountManager;
import Controller.DeckManager;
import Controller.ShopManager;
import Model.*;
import View.ShowOutput;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class InputCommandHandlerForServer extends Thread
{
    public final Object validMessageLock = new Object();
    private String message;
    private SendMessage sendMessage;
    private Socket socket;
    private AccountManager accountManager = new AccountManager();
    private ShopManager shopManager = new ShopManager();
    private DeckManager deckManager = new DeckManager();

    public InputCommandHandlerForServer(Socket socket, SendMessage sendMessage)
    {
        this.socket = socket;
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
                message = null;
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
        ServerCommand serverCommand;
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
            case GET_ALL_ACCOUNTS:
                serverCommand = new ServerCommand(ServerCommandEnum.OK, Server.getAccounts());
                String getAllAccountsJson = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                getSendMessage().addMessage(getAllAccountsJson);
                break;
            case GET_ACCOUNT:
                serverCommand = new ServerCommand(ServerCommandEnum.OK, account);
                String getAccountJson = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                getSendMessage().addMessage(getAccountJson);
                break;
            case GET_SHOP_CARDS_AND_ITEMS:
                serverCommand = new ServerCommand(ServerCommandEnum.OK, Server.getHeroes(), Server.getMinions(), Server.getSpells(), Server.getItems());
                String shopJson = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                getSendMessage().addMessage(shopJson);
                break;
            case GET_COLLECTION_CARDS_AND_ITEMS_AND_DECKS:
                serverCommand = new ServerCommand(ServerCommandEnum.OK, account.getCollection().getHeroes(), account.getCollection().getMinions(), account.getCollection().getSpells(), account.getCollection().getItems(), account.getPlayerDecks());
                String collectionJson = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                getSendMessage().addMessage(collectionJson);
                break;
            case SAVE_ACCOUNT_INFO:
                accountManager.saveAccountInfo(account, false);
                serverCommand = new ServerCommand(ServerCommandEnum.OK);
                String saveJson = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                getSendMessage().addMessage(saveJson);
                break;
            case SAVE_SHOP:
                makeShopJson();
                serverCommand = new ServerCommand(ServerCommandEnum.OK);
                String saveShopJson = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                getSendMessage().addMessage(saveShopJson);
                break;
            case ENTER_BID_MENU:
                serverCommand = new ServerCommand(ServerCommandEnum.OK, Server.getBidHero(), Server.getBidMinion(), Server.getBidSpell(), Server.getBidItem());
                String bidJson = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                getSendMessage().addMessage(bidJson);
                break;
            case BID:
                bid(clientCommand, account);
                break;
            case BUY:
                buyCardAndItem(clientCommand, account);
                break;
            case SELL:
                sellCardAndItem(clientCommand, account);
                break;
            case VALIDATE_DECK:
                serverCommand = new ServerCommand(ServerCommandEnum.OK);
                deckManager.checkDeckValidity(clientCommand.getDeck(), serverCommand);
                String validateDeckJson = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                getSendMessage().addMessage(validateDeckJson);
                break;
            case SET_MAIN_DECK:
                serverCommand = new ServerCommand(ServerCommandEnum.OK);
                deckManager.setDeckAsMainDeck(clientCommand.getDeck(), account, serverCommand);
                String setMainDeckJson = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                getSendMessage().addMessage(setMainDeckJson);
                break;
            case GET_PLAYER_DECKS:
                serverCommand = new ServerCommand(account.getPlayerDecks(), ServerCommandEnum.OK);
                String getDeckJson = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                getSendMessage().addMessage(getDeckJson);
                break;
            case REMOVE_CARD_FROM_DECK:
                Deck deck = DeckManager.findDeck(clientCommand.getDeckName(), account);
                checkIDValidityToRemoveFromDeck(deck,clientCommand.getCardOrItemID(),account);
                break;
            case ADD_TO_DECK:
                Deck deckToAddCard = DeckManager.findDeck(clientCommand.getDeckName(), account);
                checkIDValidityToAddToDeck(deckToAddCard, clientCommand.getCardOrItemID(), account);
                break;
            case IMPORT_DECK:
                importingToCollection(clientCommand.getDeckName(), account);
                break;
            case EXPORT_DECK:
                Deck deckToExport = DeckManager.findDeck(clientCommand.getDeckName(), account);
                exportingDeck(account, deckToExport);
                serverCommand = new ServerCommand(ServerCommandEnum.OK);
                String exportDeckJson = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                getSendMessage().addMessage(exportDeckJson);
                break;
            case CREATE_DECK:
                createDeck(clientCommand.getDeckName(), account);
                serverCommand = new ServerCommand(ServerCommandEnum.OK);
                String createDeckJson = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                getSendMessage().addMessage(createDeckJson);
                break;
            case DELETE_DECK:
                deleteDeck(clientCommand.getDeckName(), account);
                serverCommand = new ServerCommand(ServerCommandEnum.OK, "Deck removed");
                String deleteDeckJson = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                getSendMessage().addMessage(deleteDeckJson);
                break;
            case MAKE_CUSTOM_SPELL:
                //workingOnSpellText(clientCommand.getTextFieldsToMakeCustom(), account);
                serverCommand = new ServerCommand(ServerCommandEnum.OK);
                String customSpellJson = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                getSendMessage().addMessage(customSpellJson);
                break;
            case MAKE_CUSTOM_MINION:
                workingOnMinionText(clientCommand.getTextFieldsToMakeCustom(), account);
                serverCommand = new ServerCommand(ServerCommandEnum.OK);
                String customMinionJson = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                getSendMessage().addMessage(customMinionJson);
                break;
            case MAKE_CUSTOM_HERO:
                workingOnHeroText(clientCommand.getTextFieldsToMakeCustom(), account);
                serverCommand = new ServerCommand(ServerCommandEnum.OK);
                String customHeroJson = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                getSendMessage().addMessage(customHeroJson);
                break;
            case MAKE_STORY_BATTLE:
                break;
            case MAKE_CUSTOM_BATTLE:
                break;
            case MAKE_MULTI_PLAYER_BATTLE:
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
            case SET_MP_ICONS:
                break;
            case GET_ONLINE_ACCOUNTS:
                break;
            case SEND_MESSAGE:
                GlobalChat.getChatMessages().add(clientCommand.getChatMessage());
                serverCommand = new ServerCommand(ServerCommandEnum.OK);
                String sendMessageJson = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                getSendMessage().addMessage(sendMessageJson);
                break;
            case GET_ALL_MESSAGES_IN_CHAT:
                serverCommand = new ServerCommand(ServerCommandEnum.OK);
                serverCommand.setChatMessages(GlobalChat.getChatMessages());
                String getAllChatsJson = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                getSendMessage().addMessage(getAllChatsJson);
                break;
            case MAKE_BATTLE_FOR_MULTI_PLAYER_GAME:
                break;
            case GET_STORY_PLAYER_1:
                serverCommand = new ServerCommand(ServerCommandEnum.OK);
                serverCommand.setPlayer(AccountManager.getStoryPlayer1());
                String getStoryPlayer1 = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                getSendMessage().addMessage(getStoryPlayer1);
                break;
            case GET_STORY_PLAYER_2:
                serverCommand = new ServerCommand(ServerCommandEnum.OK);
                serverCommand.setPlayer(AccountManager.getStoryPlayer2());
                String getStoryPlayer2 = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                getSendMessage().addMessage(getStoryPlayer2);
                break;
            case GET_STORY_PLAYER_3:
                serverCommand = new ServerCommand(ServerCommandEnum.OK);
                serverCommand.setPlayer(AccountManager.getStoryPlayer3());
                String getStoryPlayer3 = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
                getSendMessage().addMessage(getStoryPlayer3);
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
                accountManager.saveAccountInfo(account, true);
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


    public void deleteDeck(String deckName, Account account)
    {
        ServerCommand serverCommand = null;
        Deck deck = DeckManager.findDeck(deckName, account);
        if (deck != null)
        {
            account.deleteDeck(deck);
            ShowOutput.getInstance().printOutput("Deck deleted");

        }
        else
        {
            serverCommand = new ServerCommand(ServerCommandEnum.ERROR, "There is no deck with this name");
            ShowOutput.getInstance().printOutput("There is no deck with this name");
        }
    }
    @SuppressWarnings("Duplicates")

    private void bid(ClientCommand clientCommand, Account account) throws Exception
    {
        ServerCommand serverCommand = null;

        Hero hero = clientCommand.getHero();
        Minion minion = clientCommand.getMinion();
        Spell spell = clientCommand.getSpell();
        Item item = clientCommand.getItem();
        if (item == null)
        {
            if (hero != null)
            {
                serverCommand = new ServerCommand(ServerCommandEnum.OK, shopManager.bidCard(Server.getBidHero(), account));
            }
            else if (minion != null)
            {
                serverCommand = new ServerCommand(ServerCommandEnum.OK, shopManager.bidCard(Server.getBidMinion(), account));
            }
            else if (spell != null)
            {
                serverCommand = new ServerCommand(ServerCommandEnum.OK, shopManager.bidCard(Server.getBidSpell(), account));
            }
        }
        else
        {
            serverCommand = new ServerCommand(ServerCommandEnum.OK, shopManager.bidItem(Server.getBidItem(),account));
        }
        String bidJson = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
        getSendMessage().addMessage(bidJson);
    }

    private void buyCardAndItem(ClientCommand clientCommand, Account account) throws Exception
    {
        ServerCommand serverCommand;

        Item item = clientCommand.getItem();
        Hero hero = clientCommand.getHero();
        Minion minion = clientCommand.getMinion();
        Spell spell = clientCommand.getSpell();

        if (item == null)
        {
            Card card = null;
            if (hero != null)
            {
                card = hero;
            }
            else if (minion != null)
            {
                card = minion;
            }
            else if (spell != null)
            {
                card = spell;
            }

            serverCommand = new ServerCommand(shopManager.buyCard(Card.findCard(card.getCardName()), account));
        }
        else
        {
            serverCommand = new ServerCommand(shopManager.buyItem(Item.findItem(item.getItemName()),account));
        }
        String buyCardJson = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
        getSendMessage().addMessage(buyCardJson);
    }
    @SuppressWarnings("Duplicates")

    private void sellCardAndItem(ClientCommand clientCommand, Account account) throws InterruptedException
    {
        ServerCommand serverCommand;

        Hero hero = clientCommand.getHero();
        Minion minion = clientCommand.getMinion();
        Spell spell = clientCommand.getSpell();
        Item item = clientCommand.getItem();
        if (item == null)
        {
            Card card = new Card();
            if (hero != null)
            {
                card = hero;
            }
            if (minion != null)
            {
                card = minion;
            }
            if (spell != null)
            {
                card = spell;
            }
            serverCommand = new ServerCommand(shopManager.detectIDToSell(card.getCardID(), account));
        }
        else
        {
            serverCommand = new ServerCommand(shopManager.sellItem(account, item.getItemID()));
        }
        String SellCardJson = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
        getSendMessage().addMessage(SellCardJson);
    }

    public void createDeck(String deckName, Account account)
    {
        Deck deck = DeckManager.findDeck(deckName, account);
        if (deck != null)
        {
            ShowOutput.getInstance().printOutput("Deck exists with this name");
            return;
        }
        Deck newDeck = new Deck(deckName);
        account.addDeck(newDeck);
        ShowOutput.getInstance().printOutput("Deck created");
    }

    @SuppressWarnings("Duplicates")

    public void checkIDValidityToRemoveFromDeck(Deck deck, String ID, Account account)
    {
        String message = null;
        if (account.getCollection().findCardinCollection(ID) != null)
        {
            for (Hero hero : account.getCollection().getHeroes())
            {
                if (ID.equals(hero.getCardID()))
                {
                   message =  deckManager.checkCircumstanceToRemoveHeroCardFromDeck(deck, hero, account);
                }
            }
            for (Minion minion : account.getCollection().getMinions())
            {
                if (ID.equals(minion.getCardID()))
                {
                     message= deckManager.checkCircumstancesToRemoveCardFromDeck(deck, minion, account);
                }
            }
            for (Spell spell : account.getCollection().getSpells())
            {
                if (ID.equals(spell.getCardID()))
                {
                   message =  deckManager.checkCircumstancesToRemoveCardFromDeck(deck, spell, account);
                }
            }
        }
        else if (account.getCollection().findItemInTheCollection(ID) != null)
        {
            for (Item item : account.getCollection().getItems())
            {
                if (ID.equals(item.getItemID()))
                {
                    message = deckManager.checkCircumstancesToRemoveItemFromDeck(deck, item, account);
                }
            }
        }
        else
        {
            message = "Invalid ID";
        }
        ServerCommand serverCommand = new ServerCommand(message);
        String removeJson = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
        System.out.println(removeJson);
        try
        {
            getSendMessage().addMessage(removeJson);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("Duplicates")

    public void checkIDValidityToAddToDeck(Deck deck, String ID, Account account)
    {
        String message = null;

        if (account.getCollection().findCardinCollection(ID) != null)
        {
            for (Hero hero : account.getCollection().getHeroes())
            {
                if (ID.equals(hero.getCardID()))
                {
                    message = deckManager.checkCircumstanceToAddHeroCardToDeck(deck, hero, account);
                }
            }
            for (Minion minion : account.getCollection().getMinions())
            {
                if (ID.equals(minion.getCardID()))
                {
                    message = deckManager.checkCircumstancesToAddCardToDeck(deck, minion, account);
                }
            }
            for (Spell spell : account.getCollection().getSpells())
            {
                if (ID.equals(spell.getCardID()))
                {
                    message = deckManager.checkCircumstancesToAddCardToDeck(deck, spell, account);
                }
            }
        }
        else if (account.getCollection().findItemInTheCollection(ID) != null)
        {
            for (Item item : account.getCollection().getItems())
            {
                if (ID.equals(item.getItemID()))
                {
                    message = deckManager.checkCircumstancesToAddItemToDeck(deck, item, account);
                }
            }
        }
        else
        {
            message = "Invalid ID";
        }
        ServerCommand serverCommand = new ServerCommand(message);
        String addJson = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
        System.out.println(addJson);
        try
        {
            getSendMessage().addMessage(addJson);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("Duplicates")
    private void importingToCollection(String deckName, Account account) throws IOException, ParseException
    {
        JsonParser jsonParser = new JsonParser();
        FileReader reader = new FileReader("SavedDecks/" + deckName + ".json");
        Object obj = jsonParser.parse(reader);
        System.out.println(obj);
        Deck deck = new Gson().fromJson(obj.toString(), Deck.class);
        deck.setDeckName("Imported " + deck.getDeckName());
        account.getPlayerDecks().add(deck);
        addImportedDeckCardsAndItemsToCollection(deck);
        ServerCommand serverCommand = new ServerCommand(ServerCommandEnum.OK);
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(serverCommand);
        try
        {
            getSendMessage().addMessage(json);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("Duplicates")

    private void exportingDeck(Account account, Deck deck)
    {

        String exportingDeckJson = new GsonBuilder().setPrettyPrinting().create().toJson(deck);
        try
        {
            writeExportedDeckNameInFile(account.getAccountName() + deck.getDeckName());

            FileWriter fileWriter = new FileWriter("SavedDecks/" + account.getAccountName() + deck.getDeckName() + ".json");
            fileWriter.write(exportingDeckJson);
            System.out.println(account.getAccountName() + deck.getDeckName());
            fileWriter.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("Duplicates")

    private void writeExportedDeckNameInFile(String exportedDeckName) throws Exception
    {
        InputStream inputStream = new FileInputStream("SavedDecks/savedDecksPath.txt");
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext())
        {
            if (scanner.nextLine().equals(exportedDeckName))
            {
                return;
            }
        }
        FileWriter savedDecksPath = new FileWriter("SavedDecks/savedDecksPath.txt", true);
        savedDecksPath.write(exportedDeckName + "\n");
        savedDecksPath.close();
    }

    private void addImportedDeckCardsAndItemsToCollection(Deck deck)
    {
        //send deck to server    //
    }

    @SuppressWarnings("Duplicates")
    public void workingOnSpellText(ArrayList<TextField> textFields, Account account)
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
        makingSpellCard(account, numOfOwnMinion, numOfOpponentMinion, ownHero, opponentHero, numOfOpponentBothNonSpell, numOfOwnBothNonSpell, allOwnMinion, allOpponentBothNonSpell, allOwnBothNonSpell, name, numOfTarget, kindOfMinion, nameOfBuff, buffType, effectValue, delay, last, friendOrEnemy, numOfFriendOrEnemy, isAll, mp, numOfHolyBuff, changingAP, changingHP, changingMp, cost);
    }

    @SuppressWarnings("Duplicates")

    private static void makingSpellCard(Account account, String numOfOwnMinion, String numOfOpponentMinion, String ownHero, String opponentHero, String numOfOpponentBothNonSpell, String numOfOwnBothNonSpell, String allOwnMinion, String allOpponentBothNonSpell, String allOwnBothNonSpell, String name, String numOfTarget, String kindOfMinion, String nameOfBuff, String buffType, String effectValue, String delay, String last, String friendOrEnemy, String numOfFriendOrEnemy, String isAll, String MP, String numOfHolyBuff, String changingAp, String changingHp, String changingMp, String cost)
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
        account.getCollection().addCard(account, spell, false);
        Server.getShop().addCardToShop(spell);
        Server.addSpell(spell);
        //showOutput.printOutput("Custom card " + spell.getCardID() + " added to your collection");//todo
    }

    @SuppressWarnings("Duplicates")

    public static void workingOnMinionText(ArrayList<String> textFields, Account account)
    {
        String name = textFields.get(0);
        String Ap = textFields.get(1);
        String Hp = textFields.get(2);
        String AttackType = textFields.get(3);
        String Range = textFields.get(4);
        String specialPowerActivation = textFields.get(5);
        String cost = textFields.get(6);
        String turnsToApply = textFields.get(7);
        String isPositive = textFields.get(8);
        String untilEnd = textFields.get(9);
        String changeAp = textFields.get(10);
        String changeHp = textFields.get(11);
        String changeMp = textFields.get(12);
        String stun = textFields.get(13);
        String disarm = textFields.get(14);
        String numOfHolyBuff = textFields.get(15);
        String toxic = textFields.get(16);
        String holyCell = textFields.get(17);
        String fiery = textFields.get(18);
        String combo = textFields.get(19);
        String numOfOwnMinion = textFields.get(20);
        String numOfOpponentMinion = textFields.get(21);
        String ownHero = textFields.get(22);
        String opponentHero = textFields.get(23);
        String numOfOpponentBothNonSpell = textFields.get(24);
        String numOfOwnBothNonSpell = textFields.get(25);
        String allOwnMinion = textFields.get(26);
        String allOpponentBothNonSpell = textFields.get(27);
        String allOwnBothNonSpell = textFields.get(28);
        makingMinionCard(account, numOfOwnMinion, numOfOpponentMinion, ownHero, opponentHero, numOfOpponentBothNonSpell, numOfOwnBothNonSpell, allOwnMinion, allOwnBothNonSpell, allOpponentBothNonSpell, name, Ap, Hp, AttackType, Range, specialPowerActivation, cost, turnsToApply, isPositive, untilEnd, changeAp, changeHp, changeMp, stun, disarm, numOfHolyBuff, toxic, holyCell, fiery, combo);
    }

    @SuppressWarnings("Duplicates")

    private static void makingMinionCard(Account account, String numOfOwnMinion, String numOfOpponentMinion, String ownHero, String opponentHero, String numOfOpponentBothNonSpell, String numOfOwnBothNonSpell, String allOwnMinion, String allOwnBothNonSpell, String allOpponentBothNonSpell, String name, String ap, String hp, String attackType, String range, String specialPowerActivation, String cost, String turn, String isPositive, String UntilEnd, String changeAP, String changeHP, String ChangeMP, String stun, String disarm, String numOfHolyBuff, String toxic, String holycell, String fiery, String combo)
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
        account.getCollection().addCard(account, minion, false);
        Server.getShop().addCardToShop(minion);
        Server.addMinion(minion);
        //showOutput.printOutput("Custom card " + minion.getCardID() + " added to your collection");//todo
    }

    @SuppressWarnings("Duplicates")

    public static void workingOnHeroText(ArrayList<String> textFields, Account account)
    {
        String name = textFields.get(0);
        String Ap = textFields.get(1);
        String Hp = textFields.get(2);
        String AttackType = textFields.get(3);
        String Range = textFields.get(4);
        String coolDown = textFields.get(5);
        String cost = textFields.get(6);
        String turnsToApply = textFields.get(7);
        String isPositive = textFields.get(8);
        String untilEnd = textFields.get(9);
        String changeAp = textFields.get(10);
        String changeHp = textFields.get(11);
        String changeMp = textFields.get(12);
        String stun = textFields.get(13);
        String disarm = textFields.get(14);
        String numOfHolyBuff = textFields.get(15);
        String toxic = textFields.get(16);
        String holyCell = textFields.get(17);
        String fiery = textFields.get(18);
        String kill = textFields.get(19);
        String numOfOwnMinion = textFields.get(20);
        String numOfOpponentMinion = textFields.get(21);
        String ownHero = textFields.get(22);
        String opponentHero = textFields.get(23);
        String numOfOpponentBothNonSpell = textFields.get(24);
        String numOfOwnBothNonSpell = textFields.get(25);
        String allOwnMinion = textFields.get(26);
        String allOpponentBothNonSpell = textFields.get(27);
        String allOwnBothNonSpell = textFields.get(28);

        makingHeroCard(account, numOfOwnMinion, numOfOpponentMinion, ownHero, opponentHero, numOfOpponentBothNonSpell, numOfOwnBothNonSpell, allOwnMinion, allOpponentBothNonSpell, allOwnBothNonSpell, name, Ap, Hp, AttackType, Range, coolDown, cost, turnsToApply, isPositive, untilEnd, changeAp, changeHp, changeMp, stun, disarm, numOfHolyBuff, toxic, holyCell, fiery, kill);
    }

    @SuppressWarnings("Duplicates")

    private static void makingHeroCard(Account account, String numOfOwnMinion, String numOfOpponentMinion, String ownHero, String opponentHero, String numOfOpponentBothNonSpell, String numOfOwnBothNonSpell, String allOwnMinion, String allOpponentBothNonSpell, String allOwnBothNonSpell, String name, String ap, String hp, String attackType, String range, String coolDown, String cost, String turnsToApply, String isPositive, String untilEnd, String changeAp, String changeHp, String changeMP, String stun, String disarm, String numOfHolyBuff, String toxic, String holyCell, String fiery, String kill)
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

        account.getCollection().addCard(account, hero, false);
        Server.getShop().addCardToShop(hero);
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

    public void makeShopJson()
    {
        String shopJson = new GsonBuilder().setPrettyPrinting().create().toJson(Server.getShop());
        try
        {
            FileWriter fileWriter = new FileWriter("shop.json");
            fileWriter.write(shopJson);
            fileWriter.close();
        } catch (Exception e)
        {
            e.printStackTrace();
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