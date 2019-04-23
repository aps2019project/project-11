package Controller;

import Model.*;
import View.*;

public class CallTheAppropriateFunction
{
    AccountManager accountManager = new AccountManager();
    CollectionManager collectionManager = new CollectionManager();

    public void setPrimarySettings()
    {
        Card.setCards();
        Item.setItems();
        Account.setAIAccounts();
        determineAccountCommand();
    }

    public void determineAccountCommand()
    {
        while (true)
        {
            Request.getAccountCommands();
            switch (Request.command)
            {
                case CREATE_ACCOUNT:
                    accountManager.checkCircumstancesToCreateAccount(Request.command.username);
                    break;
                case LOGIN:
                    accountManager.checkCircumstancesToLogin(Request.command.username);
                    break;
                case SHOW_LEADER_BOARD:
                    accountManager.sortAccountsByWins();
                    ShowOutput.showAccountsLeaderBoard();
                    break;
                case HELP:
                    ShowOutput.accountHelp();
                    break;
                case EXIT:
                    System.exit(0);
                    break;
            }
        }
    }

    public void determineMainMenuCommand()
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
                    accountManager.logout();
                    break;
                case SAVE:

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

    public void determineShopCommand()
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
                    ShowOutput.showCollectionInfo(Account.loggedInAccount.getCollection());
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

    public void determineCollectionCommand()
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
                    ShowOutput.showCollectionInfo(Account.loggedInAccount.getCollection());
                    break;
                case SEARCH:
                    collectionManager.searchCollection(Request.command.cardOrItemName);
                    break;
                case SAVE:
                    //todo
                    break;
                case CREATE_DECK:
                    collectionManager.createDeck(Request.command.deckName);
                    break;
                case DELETE_DECK:
                    collectionManager.deleteDeck(Request.command.deckName);
                    break;
                case ADD_TO_DECK:
                    collectionManager.detectID(Request.command.cardOrItemID, Request.command.deckName, "add");
                    break;
                case REMOVE_FROM_DECK:
                    collectionManager.detectID(Request.command.cardOrItemID, Request.command.deckName, "remove");
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

    public void determineBattleMenuCommand()
    {
        while (true) {
            Request.getBattleMenuCommands();
        }
    }

    public void determineBattleCommand()
    {
        while (true){
            Request.getBattleCommands();
            switch (Request.command)
            {
                case GAME_INFO:
                Battle.showGameInfo();
                break;
                case SHOW_MY_MINIONS:
                    Battle.showMyMinions();
                    break;
                case SHOW_OPPONENT_MINIONS:
                    Battle.showOpponentMinions();
                    break;
                case SHOW_CARD_INFO:
                    Battle.showCardInfo(Request.command.cardOrItemID);
                    break;
                case SELECT:
                    Battle.selectCard(Request.command.cardOrItemID);
                    break;
                case MOVE_TO:
                    Battle.moveCard(Request.command.rowOfTheHouse,Request.command.columnOfTheHouse);
                case SHOW_NEXT_CARD:

                case ENTER_GRAVEYARD:
                    determineGraveYardCommand();

                case HELP_BATTLE:

                case EXIT:
                    return;
            }
        }

    }

    public void determineGraveYardCommand()
    {
        while (true
        ){
            Request.getGraveYardCommands();
            switch (Request.command)
            {
                case SHOW_INFO:
                    /*todo*/
                case SHOW_CARDS:
                    /*todo*/
                case EXIT:
                    return;
            }
        }
    }
}
