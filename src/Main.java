import java.util.Scanner;
import java.util.regex.Pattern;

public class Main
{
    private static Pattern patternSearch = Pattern.compile("search //w+");
    private static Pattern patternCreateDeck = Pattern.compile("create deck //w+");
    private static Pattern patternDeleteDeck = Pattern.compile("delete deck //w+");
    private static Pattern patternAddCardToDeck = Pattern.compile("add //d+ to deck //w+");
    private static Pattern patternRemoveCardFromDeck = Pattern.compile("remove //d+ from deck //w+");
    private static Pattern patternValidateDeck = Pattern.compile("validate deck //w+");
    private static Pattern patternSelectMainDeck = Pattern.compile("select deck //w+");
    private static Pattern patternShowDeck = Pattern.compile("show deck //w+");
    private static Pattern patternCreateAccount = Pattern.compile("create account //w+");
    private static Pattern patternAccountLogin = Pattern.compile("login //w+");
    private static Pattern patternShopSearchCollection = Pattern.compile("search collection //w+");
    private static Pattern patternShopBuy = Pattern.compile("buy //w+");
    private static Pattern patternShopSell = Pattern.compile("sell //w+");

    static Scanner myScanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        Card.setCards();
        Item.setItems();
        Account.setAIAccounts();
        accountCommands();
        showCommandLine();
    }

    private static void showCommandLine()
    {
        System.out.println("Collection");
        System.out.println("Shop");
        System.out.println("Battle");
        System.out.println("Exit");
        System.out.println("Help");
        String command = myScanner.nextLine();
        while(!command.equalsIgnoreCase("Exit"))
        {
            if(command.equalsIgnoreCase("Enter Shop"))
            {
                shopCommands();
            }
            else if (command.equalsIgnoreCase("Enter Collection"))
            {
                collectionCommands();
            }
            else if (command.equalsIgnoreCase("Enter Battle"))
            {
                battleCommands();
            }
            else if (command.equalsIgnoreCase("Help"))
            {
                showCommandLine();
            }
            command = myScanner.nextLine();
        }
        System.exit(0);
    }

    public static void accountCommands()
    {
        String input = myScanner.nextLine();
        while (true)
        {
            String [] separatedInput = input.split(" ");
            if (patternCreateAccount.matcher(input).matches())
            {
                new Account(separatedInput[2]);
            }
            else if (patternAccountLogin.matcher(input).matches())
            {
                Account.login(separatedInput[1]);
                return;
            }
            else if (input.equalsIgnoreCase("show leaderBoard"))
            {
                Account.sortAccountsByWins();
                Account.showLeaderBoard();
            }
            else if (input.equalsIgnoreCase("save"))
            {
                //todo
            }
            else if (input.equalsIgnoreCase("logout"))
            {
                Account.logout();
            }
            else if (input.equalsIgnoreCase("help"))
            {
                Account.help();
            }
        }
    }

    public static void shopCommands() {
        String command;
        String[] partedCommand;
        while (true)
        {
            command = myScanner.nextLine();
            partedCommand = command.split("\\s");
            if (command.equals("exit"))
            {
                return;
            }
            else if(command.equals("show collection"))
            {
                Shop.shop.showCollection();
            }
            else if(patternSearch.matcher(command).matches())
            {
                Shop.shop.searchShop(partedCommand[1]);
            }
            else if(patternShopSearchCollection.matcher(command).matches())
            {
                Shop.shop.searchCollection(partedCommand[2]);
            }
            else if(patternShopBuy.matcher(command).matches())
            {
                if(Card.findCard(partedCommand[1]) != null)
                {
                    Shop.shop.buyCard(Card.findCard(partedCommand[1]));
                }
                else if(Item.findItem(partedCommand[1]) != null)
                {
                    Shop.shop.buyItem(Item.findItem(partedCommand[1]));
                }
                else
                {
                    System.out.println("Card or Item does'nt exist in Shop");
                }
            }
            else if(patternShopSell.matcher(command).matches())
            {
                Shop.shop.detectIDToSell(Integer.parseInt(partedCommand[1]));
            }
            else if(command.equals("show"))
            {
                Shop.shop.show();
            }
            else if(command.equals("help"))
            {
                Shop.help();
            }
        }
    }

    public static void collectionCommands()
    {
        String command = myScanner.nextLine();
        while (!command.equals("exit"))
        {
            String[] commandParts = command.split(" ");
            if (command.equals("show"))
            {
                Account.loggedInAccount.getCollection().show();
            }
            else if (patternSearch.matcher(command).matches())
            {
                Account.loggedInAccount.getCollection().searchCollection(commandParts[1]);
            }
            else if (command.equals("save"))
            {
                //todo
            }
            else if (patternCreateDeck.matcher(command).matches())
            {
                Account.loggedInAccount.getCollection().createDeck(commandParts[2]);
            }
            else if (patternDeleteDeck.matcher(command).matches())
            {
                Account.loggedInAccount.getCollection().deleteDeck(commandParts[2]);
            }
            else if (patternAddCardToDeck.matcher(command).matches())
            {
                int ID = Integer.parseInt(commandParts[1]);
                Account.loggedInAccount.getCollection().detectID(ID, commandParts[4], "add");
            }
            else if (patternRemoveCardFromDeck.matcher(command).matches())
            {
                int ID = Integer.parseInt(commandParts[1]);
                Account.loggedInAccount.getCollection().detectID(ID, commandParts[4], "remove");
            }
            else if (patternValidateDeck.matcher(command).matches())
            {
                Deck.checkDeckValidity(commandParts[2]);
            }
            else if (patternSelectMainDeck.matcher(command).matches())
            {
                Deck.setDeckAsMainDeck(commandParts[2]);
            }
            else if (patternShowDeck.matcher(command).matches())
            {
                Deck.showDeck(commandParts[2]);
            }
            else if (command.equals("how all decks"))
            {
                Deck.showAllDecks();
            }
            else if (command.equals("help"))
            {
                Collection.help();
            }
            command = myScanner.nextLine();
        }
    }

    public static void battleCommands()
    {
        String battleMode = myScanner.nextLine();
        //todo
        while (true)
        {
            String line = myScanner.nextLine();

        }
    }

    public static void graveYardCommands(Battle battle)
    {

    }
}


