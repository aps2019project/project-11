package View;

import Model.*;

public class ShowOutput
{
    public static void printOutput(String command)
    {
        System.out.println(command);
    }

    public static void printMainMenuCommands()
    {
        System.out.println("Collection");
        System.out.println("Shop");
        System.out.println("Battle");
        System.out.println("Save");
        System.out.println("Logout");
        System.out.println("Help");
        System.out.println("Exit");
    }

    public static void showLeaderBoard()
    {
        int counter = 1;
        for (Account account : Account.getAccounts())
        {
            System.out.println(counter + "- UserName : " + account.getAccountName() + "- Wins : " + account.getNumOfWins());
            counter ++;
        }
    }

    public static void accountHelp()
    {
        System.out.println("create account [user name]");
        System.out.println("login [user name]");
        System.out.println("show leaderBoard");
        System.out.println("help");
    }

    public static void shopHelp()
    {
        System.out.println("exit");
        System.out.println("show collection");
        System.out.println("search [item name | card name]");
        System.out.println("search collection [item name | card name]");
        System.out.println("buy [card name | item name]");
        System.out.println("sell [card ID | item ID]");
        System.out.println("show");
        System.out.println("help");
    }

    public static void collectionHelp()
    {
        System.out.println("exit");
        System.out.println("show");
        System.out.println("search [cardName | itemName]");
        System.out.println("save");
        System.out.println("create deck [deckName]");
        System.out.println("delete deck [deckName]");
        System.out.println("add [cardID | heroID | itemID] to deck [deckName]");
        System.out.println("remove [cardID | heroID | itemID] from deck [deckName]");
        System.out.println("validate deck [deckName]");
        System.out.println("select deck [deckName]");
        System.out.println("show all decks");
        System.out.println("show deck [deckName]");
        System.out.println("help");
    }

    public static void printHeroStats(Hero hero, int counter)
    {
        System.out.println(counter + " : Name :" + hero.getCardName() + " - AP : " + hero.getDefaultAP() + " – HP : " + hero.getDefaultHP() + " – Class : " + hero.getTypeOfImpact() + " – Special power: " /*todo*/ + " - Sell Cost : " + hero.getPrice());
    }
}