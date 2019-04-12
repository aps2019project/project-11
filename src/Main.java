import java.util.Scanner;
import java.util.regex.Pattern;

public class Main
{

    static Pattern patternCollectionCommands1 = Pattern.compile("search //w+");
    static Pattern patternCollectionCommands2 = Pattern.compile("create deck //w+");
    static Pattern patternCollectionCommands3 = Pattern.compile("delete deck //w+");

    static Scanner myScanner = new Scanner(System.in);

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
        while (true) {
            String line = myScanner.nextLine();
            if (line.matches("exit")) {
                return;
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
            else if ()
            {

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
