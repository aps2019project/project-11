package View;

import Controller.*;
import Model.*;

public class ShowOutput
{
    public void printOutput(String command)
    {
        System.out.println(command);
    }

    public void printMainMenuCommands()
    {
        printOutput("Enter Collection");
        printOutput("Enter Shop");
        printOutput("Enter Battle");
        printOutput("Save");
        printOutput("Logout");
        printOutput("Help");
        printOutput("Exit");
    }

    public void showAccountsLeaderBoard()
    {
        int counter = 1;
        for (Account account : AccountManager.getAccounts())
        {
            printOutput(counter + "- UserName : " + account.getAccountName() + "- Wins : " + account.getNumOfWins());
            counter++;
        }
    }

    public void accountHelp()
    {
        printOutput("create account [user name]");
        printOutput("login [user name]");
        printOutput("show leaderBoard");
        printOutput("help");
        printOutput("exit");
    }

    public void shopHelp()
    {
        printOutput("show collection");
        printOutput("search [item name | card name]");
        printOutput("search collection [item name | card name]");
        printOutput("buy [card name | item name]");
        printOutput("sell [card ID | item ID]");
        printOutput("show");
        printOutput("help");
        printOutput("exit");
    }

    public void collectionHelp()
    {
        printOutput("search [cardName | itemName]");
        printOutput("create deck [deckName]");
        printOutput("delete deck [deckName]");
        printOutput("add [cardID | heroID | itemID] to deck [deckName]");
        printOutput("remove [cardID | heroID | itemID] from deck [deckName]");
        printOutput("validate deck [deckName]");
        printOutput("select deck [deckName]");
        printOutput("show all decks");
        printOutput("show deck [deckName]");
        printOutput("save");
        printOutput("show");
        printOutput("help");
        printOutput("exit");
    }

    public void printHeroStats(Hero hero, int counter)
    {
        printOutput(counter + "- Name : " + hero.getCardName() + " - AP : " + hero.getDefaultAP() + " – HP : " + hero.getDefaultHP() + " – Class : " + hero.getImpactType() + " – Special power : " + hero.getSpecialPower().getDescriptionTypeOfSpecialPower() + " - Sell Cost : " + hero.getPrice());
    }

    public void showCollectionInfo(Collection collection)
    {
        int counter = 1;
        printOutput("Heroes :");
        for (Card card : collection.getCards())
        {
            if (card instanceof Hero)
            {
                printHeroStats((Hero) card, counter);
                counter++;
            }
        }

        counter = 1;
        printOutput("Items :");
        for (Item item : collection.getItems())
        {
            item.printItemStats(counter);
            counter++;
        }

        counter = 1;
        printOutput("Cards :");
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

    public void showAllDecksInfo()
    {
        int counter = 1;
        Deck mainDeck = Account.loggedInAccount.getMainDeck();
        if (mainDeck != null)
        {
            printOutput(counter + "- " + mainDeck.getDeckName() + " : ");
            printDeckStats(mainDeck);
            counter++;
        }
        for (Deck deck : Account.loggedInAccount.getPlayerDecks())
        {
            if (mainDeck != null && deck.getDeckName().equals(mainDeck.getDeckName()))
            {
                continue;
            }
            printOutput(counter + "- " + deck.getDeckName() + " : ");
            printDeckStats(deck);
            counter++;
        }
    }

    public void showDeckInfo(String deckName)
    {
        Deck deck = DeckManager.findDeck(deckName);
        if (deck != null)
        {
            printDeckStats(deck);
        }
        else
        {
            printOutput("There is no deck with this name");
        }
    }

    public void printDeckStats(Deck deck)
    {
        int counter = 1;
        printOutput("Heroes :");
        for (Hero hero : deck.getHero())
        {
            printHeroStats(hero, counter);
            counter++;
        }

        counter = 1;
        printOutput("Items :");
        for (Item item : deck.getItem())
        {
            item.printItemStats(counter);
            counter++;
        }

        counter = 1;
        printOutput("Cards :");
        for (Card card : deck.getNonHeroCards())
        {
            card.printCardStats(counter);
            counter++;
        }
    }

    public void showGameInfo()
    {
        printOutput("First Player : " + Battle.getCurrentBattle().getFirstPlayer().getAccount().getAccountName());
        printOutput("Second Player : " + Battle.getCurrentBattle().getSecondPlayer().getAccount().getAccountName());
        printOutput("First Player MP : " + Battle.getCurrentBattle().getFirstPlayer().getMP());
        printOutput("Second Player MP : " + Battle.getCurrentBattle().getSecondPlayer().getMP());
        if (Battle.getCurrentBattle().getBattleMode() == BattleMode.KILLING_ENEMY_HERO)
        {
            printOutput("First Player Hero HP : " + Battle.getCurrentBattle().getFirstPlayer().getMainDeck().getHero().get(0).getCurrentHP());
            printOutput("Second Player Hero HP : " + Battle.getCurrentBattle().getSecondPlayer().getMainDeck().getHero().get(0).getCurrentHP());
        }
        else if (Battle.getCurrentBattle().getBattleMode() == BattleMode.KEEP_FLAG_FOR_6_TURNS)
        {

        }
        else if (Battle.getCurrentBattle().getBattleMode() == BattleMode.GATHERING_FLAGS)
        {

        }

    }

    public void showMyMinions()
    {
        for (NonSpellCards minion : Battle.getCurrentBattle().getPlayerTurn().getInsertedCards())
        {
            showMinionInfoInTheBattle((Minion) minion);
        }
        showOwnHero(Battle.getCurrentBattle().getPlayerTurn());
    }

    private void showOwnHero(Player player)
    {
        Hero hero = player.getMainDeck().getHero().get(0);
        showInformationOfCards(hero.getCardID(), hero.getCardName(), hero.getCurrentHP(), hero.getRow(), hero.getColumn(), hero.getCurrentAP(), hero);
    }

    private void showInformationOfCards(String cardID, String cardName, int currentHP, int row, int column, int currentAP, Hero hero)
    {
        //todo behine kardan
        printOutput(cardID + " : " + cardName + ", " + "health : " + currentHP + ", " + "location : " + "(" + row + ", " + column + "), " + "power : " + currentAP);
    }

    public void showOpponentMinions()
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
        Hero hero = opponent.getMainDeck().getHero().get(0);
        showInformationOfCards(hero.getCardID(), hero.getCardName(), hero.getCurrentHP(), hero.getRow(), hero.getColumn(), hero.getCurrentAP(), hero);
    }

    private void showMinionInfoInTheBattle(Minion minion)
    {
        showInformationOfCards(minion.getCardID(), minion.getCardName(), minion.getCurrentHP(), minion.getRow(), minion.getColumn(), minion.getCurrentAP(), null);
    }

    public void showCardInfo(String cardID)
    {
        Card card = Battle.getCurrentBattle().getPlayerTurn().getAccount().getCollection().findCardinCollection(cardID);
        if (card instanceof Hero)
        {
            Hero hero = (Hero) card;
            printOutput("Hero:");
            printOutput("Name: " + hero.getCardName());
            printOutput("Cost: " + hero.getPrice());
            if (hero.getSpecialPower() != null)
            {
                printOutput("Desc: " + hero.getSpecialPower().getDescriptionTypeOfSpecialPower());
            }
            else
            {
                printOutput("This hero has no special power");
            }

        }
        if (card instanceof Minion)
        {
            Minion minion = (Minion) card;
            printOutput("Minion: ");
            printOutput("Name: " + minion.getCardName());
            printOutput("HP: " + minion.getCurrentHP());
            printOutput("AP: " + minion.getCurrentAP());
            printOutput("MP: " + minion.getRequiredMP());
            printOutput("Range: " + minion.getImpactType());
            printOutput("Combo Ability: " + minion.getImpactType());
            printOutput("Cost: " + minion.getPrice());
            if (minion.getSpecialPower() != null)
            {
                printOutput("Special Power: " + minion.getSpecialPower().getDescriptionTypeOfSpecialPower());
            }
            else
            {
                printOutput("This minion has no special power");
            }
        }
        if (card instanceof Spell)
        {
            Spell spell = (Spell) card;
            printOutput("Spell: ");
            printOutput("Name: " + spell.getCardName());
            printOutput("MP: " + spell.getRequiredMP());
            printOutput("Cost: " + spell.getPrice());
            printOutput("Desc: " + spell.getDescriptionTypeOfSpell());
        }
    }

    public void showShopInfo()
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

    public void showNextCardInfo()
    {
        Card card = Battle.getCurrentBattle().getPlayerTurn().getHand().getNextCard();
        card.printCardStats();
    }

    public void showHand(Hand hand)
    {
        for (Card card : hand.getCards())
        {
            showCardInfo(card.getCardID());
        }
        showCardInfo(hand.getNextCard().getCardID());
    }

    public void showBattleMenuCommands()
    {
        printOutput("Single Player");
        printOutput("Multi Player");
    }

    public void showBattleModes()
    {
        printOutput("Modes:");
        printOutput("1- Killing Enemy Hero");
        printOutput("2- Keep flag for 6 turns");
        printOutput("3- Gathering Flags");
    }

    public void showCollectibleItems()
    {
        int counter = 1;
        for (Item item : Battle.getCurrentBattle().getPlayerTurn().getCollectibleItems())
        {
            item.printItemStats(counter);
            counter++;
        }
    }

    public void showStoryBattleInfo()
    {
        printOutput("1- Hero : Dave White - Mode : Kill enemy hero");
        printOutput("2- Hero : Zahak - Mode : Gather and hold flag for 6 turn");
        printOutput("3- Hero : Arash - Mode : Gather half of the flags");
    }

    public void showCustomGameInfo()
    {
        showAllDecksInfo();
        showBattleModes();
    }

    public void showMenuGraveYard()
    {
        printOutput("Show Cards");
        printOutput("Show Info [card ID]");
    }

    public void showMenuBattle()
    {
        printOutput("Game info");
        printOutput("Show my minions");
        printOutput("Show opponent minions");
        printOutput("Show card info [card id]");
        printOutput("Select [card id]");
        printOutput("Show hand");
        printOutput("Insert [card name] in ( x , y )");
        printOutput("End turn");
        printOutput("Show collectibles");
        printOutput("Select [collectible id]");
        printOutput("Show Next Card");
        printOutput("Enter graveyard");
        printOutput("Help");
        printOutput("End Game");
        printOutput("Surrender");
        printOutput("Show Menu");
        printOutput("Exit");
    }

    public void showMenuAfterSelectItem()
    {
        printOutput("Show Info");
        printOutput("Use [0-9]+ [0-9]+");
        printOutput("Show Menu");
        printOutput("Exit");
    }

    public void showMenuAfterSelectCard()
    {
        printOutput("Move To [0-9]+ , [0-9]+");
        printOutput("Attack [cardID]");
        printOutput("Attack combo [opponentCardID] [myCardID] ...");
        printOutput("Use special power( [0-9]+ [0-9]+ )");
        printOutput("Show Menu");
        printOutput("Exit");
    }

    public void showMenuMultiPlayerMatchMode()
    {
        printOutput("Start MultiPlayer Game KillingEnemyHero");
        printOutput("Start MultiPlayer Game KeepFlagFor6Turns");
        printOutput("Start MultiPlayer Game GatheringFlags");
        printOutput("ShowMenu");
        printOutput("Exit");
    }

    public void showMenuSelectUserForMultiPlayerMatch()
    {
        printOutput("Select User [userName]");
        printOutput("Show Menu");
        printOutput("Exit");
    }

    public void showMenuSinglePlayerMatch()
    {
        printOutput("Story");
        printOutput("Custom Game");
        printOutput("Show Menu");
        printOutput("Exit");
    }

    public void printMatrixValues(int[][] moveAbleCells, String command)
    {
        printOutput(command);
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                //todo println
                System.out.print(moveAbleCells[i][j] + " ");
            }
            System.out.println();
        }
    }
}
