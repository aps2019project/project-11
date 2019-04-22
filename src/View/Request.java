package View;

import Model.CommandType;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Request
{
    public static Scanner myScanner = new Scanner(System.in);

    private static Pattern patternSearch = Pattern.compile("search [a-zA-Z_0-9]+");
    private static Pattern patternCreateDeck = Pattern.compile("create deck [a-zA-Z_0-9]+");
    private static Pattern patternDeleteDeck = Pattern.compile("delete deck [a-zA-Z_0-9]+");
    private static Pattern patternAddCardToDeck = Pattern.compile("add //d+ to deck [a-zA-Z_0-9]+");
    private static Pattern patternRemoveCardFromDeck = Pattern.compile("remove //d+ from deck [a-zA-Z_0-9]+");
    private static Pattern patternValidateDeck = Pattern.compile("validate deck [a-zA-Z_0-9]+");
    private static Pattern patternSelectMainDeck = Pattern.compile("select deck [a-zA-Z_0-9]+");
    private static Pattern patternShowDeck = Pattern.compile("show deck [a-zA-Z_0-9]+");
    private static Pattern patternCreateAccount = Pattern.compile("create account [a-zA-Z_0-9]+");
    private static Pattern patternAccountLogin = Pattern.compile("login [a-zA-Z_0-9]+");
    private static Pattern patternShopSearchCollection = Pattern.compile("search collection [a-zA-Z_0-9]+");
    private static Pattern patternShopBuy = Pattern.compile("buy [a-zA-Z_0-9]+");
    private static Pattern patternShopSell = Pattern.compile("sell [a-zA-Z_0-9]+");

    public static CommandType command;

    public static void getMainMenuCommands()
    {
        String input = myScanner.nextLine();
        if(input.equalsIgnoreCase("Enter Shop"))
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
    }

    public static void getShopCommands()
    {
        String input = myScanner.nextLine();
        String[] partedInput = input.split("\\s");
        if (input.equals("exit"))
        {
            command = CommandType.EXIT;
        }
        else if(input.equals("show collection"))
        {
            command = CommandType.SHOW_COLLECTION;
        }
        else if(patternSearch.matcher(input).matches())
        {
            command = CommandType.SEARCH;
            command.cardOrItemName = partedInput[1];
        }
        else if(patternShopSearchCollection.matcher(input).matches())
        {
            command = CommandType.SEARCH_COLLECTION;
            command.cardOrItemName = partedInput[1];
        }
        else if(patternShopBuy.matcher(input).matches())
        {
            command = CommandType.BUY;
        }
        else if(patternShopSell.matcher(input).matches())
        {
            command = CommandType.SELL;
            command.cardOrItemID = Integer.parseInt(partedInput[1]);
        }
        else if(input.equals("show"))
        {
            command = CommandType.SHOW;
        }
        else if(input.equals("help"))
        {
            command = CommandType.HELP;
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
            command.cardOrItemID = Integer.parseInt(inputParts[1]);
        }
        else if (patternRemoveCardFromDeck.matcher(input).matches())
        {
            command = CommandType.REMOVE_FROM_DECK;
            command.deckName = inputParts[4];
            command.cardOrItemID = Integer.parseInt(inputParts[1]);
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
    }

    public static void getBattleCommands()
    {

    }

    public static void getAfterSelectCardCommands()
    {

    }

    public static void getGraveYardCommands()
    {

    }

    public static String getPassword()
    {
        return myScanner.nextLine();
    }
}
