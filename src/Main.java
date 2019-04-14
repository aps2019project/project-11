import java.util.Scanner;
import java.util.regex.Pattern;

public class Main
{

    static Pattern patternCollectionCommands1 = Pattern.compile("search //w+");
    static Pattern patternCollectionCommands2 = Pattern.compile("create deck //w+");
    static Pattern patternCollectionCommands3 = Pattern.compile("delete deck //w+");
    static Pattern patternCollectionCommands4 = Pattern.compile("add //d+ to deck //w+");
    static Pattern patternCollectionCommands5 = Pattern.compile("remove //d+ from deck //w+");
    static Pattern patternCollectionCommands6 = Pattern.compile("validate deck //w+");
    static Pattern patternCollectionCommands7 = Pattern.compile("select deck //w+");
    static Pattern patternCollectionCommands8 = Pattern.compile("show deck //w+");
    static Pattern patternCollectionCommands9 = Pattern.compile("search collection //w+");

    static Scanner myScanner = new Scanner(System.in);

    static Shop shop = new Shop();
    public static void main(String[] args)
    {
        Card.setCards();
        Item.setItems();
        Account.setAIAccounts();
        accountCommands();
        showCommandLine();
    }

    private static void showCommandLine() {
        //ToDo
        while (true)
        {
            String line = myScanner.nextLine();
        }
    }

    public static void accountCommands()
    {
        while (true)
        {

        }
    }

    public static void shopCommands() {
        String command;
        String[] partedCommand;
        while (true) {
            command = myScanner.nextLine();
            partedCommand = command.split("\\s");
            if (command.equals("exit")) {
                return;
            }
            else if(command.equals("show collection")){
                shop.showCollection();
            }
            else if(patternCollectionCommands1.matcher(command).matches()){
                shop.searchShop(partedCommand[1]);
            }
            else if(patternCollectionCommands9.matcher(command).matches()){
                shop.searchCollection(partedCommand[2]);
            }
            else if()

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
            else if (patternCollectionCommands1.matcher(command).matches())
            {
                Account.loggedInAccount.getCollection().searchCollection(commandParts[1]);
            }
            else if (command.equals("save"))
            {
                //todo
            }
            else if (patternCollectionCommands2.matcher(command).matches())
            {
                Account.loggedInAccount.getCollection().createDeck(commandParts[2]);
            }
            else if (patternCollectionCommands3.matcher(command).matches())
            {
                Account.loggedInAccount.getCollection().deleteDeck(commandParts[2]);
            }
            else if (patternCollectionCommands4.matcher(command).matches())
            {
                int ID = Integer.parseInt(commandParts[1]);
                Account.loggedInAccount.getCollection().detectID(ID, commandParts[4], "add");
            }
            else if (patternCollectionCommands5.matcher(command).matches())
            {
                int ID = Integer.parseInt(commandParts[1]);
                Account.loggedInAccount.getCollection().detectID(ID, commandParts[4], "remove");
            }
            else if (patternCollectionCommands6.matcher(command).matches())
            {
                Deck.checkDeckValidity(commandParts[2]);
            }
            else if (patternCollectionCommands7.matcher(command).matches())
            {
                Deck.setDeckAsMainDeck(commandParts[2]);
            }
            else if (patternCollectionCommands8.matcher(command).matches())
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
