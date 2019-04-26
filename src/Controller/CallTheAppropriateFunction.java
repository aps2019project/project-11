package Controller;

import Model.*;
import View.*;

public class CallTheAppropriateFunction
{
    private AccountManager accountManager = new AccountManager();
    private CollectionManager collectionManager = new CollectionManager();
    private DeckManager deckManager = new DeckManager();
    private ShopManager shopManager = new ShopManager();
    private BattleManager battleManager = new BattleManager();

    public void setPrimarySettings()
    {
        Card.setCards();
        Item.setItems();
        Account.setAIAccounts();
        determineAccountCommand();
    }

    void determineMainMenuCommand()
    {
        while (true)
        {
            Request.getMainMenuCommands();
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

    private void determineAccountCommand()
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

    private void determineShopCommand()
    {
        while (true)
        {
            Request.getShopCommands();
            switch (Request.command)
            {
                case SHOW_COLLECTION:
                    ShowOutput.showCollectionInfo(Account.loggedInAccount.getCollection());
                    break;
                case SEARCH:
                    shopManager.searchShop(Request.command.cardOrItemName);
                    break;
                case SEARCH_COLLECTION:
                    collectionManager.searchCollection(Request.command.cardOrItemName);
                    break;
                case BUY:
                    if(Card.findCard(Request.command.cardOrItemName) != null)
                    {
                        shopManager.buyCard(Card.findCard(Request.command.cardOrItemName));
                    }
                    else if(Item.findItem(Request.command.cardOrItemName) != null)
                    {
                        shopManager.buyItem(Item.findItem(Request.command.cardOrItemName));
                    }
                    else
                    {
                        ShowOutput.printOutput("Card or Item does'nt exist in Shop");
                    }
                    break;
                case SELL:
                    shopManager.detectIDToSell(Request.command.cardOrItemID);
                    break;
                case SHOW:
                    ShowOutput.showShopInfo();
                    break;
                case HELP:
                    ShowOutput.shopHelp();
                    break;
                case EXIT:
                    ShowOutput.printMainMenuCommands();
                    determineMainMenuCommand();
                    break;
            }
        }
    }

    private void determineCollectionCommand()
    {
        while (true)
        {
            Request.getCollectionCommands();
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
                    deckManager.checkDeckValidity(Request.command.deckName);
                    break;
                case SET_MAIN_DECK:
                    deckManager.setDeckAsMainDeck(Request.command.deckName);
                    break;
                case SHOW_DECK:
                    ShowOutput.showDeckInfo(Request.command.deckName);
                    break;
                case SHOW_ALL_DECKS:
                    ShowOutput.showAllDecksInfo();
                    break;
                case HELP:
                    ShowOutput.collectionHelp();
                    break;

            }
        }
    }

    private void determineBattleMenuCommand()
    {
        while (true)
        {
            Request.getBattleMenuCommands();
        }
    }

    private void determineBattleCommand()
    {
        while (true)
        {
            Request.getBattleCommands();
            switch (Request.command)
            {
                case GAME_INFO:
                    ShowOutput.showGameInfo();
                break;
                case SHOW_MY_MINIONS:
                    ShowOutput.showMyMinions();
                    break;
                case SHOW_OPPONENT_MINIONS:
                    ShowOutput.showOpponentMinions();
                    break;
                case SHOW_CARD_INFO:
                    ShowOutput.showCardInfo(Request.command.cardOrItemID);
                    break;
                case SELECT:
                    Battle.selectCard(Request.command.cardOrItemID);
                    break;
                case MOVE_TO:
                    Battle.moveCard(Request.command.rowOfTheHouse,Request.command.columnOfTheHouse);
                    break;
                case SHOW_HAND:
                    ShowOutput.showHand(Battle.currentBattle.getPlayerTurn().getHand());
                    break;
                case INSERT_CARD:
                    battleManager.CheckCircumstancesToInsertCard(Request.command.cardOrItemName, Request.command.rowOfTheHouse, Request.command.columnOfTheHouse);
                case SHOW_NEXT_CARD:

                case ENTER_GRAVEYARD:
                    determineGraveYardCommand();

                case HELP_BATTLE:

                case EXIT:
                    return;
            }
        }

    }

    private void determineGraveYardCommand()
    {
        while (true)
        {
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
