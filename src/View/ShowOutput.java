package View;

import Controller.*;
import Model.*;

public class ShowOutput {
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

    public static void showAccountsLeaderBoard()
    {
        int counter = 1;
        for (Account account : new AccountManager().getAccounts())
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
        System.out.println(counter + " : Name :" + hero.getCardName() + " - AP : " + hero.getDefaultAP() + " – HP : " + hero.getDefaultHP() + " – Class : " + hero.getImpactType() + " – Special power: " + hero.getSpecialPower().getDescriptionTypeOfSpecialPower() + " - Sell Cost : " + hero.getPrice());
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
        int counter = 0;
        Deck mainDeck = Account.loggedInAccount.getMainDeck();
        if (mainDeck != null)
        {
            System.out.println(counter + " : " + mainDeck.getDeckName() + " : ");
            printDeckStats(mainDeck);
            counter++;
        }
        for (Deck deck : Account.loggedInAccount.getPlayerDecks())
        {
            if (mainDeck != null && deck.getDeckName().equals(mainDeck.getDeckName()))
            {
                continue;
            }
            System.out.println(counter + " : " + deck.getDeckName() + " : ");
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
        //todo
    }

    public static void showMyMinions()
    {
        for (Card card : Battle.getCurrentBattle().getPlayerTurn().getInsertedCards())
        {
            if (card instanceof Minion)
            {
                showMinionInfoInTheBattle((Minion) card);
            }
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
        for (Card card : opponent.getInsertedCards())
        {
            if (card instanceof Minion)
            {
                showMinionInfoInTheBattle((Minion) card);
            }
        }

    }

    private static void showMinionInfoInTheBattle(Minion minion)
    {
        System.out.print(minion.getCardID() + " : " );
        System.out.print(minion.getCardName() +", ");
        System.out.print("health : ");
        System.out.print(minion.getCurrentHP() + ", ");
        System.out.print("location : ");
        System.out.print("("+ minion.getRow() + ", " + minion.getColumn() + "), ");
        System.out.print("power : ");
        System.out.println(minion.getCurrentAP());
    }

    public static void showCardInfo(int cardID)
    {
        Card card = Card.findCard(cardID);
        if (card instanceof Hero)
        {
            Hero hero = (Hero)card;
            System.out.println("Hero");
            System.out.print("Name:");
            System.out.println(hero.getCardName());
            System.out.print("Cost:");
            System.out.println(hero.getPrice());
            System.out.println(/*todo*/);
        }
        if (card instanceof Minion)
        {
            Minion minion=(Minion)card;
            System.out.println("Minion");
            System.out.print("Name:");
            System.out.println(minion.getCardName());
            System.out.print("HP:");
            System.out.print(minion.getCurrentHP());
            System.out.print("AP:");
            System.out.print(minion.getCurrentAP());
            System.out.print("MP:");
            System.out.println(minion.getRequiredMP());
            System.out.print("Range:");
            System.out.println(minion.getImpactType());
            System.out.print("Combo-ability:");
            System.out.println(/*todo*/);
            System.out.print("Cost:");
            System.out.println(minion.getPrice());
            System.out.print("Special Power:");
            System.out.println(minion.getSpecialPower().getDescriptionTypeOfSpecialPower());
        }
        if (card instanceof Spell)
        {
            Spell spell=(Spell)card;
            System.out.println("Spell");
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
        int counter = 1;
        for (Hero hero : Hero.getHeroes())
        {
            ShowOutput.printHeroStats(hero, counter);
            counter ++;
        }
        counter = 1;
        for (Item item : Item.getItems())
        {
            item.printItemStats(counter);
            counter ++;
        }
        counter = 1;
        for (Card card : Card.getCards())
        {
            if (card instanceof Spell)
            {
                Spell spell = (Spell) card;
                spell.printSpellCardStats(counter);
                counter ++;
            } else if (card instanceof Minion)
            {
                Minion minion = (Minion) card;
                minion.printMinionStats(counter);
                counter ++;
            }
        }
    }

    public static void showNextCardInfo(Card card)
    {
        showCardInfo(card.getCardID());
    }

    public static void showNextCard()
    {
        showNextCardInfo(Battle.getCurrentBattle().getPlayerTurn().getHand().getNextCard());
    }

    public static void showHand(Hand hand)
    {
        for (Card card : hand.getCards())
        {
            showCardInfo(card.getCardID());
        }
        showCardInfo(hand.getNextCard().getCardID());
    }

    public static void showBattleModes()
    {
        printOutput("Killing Enemy Hero");
        printOutput("Keep flag for 6 turns");
        printOutput("Gathering Flags");
    }

    public static void showCollectibleItems()
    {
        int counter = 1;
        for (Item item : Battle.getCurrentBattle().getPlayerTurn().getCollectibleItems())
        {
            item.printItemStats(counter);
            counter ++;
        }
    }
}
