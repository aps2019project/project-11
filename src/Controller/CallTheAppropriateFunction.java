package Controller;

import Model.*;
import View.*;

public class CallTheAppropriateFunction
{
    public static void setPrimarySettings()
    {
        Card.setCards();
        Item.setItems();
        Account.setAIAccounts();
        determineAccountCommand();
    }

    public static void determineAccountCommand()
    {
        while (true)
        {
            Request.getAccountCommands();
            if (Request.command == null)
            {
                continue;
            }
            switch (Request.command)
            {
                case CREATE_ACCOUNT:
                    new Account(Request.command.username);
                    break;
                case LOGIN:
                    Account.login(Request.command.username);
                    if (Account.loggedInAccount != null)
                    {
                        ShowOutput.printMainMenuCommands();
                        determineMainMenuCommand();
                    }
                    break;
                case EXIT:
                    System.exit(0);
                    break;
                case HELP:
                    ShowOutput.accountHelp();
                    break;
                case SHOW_LEADER_BOARD:
                    Account.sortAccountsByWins();
                    ShowOutput.showLeaderBoard();
                    break;
            }
        }
    }

    public static void determineMainMenuCommand()
    {
        while (true)
        {
            Request.getMainMenuCommands();
            if (Request.command == null)
            {
                continue;
            }
            switch (Request.command)
            {
                case ENTER_SHOP:
                    determineShopCommand();
                    break;
                case ENTER_COLLECTION:
                    determineCollectionCommand();
                    break;
                case ENTER_BATTLE:
                    determineBattleMenuCommand();
                    break;
                case LOGOUT:
                    Account.logout();
                    determineAccountCommand();
                    break;
                case SAVE:
                    //todo
                    break;
                case EXIT:
                    System.exit(0);
                    break;
                case HELP:
                    ShowOutput.printMainMenuCommands();
                    break;
            }
        }
    }

    public static void determineShopCommand()
    {
        while (true)
        {
            Request.getShopCommands();
            if (Request.command == null)
            {
                continue;
            }
            switch (Request.command)
            {
                case EXIT:
                    ShowOutput.printMainMenuCommands();
                    determineMainMenuCommand();
                    break;
                case SHOW_COLLECTION:
                    Account.loggedInAccount.getCollection().show();
                    break;
                case SEARCH:
                    Shop.shop.searchShop(Request.command.cardOrItemName);
                    break;
                case SEARCH_COLLECTION:
                    Shop.shop.searchCollection(Request.command.cardOrItemName);
                    break;
                case BUY:
                    if(Card.findCard(Request.command.cardOrItemName) != null)
                    {
                        Shop.shop.buyCard(Card.findCard(Request.command.cardOrItemName));
                    }
                    else if(Item.findItem(Request.command.cardOrItemName) != null)
                    {
                        Shop.shop.buyItem(Item.findItem(Request.command.cardOrItemName));
                    }
                    else
                    {
                        ShowOutput.printOutput("Card or Item does'nt exist in Shop");
                    }
                    break;
                case SELL:
                    Shop.shop.detectIDToSell(Request.command.cardOrItemID);
                    break;
                case SHOW:
                    Shop.shop.show();
                    break;
                case HELP:
                    ShowOutput.shopHelp();
                    break;
            }
        }
    }

    public static void determineCollectionCommand()
    {
        while (true)
        {
            Request.getCollectionCommands();
            if (Request.command == null)
            {
                continue;
            }
            switch (Request.command)
            {
                case EXIT:
                    ShowOutput.printMainMenuCommands();
                    determineMainMenuCommand();
                    break;
                case SHOW:
                    Account.loggedInAccount.getCollection().show();
                    break;
                case SEARCH:
                    Account.loggedInAccount.getCollection().searchCollection(Request.command.cardOrItemName);
                    break;
                case SAVE:
                    //todo
                    break;
                case CREATE_DECK:
                    Account.loggedInAccount.getCollection().createDeck(Request.command.deckName);
                    break;
                case DELETE_DECK:
                    Account.loggedInAccount.getCollection().deleteDeck(Request.command.deckName);
                    break;
                case ADD_TO_DECK:
                    Account.loggedInAccount.getCollection().detectID(Request.command.cardOrItemID, Request.command.deckName, "add");
                    break;
                case REMOVE_FROM_DECK:
                    Account.loggedInAccount.getCollection().detectID(Request.command.cardOrItemID, Request.command.deckName, "remove");
                    break;
                case VALIDATE_DECK:
                    Deck.checkDeckValidity(Request.command.deckName);
                    break;
                case SET_MAIN_DECK:
                    Deck.setDeckAsMainDeck(Request.command.deckName);
                    break;
                case SHOW_DECK:
                    Deck.showDeck(Request.command.deckName);
                    break;
                case SHOW_ALL_DECKS:
                    Deck.showAllDecks();
                    break;
                case HELP:
                    ShowOutput.collectionHelp();
                    break;

            }
        }
    }

    public static void determineBattleMenuCommand()
    {
        while (true) {
            Request.getBattleMenuCommands();
        }
    }

    public static void determineBattleCommand()
    {
        while (true){
            Request.getBattleCommands();
            switch (Request.command){
                case SHOW_NEXT_CARD:
                case ENTER_GRAVEYARD:
                    determineGraveYardCommand();
                case EXIT:
            }
        }

    }

    public static void determineGraveYardCommand(){
        while (true){
            Request.getGraveYardCommands();
            switch (Request.command){
                case SHOW_INFO:
                case SHOW_CARDS:
                case HELP_GRAVEYARD:
                case EXIT:
                    return;
            }
        }
    }

    public static void logout()
    {
        Account.logout();
        Request.getAccountCommands();
    }

    public static void printOutput(String output)
    {
        ShowOutput.printOutput(output);
    }

    public static String getPassword()
    {
        return Request.getPassword();
    }

    public static void printHeroStats(Hero hero, int counter)
    {
        ShowOutput.printHeroStats(hero, counter);
    }
}
