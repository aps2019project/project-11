package Controller;

import Model.*;
import View.*;

public class CallTheAppropriateFunction extends Thread
{
    private AccountManager accountManager = new AccountManager();
    private CollectionManager collectionManager = new CollectionManager();
    private DeckManager deckManager = new DeckManager();
    private ShopManager shopManager = new ShopManager();
    private BattleManager battleManager = new BattleManager();
    private Request request = new Request();
    private ShowOutput showOutput = new ShowOutput();

    @Override
    public void run()
    {
        try
        {
            setPrimarySettings();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void setPrimarySettings() throws InterruptedException
    {
        Card.setCards();
        Item.setItems();
        determineMainMenuCommand();
    }

    private void determineMainMenuCommand() throws InterruptedException
    {
        showOutput.printMainMenuCommands();
        while (true)
        {
            if (request.getCommand() == null)
            {
                synchronized (Request.requestLock)
                {
                    Request.requestLock.wait();
                }
            }
            switch (request.getCommand())
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
                case SHOW_LEADER_BOARD:
                    accountManager.sortAccountsByWins();
                    showOutput.showAccountsLeaderBoard();
                    break;
                case LOGOUT:
                    accountManager.logout();
                    break;
                case SAVE:
                    //todo
                    break;
                case HELP:
                    showOutput.printMainMenuCommands();
                    break;
                case EXIT:
                    System.exit(0);
                    break;
            }
            Request.setCommand(null);
        }
    }

    private void determineShopCommand() throws InterruptedException
    {
        while (true)
        {
            request.getShopCommands();
            if (request.getCommand() == null)
            {
                continue;
            }
            switch (request.getCommand())
            {
                case SHOW_COLLECTION:
                    showOutput.showCollectionInfo(Account.loggedInAccount.getCollection());
                    break;
                case SEARCH:
                    shopManager.searchShop(request.getCommand().cardOrItemName);
                    break;
                case SEARCH_COLLECTION:
                    collectionManager.searchCollection(request.getCommand().cardOrItemName);
                    break;
                case BUY:
                    try
                    {
                        Card card = Card.findCard(request.getCommand().cardOrItemName);
                        Item item = Item.findItem(request.getCommand().cardOrItemName);
                        if (card != null)
                        {
                            shopManager.buyCard((Card) card.clone());
                        }
                        else if (item != null)
                        {
                            shopManager.buyItem((Item) item.clone());
                        }
                        else
                        {
                            showOutput.printOutput("Card or Item doesn't exist in Shop");
                        }
                    } catch (CloneNotSupportedException ignored)
                    {

                    }
                    break;
                case SELL:
                    shopManager.detectIDToSell(request.getCommand().cardOrItemID);
                    break;
                case SHOW:
                    showOutput.showShopInfo();
                    break;
                case HELP:
                    showOutput.shopHelp();
                    break;
                case EXIT:
                    determineMainMenuCommand();
                    break;
            }
        }
    }

    private void determineCollectionCommand() throws InterruptedException
    {
        while (true)
        {
            request.getCollectionCommands();
            if (request.getCommand() == null)
            {
                continue;
            }
            switch (request.getCommand())
            {
                case SHOW:
                    showOutput.showCollectionInfo(Account.loggedInAccount.getCollection());
                    break;
                case SEARCH:
                    collectionManager.searchCollection(request.getCommand().cardOrItemName);
                    break;
                case SAVE:
                    //todo
                    break;
                case CREATE_DECK:
                    collectionManager.createDeck(request.getCommand().deckName);
                    break;
                case DELETE_DECK:
                    collectionManager.deleteDeck(request.getCommand().deckName);
                    break;
                case ADD_TO_DECK:
                    collectionManager.detectID(request.getCommand().cardOrItemID, request.getCommand().deckName, "add");
                    break;
                case REMOVE_FROM_DECK:
                    collectionManager.detectID(request.getCommand().cardOrItemID, request.getCommand().deckName, "remove");
                    break;
                case VALIDATE_DECK:
                    deckManager.checkDeckValidity(request.getCommand().deckName);
                    break;
                case SET_MAIN_DECK:
                    deckManager.setDeckAsMainDeck(request.getCommand().deckName);
                    break;
                case SHOW_DECK:
                    showOutput.showDeckInfo(request.getCommand().deckName);
                    break;
                case SHOW_ALL_DECKS:
                    showOutput.showAllDecksInfo();
                    break;
                case HELP:
                    showOutput.collectionHelp();
                    break;
                case EXIT:
                    determineMainMenuCommand();
                    break;
            }
        }
    }

    private void determineBattleMenuCommand()
    {
        if (Account.loggedInAccount.getMainDeck() != null)
        {
            while (true)
            {
                showOutput.showBattleMenuCommands();
                request.getBattleMenuCommands();
                if (request.getCommand() == null)
                {
                    continue;
                }
                switch (request.getCommand())
                {
                    case SINGLE_PLAYER:
                        selectSinglePlayerMatchMode();
                        break;
                    case MULTI_PLAYER:
                        selectSecondPlayerInMultiPlayerMatch();
                        break;
                    case EXIT:
                        return;
                }
            }
        }
        else
        {
            showOutput.printOutput("you don't have Valid mainDeck");
        }
    }

    private void selectSinglePlayerMatchMode()
    {
        showOutput.printOutput("Story");
        showOutput.printOutput("Custom Game");
        while (true)
        {
            request.getSinglePlayerMatchMode();
            if (request.getCommand() == null)
            {
                continue;
            }
            switch (request.getCommand())
            {
                case STORY:
                    showOutput.showStoryBattleInfo();
                    int numberOfLevel = request.getStoryMatchLevel();
                    while (numberOfLevel < 1 || numberOfLevel > 3)
                    {
                        showOutput.printOutput("Entered number is invalid");
                        numberOfLevel = request.getStoryMatchLevel();
                    }
                    Player opponentPlayerForStory = accountManager.makeStoryPlayer(numberOfLevel);
                    BattleType battleTypeStory = getBattleTypeStory(numberOfLevel);
                    new Battle(new Player(Account.loggedInAccount, false), opponentPlayerForStory, BattleMode.getBattleMode(numberOfLevel), battleTypeStory);
                    determineBattleCommand();
                    break;
                case CUSTOM_GAME:
                    showOutput.showCustomGameInfo();
                    request.getCustomGameCommands();
                    Player opponentPlayerForCustomGame = accountManager.makeCustomGamePlayer(request.getCommand().deckNameForCustomGame);
                    new Battle(new Player(Account.loggedInAccount, false), opponentPlayerForCustomGame, BattleMode.getBattleMode(request.getCommand().customGameMode), BattleType.CUSTOM_GAME);
                    determineBattleCommand();
                    break;
                case SHOW_MENU:
                    showOutput.showMenuSinglePlayerMatch();
                    break;
                case EXIT:
                    return;
            }
        }
    }

    private BattleType getBattleTypeStory(int storyGameMode)
    {
        switch (storyGameMode)
        {
            case 1:
                return BattleType.STORY_GAME_1;
            case 2:
                return BattleType.STORY_GAME_2;
            case 3:
                return BattleType.STORY_GAME_3;
        }
        return null;
    }

    private void selectSecondPlayerInMultiPlayerMatch()
    {
        while (true)
        {
            accountManager.showAllPlayers();
            request.getSecondPlayerInMultiPlayerMatch();
            if (request.getCommand() == null)
            {
                continue;
            }
            switch (request.getCommand())
            {
                case SELECT_USER:
                    Player firstPlayer = new Player(Account.loggedInAccount, false);
                    Player secondPlayer = battleManager.selectSecondPlayer(request.getCommand().username);
                    if (secondPlayer != null)
                    {
                        selectMultiPlayerMatchMode(firstPlayer, secondPlayer);
                    }
                    break;
                case SHOW_MENU:
                    showOutput.showMenuSelectUserForMultiPlayerMatch();
                    break;
                case EXIT:
                    return;
            }
        }
    }

    private void selectMultiPlayerMatchMode(Player firstPlayer, Player secondPlayer)
    {
        while (true)
        {
            showOutput.showBattleModes();
            request.getMultiPlayerMatchMode();
            if (request.getCommand() == null)
            {
                continue;
            }
            switch (request.getCommand())
            {
                case START_MULTI_PLAYER_GAME:

                    Battle battle = null;
                    if (request.getCommand().multiPlayerMatchMode.equalsIgnoreCase("KillingEnemyHero"))
                    {
                        battle = new Battle(firstPlayer, secondPlayer, BattleMode.KILLING_ENEMY_HERO, BattleType.MULTI_PLAYER_GAME);

                    }
                    else if (request.getCommand().multiPlayerMatchMode.equalsIgnoreCase("KeepFlagFor6Turns"))
                    {
                        battle = new Battle(firstPlayer, secondPlayer, BattleMode.KEEP_FLAG_FOR_6_TURNS, BattleType.MULTI_PLAYER_GAME);
                    }
                    else if (request.getCommand().multiPlayerMatchMode.equalsIgnoreCase("GatheringFlags"))
                    {
                        battle = new Battle(firstPlayer, secondPlayer, BattleMode.GATHERING_FLAGS, BattleType.MULTI_PLAYER_GAME);
                        Battle.getCurrentBattle().setNumOfFlagsInGatheringFlagsMatchMode(request.getCommand().numOfFlags);
                    }
                    if (battle != null)
                    {
                        showOutput.printOutput("Let's Fight");
                        determineBattleCommand();
                    }
                    break;
                case SHOW_MENU:
                    showOutput.showMenuMultiPlayerMatchMode();
                    break;
                case EXIT:
                    return;
            }
        }
    }

    private void determineBattleCommand()
    {
        Battle.getCurrentBattle().setHeroesInBattlefield();
        while (true)
        {
            if (Battle.getCurrentBattle().getPlayerTurn().isAIPlayer())
            {
                Battle.getCurrentBattle().AIPlayerWorks(battleManager);
                continue;
            }
            request.getBattleCommands();
            if (request.getCommand() == null)
            {
                continue;
            }
            if (Battle.getCurrentBattle().isGameEnded(request.getCommand().storyGameMode))
            {
                determineAfterGameEndedCommand();
            }
            switch (request.getCommand())
            {
                case GAME_INFO:
                    showOutput.showGameInfo();
                    break;
                case SHOW_MY_MINIONS:
                    showOutput.showMyMinions();
                    break;
                case SHOW_OPPONENT_MINIONS:
                    showOutput.showOpponentMinions();
                    break;
                case SHOW_CARD_INFO:
                    showOutput.showCardInfo(request.getCommand().cardOrItemID);
                    break;
                case SELECT:
                    battleManager.selectCard(request.getCommand().cardOrItemID);
                    determineAfterSelectCardCommand();
                    break;
                case SHOW_HAND:
                    showOutput.showHand(Battle.getCurrentBattle().getPlayerTurn().getHand());
                    break;
                case INSERT_CARD:
                    battleManager.checkCircumstancesToInsertCard(request.getCommand().insertCardName, request.getCommand().insertRow, request.getCommand().insertColumn);
                    break;
                case SHOW_COLLECTIBLES:
                    showOutput.showCollectibleItems();
                    break;
                case SELECT_ITEM:
                    battleManager.selectItem(request.getCommand().cardOrItemID);
                    determineAfterSelectItemCommand();
                    break;
                case SHOW_NEXT_CARD:
                    showOutput.showNextCardInfo();
                    break;
                case ENTER_GRAVEYARD:
                    determineGraveYardCommand();
                    break;
                case END_TURN:
                    Battle.getCurrentBattle().endTurn();
                    showOutput.printOutput(Battle.getCurrentBattle().getPlayerTurn().getAccount().getAccountName() + " turn");
                    break;
                case SURRENDER:
                    Battle.getCurrentBattle().tasksWhenSurrender();
                    break;
                case HELP_BATTLE:
                    Battle.getCurrentBattle().help();
                    break;
                case SHOW_MENU:
                    showOutput.showMenuBattle();
                    break;
                case EXIT:
                    return;
            }
        }
    }

    private void determineAfterSelectCardCommand()
    {
        while (true)
        {
            request.getAfterSelectCardCommands();
            if (request.getCommand() == null)
            {
                continue;
            }
            switch (request.getCommand())
            {
                case MOVE_TO:
                    battleManager.moveCard(request.getCommand().rowOfTheCell, request.getCommand().columnOfTheCell);
                    break;
                case NORMAL_ATTACK:
                    battleManager.attackToOpponent(request.getCommand().enemyCardIDForNormalAttack);
                    break;
                case COMBO_ATTACK:
                    Battle.getCurrentBattle().comboAttack(request.getCommand().enemyCardIDForCombo, request.getCommand().cardIDsForComboAttack);
                    break;
                case USE_SPECIAL_POWER:
                    Battle.getCurrentBattle().getSelectedCard().getSpecialPower().applySpecialPower(request.getCommand().rowOfTheCell, request.getCommand().columnOfTheCell);
                    battleManager.useSpecialPower(Battle.getCurrentBattle().getSelectedCard().getSpecialPower(), request.getCommand().rowOfTheCell, request.getCommand().columnOfTheCell);
                    break;
                case SHOW_MENU:
                    showOutput.showMenuAfterSelectCard();
                    break;
                case EXIT:
                    showOutput.printOutput("exited");
                    return;
            }

        }
    }

    private void determineAfterSelectItemCommand()
    {
        while (true)
        {
            request.getAfterSelectItemCommands();
            if (request.getCommand() == null)
            {
                continue;
            }
            switch (request.getCommand())
            {
                case SHOW_ITEM_INFO:
                    showOutput.printItemStats(Battle.getCurrentBattle().getSelectedICollectibleItem());
                    break;
                case USE_ITEM:
                    int x = request.getCommand().rowOfTheCell;
                    int y = request.getCommand().columnOfTheCell;
                    Battle.getCurrentBattle().getSelectedICollectibleItem().applyCollectibleItem(x, y);
                    Battle.getCurrentBattle().checkUsedItemsToApplyItemChange();
                    break;
                case SHOW_MENU:
                    showOutput.showMenuAfterSelectItem();
                    break;
                case EXIT:
                    return;
            }
        }
    }

    private void determineGraveYardCommand()
    {
        while (true)
        {
            request.getGraveYardCommands();
            if (request.getCommand() == null)
            {
                continue;
            }
            switch (request.getCommand())
            {
                case SHOW_INFO:
                    showOutput.showGraveYardCardInfo(request.getCommand().cardOrItemIDInGraveYard);
                    break;
                case SHOW_MENU:
                    showOutput.showMenuGraveYard();
                    break;
                case SHOW_CARDS:
                    showOutput.showAllCardsInTheGraveYard();
                    break;
                case EXIT:
                    return;
            }
        }
    }

    private void determineAfterGameEndedCommand()
    {
        while (true)
        {
            request.getAfterGameEndedCommand();
            if (request.getCommand() == null)
            {
                continue;
            }
            if (request.getCommand() == CommandType.END_GAME)
            {
                Battle.getCurrentBattle().tasksAtEndOfGame();
            }
        }
    }
}