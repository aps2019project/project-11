package View;

import Model.CommandType;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Request
{
    public static Scanner myScanner = new Scanner(System.in);

    private final static Pattern patternSearch = Pattern.compile("search [a-zA-Z_0-9]+");
    private final static Pattern patternCreateDeck = Pattern.compile("create deck [a-zA-Z_0-9]+");
    private final static Pattern patternDeleteDeck = Pattern.compile("delete deck [a-zA-Z_0-9]+");
    private final static Pattern patternAddCardToDeck = Pattern.compile("add [a-zA-Z_0-9]+ to deck [a-zA-Z_0-9]+");
    private final static Pattern patternRemoveCardFromDeck = Pattern.compile("remove [a-zA-Z_0-9]+ from deck [a-zA-Z_0-9]+");
    private final static Pattern patternValidateDeck = Pattern.compile("validate deck [a-zA-Z_0-9]+");
    private final static Pattern patternSelectMainDeck = Pattern.compile("select deck [a-zA-Z_0-9]+");
    private final static Pattern patternShowDeck = Pattern.compile("show deck [a-zA-Z_0-9]+");
    private final static Pattern patternCreateAccount = Pattern.compile("create account [a-zA-Z_0-9]+");
    private final static Pattern patternAccountLogin = Pattern.compile("login [a-zA-Z_0-9]+");
    private final static Pattern patternShopSearchCollection = Pattern.compile("search collection [a-zA-Z_0-9]+");
    private final static Pattern patternShopBuy = Pattern.compile("buy [a-zA-Z_0-9]+");
    private final static Pattern patternShopSell = Pattern.compile("sell [a-zA-Z_0-9]+");
    private final static Pattern patternShowInfoOfCardInGraveYard = Pattern.compile("Show info [a-zA-Z_0-9]+");
    private final static Pattern patternShowCardInfo = Pattern.compile("Show info [a-zA-Z_0-9]+");
    private final static Pattern patternSelect = Pattern.compile("Select (.)+");
    // private final static Pattern patternMoveTo = Pattern.compile("Move To ([0-9]+ [,] [0-9]+)");
    private final static Pattern patternSelectUser = Pattern.compile("Select User [a-zA-Z_0-9]+");
    private final static Pattern patternStartMultiPlayerGame = Pattern.compile("Start MultiPlayer Game [a-zA-Z_0-9]+[\\t\\f\\r]?[0-9]*");
    private final static Pattern patternComboAttack = Pattern.compile("Attack combo (([a-zA-Z_0-9]+)(\\s))+");
    private final static Pattern patternSelectItem = Pattern.compile("Select [a-zA-Z_0-9]+");
    private final static Pattern patternUseItem = Pattern.compile("Use [0-9]+ [0-9]+");
    private final static Pattern patternNormalAttack = Pattern.compile("Attack [a-zA-Z_0-9]+");
    private final static Pattern patternUseSpecialPower = Pattern.compile("Use special power( [0-9]+ [0-9]+ )");
    private final static Pattern patternInsertCard = Pattern.compile("Insert [a-zA-Z 0-9]+ in ((\\() [0-9]+ [,] [0-9]+ (\\)))");

    public static CommandType command;

    public static void getMainMenuCommands()
    {
        String input = myScanner.nextLine();
        if (input.equalsIgnoreCase("Enter Shop"))
        {
            command = CommandType.ENTER_SHOP;
        }
        else if (input.equalsIgnoreCase("Enter Collection"))
        {
            command = CommandType.ENTER_COLLECTION;
        }
        else if (input.equalsIgnoreCase("Enter Battle"))
        {
            command = CommandType.ENTER_BATTLE;
        }
        else if (input.equalsIgnoreCase("save"))
        {
            command = CommandType.SAVE;
        }
        else if (input.equalsIgnoreCase("logout"))
        {
            command = CommandType.LOGOUT;
        }
        else if (input.equalsIgnoreCase("Help"))
        {
            command = CommandType.HELP;
        }
        else if (input.equalsIgnoreCase("Exit"))
        {
            command = CommandType.EXIT;
        }
        else
        {
            ShowOutput.printOutput("invalid command");
            command = null;
        }
    }

    public static void getSpellMakingCommand()
    {
        //phase 2
    }

    public static void getAccountCommands()
    {
        String input = myScanner.nextLine();
        String[] separatedInput = input.split(" ");
        if (input.equalsIgnoreCase("Exit"))
        {
            command = CommandType.EXIT;
        }
        else if (patternCreateAccount.matcher(input).matches())
        {
            command = CommandType.CREATE_ACCOUNT;
            command.username = separatedInput[2];
        }
        else if (patternAccountLogin.matcher(input).matches())
        {
            command = CommandType.LOGIN;
            command.username = separatedInput[1];
        }
        else if (input.equalsIgnoreCase("show leaderBoard"))
        {
            command = CommandType.SHOW_LEADER_BOARD;
        }
        else if (input.equalsIgnoreCase("help"))
        {
            command = CommandType.HELP;
        }
        else
        {
            ShowOutput.printOutput("invalid command");
            command = null;
        }
    }

    public static void getShopCommands()
    {
        String input = myScanner.nextLine();
        String[] partedInput = input.split("\\s");
        if (input.equals("exit"))
        {
            command = CommandType.EXIT;
        }
        else if (input.equals("show collection"))
        {
            command = CommandType.SHOW_COLLECTION;
        }
        else if (patternSearch.matcher(input).matches())
        {
            command = CommandType.SEARCH;
            command.cardOrItemName = partedInput[1];
        }
        else if (patternShopSearchCollection.matcher(input).matches())
        {
            command = CommandType.SEARCH_COLLECTION;
            command.cardOrItemName = partedInput[2];
        }
        else if (patternShopBuy.matcher(input).matches())
        {
            command = CommandType.BUY;
            command.cardOrItemName = partedInput[1];
        }
        else if (patternShopSell.matcher(input).matches())
        {
            command = CommandType.SELL;
            command.cardOrItemID = partedInput[1];
        }
        else if (input.equals("show"))
        {
            command = CommandType.SHOW;
        }
        else if (input.equals("help"))
        {
            command = CommandType.HELP;
        }
        else
        {
            ShowOutput.printOutput("invalid command");
            command = null;
        }
    }

    public static void getCollectionCommands()
    {
        String input = myScanner.nextLine();
        String[] inputParts = input.split(" ");
        if (input.equals("show"))
        {
            command = CommandType.SHOW;
        }
        else if (patternSearch.matcher(input).matches())
        {
            command = CommandType.SEARCH;
            command.cardOrItemName = inputParts[1];
        }
        else if (input.equals("save"))
        {
            command = CommandType.SAVE;
        }
        else if (patternCreateDeck.matcher(input).matches())
        {
            command = CommandType.CREATE_DECK;
            command.deckName = inputParts[2];
        }
        else if (patternDeleteDeck.matcher(input).matches())
        {
            command = CommandType.DELETE_DECK;
            command.deckName = inputParts[2];
        }
        else if (patternAddCardToDeck.matcher(input).matches())
        {
            command = CommandType.ADD_TO_DECK;
            command.deckName = inputParts[4];
            command.cardOrItemID = inputParts[1];
        }
        else if (patternRemoveCardFromDeck.matcher(input).matches())
        {
            command = CommandType.REMOVE_FROM_DECK;
            command.deckName = inputParts[4];
            command.cardOrItemID = inputParts[1];
        }
        else if (patternValidateDeck.matcher(input).matches())
        {
            command = CommandType.VALIDATE_DECK;
            command.deckName = inputParts[2];
        }
        else if (patternSelectMainDeck.matcher(input).matches())
        {
            command = CommandType.SET_MAIN_DECK;
            command.deckName = inputParts[2];
        }
        else if (patternShowDeck.matcher(input).matches())
        {
            command = CommandType.SHOW_DECK;
            command.deckName = inputParts[2];
        }
        else if (input.equals("show all decks"))
        {
            command = CommandType.SHOW_ALL_DECKS;
        }
        else if (input.equals("help"))
        {
            command = CommandType.HELP;
        }
        else if (input.equals("exit"))
        {
            command = CommandType.EXIT;
        }
        else
        {
            ShowOutput.printOutput("invalid command");
            command = null;
        }
    }

    public static void getBattleMenuCommands()
    {
        String input = myScanner.nextLine();
        if (input.equalsIgnoreCase("Single Player"))
        {
            command = CommandType.SINGLE_PLAYER;
        }
        else if (input.equalsIgnoreCase("Multi Player"))
        {
            command = CommandType.MULTI_PLAYER;
        }
        else if (input.equals("exit"))
        {
            command = CommandType.EXIT;
        }
        else
        {
            ShowOutput.printOutput("invalid command");
            command = null;
        }
    }

    public static void getSinglePlayerMatchMode()
    {
        String input = myScanner.nextLine();
        String[] inputParts = input.split(" ");
        if (input.equalsIgnoreCase("Story"))
        {
            command = CommandType.STORY;
        }
        else if (input.equalsIgnoreCase("Custom Game"))
        {
            command = CommandType.CUSTOM_GAME;
            String line = myScanner.nextLine();
            String[] partedLine = line.split(" ");
            command.deckNameForCustomGame = partedLine[2];
            command.customGameMode = Integer.parseInt(partedLine[3]);
            if (partedLine.length == 5)    ///I have doubt about it
            {
                command.customGameFlagNumber = Integer.parseInt(partedLine[4]);
            }
        }
        else
        {
            getShowMenuAndExitCommand(input);
        }
    }

    public static void getSecondPlayerInMultiPlayerMatch()
    {
        String input = myScanner.nextLine();
        String[] inputParts = input.split(" ");
        if (patternSelectUser.matcher(input).matches())
        {
            command = CommandType.SELECT_USER;
            Request.command.username = inputParts[2];
        }
        else
        {
            getShowMenuAndExitCommand(input);
        }
    }

    public static void getMultiPlayerMatchMode()
    {
        String input = myScanner.nextLine();
        String[] inputParts = input.split(" ");
        if (patternStartMultiPlayerGame.matcher(input).matches())
        {
            command = CommandType.START_MULTI_PLAYER_GAME;
            Request.command.multiPlayerMatchMode = inputParts[3];
            if (inputParts.length > 4)
            {
                Request.command.numOfFlags = Integer.parseInt(inputParts[4]);
            }
        }
        else
        {
            getShowMenuAndExitCommand(input);
        }
    }

    public static void getBattleCommands()
    {
        String input = myScanner.nextLine();
        String[] inputParts = input.split(" ");
        if (input.equalsIgnoreCase("Game Info"))
        {
            command = CommandType.GAME_INFO;
        }
        else if (input.equalsIgnoreCase("Show My Minions"))
        {
            command = CommandType.SHOW_MY_MINIONS;
        }
        else if (input.equalsIgnoreCase("Show Opponent Minions"))
        {
            command = CommandType.SHOW_OPPONENT_MINIONS;
        }
        else if (patternShowCardInfo.matcher(input).matches())
        {
            command = CommandType.SHOW_CARD_INFO;
            command.cardOrItemID = inputParts[3];
        }
        else if (patternSelect.matcher(input).matches())
        {
            command = CommandType.SELECT;
            command.cardOrItemID = inputParts[1];
        }
        else if (input.equalsIgnoreCase("Show collectibles"))
        {
            command = CommandType.SHOW_COLLECTIBLES;
        }
        else if (patternSelectItem.matcher(input).matches())
        {
            command = CommandType.SELECT_ITEM;
            Request.command.cardOrItemID = inputParts[1];
        }
        else if (patternInsertCard.matcher(input).matches())
        {
            command = CommandType.INSERT_CARD;
            command.insertRow = Integer.parseInt(inputParts[4]);
            command.insertColumn = Integer.parseInt(inputParts[6]);
            command.insertCardName = inputParts[1];

        }
        else if (input.equalsIgnoreCase("Show Hand"))
        {
            command = CommandType.SHOW_HAND;
        }
        else if (input.equalsIgnoreCase("Show Next Card"))
        {
            command = CommandType.SHOW_NEXT_CARD;
        }
        else if (input.equalsIgnoreCase("Enter graveyard"))
        {
            command = CommandType.ENTER_GRAVEYARD;
        }
        else if (input.equalsIgnoreCase("Help"))
        {
            command = CommandType.HELP_BATTLE;
        }
        else if (input.equalsIgnoreCase("End Turn"))
        {
            command = CommandType.END_TURN;
        }
        else if (input.equalsIgnoreCase("Surrender"))
        {
            command = CommandType.SURRENDER;
        }
        else
        {
            getShowMenuAndExitCommand(input);
        }
    }

    public static void getAfterSelectCardCommands()
    {
        String input = myScanner.nextLine();
        String[] inputParts = input.split(" ");
        if (patternNormalAttack.matcher(input).matches())
        {
            command = CommandType.NORMAL_ATTACK;
            command.enemyCardIDForNormalAttack = inputParts[1];
        }
        else if (input.contains("Move To"))
        {
            command = CommandType.MOVE_TO;
            command.rowOfTheCell = Integer.parseInt(inputParts[2]);
            command.columnOfTheCell = Integer.parseInt(inputParts[4]);
        }
        else if (patternUseSpecialPower.matcher(input).matches())
        {
            command = CommandType.USE_SPECIAL_POWER;
            command.rowOfTheCell = Integer.parseInt(inputParts[3]);
            command.columnOfTheCell = Integer.parseInt(inputParts[4]);
        }
        else if (patternComboAttack.matcher(input).matches())
        {
            command = CommandType.COMBO_ATTACK;
            command.enemyCardIDForCombo = inputParts[2];
            for (int counter = 3; counter < inputParts.length; counter++)
            {
                command.cardIDsForComboAttack.add(inputParts[counter]);
            }
        }
        else
        {
            getShowMenuAndExitCommand(input);
        }
    }

    public static void getAfterSelectItemCommands()
    {
        String input = myScanner.nextLine();
        String[] inputParts = input.split(" ");
        if (input.equalsIgnoreCase("Show Info"))
        {
            command = CommandType.SHOW_ITEM_INFO;
        }
        else if (patternUseItem.matcher(input).matches())
        {
            command = CommandType.USE_ITEM;
            Request.command.rowOfTheCell = Integer.parseInt(inputParts[1]);
            Request.command.columnOfTheCell = Integer.parseInt(inputParts[2]);
        }
        else
        {
            getShowMenuAndExitCommand(input);
        }
    }

    public static void getGraveYardCommands()
    {
        String input = myScanner.nextLine();
        String[] inputParts = input.split(" ");
        if (patternShowInfoOfCardInGraveYard.matcher(input).matches())
        {
            command = CommandType.SHOW_INFO;
            command.cardOrItemIDInGraveYard = inputParts[2];
        }
        else if (input.equalsIgnoreCase("Show cards"))
        {
            command = CommandType.SHOW_CARDS;
        }
        else
        {
            getShowMenuAndExitCommand(input);
        }
    }

    public static void getAfterGameEndedCommand()
    {
        String input = myScanner.nextLine();
        if (input.equalsIgnoreCase("End Game"))
        {
            command = CommandType.END_GAME;
        }
        else
        {
            ShowOutput.printOutput("invalid command");
            command = null;
        }
    }

    public static void getShowMenuAndExitCommand(String input)
    {
        if (input.equalsIgnoreCase("Show menu"))
        {
            command = CommandType.SHOW_MENU;
        }
        else if (input.equals("exit"))
        {
            command = CommandType.EXIT;
        }
        else
        {
            ShowOutput.printOutput("invalid command");
            command = null;
        }
    }

    public static String getPassword()
    {
        return myScanner.nextLine();
    }

    public static int getStoryMatchLevel()
    {
        try
        {
            ShowOutput.printOutput("Enter Level number");
            String input = myScanner.nextLine();
            Request.command.storyGameMode = Integer.parseInt(input);
        } catch (NumberFormatException e)
        {
            ShowOutput.printOutput("Try Again");
            getStoryMatchLevel();
        }
        return Request.command.storyGameMode;
    }
}
