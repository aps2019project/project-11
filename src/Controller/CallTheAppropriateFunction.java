package Controller;

import Model.*;
import Network.Client;
import Network.ClientCommand;
import Network.ClientCommandEnum;
import Network.ServerCommandEnum;
import View.*;
import com.google.gson.GsonBuilder;

public class CallTheAppropriateFunction extends Thread
{
    private AccountManager accountManager = new AccountManager();
    private Request request;
    private ShowOutput showOutput = ShowOutput.getInstance();


    public CallTheAppropriateFunction(Request request)
    {
        this.request = request;
    }

    @Override
    public void run()
    {
        try
        {
            determineMainMenuCommand();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void determineMainMenuCommand() throws Exception
    {
        showOutput.printMainMenuCommands();
        while (true)
        {
            if (request.getCommand() == null)
            {
                synchronized (request.requestLock)
                {
                    request.requestLock.wait();
                }
            }
            switch (request.getCommand())
            {
                case ENTER_SHOP:
                    request.setCommand(null);
                    determineShopCommand();
                    break;
                case ENTER_COLLECTION:
                    request.setCommand(null);
                    determineCollectionCommand();
                    break;
                case ENTER_BATTLE:
                    request.setCommand(null);
                    determineBattleMenuCommand();
                    break;
                case CHAT:
                    synchronized (request.requestLock)
                    {
                        request.requestLock.wait();
                    }
                    request.setCommand(null);
                    break;
                case SHOW_LEADER_BOARD:
                    accountManager.sortAccountsByWins();
                    synchronized (request.requestLock)
                    {
                        request.requestLock.wait();
                    }
                    request.setCommand(null);
                    break;
                case SHOW_PROFILE:
                    synchronized (request.requestLock)
                    {
                        request.requestLock.wait();
                    }
                    request.setCommand(null);
                    break;
                case CUSTOMCARDS:
                    synchronized (request.requestLock)
                    {
                        request.requestLock.wait();
                    }
                    request.setCommand(null);
                    break;
                case HELP:
                    showOutput.printMainMenuCommands();
                    break;
                case EXIT:
                    System.exit(0);
                    break;
            }
            request.setCommand(null);
        }
    }

    private void determineShopCommand() throws Exception
    {
        while (true)
        {
            if (request.getCommand() == null)
            {
                synchronized (request.requestLock)
                {
                    request.requestLock.wait();
                }
            }
            switch (request.getCommand())
            {
                case SHOW_COLLECTION:
                    request.setCommand(null);
                    determineCollectionCommand();
                    break;
                case EXIT:
                    request.setCommand(null);
                    determineMainMenuCommand();
                    break;
            }
            request.setCommand(null);
        }
    }

    private void determineCollectionCommand() throws Exception
    {
        while (true)
        {
            if (request.getCommand() == null)
            {
                synchronized (request.requestLock)
                {
                    request.requestLock.wait();
                }
            }
            if (request.getCommand() == CommandType.EXIT)
            {
                request.setCommand(null);
                determineMainMenuCommand();
            }
            request.setCommand(null);
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

    private void determineBattleMenuCommand() throws Exception
    {
        showOutput.showBattleMenuCommands();
        if (request.getAccountConnectedToThisClient().getMainDeck() != null)
        {
            while (true)
            {
                if (request.getCommand() == null)
                {
                    synchronized (request.requestLock)
                    {
                        request.requestLock.wait();
                    }
                }
                switch (request.getCommand())
                {
                    case SINGLE_PLAYER:
                        request.setCommand(null);
                        selectSinglePlayerMatchMode();
                        break;
                    case MULTI_PLAYER:
                        request.setCommand(null);
                        selectSecondPlayerInMultiPlayerMatch();
                        break;
                    case EXIT:
                        request.setCommand(null);
                        determineMainMenuCommand();
                        return;
                }
                request.setCommand(null);
            }
        }
        else
        {
            showOutput.printOutput("you don't have Valid mainDeck");
        }
    }

    private void selectSinglePlayerMatchMode() throws Exception
    {
        showOutput.printOutput("Story");
        showOutput.printOutput("Custom Game");
        while (true)
        {
            if (request.getCommand() == null)
            {
                synchronized (request.requestLock)
                {
                    request.requestLock.wait();
                }
            }
            switch (request.getCommand())
            {
                case STORY:
                    showOutput.showStoryBattleInfo();
                    synchronized (request.requestLock)
                    {
                        request.requestLock.wait();
                    }
                    request.setCommand(null);
                    selectSinglePlayerMatchMode();
                    break;
                case CUSTOM_GAME:
                    showOutput.showCustomGameInfo();
                    synchronized (request.requestLock)
                    {
                        request.requestLock.wait();
                    }
                    request.setCommand(null);
                    selectSinglePlayerMatchMode();
                    break;
                case EXIT:
                    request.setCommand(null);
                    determineBattleMenuCommand();
                    break;
                case END_GAME:
                    request.setCommand(null);
                    determineMainMenuCommand();
                    break;
            }
            request.setCommand(null);
        }
    }

    private void selectSecondPlayerInMultiPlayerMatch() throws Exception
    {
        accountManager.showAllPlayers();
        while (true)
        {
            if (request.getCommand() == null)
            {
                synchronized (request.requestLock)
                {
                    request.requestLock.wait();
                }
            }
            switch (request.getCommand())
            {
                case EXIT:
                    request.setCommand(null);
                    determineBattleMenuCommand();
                    break;
                case END_GAME:
                    request.setCommand(null);
                    determineMainMenuCommand();
                    break;
            }
            request.setCommand(null);
        }
    }

    public Battle storyModeBattleMaker(Account loggedInAccount, int numberOfLevel, Client client)
    {
        BattleType battleTypeStory = getBattleTypeStory(numberOfLevel);
        switch (numberOfLevel)
        {
            case 1:
                ClientCommand clientCommand1 = new ClientCommand(ClientCommandEnum.GET_STORY_PLAYER_1);
                String getStoryPlayer1 = new GsonBuilder().setPrettyPrinting().create().toJson(clientCommand1);
                try
                {
                    Client.getSendMessage().addMessage(getStoryPlayer1);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                synchronized (client.getRequest().validMessageFromServer)
                {
                    try
                    {
                        client.getRequest().validMessageFromServer.wait();
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                if (client.getMessageFromServer().getServerCommandEnum().equals(ServerCommandEnum.OK))
                {
                    return new Battle(new Player(loggedInAccount, false), client.getMessageFromServer().getPlayer(), BattleMode.getBattleMode(numberOfLevel), battleTypeStory);
                }
                break;
            case 2:
                ClientCommand clientCommand2 = new ClientCommand(ClientCommandEnum.GET_STORY_PLAYER_2);
                String getStoryPlayer2 = new GsonBuilder().setPrettyPrinting().create().toJson(clientCommand2);
                try
                {
                    Client.getSendMessage().addMessage(getStoryPlayer2);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                synchronized (client.getRequest().validMessageFromServer)
                {
                    try
                    {
                        client.getRequest().validMessageFromServer.wait();
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                if (client.getMessageFromServer().getServerCommandEnum().equals(ServerCommandEnum.OK))
                {
                    return new Battle(new Player(loggedInAccount, false), client.getMessageFromServer().getPlayer(), BattleMode.getBattleMode(numberOfLevel), battleTypeStory);
                }
                break;
            case 3:
                ClientCommand clientCommand3 = new ClientCommand(ClientCommandEnum.GET_STORY_PLAYER_3);
                String getStoryPlayer3 = new GsonBuilder().setPrettyPrinting().create().toJson(clientCommand3);
                try
                {
                    Client.getSendMessage().addMessage(getStoryPlayer3);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                synchronized (client.getRequest().validMessageFromServer)
                {
                    try
                    {
                        client.getRequest().validMessageFromServer.wait();
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                if (client.getMessageFromServer().getServerCommandEnum().equals(ServerCommandEnum.OK))
                {
                    return new Battle(new Player(loggedInAccount, false), client.getMessageFromServer().getPlayer(), BattleMode.getBattleMode(numberOfLevel), battleTypeStory);
                }
                break;
        }
        return null;
    }

    public Battle customGameBattleMaker(Account accountConnectedToThisClient, Deck selectedDeck, int number)
    {
        Player opponentPlayerForCustomGame = accountManager.makeCustomGamePlayer(selectedDeck.getDeckName(), accountConnectedToThisClient);

        if (opponentPlayerForCustomGame != null)
        {
            return new Battle(new Player(accountConnectedToThisClient, false), opponentPlayerForCustomGame, BattleMode.getBattleMode(number), BattleType.CUSTOM_GAME);
        }
        return null;
    }
}