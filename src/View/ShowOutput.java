package View;

import Controller.*;
import Model.*;

public class ShowOutput
{
    public static void printOutput(String command)
    {
        System.out.println(command);
    }

    public static void printMainMenuCommands()
    {
        System.out.println("Enter Collection");
        System.out.println("Enter Shop");
        System.out.println("Enter Battle");
        System.out.println("Save");
        System.out.println("Logout");
        System.out.println("Help");
        System.out.println("Exit");
    }

    public static void showAccountsLeaderBoard()
    {
        int counter = 1;
        for (Account account : AccountManager.getAccounts())
        {
            System.out.println(counter + "- UserName : " + account.getAccountName() + "- Wins : " + account.getNumOfWins());
            counter++;
        }
    }

    public static void accountHelp()
    {
        System.out.println("create account [user name]");
        System.out.println("login [user name]");
        System.out.println("show leaderBoard");
        System.out.println("help");
        System.out.println("exit");
    }

    public static void shopHelp()
    {
        System.out.println("show collection");
        System.out.println("search [item name | card name]");
        System.out.println("search collection [item name | card name]");
        System.out.println("buy [card name | item name]");
        System.out.println("sell [card ID | item ID]");
        System.out.println("show");
        System.out.println("help");
        System.out.println("exit");
    }

    public static void collectionHelp()
    {
        System.out.println("search [cardName | itemName]");
        System.out.println("create deck [deckName]");
        System.out.println("delete deck [deckName]");
        System.out.println("add [cardID | heroID | itemID] to deck [deckName]");
        System.out.println("remove [cardID | heroID | itemID] from deck [deckName]");
        System.out.println("validate deck [deckName]");
        System.out.println("select deck [deckName]");
        System.out.println("show all decks");
        System.out.println("show deck [deckName]");
        System.out.println("save");
        System.out.println("show");
        System.out.println("help");
        System.out.println("exit");
    }

    public static void printHeroStats(Hero hero, int counter)
    {
        System.out.println(counter + "- Name : " + hero.getCardName() + " - AP : " + hero.getDefaultAP() + " – HP : " + hero.getDefaultHP() + " – Class : " + hero.getImpactType() + " – Special power : " + hero.getSpecialPower().getDescriptionTypeOfSpecialPower() + " - Sell Cost : " + hero.getPrice());
    }

    public static void showCollectionInfo(Collection collection)
    {
        int counter = 1;
        System.out.println("Heroes :");
        for (Card card : collection.getCards())
        {
            if (card instanceof Hero)
            {
                ShowOutput.printHeroStats((Hero) card, counter);
                counter++;
            }
        }

        counter = 1;
        System.out.println("Items :");
        for (Item item : collection.getItems())
        {
            item.printItemStats(counter);
            counter++;
        }

        counter = 1;
        System.out.println("Cards :");
        for (Card card : collection.getCards())
        {
            if (card instanceof Hero)
            {
                continue;
            }
            card.printCardStats(counter);
            counter++;
        }
    }

    public static void showAllDecksInfo()
    {
        int counter = 1;
        Deck mainDeck = Account.loggedInAccount.getMainDeck();
        if (mainDeck != null)
        {
            System.out.println(counter + "- " + mainDeck.getDeckName() + " : ");
            printDeckStats(mainDeck);
            counter++;
        }
        for (Deck deck : Account.loggedInAccount.getPlayerDecks())
        {
            if (mainDeck != null && deck.getDeckName().equals(mainDeck.getDeckName()))
            {
                continue;
            }
            System.out.println(counter + "- " + deck.getDeckName() + " : ");
            printDeckStats(deck);
            counter++;
        }
    }

    public static void showDeckInfo(String deckName)
    {
        Deck deck = DeckManager.findDeck(deckName);
        if (deck != null)
        {
            printDeckStats(deck);
        }
        else
        {
            ShowOutput.printOutput("There is no deck with this name");
        }
    }

    public static void printDeckStats(Deck deck)
    {
        int counter = 1;
        System.out.println("Heroes :");
        for (Hero hero : deck.getHero())
        {
            ShowOutput.printHeroStats(hero, counter);
            counter++;
        }

        counter = 1;
        System.out.println("Items :");
        for (Item item : deck.getItem())
        {
            item.printItemStats(counter);
            counter++;
        }

        counter = 1;
        System.out.println("Cards :");
        for (Card card : deck.getNonHeroCards())
        {
            card.printCardStats(counter);
            counter++;
        }
    }

    public static void showGameInfo()
    {
        System.out.println("First Player MP : " + Battle.getCurrentBattle().getFirstPlayer().getMP());
        System.out.println("Second Player MP : " + Battle.getCurrentBattle().getSecondPlayer().getMP());
        if (Battle.getCurrentBattle().getBattleMode() == BattleMode.KILLING_ENEMY_HERO)
        {
            System.out.println("First Player Hero HP : " + Battle.getCurrentBattle().getFirstPlayer().getMainDeck().getHero().get(0).getCurrentHP());
            System.out.println("Second Player Hero HP : " + Battle.getCurrentBattle().getSecondPlayer().getMainDeck().getHero().get(0).getCurrentHP());
        }
        else if (Battle.getCurrentBattle().getBattleMode() == BattleMode.KEEP_FLAG_FOR_6_TURNS)
        {

        }
        else if (Battle.getCurrentBattle().getBattleMode() == BattleMode.GATHERING_FLAGS)
        {

        }

    }

    public static void showMyMinions()
    {
        for (NonSpellCards minion : Battle.getCurrentBattle().getPlayerTurn().getInsertedCards())
        {
            showMinionInfoInTheBattle((Minion) minion);
        }
    }

    public static void showOpponentMinions()
    {
        Player opponent;
        if (Battle.getCurrentBattle().getPlayerTurn() == Battle.getCurrentBattle().getFirstPlayer())
        {
            opponent = Battle.getCurrentBattle().getSecondPlayer();
        }
        else
        {
            opponent = Battle.getCurrentBattle().getFirstPlayer();
        }
        for (NonSpellCards minion : opponent.getInsertedCards())
        {
            showMinionInfoInTheBattle((Minion) minion);
        }
    }

    private static void showMinionInfoInTheBattle(Minion minion)
    {
        System.out.print(minion.getCardID() + " : ");
        System.out.print(minion.getCardName() + ", ");
        System.out.print("health : ");
        System.out.print(minion.getCurrentHP() + ", ");
        System.out.print("location : ");
        System.out.print("(" + minion.getRow() + ", " + minion.getColumn() + "), ");
        System.out.print("power : ");
        System.out.println(minion.getCurrentAP());
    }

    public static void showCardInfo(String cardID)
    {
        Card card = Battle.getCurrentBattle().getPlayerTurn().getAccount().getCollection().findCardinCollection(cardID);
        if (card instanceof Hero)
        {
            Hero hero = (Hero) card;
            System.out.println();
            System.out.println("Hero:");
            System.out.print("Name: ");
            System.out.println(hero.getCardName());
            System.out.print("Cost: ");
            System.out.println(hero.getPrice());
            System.out.print("Desc: ");
            if (hero.getSpecialPower() != null)
            {
                System.out.println(hero.getSpecialPower().getDescriptionTypeOfSpecialPower());
            }
            else
            {
                System.out.println("This hero has no special power");
            }

        }
        if (card instanceof Minion)
        {
            Minion minion = (Minion) card;
            System.out.println();
            System.out.println("Minion: ");
            System.out.print("Name: ");
            System.out.println(minion.getCardName());
            System.out.print("HP: ");
            System.out.print(minion.getCurrentHP());
            System.out.print("  AP: ");
            System.out.print(minion.getCurrentAP());
            System.out.print("  MP: ");
            System.out.println(minion.getRequiredMP());
            System.out.print("Range: ");
            System.out.println(minion.getImpactType());
            System.out.print("Combo-ability: ");
            System.out.println(minion.getImpactType());
            System.out.print("Cost: ");
            System.out.println(minion.getPrice());
            System.out.print("Special Power: ");
            if (minion.getSpecialPower() != null)
            {
                System.out.println(minion.getSpecialPower().getDescriptionTypeOfSpecialPower());
            }
            else
            {
                System.out.println("This minion has no special power");
            }
        }
        if (card instanceof Spell)
        {
            Spell spell = (Spell) card;
            System.out.println();
            System.out.println("Spell:");
            System.out.print("Name:");
            System.out.println(spell.getCardName());
            System.out.print("MP:");
            System.out.println(spell.getRequiredMP());
            System.out.print("Cost");
            System.out.println(spell.getPrice());
            System.out.print("Desc:");
            System.out.println(spell.getDescriptionTypeOfSpell());
        }

    }

    public static void showShopInfo()
    {
        printOutput("Heroes :");
        int counter = 1;
        for (Card card : Shop.getInstance().getCards())
        {
            if (card instanceof Hero)
            {
                printHeroStats((Hero) card, counter);
                counter++;
            }

        }
        printOutput("Items :");
        counter = 1;
        for (Item item : Shop.getInstance().getItems())
        {
            item.printItemStats(counter);
            counter++;
        }
        printOutput("Spells :");
        counter = 1;
        for (Card card : Shop.getInstance().getCards())
        {
            if (card instanceof Spell)
            {
                ((Spell) card).printSpellCardStats(counter);
                counter++;
            }
        }
        printOutput("Minions :");
        counter = 1;
        for (Card card : Shop.getInstance().getCards())
        {
            if (card instanceof Minion)
            {
                ((Minion) card).printMinionStats(counter);
                counter++;
            }
        }
    }

    public static void showNextCardInfo()
    {
        Card card = Battle.getCurrentBattle().getPlayerTurn().getHand().getNextCard();
        card.printCardStats();
    }

    public static void showHand(Hand hand)
    {
        for (Card card : hand.getCards())
        {
            showCardInfo(card.getCardID());
        }
        showCardInfo(hand.getNextCard().getCardID());
    }

    public static void showBattleMenuCommands()
    {
        printOutput("Single Player");
        printOutput("Multi Player");
    }

    public static void showBattleModes()
    {
        printOutput("Modes:");
        printOutput("1- Killing Enemy Hero");
        printOutput("2- Keep flag for 6 turns");
        printOutput("3- Gathering Flags");
    }

    public static void showCollectibleItems()
    {
        int counter = 1;
        for (Item item : Battle.getCurrentBattle().getPlayerTurn().getCollectibleItems())
        {
            item.printItemStats(counter);
            counter++;
        }
    }

    public static void showStoryBattleInfo()
    {
        System.out.println("1- Hero : Dave White - Mode : Kill enemy hero");
        System.out.println("2- Hero : Zahak - Mode : Gather and hold flag for 6 turn");
        System.out.println("3- Hero : Arash - Mode : Gather half of the flags");
    }

    public static void showCustomGameInfo()
    {
        showAllDecksInfo();
        showBattleModes();
    }

    public static void showMenuGraveYard()
    {
        System.out.println("Show Cards");
        System.out.println("Show Info [card ID]");
    }

    public static void showMenuBattle()
    {
        System.out.println("Game info");
        System.out.println("Show my minions");
        System.out.println("Show opponent minions");
        System.out.println("Show card info [card id]");
        System.out.println("Select [card id]");
        System.out.println("Show hand");
        System.out.println("Insert [card name] in ( x , y )");
        System.out.println("End turn");
        System.out.println("Show collectibles");
        System.out.println("Select [collectible id]");
        System.out.println("Show Next Card");
        System.out.println("Enter graveyard");
        System.out.println("Help");
        System.out.println("End Game");
        System.out.println("Surrender");
        System.out.println("Show Menu");
        System.out.println("Exit");

    }

    public static void showMenuAfterSelectItem()
    {
        System.out.println("Show Info");
        System.out.println("Use [0-9]+ [0-9]+");
        System.out.println("Show Menu");
        System.out.println("Exit");
    }

    public static void showMenuAfterSelectCard()
    {
        System.out.println("Move To [0-9]+ [0-9]+");
        System.out.println("Attack [cardID]");
        System.out.println("Attack combo [opponentCardID] [myCardID] ...");
        System.out.println("Use special power( [0-9]+ [0-9]+ )");
        System.out.println("Show Menu");
        System.out.println("Exit");
    }

    public static void showMenuMultiPlayerMatchMode()
    {
        System.out.println("Start MultiPlayer Game KillingEnemyHero");
        System.out.println("Start MultiPlayer Game KeepFlagFor6Turns");
        System.out.println("Start MultiPlayer Game GatheringFlags");
        System.out.println("ShowMenu");
        System.out.println("Exit");
    }

    public static void showMenuSelectUserForMultiPlayerMatch()
    {
        System.out.println("Select User [userName]");
        System.out.println("Show Menu");
        System.out.println("Exit");
    }

    public static void showMenuSinglePlayerMatch()
    {
        System.out.println("Story");
        System.out.println("Custom Game");
        System.out.println("Show Menu");
        System.out.println("Exit");
    }
}
